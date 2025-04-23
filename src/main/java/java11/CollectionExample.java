package java11;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Java 11 컬렉션과 스트림 API 관련 새로운 메서드 예제
 * 
 * Java 11에서는 컬렉션 클래스와 스트림 API에 여러 편리한 메서드가 추가되었습니다.
 */
public class CollectionExample {

    public static void main(String[] args) {
        // 예제 1: Collection.toArray(IntFunction)
        collectionsToArrayExample();
        
        // 예제 2: List.of(), Set.of(), Map.of()의 불변 컬렉션
        immutableCollectionsExample();
        
        // 예제 3: String의 새로운 메서드들
        stringNewMethodsExample();
        
        // 예제 4: Predicate.not
        predicateNotExample();
        
        // 예제 5: Stream.ofNullable와 스트림 추가 기능
        streamEnhancementsExample();
    }
    
    /**
     * 예제 1: Collection.toArray(IntFunction)
     * 
     * 컬렉션에서 타입 안전한 방식으로 배열을 생성할 수 있는 새로운 toArray 메서드
     */
    private static void collectionsToArrayExample() {
        System.out.println("\n=== Collection.toArray 새 메서드 ===");
        
        List<String> fruits = List.of("사과", "바나나", "오렌지", "포도");
        
        // Java 11 이전 방식 - 타입 안전하지 않음
        String[] oldWay = fruits.toArray(new String[0]);
        
        // Java 11의 새로운 방식 - 타입 안전함
        String[] newWay = fruits.toArray(String[]::new);
        
        System.out.println("Java 11 이전 방식: " + String.join(", ", oldWay));
        System.out.println("Java 11 새로운 방식: " + String.join(", ", newWay));
    }
    
    /**
     * 예제 2: 불변 컬렉션
     * 
     * Java 9에서 도입되고 Java 11에서 개선된 불변 컬렉션 생성 메서드
     */
    private static void immutableCollectionsExample() {
        System.out.println("\n=== 불변 컬렉션 생성 메서드 ===");
        
        // 불변 List 생성
        List<String> immutableList = List.of("첫 번째", "두 번째", "세 번째");
        System.out.println("불변 List: " + immutableList);
        
        // 불변 Set 생성
        Set<Integer> immutableSet = Set.of(1, 2, 3, 4, 5);
        System.out.println("불변 Set: " + immutableSet);
        
        // 불변 Map 생성
        Map<String, Integer> immutableMap = Map.of(
                "하나", 1,
                "둘", 2,
                "셋", 3
        );
        System.out.println("불변 Map: " + immutableMap);
        
        // 더 많은 요소를 가진 불변 Map - Map.ofEntries 사용
        Map<String, String> largeMap = Map.ofEntries(
                Map.entry("키1", "값1"),
                Map.entry("키2", "값2"),
                Map.entry("키3", "값3"),
                Map.entry("키4", "값4"),
                Map.entry("키5", "값5")
        );
        System.out.println("더 큰 불변 Map: " + largeMap);
        
        try {
            // 불변 컬렉션에 요소 추가 시도 - UnsupportedOperationException 발생
            immutableList.add("네 번째");
        } catch (UnsupportedOperationException e) {
            System.out.println("예상된 예외: 불변 리스트는 수정할 수 없습니다");
        }
    }
    
    /**
     * 예제 3: String의 새로운 메서드들
     * 
     * Java 11에서는 String 클래스에 여러 유용한 메서드가 추가되었습니다.
     */
    private static void stringNewMethodsExample() {
        System.out.println("\n=== String 클래스의 새 메서드 ===");
        
        // isBlank() - 문자열이 비어 있거나 공백 문자만 포함하는지 확인
        String emptyString = "";
        String blankString = "   \t\n";
        String nonBlankString = "Java 11";
        
        System.out.println("emptyString.isBlank(): " + emptyString.isBlank());
        System.out.println("blankString.isBlank(): " + blankString.isBlank());
        System.out.println("nonBlankString.isBlank(): " + nonBlankString.isBlank());
        
        // lines() - 문자열을 줄 단위로 스트림으로 분할
        String multilineString = "첫 번째 줄\n두 번째 줄\r\n세 번째 줄";
        System.out.println("\n문자열 라인으로 분할:");
        multilineString.lines().forEach(line -> System.out.println("  " + line));
        
        // strip(), stripLeading(), stripTrailing() - 유니코드 인식 공백 제거
        String paddedString = "\u2000 안녕하세요! \u2000";
        System.out.println("\n유니코드 공백 제거:");
        System.out.println("원본 문자열: '" + paddedString + "'");
        System.out.println("strip(): '" + paddedString.strip() + "'");
        System.out.println("stripLeading(): '" + paddedString.stripLeading() + "'");
        System.out.println("stripTrailing(): '" + paddedString.stripTrailing() + "'");
        
        // trim()과 strip()의 차이
        String unicodeWhitespace = "\u2000ABC\u2000";
        System.out.println("\ntrim()과 strip()의 차이:");
        System.out.println("원본: '" + unicodeWhitespace + "'");
        System.out.println("trim(): '" + unicodeWhitespace.trim() + "'"); // trim()은 유니코드 공백을 인식하지 못함
        System.out.println("strip(): '" + unicodeWhitespace.strip() + "'");
        
        // repeat() - 문자열을 지정된 횟수만큼 반복
        String star = "*";
        System.out.println("\nrepeat() 메서드:");
        System.out.println(star.repeat(1));
        System.out.println(star.repeat(5));
        System.out.println(star.repeat(10));
        
        // 문자열 형식 지정에 유용
        String formatted = "-".repeat(10) + " 제목 " + "-".repeat(10);
        System.out.println(formatted);
    }
    
