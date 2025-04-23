package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Java 8 인터페이스 개선 기능 예제
 * 
 * Java 8에서는 인터페이스에 기본 메서드(default method)와 정적 메서드(static method)를 
 * 정의할 수 있게 되었습니다. 이를 통해 기존 인터페이스를 확장하면서도 이전 코드의 호환성을 유지할 수 있습니다.
 */
public class InterfaceEnhancementsExample {

    public static void main(String[] args) {
        // 예제 1: 기본 메서드(default method)
        defaultMethodExample();
        
        // 예제 2: 정적 메서드(static method)
        staticMethodExample();
        
        // 예제 3: 다중 상속과 기본 메서드 충돌 해결
        multipleInheritanceExample();
        
        // 예제 4: 함수형 인터페이스
        functionalInterfaceExample();
        
        // 예제 5: 실제 사용 사례
        realWorldExample();
    }
    
    /**
     * 예제 1: 기본 메서드(default method)
     */
    private static void defaultMethodExample() {
        System.out.println("\n=== 기본 메서드 예제 ===");
        
        // Vehicle 인터페이스의 기본 구현체
        Vehicle car = new Car("승용차", 5);
        Vehicle bus = new Bus("버스", 30);
        
        // 기본 메서드 호출
        car.start();
        car.honk();
        car.stop();
        
        System.out.println("\n" + bus.getType() + " 정보:");
        bus.displayInfo();
        
        // 오버라이드된 기본 메서드
        bus.start(); // Bus에서 오버라이드됨
        bus.honk(); // Bus에서 오버라이드됨
        bus.stop();
    }
    
    /**
     * 예제 2: 정적 메서드(static method)
     */
    private static void staticMethodExample() {
        System.out.println("\n=== 정적 메서드 예제 ===");
        
        // 인터페이스의 정적 메서드 호출
        Vehicle.printVehicleSound();
        
        // 유틸리티 메서드 호출
        String vehicleInfo = Vehicle.getVehicleDescription("트럭", 2);
        System.out.println(vehicleInfo);
        
        // 타입 체크 유틸리티
        Vehicle car = new Car("SUV", 7);
        
        if (Vehicle.isPassengerVehicle(car)) {
            System.out.println(car.getType() + "는 승객용 차량입니다.");
        }
        
        // Calculator 인터페이스의 정적 메서드 사용
        System.out.println("\n계산 유틸리티:");
        System.out.println("10과 20의 합: " + Calculator.add(10, 20));
        System.out.println("30과 15의 차: " + Calculator.subtract(30, 15));
        System.out.println("7과 8의 곱: " + Calculator.multiply(7, 8));
        System.out.println("100을 5로 나눈 값: " + Calculator.divide(100, 5));
    }
    
    /**
     * 예제 3: 다중 상속과 기본 메서드 충돌 해결
     */
    private static void multipleInheritanceExample() {
        System.out.println("\n=== 다중 상속과 기본 메서드 충돌 해결 예제 ===");
        
        // 둘 이상의 인터페이스를 구현하고 기본 메서드 충돌을 해결하는 클래스
        SmartDevice tablet = new Tablet();
        
        // 각 인터페이스의 메서드 호출
        System.out.println("=== 태블릿 기능 테스트 ===");
        tablet.turnOn();
        tablet.connectToWifi();
        tablet.showScreen();
        tablet.process();
        tablet.turnOff();
        
        // Printable 인터페이스의 기본 메서드
        tablet.print();
        
        // 충돌하는 메서드(Computer/Phone 모두 동일한 기본 메서드를 가짐)
        tablet.charging(); // 어떤 구현을 사용할지 클래스에서 결정해야 함
    }
    
    /**
     * 예제 4: 함수형 인터페이스
     */
    private static void functionalInterfaceExample() {
        System.out.println("\n=== 함수형 인터페이스 예제 ===");
        
        // Math 작업 구현
        System.out.println("=== 수학 연산 ===");
        
        // 익명 클래스로 구현
        MathOperation addition = new MathOperation() {
            @Override
            public int operate(int a, int b) {
                return a + b;
            }
        };
        
        // 람다 표현식으로 구현
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;
        
        // 인터페이스를 사용한 연산
        int result1 = calculate(10, 5, addition);
        int result2 = calculate(10, 5, subtraction);
        int result3 = calculate(10, 5, multiplication);
        int result4 = calculate(10, 5, division);
        
        System.out.println("10 + 5 = " + result1);
        System.out.println("10 - 5 = " + result2);
        System.out.println("10 * 5 = " + result3);
        System.out.println("10 / 5 = " + result4);
        
        // StringProcessor 구현
        System.out.println("\n=== 문자열 처리 ===");
        
        StringProcessor toUpperCase = s -> s.toUpperCase();
        StringProcessor toLowerCase = s -> s.toLowerCase();
        StringProcessor addExclamation = s -> s + "!";
        
        // 함수형 인터페이스의 기본 메서드와 조합
        StringProcessor combined = toUpperCase
                .andThen(addExclamation)
                .andThen(s -> "결과: " + s);
        
        String text = "Java 8 Interface";
        System.out.println("원본: " + text);
        System.out.println("대문자: " + processString(text, toUpperCase));
        System.out.println("소문자: " + processString(text, toLowerCase));
        System.out.println("조합 처리: " + processString(text, combined));
    }
    
