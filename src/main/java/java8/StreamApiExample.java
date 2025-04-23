package java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java 8 스트림(Stream) API 예제
 * 
 * 스트림 API는 Java 8에서 가장 중요한 기능 중 하나로, 컬렉션의 요소를 선언적으로 처리할 수 있게 해줍니다.
 * 함수형 프로그래밍 스타일을 자바에 도입하여 병렬 처리가 용이하고 가독성이 좋은 코드를 작성할 수 있게 합니다.
 */
public class StreamApiExample {

    public static void main(String[] args) {
        // 예제 1: 기본 스트림 생성 및 변환
        basicStreamExample();
        
        // 예제 2: 필터링과 매핑
        filteringAndMappingExample();
        
        // 예제 3: 집계 함수
        aggregationExample();
        
        // 예제 4: 컬렉션 및 리듀싱 연산
        collectionAndReducingExample();
        
        // 예제 5: 병렬 스트림
        parallelStreamExample();
    }
    
    /**
     * 예제 1: 기본 스트림 생성 및 변환
     */
    private static void basicStreamExample() {
        System.out.println("\n=== 기본 스트림 예제 ===");
        
        // 컬렉션에서 스트림 생성
        List<String> names = Arrays.asList("김철수", "이영희", "박민수", "정지영", "최동현");
        
        // 스트림을 사용한 출력
        System.out.println("모든 이름 출력:");
        names.stream().forEach(System.out::println);
        
        // 배열에서 스트림 생성
        int[] numbers = {1, 2, 3, 4, 5};
        System.out.println("\n숫자 배열 합계: " + Arrays.stream(numbers).sum());
        
        // 직접 스트림 생성
        System.out.println("\n직접 생성한 스트림:");
        java.util.stream.Stream.of("자바", "스트림", "API")
                .forEach(System.out::println);
                
        // 무한 스트림 (제한 필요)
        System.out.println("\n무한 스트림에서 첫 5개 짝수:");
        java.util.stream.Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println);
    }
    
    /**
     * 예제 2: 필터링과 매핑
     */
    private static void filteringAndMappingExample() {
        System.out.println("\n=== 필터링과 매핑 예제 ===");
        
        List<Student> students = Arrays.asList(
                new Student("홍길동", 18, "남성", 85),
                new Student("김영희", 17, "여성", 92),
                new Student("이철수", 19, "남성", 77),
                new Student("박지영", 18, "여성", 93),
                new Student("최민수", 19, "남성", 62)
        );
        
        // 필터링: 18세 이상 학생만 선택
        System.out.println("18세 이상 학생:");
        students.stream()
                .filter(student -> student.getAge() >= 18)
                .forEach(student -> System.out.println(student.getName() + " - " + student.getAge() + "세"));
        
        // 매핑: 학생 이름만 추출
        System.out.println("\n모든 학생 이름:");
        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);
        
        // 여러 조건 조합: 19세 남학생의 점수
        System.out.println("\n19세 남학생의 점수:");
        students.stream()
                .filter(student -> student.getAge() == 19)
                .filter(student -> "남성".equals(student.getGender()))
                .map(Student::getScore)
                .forEach(score -> System.out.println(score + "점"));
        
        // flatMap 예제: 문장에서 고유 단어 추출
        List<String> sentences = Arrays.asList(
                "안녕하세요 자바 프로그래밍",
                "자바 스트림 API는 멋집니다",
                "함수형 프로그래밍을 자바에서"
        );
        
        System.out.println("\n모든 문장에서 고유 단어 추출:");
        List<String> uniqueWords = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .distinct()
                .collect(Collectors.toList());
                
        System.out.println(uniqueWords);
    }
    
    /**
     * 예제 3: 집계 함수
     */
    private static void aggregationExample() {
        System.out.println("\n=== 집계 함수 예제 ===");
        
        List<Integer> numbers = Arrays.asList(1, 5, 8, 12, 3, 9, 4, 7);
        
        // 개수 세기
        long count = numbers.stream().count();
        System.out.println("요소 개수: " + count);
        
        // 최댓값 찾기
        int max = numbers.stream().max(Integer::compare).orElse(0);
        System.out.println("최댓값: " + max);
        
        // 최솟값 찾기
        int min = numbers.stream().min(Integer::compare).orElse(0);
        System.out.println("최솟값: " + min);
        
        // 합계 계산
        int sum = numbers.stream().mapToInt(Integer::intValue).sum();
        System.out.println("합계: " + sum);
        
        // 평균 계산
        double average = numbers.stream().mapToInt(Integer::intValue).average().orElse(0);
        System.out.println("평균: " + average);
        
        // 통계 정보
        java.util.IntSummaryStatistics stats = numbers.stream().mapToInt(Integer::intValue).summaryStatistics();
        System.out.println("\n통계 정보:");
        System.out.println("  - 개수: " + stats.getCount());
        System.out.println("  - 합계: " + stats.getSum());
        System.out.println("  - 평균: " + stats.getAverage());
        System.out.println("  - 최솟값: " + stats.getMin());
        System.out.println("  - 최댓값: " + stats.getMax());
    }
    
    /**
     * 예제 4: 컬렉션 및 리듀싱 연산
     */
    private static void collectionAndReducingExample() {
        System.out.println("\n=== 컬렉션 및 리듀싱 연산 예제 ===");
        
        List<Product> products = Arrays.asList(
                new Product("노트북", "전자제품", 1200000),
                new Product("스마트폰", "전자제품", 800000),
                new Product("청바지", "의류", 50000),
                new Product("티셔츠", "의류", 30000),
                new Product("신발", "의류", 70000),
                new Product("냉장고", "가전", 1500000),
                new Product("TV", "가전", 2000000)
        );
        
        // 카테고리별 그룹화
        System.out.println("카테고리별 제품 그룹화:");
        
        java.util.Map<String, List<Product>> productsByCategory = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
                
        productsByCategory.forEach((category, productList) -> {
            System.out.println("* " + category + ":");
            productList.forEach(product -> 
                System.out.println("  - " + product.getName() + ": " + product.getPrice() + "원"));
        });
        
        // 카테고리별 평균 가격
        System.out.println("\n카테고리별 평균 가격:");
        
        java.util.Map<String, Double> avgPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.averagingInt(Product::getPrice)
                ));
                
        avgPriceByCategory.forEach((category, avgPrice) -> 
            System.out.println(category + ": " + Math.round(avgPrice) + "원"));
        
        // reduce 연산 - 모든 제품 가격의 합
        int totalPrice = products.stream()
                .map(Product::getPrice)
                .reduce(0, Integer::sum);
                
        System.out.println("\n모든 제품 가격의 합: " + totalPrice + "원");
        
        // reduce 연산 - 가장 비싼 제품 찾기
        Product mostExpensive = products.stream()
                .reduce((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2)
                .orElse(null);
                
        if (mostExpensive != null) {
            System.out.println("가장 비싼 제품: " + mostExpensive.getName() + " (" + 
                    mostExpensive.getPrice() + "원)");
        }
        
        // joining 연산 - 모든 제품 이름을 쉼표로 구분하여 연결
        String productNames = products.stream()
                .map(Product::getName)
                .collect(Collectors.joining(", "));
                
        System.out.println("\n모든 제품 이름: " + productNames);
    }
    
    /**
     * 예제 5: 병렬 스트림
     */
    private static void parallelStreamExample() {
        System.out.println("\n=== 병렬 스트림 예제 ===");
        
        // 대량의 데이터 생성
        List<Integer> bigList = new java.util.ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            bigList.add(i);
        }
        
        // 순차 처리 시간 측정
        long startTime = System.currentTimeMillis();
        
        long sequentialCount = bigList.stream()
                .filter(n -> n % 3 == 0 && n % 5 == 0)
                .count();
                
        long sequentialTime = System.currentTimeMillis() - startTime;
        System.out.println("순차 처리 결과: " + sequentialCount);
        System.out.println("순차 처리 시간: " + sequentialTime + "ms");
        
        // 병렬 처리 시간 측정
        startTime = System.currentTimeMillis();
        
        long parallelCount = bigList.parallelStream()
                .filter(n -> n % 3 == 0 && n % 5 == 0)
                .count();
                
        long parallelTime = System.currentTimeMillis() - startTime;
        System.out.println("병렬 처리 결과: " + parallelCount);
        System.out.println("병렬 처리 시간: " + parallelTime + "ms");
        System.out.println("속도 향상: " + String.format("%.2f", (double)sequentialTime / parallelTime) + "배");
        
        // 주의사항: 병렬 스트림에서의 순서 예측 불가
        List<Integer> smallList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        System.out.println("\n순차 스트림 처리 순서:");
        smallList.stream()
                .map(n -> {
                    System.out.println("매핑: " + n + " (스레드: " + Thread.currentThread().getName() + ")");
                    return n * 2;
                })
                .forEach(n -> {
                    System.out.println("출력: " + n + " (스레드: " + Thread.currentThread().getName() + ")");
                });
                
        System.out.println("\n병렬 스트림 처리 순서:");
        smallList.parallelStream()
                .map(n -> {
                    System.out.println("매핑: " + n + " (스레드: " + Thread.currentThread().getName() + ")");
                    return n * 2;
                })
                .forEach(n -> {
                    System.out.println("출력: " + n + " (스레드: " + Thread.currentThread().getName() + ")");
                });
    }
    
    // 예제 클래스
    static class Student {
        private String name;
        private int age;
        private String gender;
        private int score;
        
        public Student(String name, int age, String gender, int score) {
            this.name = name;
            this.age = age;
            this.gender = gender;
            this.score = score;
        }
        
        public String getName() { return name; }
        public int getAge() { return age; }
        public String getGender() { return gender; }
        public int getScore() { return score; }
    }
    
    static class Product {
        private String name;
        private String category;
        private int price;
        
        public Product(String name, String category, int price) {
            this.name = name;
            this.category = category;
            this.price = price;
        }
        
        public String getName() { return name; }
        public String getCategory() { return category; }
        public int getPrice() { return price; }
    }
}