    /**
     * 예제 4: Predicate.not 메서드
     * 
     * 기존 Predicate의 부정을 더 읽기 쉽게 표현할 수 있는 정적 메서드
     */
    private static void predicateNotExample() {
        System.out.println("\n=== Predicate.not 예제 ===");
        
        List<String> list = List.of("", "Java", " ", "11", "\t", "LTS");
        
        // Java 11 이전 방식
        List<String> nonEmptyOldWay = list.stream()
                .filter(s -> !s.isBlank())
                .collect(Collectors.toList());
        
        // Java 11의 새로운 방식 - Predicate.not 사용
        List<String> nonEmptyNewWay = list.stream()
                .filter(Predicate.not(String::isBlank))
                .collect(Collectors.toList());
        
        System.out.println("원본 리스트: " + list);
        System.out.println("이전 방식 (비어있지 않은 항목): " + nonEmptyOldWay);
        System.out.println("새 방식 (비어있지 않은 항목): " + nonEmptyNewWay);
        
        // 복잡한 조건에서 더 가독성이 좋음
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        
        // 짝수가 아닌 수 (홀수)
        List<Integer> odds = numbers.stream()
                .filter(Predicate.not(n -> n % 2 == 0))
                .collect(Collectors.toList());
                
        System.out.println("\n홀수 (Predicate.not 사용): " + odds);
        
        // 여러 조건 조합
        List<String> filtered = list.stream()
                .filter(Predicate.not(String::isBlank)
                        .and(s -> s.length() > 1))
                .collect(Collectors.toList());
                
        System.out.println("비어있지 않고 길이가 1보다 큰 항목: " + filtered);
    }
    
    /**
     * 예제 5: Stream API 개선사항
     * 
     * Stream.ofNullable과 기타 스트림 관련 개선사항
     */
    private static void streamEnhancementsExample() {
        System.out.println("\n=== Stream API 개선사항 ===");
        
        // Stream.ofNullable - null을 빈 스트림으로 처리
        String nullableValue = null;
        
        // 이전 방식
        Stream<String> oldWay = nullableValue == null ? Stream.empty() : Stream.of(nullableValue);
        System.out.println("이전 방식의 결과 개수: " + oldWay.count());
        
        // 새로운 방식
        Stream<String> newWay = Stream.ofNullable(nullableValue);
        System.out.println("Stream.ofNullable 결과 개수: " + newWay.count());
        
        // 실제 값을 사용하는 예제
        String nonNullValue = "안녕하세요";
        long count = Stream.ofNullable(nonNullValue).count();
        System.out.println("null이 아닌 값의 Stream.ofNullable 결과 개수: " + count);
        
        // Optional.stream() 활용
        List<String> names = List.of("김철수", null, "이영희", null, "박민수");
        
        // null 값을 건너뛰고 처리
        List<String> nonNullNames = names.stream()
                .flatMap(Stream::ofNullable)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
                
        System.out.println("\nnull을 제외한 대문자 이름: " + nonNullNames);
        
        // dropWhile, takeWhile (Java 9에서 도입되었지만 Java 11과 함께 많이 사용됨)
        List<Integer> numberList = List.of(2, 4, 6, 8, 1, 3, 5, 7, 9, 10, 12);
        
        List<Integer> afterFirstOdd = numberList.stream()
                .dropWhile(n -> n % 2 == 0) // 첫 번째 홀수가 나올 때까지 건너뜀
                .collect(Collectors.toList());
                
        System.out.println("\n첫 번째 홀수 이후의 숫자: " + afterFirstOdd);
        
        List<Integer> untilFirstOdd = numberList.stream()
                .takeWhile(n -> n % 2 == 0) // 첫 번째 홀수 전까지 가져옴
                .collect(Collectors.toList());
                
        System.out.println("첫 번째 홀수 전까지의 숫자: " + untilFirstOdd);
    }
}
