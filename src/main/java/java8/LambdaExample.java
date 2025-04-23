package java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java 8 람다 표현식(Lambda Expressions) 및 함수형 인터페이스 예제
 * 
 * 람다 표현식은 Java 8에서 도입된 주요 기능으로, 함수형 프로그래밍을 지원합니다.
 * 간결한 코드 작성이 가능하며, 익명 함수를 표현하는 방법입니다.
 */
public class LambdaExample {

    public static void main(String[] args) {
        // 예제 1: 기본 람다 표현식
        basicLambdaExample();
        
        // 예제 2: 함수형 인터페이스
        functionalInterfaceExample();
        
        // 예제 3: 메서드 레퍼런스
        methodReferenceExample();
        
        // 예제 4: 실전 람다 사용 예제
        practicalExample();
        
        // 예제 5: Optional 클래스 활용
        optionalExample();
    }
    
    /**
     * 예제 1: 기본 람다 표현식 사용법
     */
    private static void basicLambdaExample() {
        System.out.println("\n=== 기본 람다 표현식 ===");
        
        // 예제 1.1: 파라미터가 없는 람다 표현식
        Runnable runnable = () -> System.out.println("Hello Lambda!");
        runnable.run();
        
        // 예제 1.2: 한 개의 파라미터를 가진 람다 표현식 (괄호 생략 가능)
        List<String> names = Arrays.asList("김철수", "이영희", "박민수");
        
        System.out.println("\n람다로 리스트 순회하기:");
        names.forEach(name -> System.out.println(name));
        
        // 예제 1.3: 여러 개의 파라미터를 가진 람다 표현식
        Calculator add = (a, b) -> a + b;
        Calculator subtract = (a, b) -> a - b;
        
        System.out.println("\n람다로 계산하기:");
        System.out.println("10 + 5 = " + add.calculate(10, 5));
        System.out.println("10 - 5 = " + subtract.calculate(10, 5));
        
        // 예제 1.4: 여러 문장이 있는 람다 표현식
        Calculator multiply = (a, b) -> {
            System.out.println("곱하기 연산 수행 중...");
            return a * b;
        };
        
        System.out.println("\n10 * 5 = " + multiply.calculate(10, 5));
        
        // 예제 1.5: 람다 표현식에서 변수 캡처
        int factor = 2;
        Calculator scale = (a, b) -> (a + b) * factor;
        
        System.out.println("\n(10 + 5) * 2 = " + scale.calculate(10, 5));
    }
    
    /**
     * 예제 2: 함수형 인터페이스와 Java의 내장 함수형 인터페이스 사용하기
     */
    private static void functionalInterfaceExample() {
        System.out.println("\n=== 함수형 인터페이스 ===");
        
        // Predicate<T>: T -> boolean
        System.out.println("\nPredicate 예제:");
        Predicate<String> isLongString = s -> s.length() > 3;
        
        System.out.println("'자바'는 길이가 3보다 긴가? " + isLongString.test("자바"));
        System.out.println("'자바8'은 길이가 3보다 긴가? " + isLongString.test("자바8"));
        
        // Consumer<T>: T -> void
        System.out.println("\nConsumer 예제:");
        Consumer<String> printUpperCase = s -> System.out.println(s.toUpperCase());
        
        printUpperCase.accept("hello lambda");
        
        // Consumer 체이닝
        Consumer<String> printFirst = s -> System.out.print("첫 번째 출력: " + s);
        Consumer<String> printSecond = s -> System.out.println(", 두 번째 출력: " + s + "!");
        
        printFirst.andThen(printSecond).accept("안녕하세요");
        
        // Function<T, R>: T -> R
        System.out.println("\nFunction 예제:");
        Function<String, Integer> stringToLength = s -> s.length();
        
        System.out.println("'함수형 프로그래밍'의 길이: " + stringToLength.apply("함수형 프로그래밍"));
        
        // Function 합성
        Function<Integer, Integer> multiply2 = i -> i * 2;
        Function<Integer, Integer> add1 = i -> i + 1;
        
        // compose: multiply2(add1(x))
        Function<Integer, Integer> add1ThenMultiply2 = multiply2.compose(add1);
        // andThen: add1(multiply2(x))
        Function<Integer, Integer> multiply2ThenAdd1 = multiply2.andThen(add1);
        
        System.out.println("(5 + 1) * 2 = " + add1ThenMultiply2.apply(5));
        System.out.println("(5 * 2) + 1 = " + multiply2ThenAdd1.apply(5));
        
        // Supplier<T>: () -> T
        System.out.println("\nSupplier 예제:");
        Supplier<Double> randomSupplier = () -> Math.random();
        
        System.out.println("난수 생성: " + randomSupplier.get());
        System.out.println("난수 생성: " + randomSupplier.get());
    }
    
