package java17;

/**
 * Java 17 봉인 클래스(Sealed Classes) 예제
 * 
 * 봉인 클래스는 어떤 클래스가 해당 클래스를 상속할 수 있는지 제한할 수 있는 기능입니다.
 * 클래스 계층 구조를 더 정확하게 모델링하고 컴파일 타임에 더 많은 정보를 제공합니다.
 */
public class SealedClassesExample {

    // 예제 1: 기본 봉인 클래스와 허용된 구현체
    public sealed interface Shape permits Circle, Rectangle, Triangle {
        double area();
    }
    
    public final class Circle implements Shape {
        private final double radius;
        
        public Circle(double radius) {
            this.radius = radius;
        }
        
        @Override
        public double area() {
            return Math.PI * radius * radius;
        }
    }
    
    public final class Rectangle implements Shape {
        private final double width;
        private final double height;
        
        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }
        
        @Override
        public double area() {
            return width * height;
        }
    }
    
    public final class Triangle implements Shape {
        private final double base;
        private final double height;
        
        public Triangle(double base, double height) {
            this.base = base;
            this.height = height;
        }
        
        @Override
        public double area() {
            return (base * height) / 2;
        }
    }
    
    // 예제 2: 봉인 클래스와 패턴 매칭
    public static double calculateArea(Shape shape) {
        // Java 17에서는 아직 완전한 패턴 매칭을 제공하지 않지만
        // instanceof와 패턴 변수를 사용하여 비슷한 효과를 낼 수 있습니다.
        if (shape instanceof Circle c) {
            return c.area();
        } else if (shape instanceof Rectangle r) {
            return r.area();
        } else if (shape instanceof Triangle t) {
            return t.area();
        } else {
            throw new IllegalArgumentException("알 수 없는 도형");
        }
    }
    
    // 예제 3: 더 복잡한 봉인 클래스 계층 구조
    public sealed abstract class Vehicle permits Car, Truck, Motorcycle {
        protected String registrationNumber;
        
        public Vehicle(String registrationNumber) {
            this.registrationNumber = registrationNumber;
        }
        
        public abstract String getType();
    }
    
    // non-sealed 클래스 - 이 클래스를 상속하는 것에 제한이 없음
    public non-sealed class Car extends Vehicle {
        private final int numDoors;
        
        public Car(String registrationNumber, int numDoors) {
            super(registrationNumber);
            this.numDoors = numDoors;
        }
        
        @Override
        public String getType() {
            return "Car";
        }
        
        public int getNumDoors() {
            return numDoors;
        }
    }
    
    // 봉인된 클래스의 최종 하위 클래스
    public final class Truck extends Vehicle {
        private final double loadCapacity;
        
        public Truck(String registrationNumber, double loadCapacity) {
            super(registrationNumber);
            this.loadCapacity = loadCapacity;
        }
        
        @Override
        public String getType() {
            return "Truck";
        }
        
        public double getLoadCapacity() {
            return loadCapacity;
        }
    }
    
    // 봉인된 클래스를 확장하는 또 다른 봉인된 클래스
    public sealed class Motorcycle extends Vehicle permits SportBike, CruiserBike {
        private final boolean hasSideCar;
        
        public Motorcycle(String registrationNumber, boolean hasSideCar) {
            super(registrationNumber);
            this.hasSideCar = hasSideCar;
        }
        
        @Override
        public String getType() {
            return "Motorcycle";
        }
        
        public boolean hasSideCar() {
            return hasSideCar;
        }
    }
    
    public final class SportBike extends Motorcycle {
        private final int topSpeed;
        
        public SportBike(String registrationNumber, boolean hasSideCar, int topSpeed) {
            super(registrationNumber, hasSideCar);
            this.topSpeed = topSpeed;
        }
        
        public int getTopSpeed() {
            return topSpeed;
        }
    }
    
    public final class CruiserBike extends Motorcycle {
        private final double fuelCapacity;
        
        public CruiserBike(String registrationNumber, boolean hasSideCar, double fuelCapacity) {
            super(registrationNumber, hasSideCar);
            this.fuelCapacity = fuelCapacity;
        }
        
        public double getFuelCapacity() {
            return fuelCapacity;
        }
    }
    
    // 예제 4: enum과 봉인 클래스의 유사점 및 차이점 설명
    public enum Direction { NORTH, SOUTH, EAST, WEST }
    
    public static void main(String[] args) {
        // 봉인 클래스 계층 구조 사용 예시
        SealedClassesExample example = new SealedClassesExample();
        
        Shape circle = example.new Circle(5.0);
        Shape rectangle = example.new Rectangle(4.0, 6.0);
        Shape triangle = example.new Triangle(3.0, 4.0);
        
        System.out.println("원 면적: " + calculateArea(circle));
        System.out.println("사각형 면적: " + calculateArea(rectangle));
        System.out.println("삼각형 면적: " + calculateArea(triangle));
        
        // 봉인 클래스 허용 검사를 통한 타입 안전성
        Vehicle car = example.new Car("CA-1234", 4);
        Vehicle truck = example.new Truck("TR-5678", 5000.0);
        Vehicle sportBike = example.new SportBike("SB-9012", false, 300);
        
        System.out.println(car.getType() + ": " + ((Car)car).getNumDoors() + "개 문");
        System.out.println(truck.getType() + ": " + ((Truck)truck).getLoadCapacity() + "kg 적재용량");
        System.out.println(sportBike.getType() + ": " + ((SportBike)sportBike).getTopSpeed() + "km/h 최고속도");
        
        // 컴파일 타임 오류 예시 (실행 불가, 주석 처리)
        // class IllegalShape implements Shape { // 오류: Shape은 봉인되어 있고 IllegalShape은 허용되지 않음
        //     @Override
        //     public double area() {
        //         return 0;
        //     }
        // }
    }
}