    /**
     * 예제 5: 실제 사용 사례
     */
    private static void realWorldExample() {
        System.out.println("\n=== 실제 사용 사례 예제 ===");
        
        // 사용자 목록
        List<User> users = Arrays.asList(
                new User("홍길동", "admin", 35),
                new User("김영희", "user", 28),
                new User("이철수", "user", 42),
                new User("박지영", "manager", 31),
                new User("최민수", "user", 25)
        );
        
        // 사용자 필터링을 위한 UserFilter 생성
        UserFilter adminFilter = UserFilter.getAdminFilter();
        UserFilter ageFilter = UserFilter.getAgeFilter(30);
        
        // 기본 필터 메서드와 조합(체이닝)
        UserFilter combinedFilter = adminFilter.or(ageFilter);
        
        System.out.println("관리자 또는 30세 이상인 사용자:");
        filterAndPrintUsers(users, combinedFilter);
        
        // UserService 활용
        UserService userService = new UserServiceImpl(users);
        
        System.out.println("\n모든 사용자:");
        userService.getAllUsers().forEach(System.out::println);
        
        System.out.println("\n역할별 사용자 수:");
        System.out.println(userService.countUsersByRole());
        
        System.out.println("\n수정 전 첫 번째 사용자: " + users.get(0));
        userService.updateUsername(users.get(0), "홍길동2");
        System.out.println("수정 후 첫 번째 사용자: " + users.get(0));
    }
    
    // 연산 실행 헬퍼 메서드
    private static int calculate(int a, int b, MathOperation operation) {
        return operation.operate(a, b);
    }
    
    // 문자열 처리 헬퍼 메서드
    private static String processString(String input, StringProcessor processor) {
        return processor.process(input);
    }
    
    // 사용자 필터링 헬퍼 메서드
    private static void filterAndPrintUsers(List<User> users, UserFilter filter) {
        users.stream()
                .filter(filter::filter)
                .forEach(System.out::println);
    }
    
    // === 인터페이스 선언 ===
    
    /**
     * 예제 1: 기본 메서드를 가진 인터페이스
     */
    interface Vehicle {
        // 추상 메서드 (구현 필수)
        String getType();
        int getPassengerCount();
        
        // 기본 메서드 (구현 선택적)
        default void start() {
            System.out.println(getType() + "가 출발합니다.");
        }
        
        default void stop() {
            System.out.println(getType() + "가 정지합니다.");
        }
        
        default void honk() {
            System.out.println("경적: 빵빵!");
        }
        
        default void displayInfo() {
            System.out.println("차량 유형: " + getType());
            System.out.println("승객 수: " + getPassengerCount());
        }
        
        // 정적 메서드
        static void printVehicleSound() {
            System.out.println("차량은 다양한 소리를 냅니다.");
        }
        
        static String getVehicleDescription(String type, int passengerCount) {
            return type + " (승객 수용: " + passengerCount + "명)";
        }
        
        static boolean isPassengerVehicle(Vehicle vehicle) {
            return vehicle.getPassengerCount() > 0;
        }
    }
    
    /**
     * 예제 2: 정적 메서드를 가진 인터페이스
     */
    interface Calculator {
        // 정적 메서드
        static int add(int a, int b) {
            return a + b;
        }
        
        static int subtract(int a, int b) {
            return a - b;
        }
        
        static int multiply(int a, int b) {
            return a * b;
        }
        
        static int divide(int a, int b) {
            if (b == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            return a / b;
        }
    }
    
    /**
     * 예제 3: 다중 상속에서의 인터페이스
     */
    interface Computer {
        void process();
        void showScreen();
        
        default void turnOn() {
            System.out.println("컴퓨터 전원을 켭니다.");
        }
        
        default void turnOff() {
            System.out.println("컴퓨터 전원을 끕니다.");
        }
        
        default void charging() {
            System.out.println("컴퓨터를 충전합니다.");
        }
    }
    
    interface Phone {
        void call();
        void text();
        
        default void turnOn() {
            System.out.println("폰 전원을 켭니다.");
        }
        
        default void turnOff() {
            System.out.println("폰 전원을 끕니다.");
        }
        
        default void charging() {
            System.out.println("폰을 충전합니다.");
        }
    }
    
    interface Printable {
        default void print() {
            System.out.println("인쇄를 시작합니다.");
        }
    }
    
    interface NetworkDevice {
        default void connectToWifi() {
            System.out.println("Wi-Fi에 연결합니다.");
        }
    }
    
