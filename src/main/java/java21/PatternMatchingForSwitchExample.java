package java21;

/**
 * Java 21 switch 문에서의 패턴 매칭(Pattern Matching for switch) 예제
 * 
 * switch 문에서 타입 패턴, 가드 표현식(when 절), 그리고 레코드 패턴을 활용할 수 있습니다.
 * 이를 통해 switch 문이 더 강력하고 유연해집니다.
 */
public class PatternMatchingForSwitchExample {

    // 도형 계층 구조 정의
    sealed interface Shape permits Circle, Rectangle, Triangle {}
    record Circle(double radius) implements Shape {}
    record Rectangle(double width, double height) implements Shape {}
    record Triangle(double base, double height) implements Shape {}
    
    // 다양한 타입의 객체
    record Person(String name, int age) {}
    record Employee(String name, int id, String department) {}

    public static void main(String[] args) {
        // 예제 1: 기본 타입 패턴 매칭
        Object obj = "Hello, World!";
        String result = switch (obj) {
            case String s -> "문자열: " + s;
            case Integer i -> "정수: " + i;
            case Long l -> "롱: " + l;
            case Double d -> "더블: " + d;
            case Boolean b -> "불리언: " + b;
            case null -> "null 값";
            default -> "기타 객체: " + obj.getClass().getSimpleName();
        };
        System.out.println(result);
        
        // 예제 2: when 가드 조건 사용
        Object value = 42;
        String category = switch (value) {
            case Integer i when i < 0 -> "음수";
            case Integer i when i == 0 -> "영";
            case Integer i when i > 0 && i <= 10 -> "작은 양수";
            case Integer i when i > 10 && i <= 100 -> "중간 양수";
            case Integer i -> "큰 양수";
            case String s when s.length() == 0 -> "빈 문자열";
            case String s -> s.length() + "글자 문자열";
            default -> "다른 타입";
        };
        System.out.println("카테고리: " + category);
        
        // 예제 3: 도형 계층 구조와 switch
        Shape shape = new Rectangle(5.0, 3.0);
        double area = calculateArea(shape);
        System.out.println("도형 면적: " + area);
        
        // 예제 4: null 처리와 패턴 매칭 결합
        Object data = null;
        processData(data);
        
        // 예제 5: 다양한 레코드 타입 처리
        Object record = new Employee("홍길동", 12345, "개발");
        String info = switch (record) {
            case Person(String name, int age) -> 
                name + "님은 " + age + "세입니다.";
            case Employee(String name, int id, String dept) -> 
                name + "님은 " + dept + " 부서의 직원 ID " + id + "입니다.";
            default -> "처리할 수 없는 레코드";
        };
        System.out.println(info);
        
        // 예제 6: 중첩 패턴과 조건 결합
        Object[] items = {1, "2", 3.0, true, null};
        for (Object item : items) {
            String description = switch (item) {
                case Integer i when i % 2 == 0 -> "짝수";
                case Integer i -> "홀수";
                case String s when s.matches("\\d+") -> "숫자 문자열: " + s;
                case String s -> "일반 문자열: " + s;
                case Double d when d.isNaN() -> "숫자가 아님(NaN)";
                case Double d when d.isInfinite() -> "무한대";
                case Double d -> "실수: " + d;
                case null -> "null 값";
                default -> "기타 타입: " + item.getClass().getSimpleName();
            };
            System.out.println(description);
        }
    }
    
    private static double calculateArea(Shape shape) {
        return switch (shape) {
            case Circle(double radius) -> Math.PI * radius * radius;
            case Rectangle(double width, double height) -> width * height;
            case Triangle(double base, double height) -> (base * height) / 2;
        };
    }
    
    private static void processData(Object data) {
        String status = switch (data) {
            case String s when s.isBlank() -> "빈 문자열";
            case String s -> "문자열 데이터: " + s;
            case Integer i -> "정수 데이터: " + i;
            case null -> "데이터 없음";
            default -> "알 수 없는 데이터 유형";
        };
        System.out.println("데이터 상태: " + status);
    }
}