    /**
     * 예제 3: 메서드 레퍼런스
     */
    private static void methodReferenceExample() {
        System.out.println("\n=== 메서드 레퍼런스 ===");
        
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "정지영");
        
        // 정적 메서드 참조
        System.out.println("\n정적 메서드 참조:");
        
        // 람다 표현식
        names.forEach(name -> StringUtils.printWithPrefix(name));
        
        // 메서드 레퍼런스로 변환
        names.forEach(StringUtils::printWithPrefix);
        
        // 인스턴스 메서드 참조
        System.out.println("\n인스턴스 메서드 참조:");
        StringUtils utils = new StringUtils("회원");
        
        // 람다 표현식
        names.forEach(name -> utils.printWithCustomPrefix(name));
        
        // 메서드 레퍼런스로 변환
        names.forEach(utils::printWithCustomPrefix);
        
        // 생성자 레퍼런스
        System.out.println("\n생성자 레퍼런스:");
        
        // 람다 표현식
        Function<String, Person> personCreator1 = name -> new Person(name);
        Person person1 = personCreator1.apply("홍길동");
        System.out.println("람다로 생성: " + person1.getName());
        
        // 생성자 레퍼런스로 변환
        Function<String, Person> personCreator2 = Person::new;
        Person person2 = personCreator2.apply("김영희");
        System.out.println("생성자 레퍼런스로 생성: " + person2.getName());
        
        // 특정 객체의 인스턴스 메서드 참조
        System.out.println("\n특정 타입의 인스턴스 메서드 참조:");
        
        List<String> words = Arrays.asList("사과", "바나나", "오렌지", "포도");
        
        // 람다 표현식
        words.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("람다로 정렬: " + words);
        
