package java21;

/**
 * Java 21 레코드 패턴(Record Patterns) 예제
 * 
 * 레코드 패턴은 레코드 값의 구조를 분해하고 매칭하는 데 사용할 수 있는 패턴입니다.
 * 이를 통해 복잡한 데이터 구조를 보다 간결하고 안전하게 처리할 수 있습니다.
 */
public class RecordPatternsExample {

    // 기본 레코드 정의
    record Point(int x, int y) {}
    record Rectangle(Point upperLeft, Point lowerRight) {}
    record Circle(Point center, int radius) {}
    record Triangle(Point a, Point b, Point c) {}
    
    // 복잡한 중첩 레코드
    record ColoredShape(Shape shape, Color color) {}
    record Shape(String type, Object geometry) {}
    record Color(int r, int g, int b) {
        public String toHex() {
            return String.format("#%02x%02x%02x", r, g, b);
        }
    }

    public static void main(String[] args) {
        // 예제 1: 기본 레코드 패턴
        Point point = new Point(10, 20);
        if (point instanceof Point(int x, int y)) {
            System.out.println("점의 좌표: (" + x + ", " + y + ")");
        }
        
        // 예제 2: 중첩 레코드 패턴
        Rectangle rectangle = new Rectangle(new Point(1, 1), new Point(5, 5));
        if (rectangle instanceof Rectangle(Point(int x1, int y1), Point(int x2, int y2))) {
            int width = x2 - x1;
            int height = y2 - y1;
            System.out.println("너비: " + width + ", 높이: " + height);
            System.out.println("면적: " + (width * height));
        }
        
        // 예제 3: switch 표현식과 레코드 패턴 활용
        Object shape = new Circle(new Point(0, 0), 10);
        String description = switch (shape) {
            case Rectangle(Point(var x1, var y1), Point(var x2, var y2)) -> {
                int width = x2 - x1;
                int height = y2 - y1;
                yield "사각형 (너비: " + width + ", 높이: " + height + ")";
            }
            case Circle(Point(var x, var y), var radius) -> 
                "원 (중심: (" + x + ", " + y + "), 반지름: " + radius + ")";
            case Triangle(var p1, var p2, var p3) -> 
                "삼각형 (점: " + p1 + ", " + p2 + ", " + p3 + ")";
            default -> "알 수 없는 도형";
        };
        System.out.println(description);
        
        // 예제 4: 더 복잡한 중첩 패턴
        ColoredShape coloredShape = new ColoredShape(
            new Shape("circle", new Circle(new Point(3, 4), 7)),
            new Color(255, 0, 0)
        );
        
        if (coloredShape instanceof ColoredShape(
                Shape(String type, Circle(Point(int cx, int cy), int r)),
                Color(int red, int green, int blue)
            )) {
            System.out.println("타입: " + type);
            System.out.println("중심: (" + cx + ", " + cy + ")");
            System.out.println("반지름: " + r);
            System.out.println("색상: rgb(" + red + ", " + green + ", " + blue + ")");
        }
        
        // 예제 5: var 패턴 변수와 when 절 조합
        Object obj = new Point(100, 200);
        switch (obj) {
            case Point(var x, var y) when x > 0 && y > 0 -> 
                System.out.println("1사분면의 점: (" + x + ", " + y + ")");
            case Point(var x, var y) when x < 0 && y > 0 -> 
                System.out.println("2사분면의 점: (" + x + ", " + y + ")");
            case Point(var x, var y) when x < 0 && y < 0 -> 
                System.out.println("3사분면의 점: (" + x + ", " + y + ")");
            case Point(var x, var y) when x > 0 && y < 0 -> 
                System.out.println("4사분면의 점: (" + x + ", " + y + ")");
            case Point(var x, var y) -> 
                System.out.println("축 위의 점: (" + x + ", " + y + ")");
            default -> 
                System.out.println("점이 아님");
        }
    }
}
