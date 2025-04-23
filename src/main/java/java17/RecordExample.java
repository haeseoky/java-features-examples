package java17;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Java 16에서 정식 도입된 레코드(Records) 예제
 * (Java 17 LTS에서 완전히 지원됨)
 * 
 * 레코드는 데이터 클래스를 간결하게 정의할 수 있는 방법을 제공합니다.
 * 불변(immutable) 데이터 클래스를 쉽게 생성하여 보일러플레이트 코드를 줄여줍니다.
 */
public class RecordExample {

    // 예제 1: 기본 레코드 정의
    public record Person(String name, int age) {
        // 컴팩트 생성자(Compact Constructor)
        public Person {
            // 유효성 검사
            if (age < 0) {
                throw new IllegalArgumentException("나이는 음수가 될 수 없습니다.");
            }
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("이름은 비어 있을 수 없습니다.");
            }
        }
    }
    
    // 예제 2: 메서드가 있는 레코드
    public record Rectangle(double width, double height) {
        // 기본 메서드 추가
        public double area() {
            return width * height;
        }
        
        public double perimeter() {
            return 2 * (width + height);
        }
        
        // 정적 메서드 추가
        public static Rectangle square(double side) {
            return new Rectangle(side, side);
        }
    }
    
    // 예제 3: 중첩 레코드와 제네릭
    public record Pair<T, U>(T first, U second) {
        // 타입 변환 유틸리티 메서드
        public <V> Pair<V, U> mapFirst(java.util.function.Function<T, V> mapper) {
            return new Pair<>(mapper.apply(first), second);
        }
        
        public <V> Pair<T, V> mapSecond(java.util.function.Function<U, V> mapper) {
            return new Pair<>(first, mapper.apply(second));
        }
    }
    
    // 예제 4: 인터페이스 구현
    public interface Printable {
        String getPrintableForm();
    }
    
    public record PrintablePerson(String name, int age) implements Printable {
        @Override
        public String getPrintableForm() {
            return String.format("%s (%d세)", name, age);
        }
    }
    
    // 예제 5: 중첩 레코드 사용
    public record Address(String street, String city, String zipCode) {}
    
    public record Employee(String name, int id, Address address) {
        // 접근자 메서드 오버라이딩
        @Override
        public Address address() {
            // 반환값을 수정하거나 로깅할 수 있음
            System.out.println("주소 접근: " + address);
            return address;
        }
    }
    
    // 예제 6: 레코드 상속 구조(레코드는 다른 클래스를 확장할 수 없지만 인터페이스는 구현 가능)
    public interface Vehicle {
        String getRegistrationNumber();
        default void printDetails() {
            System.out.println("차량 등록 번호: " + getRegistrationNumber());
        }
    }
    
    public record Car(String registrationNumber, String model, int year) implements Vehicle {
        @Override
        public String getRegistrationNumber() {
            return registrationNumber;
        }
        
        // 기본 메서드 오버라이딩
        @Override
        public void printDetails() {
            Vehicle.super.printDetails();
            System.out.println("모델: " + model);
            System.out.println("연식: " + year);
        }
    }
    
    // 예제 7: 기존 클래스와 레코드 비교
    public static class TraditionalPerson {
        private final String name;
        private final int age;
        
        public TraditionalPerson(String name, int age) {
            this.name = name;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TraditionalPerson that = (TraditionalPerson) o;
            return age == that.age && Objects.equals(name, that.name);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }
        
        @Override
        public String toString() {
            return "TraditionalPerson{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    
    public static void main(String[] args) {
        // 예제 1: 기본 레코드 사용
        Person person = new Person("홍길동", 30);
        System.out.println("이름: " + person.name());
        System.out.println("나이: " + person.age());
        System.out.println("toString(): " + person);
        
        // equals(), hashCode() 자동 생성
        Person anotherPerson = new Person("홍길동", 30);
        System.out.println("동등성 검사: " + person.equals(anotherPerson));
        
        try {
            // 유효성 검사 테스트
            Person invalidPerson = new Person("", -5);
        } catch (IllegalArgumentException e) {
            System.out.println("예상된 예외: " + e.getMessage());
        }
        
        // 예제 2: 메서드가 있는 레코드 사용
        Rectangle rectangle = new Rectangle(5.0, 3.0);
        System.out.println("사각형 면적: " + rectangle.area());
        System.out.println("사각형 둘레: " + rectangle.perimeter());
        
        // 정적 팩토리 메서드
        Rectangle square = Rectangle.square(4.0);
        System.out.println("정사각형: " + square);
        System.out.println("정사각형 면적: " + square.area());
        
        // 예제 3: 제네릭 레코드
        Pair<String, Integer> nameAge = new Pair<>("김철수", 25);
        Pair<Integer, String> ageFirstPair = nameAge.mapFirst(name -> name.length())
                                                   .mapSecond(age -> "나이: " + age);
        System.out.println("변환된 쌍: " + ageFirstPair);
        
        // 예제 4: 인터페이스 구현
        PrintablePerson printablePerson = new PrintablePerson("이영희", 28);
        System.out.println("출력 형식: " + printablePerson.getPrintableForm());
        
        // 예제 5: 중첩 레코드
        Address address = new Address("가로수길 123", "서울", "06000");
        Employee employee = new Employee("박지민", 1001, address);
        
        // 접근자 메서드 오버라이딩 테스트
        Address employeeAddress = employee.address();
        
        // 예제 6: 인터페이스 구현
        Car car = new Car("12가3456", "소나타", 2022);
        car.printDetails();
        
        // 예제 7: 기존 클래스와 비교
        TraditionalPerson traditionalPerson = new TraditionalPerson("김민준", 35);
        System.out.println("\n기존 클래스: " + traditionalPerson);
        System.out.println("레코드: " + person);
        
        System.out.println("\n--- 레코드의 장점 ---");
        System.out.println("1. 간결한 코드 (equals, hashCode, toString 자동 생성)");
        System.out.println("2. 명확한 의도 (데이터 전달 목적임을 명시)");
        System.out.println("3. 불변성 보장 (모든 필드는 final)");
        System.out.println("4. 구조적 패턴 매칭과의 시너지");
        
        // 예제 8: 레코드 컬렉션 처리
        List<Person> people = new ArrayList<>();
        people.add(new Person("김영수", 20));
        people.add(new Person("이지원", 30));
        people.add(new Person("박철수", 25));
        
        // 스트림 API와 함께 사용
        double averageAge = people.stream()
                .mapToInt(Person::age)
                .average()
                .orElse(0);
                
        System.out.println("\n평균 나이: " + averageAge);
        
        // 이름이 가장 긴 사람 찾기
        Person personWithLongestName = people.stream()
                .max((p1, p2) -> Integer.compare(p1.name().length(), p2.name().length()))
                .orElse(null);
                
        System.out.println("가장 긴 이름을 가진 사람: " + personWithLongestName);
    }
}