    interface SmartDevice extends Computer, Phone, Printable, NetworkDevice {
        // 모든 인터페이스로부터 상속받은 기본 메서드의 충돌을 해결해야 함
        @Override
        default void turnOn() {
            System.out.println("스마트 기기 전원을 켭니다.");
            // Computer.super.turnOn(); // 부모 인터페이스의 기본 메서드를 호출할 수도 있음
        }
        
        @Override
        default void turnOff() {
            System.out.println("스마트 기기 전원을 끕니다.");
        }
        
        // charging()은 여전히 충돌상태 - 구현 클래스에서 해결해야 함
        @Override
        default void charging() {
            System.out.println("스마트 기기를 충전합니다.");
        }
    }
    
    /**
     * 예제 4: 함수형 인터페이스
     */
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }
    
    @FunctionalInterface
    interface StringProcessor {
        String process(String input);
        
        // 기본 메서드로 함수형 인터페이스 조합을 지원
        default StringProcessor andThen(StringProcessor after) {
            return input -> after.process(this.process(input));
        }
    }
    
    /**
     * 예제 5: 실제 사용 사례를 위한 인터페이스
     */
    @FunctionalInterface
    interface UserFilter {
        boolean filter(User user);
        
        // 기본 메서드를 통한 인터페이스 확장
        default UserFilter and(UserFilter other) {
            return user -> this.filter(user) && other.filter(user);
        }
        
        default UserFilter or(UserFilter other) {
            return user -> this.filter(user) || other.filter(user);
        }
        
        default UserFilter negate() {
            return user -> !this.filter(user);
        }
        
        // 정적 팩토리 메서드
        static UserFilter getAdminFilter() {
            return user -> "admin".equals(user.getRole());
        }
        
        static UserFilter getManagerFilter() {
            return user -> "manager".equals(user.getRole());
        }
        
        static UserFilter getAgeFilter(int minAge) {
            return user -> user.getAge() >= minAge;
        }
    }
    
    interface UserService {
        List<User> getAllUsers();
        List<User> filterUsers(UserFilter filter);
        void updateUsername(User user, String newName);
        
        // 기본 메서드를 통한 추가 기능
        default Map<String, Long> countUsersByRole() {
            return getAllUsers().stream()
                    .collect(Collectors.groupingBy(
                            User::getRole,
                            Collectors.counting()
                    ));
        }
        
        default double getAverageAge() {
            return getAllUsers().stream()
                    .mapToInt(User::getAge)
                    .average()
                    .orElse(0);
        }
    }
    
    // === 클래스 구현 ===
    
    static class Car implements Vehicle {
        private String type;
        private int passengerCount;
        
        public Car(String type, int passengerCount) {
            this.type = type;
            this.passengerCount = passengerCount;
        }
        
        @Override
        public String getType() {
            return type;
        }
        
        @Override
        public int getPassengerCount() {
            return passengerCount;
        }
    }
    
    static class Bus implements Vehicle {
        private String type;
        private int passengerCount;
        
        public Bus(String type, int passengerCount) {
            this.type = type;
            this.passengerCount = passengerCount;
        }
        
        @Override
        public String getType() {
            return type;
        }
        
        @Override
        public int getPassengerCount() {
            return passengerCount;
        }
        
        // 기본 메서드 오버라이드
        @Override
        public void start() {
            System.out.println("버스 출발 전 승객을 확인합니다.");
            System.out.println(getType() + "가 출발합니다.");
        }
        
        @Override
        public void honk() {
            System.out.println("버스 경적: 빵빵빵!");
        }
    }
    
    static class Tablet implements SmartDevice {
        @Override
        public void process() {
            System.out.println("태블릿이 작업을 처리합니다.");
        }
        
        @Override
        public void showScreen() {
            System.out.println("태블릿 화면을 표시합니다.");
        }
        
        @Override
        public void call() {
            System.out.println("태블릿으로 전화를 겁니다.");
        }
        
        @Override
        public void text() {
            System.out.println("태블릿으로 문자를 보냅니다.");
        }
        
        // 충돌하는 메서드는 직접 구현해야 함
        @Override
        public void charging() {
            System.out.println("태블릿을 USB-C로 충전합니다.");
            // 또는 부모 인터페이스 중 하나를 선택할 수도 있음
            // Computer.super.charging();
        }
    }
    
    static class User {
        private String name;
        private String role;
        private int age;
        
        public User(String name, String role, int age) {
            this.name = name;
            this.role = role;
            this.age = age;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
        public String getRole() {
            return role;
        }
        
        public int getAge() {
            return age;
        }
        
        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", role='" + role + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
    
    static class UserServiceImpl implements UserService {
        private List<User> users;
        
        public UserServiceImpl(List<User> users) {
            this.users = users;
        }
        
        @Override
        public List<User> getAllUsers() {
            return users;
        }
        
        @Override
        public List<User> filterUsers(UserFilter filter) {
            return users.stream()
                    .filter(filter::filter)
                    .collect(Collectors.toList());
        }
        
        @Override
        public void updateUsername(User user, String newName) {
            user.setName(newName);
        }
    }
}
