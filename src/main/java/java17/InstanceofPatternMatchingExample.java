package java17;

/**
 * Java 14에서 도입되고 Java 17 LTS에서 완전히 지원되는 instanceof 패턴 매칭 예제
 * 
 * 패턴 매칭을 통해 타입 검사와 캐스팅을 한 번에 처리할 수 있습니다.
 * 코드의 가독성과 안전성을 높여줍니다.
 */
public class InstanceofPatternMatchingExample {

    // 예제를 위한 클래스 계층 구조
    static abstract class Animal {
        private String name;
        
        public Animal(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public abstract String makeSound();
    }
    
    static class Dog extends Animal {
        private String breed;
        
        public Dog(String name, String breed) {
            super(name);
            this.breed = breed;
        }
        
        public String getBreed() {
            return breed;
        }
        
        @Override
        public String makeSound() {
            return "멍멍!";
        }
        
        public void fetch() {
            System.out.println(getName() + "가 공을 가져옵니다.");
        }
    }
    
    static class Cat extends Animal {
        private boolean isIndoor;
        
        public Cat(String name, boolean isIndoor) {
            super(name);
            this.isIndoor = isIndoor;
        }
        
        public boolean isIndoor() {
            return isIndoor;
        }
        
        @Override
        public String makeSound() {
            return "야옹~";
        }
        
        public void purr() {
            System.out.println(getName() + "가 그르렁거립니다.");
        }
    }
    
    static class Bird extends Animal {
        private boolean canFly;
        
        public Bird(String name, boolean canFly) {
            super(name);
            this.canFly = canFly;
        }
        
        public boolean canFly() {
            return canFly;
        }
        
        @Override
        public String makeSound() {
            return "짹짹!";
        }
        
        public void fly() {
            if (canFly) {
                System.out.println(getName() + "가 날아갑니다.");
            } else {
                System.out.println(getName() + "는 날 수 없습니다.");
            }
        }
    }

    public static void main(String[] args) {
        // 예제 1: 기본 instanceof 패턴 매칭
        Animal myPet = new Dog("바둑이", "진돗개");
        describeAnimal(myPet);
        
        myPet = new Cat("나비", true);
        describeAnimal(myPet);
        
        myPet = new Bird("참새", true);
        describeAnimal(myPet);
        
        // 예제 2: 다양한 객체 타입 처리
        Object[] objects = {
            "Hello, World!",
            Integer.valueOf(42),
            new Dog("럭키", "리트리버"),
            Double.valueOf(3.14),
            new Cat("야옹이", false),
            Boolean.TRUE
        };
        
        for (Object obj : objects) {
            processObject(obj);
        }
        
        // 예제 3: 조건이 추가된 패턴 매칭
        Animal[] animals = {
            new Dog("스피드", "그레이하운드"),
            new Dog("뭉치", "푸들"),
            new Cat("나비", true),
            new Cat("길고양이", false),
            new Bird("파랑새", true),
            new Bird("펭귄", false)
        };
        
        for (Animal animal : animals) {
            checkSpecialAbilities(animal);
        }
        
        // 예제 4: 예전 방식과 새로운 패턴 매칭 비교
        compareOldAndNewApproach(new Dog("바둑이", "시바견"));
    }
    
    // 예제 1: 기본 instanceof 패턴 매칭 사용
    public static void describeAnimal(Animal animal) {
        System.out.println("\n동물 소개:");
        System.out.println("이름: " + animal.getName());
        System.out.println("소리: " + animal.makeSound());
        
        // Java 17 패턴 매칭 사용
        if (animal instanceof Dog dog) {
            // 타입 체크와 캐스팅이 한 번에 이루어짐
            System.out.println("견종: " + dog.getBreed());
            dog.fetch();
        } else if (animal instanceof Cat cat) {
            System.out.println("실내고양이: " + (cat.isIndoor() ? "예" : "아니오"));
            cat.purr();
        } else if (animal instanceof Bird bird) {
            System.out.println("날 수 있나요: " + (bird.canFly() ? "예" : "아니오"));
            bird.fly();
        }
    }
    
    // 예제 2: 다양한 타입의 객체 처리
    public static void processObject(Object obj) {
        // 패턴 변수를 사용한 타입 확인 및 데이터 추출
        if (obj instanceof String s) {
            System.out.println("문자열 처리: " + s.toUpperCase());
        } else if (obj instanceof Integer i) {
            System.out.println("정수 처리: " + (i * 2));
        } else if (obj instanceof Double d) {
            System.out.println("실수 처리: " + Math.round(d));
        } else if (obj instanceof Dog dog) {
            System.out.println("개 처리: " + dog.getName() + "는 " + dog.getBreed() + " 견종입니다.");
        } else if (obj instanceof Animal animal) {
            System.out.println("동물 처리: " + animal.getName() + "는 " + animal.makeSound() + " 소리를 냅니다.");
        } else {
            System.out.println("기타 객체 처리: " + obj);
        }
    }
    
    // 예제 3: 조건이 포함된 패턴 매칭
    public static void checkSpecialAbilities(Animal animal) {
        // 패턴 매칭과 조건문 결합
        if (animal instanceof Dog dog && "그레이하운드".equals(dog.getBreed())) {
            System.out.println(dog.getName() + "는 매우 빠른 개입니다!");
        } else if (animal instanceof Cat cat && !cat.isIndoor()) {
            System.out.println(cat.getName() + "는 야외 고양이로 사냥을 잘합니다.");
        } else if (animal instanceof Bird bird && bird.canFly()) {
            System.out.println(bird.getName() + "는 하늘을 자유롭게 날 수 있습니다.");
        }
    }
    
    // 예제 4: 예전 방식과 새로운 패턴 매칭 비교
    public static void compareOldAndNewApproach(Animal animal) {
        System.out.println("\n== 예전 방식 ==");
        // Java 16 이전 방식 - 타입 확인과 캐스팅이 분리됨
        if (animal instanceof Dog) {
            Dog dog = (Dog) animal; // 추가적인 캐스팅 필요
            System.out.println(dog.getName() + "는 " + dog.getBreed() + " 견종이며 " + dog.makeSound() + " 소리를 냅니다.");
        }
        
        System.out.println("== 새로운 방식 ==");
        // Java 16 이후 방식 - 타입 확인과 캐스팅이 결합됨
        if (animal instanceof Dog dog) {
            // 패턴 변수 'dog'를 바로 사용 가능
            System.out.println(dog.getName() + "는 " + dog.getBreed() + " 견종이며 " + dog.makeSound() + " 소리를 냅니다.");
        }
        
        // 부정적 매칭에서의 스코프 차이
        System.out.println("\n== 스코프 차이 ==");
        if (!(animal instanceof Dog dog)) {
            // dog 변수는 이 블록에서 사용할 수 없음
            System.out.println("이 동물은 개가 아닙니다.");
        } else {
            // dog 변수는 이 블록에서만 사용 가능
            System.out.println("이 동물은 " + dog.getBreed() + " 견종의 개입니다.");
        }
    }
}