        // 메서드 레퍼런스로 변환
        words = Arrays.asList("사과", "바나나", "오렌지", "포도");
        words.sort(String::compareTo);
        System.out.println("메서드 레퍼런스로 정렬: " + words);
    }
    
    /**
     * 예제 4: 실전 람다 사용 예제
     */
    private static void practicalExample() {
        System.out.println("\n=== 실전 람다 사용 예제 ===");
        
        List<Employee> employees = Arrays.asList(
                new Employee("김철수", "개발", 5000000),
                new Employee("이영희", "마케팅", 4500000),
                new Employee("박민수", "개발", 6000000),
                new Employee("정지영", "인사", 4000000),
                new Employee("최동현", "개발", 5500000)
        );
        
        // 예제 4.1: 부서별 직원 필터링
        System.out.println("\n개발 부서 직원:");
        filterEmployees(employees, e -> "개발".equals(e.getDepartment()));
        
        System.out.println("\n급여가 5백만원 이상인 직원:");
        filterEmployees(employees, e -> e.getSalary() >= 5000000);
        
        // 예제 4.2: 급여 인상 적용
        System.out.println("\n급여 인상 후:");
        List<Employee> updatedEmployees = updateSalaries(employees, e -> {
            if ("개발".equals(e.getDepartment())) {
                return e.getSalary() * 1.1; // 개발 부서는 10% 인상
            } else {
                return e.getSalary() * 1.05; // 다른 부서는 5% 인상
            }
        });
        
        for (Employee e : updatedEmployees) {
            System.out.println(e.getName() + " (" + e.getDepartment() + "): " + e.getSalary() + "원");
        }
        
        // 예제 4.3: 직원 정보를 다양한 형식으로 출력
        System.out.println("\n다양한 형식으로 직원 정보 출력:");
        
        // 이름과 부서만 출력
        System.out.println("이름과 부서:");
        processEmployees(employees, e -> System.out.println(e.getName() + " - " + e.getDepartment()));
        
        // 모든 정보 출력
        System.out.println("\n모든 정보:");
        processEmployees(employees, e -> {
            System.out.println("이름: " + e.getName());
            System.out.println("부서: " + e.getDepartment());
            System.out.println("급여: " + e.getSalary() + "원");
            System.out.println("-----------------");
        });
    }
    
    /**
     * 예제 5: Optional 클래스 활용
     * 
     * Java 8에서 도입된 Optional 클래스는 null을 보다 안전하게 처리하기 위한 컨테이너 클래스입니다.
     */
    private static void optionalExample() {
        System.out.println("\n=== Optional 클래스 활용 ===");
        
        // Optional 생성
        Optional<String> emptyOpt = Optional.empty();
        Optional<String> nameOpt = Optional.of("홍길동");
        Optional<String> nullableOpt = Optional.ofNullable(null); // null일 수 있는 값
        
        // isPresent()를 사용한 값 존재 확인
        System.out.println("emptyOpt에 값이 있는가? " + emptyOpt.isPresent());
        System.out.println("nameOpt에 값이 있는가? " + nameOpt.isPresent());
        
        // ifPresent()를 사용한 조건부 실행
        System.out.println("\nifPresent() 예제:");
        emptyOpt.ifPresent(name -> System.out.println("이름: " + name)); // 실행 안 됨
        nameOpt.ifPresent(name -> System.out.println("이름: " + name)); // "이름: 홍길동" 출력
        
        // orElse(), orElseGet(), orElseThrow() 사용
        System.out.println("\n기본값 설정 예제:");
        String name1 = emptyOpt.orElse("이름 없음");
        System.out.println("orElse 결과: " + name1);
        
        String name2 = emptyOpt.orElseGet(() -> "동적으로 생성된 이름");
        System.out.println("orElseGet 결과: " + name2);
        
        try {
            String name3 = emptyOpt.orElseThrow(() -> new NoSuchElementException("이름이 없습니다."));
        } catch (NoSuchElementException e) {
            System.out.println("orElseThrow 결과: " + e.getMessage());
        }
        
        // map(), filter()를 사용한 변환 및 필터링
        System.out.println("\n변환 및 필터링 예제:");
        
        Optional<String> upperNameOpt = nameOpt.map(String::toUpperCase);
        System.out.println("대문자로 변환: " + upperNameOpt.orElse("없음"));
        
        Optional<String> filteredNameOpt = nameOpt.filter(name -> name.length() > 4);
        System.out.println("4글자 초과 이름? " + filteredNameOpt.isPresent());
        
        // 실용적인 예제: 사용자 검색
        System.out.println("\n사용자 검색 예제:");
        
        User user1 = findUserByUsername("admin").orElse(new User("guest", "Guest User"));
        System.out.println("검색된 사용자: " + user1.getName());
        
        Optional<User> userOpt = findUserByUsername("john");
        
        // 메서드 체이닝
        String displayName = userOpt
                .map(User::getName)
                .map(name -> name.toUpperCase())
                .orElse("사용자를 찾을 수 없음");
                
        System.out.println("표시 이름: " + displayName);
    }
    
    // 직원 필터링 헬퍼 메서드
    private static void filterEmployees(List<Employee> employees, Predicate<Employee> predicate) {
        for (Employee employee : employees) {
            if (predicate.test(employee)) {
                System.out.println(employee.getName() + " (" + employee.getDepartment() + "): " + employee.getSalary() + "원");
            }
        }
    }
    
    // 급여 업데이트 헬퍼 메서드
    private static List<Employee> updateSalaries(List<Employee> employees, Function<Employee, Double> updateFunction) {
        List<Employee> result = new ArrayList<>();
        
        for (Employee employee : employees) {
            double newSalary = updateFunction.apply(employee);
            Employee updatedEmployee = new Employee(employee.getName(), employee.getDepartment(), newSalary);
            result.add(updatedEmployee);
        }
        
        return result;
    }
    
    // 직원 처리 헬퍼 메서드
    private static void processEmployees(List<Employee> employees, Consumer<Employee> consumer) {
        for (Employee employee : employees) {
            consumer.accept(employee);
        }
    }
    
    // Optional 예제에 사용할 사용자 검색 메서드
    private static Optional<User> findUserByUsername(String username) {
        if ("admin".equals(username)) {
            return Optional.of(new User("admin", "관리자"));
        }
        return Optional.empty();
    }
    
    // 함수형 인터페이스 (계산기)
    @FunctionalInterface
    interface Calculator {
        int calculate(int a, int b);
    }
    
    // 메서드 레퍼런스 예제용 유틸리티 클래스
    static class StringUtils {
        private String prefix;
        
        public StringUtils(String prefix) {
            this.prefix = prefix;
        }
        
        // 정적 메서드
        public static void printWithPrefix(String text) {
            System.out.println("사용자: " + text);
        }
        
        // 인스턴스 메서드
        public void printWithCustomPrefix(String text) {
            System.out.println(prefix + ": " + text);
        }
    }
    
    // 예제용 클래스들
    static class Person {
        private String name;
        
        public Person(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
    }
    
    static class Employee {
        private String name;
        private String department;
        private double salary;
        
        public Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        
        public String getName() { return name; }
        public String getDepartment() { return department; }
        public double getSalary() { return salary; }
    }
    
    static class User {
        private String username;
        private String name;
        
        public User(String username, String name) {
            this.username = username;
            this.name = name;
        }
        
        public String getUsername() { return username; }
        public String getName() { return name; }
    }
}
