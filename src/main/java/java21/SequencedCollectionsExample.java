package java21;

import java.util.*;

/**
 * Java 21 시퀀스 컬렉션(Sequenced Collections) 예제
 * 
 * Sequenced Collections API는 Java 컬렉션 프레임워크에 추가된 새로운 인터페이스로,
 * 순서가 있는 컬렉션에 대한 처음/마지막 요소 접근, 추가, 제거를 위한 통합된 API를 제공합니다.
 */
public class SequencedCollectionsExample {

    public static void main(String[] args) {
        // 예제 1: SequencedCollection 인터페이스 - List
        System.out.println("=== ArrayList 예제 ===");
        List<String> fruits = new ArrayList<>(List.of("사과", "바나나", "오렌지", "포도"));
        
        // 첫 번째, 마지막 요소 접근
        System.out.println("첫 번째 요소: " + fruits.getFirst());
        System.out.println("마지막 요소: " + fruits.getLast());
        
        // 첫 번째, 마지막에 요소 추가
        fruits.addFirst("딸기");
        fruits.addLast("키위");
        System.out.println("요소 추가 후: " + fruits);
        
        // 첫 번째, 마지막 요소 제거
        String firstRemoved = fruits.removeFirst();
        String lastRemoved = fruits.removeLast();
        System.out.println("제거된 첫 번째 요소: " + firstRemoved);
        System.out.println("제거된 마지막 요소: " + lastRemoved);
        System.out.println("요소 제거 후: " + fruits);
        
        // 역순으로 된 뷰 얻기
        List<String> reversedFruits = fruits.reversed();
        System.out.println("역순 뷰: " + reversedFruits);
        
        // 예제 2: SequencedSet 인터페이스 - LinkedHashSet
        System.out.println("\n=== LinkedHashSet 예제 ===");
        LinkedHashSet<Integer> numbers = new LinkedHashSet<>(Set.of(10, 20, 30, 40, 50));
        
        // 첫 번째, 마지막 요소 접근
        System.out.println("첫 번째 요소: " + numbers.getFirst());
        System.out.println("마지막 요소: " + numbers.getLast());
        
        // 첫 번째, 마지막에 요소 추가
        numbers.addFirst(5);
        numbers.addLast(60);
        System.out.println("요소 추가 후: " + numbers);
        
        // 첫 번째, 마지막 요소 제거
        int firstNum = numbers.removeFirst();
        int lastNum = numbers.removeLast();
        System.out.println("제거된 첫 번째 요소: " + firstNum);
        System.out.println("제거된 마지막 요소: " + lastNum);
        System.out.println("요소 제거 후: " + numbers);
        
        // 역순으로 된 뷰 얻기
        NavigableSet<Integer> reversedNumbers = new TreeSet<>(numbers).reversed();
        System.out.println("역순 뷰: " + reversedNumbers);
        
        // 예제 3: SequencedMap 인터페이스 - LinkedHashMap
        System.out.println("\n=== LinkedHashMap 예제 ===");
        LinkedHashMap<String, Integer> scores = new LinkedHashMap<>();
        scores.put("Alice", 95);
        scores.put("Bob", 87);
        scores.put("Charlie", 92);
        scores.put("David", 78);
        
        // 첫 번째, 마지막 항목 접근
        System.out.println("첫 번째 항목: " + scores.firstEntry());
        System.out.println("마지막 항목: " + scores.lastEntry());
        
        // 첫 번째, 마지막에 항목 추가
        scores.putFirst("Eve", 99);
        scores.putLast("Frank", 85);
        
        System.out.println("항목 추가 후: " + scores);
        
        // 첫 번째, 마지막 항목 제거
        Map.Entry<String, Integer> firstEntry = scores.pollFirstEntry();
        Map.Entry<String, Integer> lastEntry = scores.pollLastEntry();
        System.out.println("제거된 첫 번째 항목: " + firstEntry);
        System.out.println("제거된 마지막 항목: " + lastEntry);
        System.out.println("항목 제거 후: " + scores);
        
        // 키, 값, 엔트리의 시퀀스 컬렉션 얻기
        System.out.println("키 시퀀스: " + scores.sequencedKeySet());
        System.out.println("값 시퀀스: " + scores.sequencedValues());
        System.out.println("엔트리 시퀀스: " + scores.sequencedEntrySet());
        
        // 역순으로 된 뷰 얻기
        Map<String, Integer> reversedMap = scores.reversed();
        System.out.println("역순 맵 뷰: " + reversedMap);
        
        // 예제 4: 기존 TreeSet, TreeMap에도 적용
        System.out.println("\n=== TreeSet/TreeMap 예제 ===");
        TreeSet<String> treeSet = new TreeSet<>(Set.of("Alpha", "Bravo", "Charlie", "Delta"));
        System.out.println("TreeSet 첫 번째: " + treeSet.getFirst());
        System.out.println("TreeSet 마지막: " + treeSet.getLast());
        
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");
        
        System.out.println("TreeMap 첫 번째 항목: " + treeMap.firstEntry());
        System.out.println("TreeMap 마지막 항목: " + treeMap.lastEntry());
        
        // 예제 5: 알고리즘에서 시퀀스 컬렉션 활용
        System.out.println("\n=== 알고리즘 활용 예제 ===");
        Deque<Integer> deque = new ArrayDeque<>(List.of(1, 2, 3, 4, 5));
        
        // 양쪽 끝에서 작업하는 알고리즘 구현
        processFromBothEnds(deque);
        System.out.println("양쪽 처리 후: " + deque);
    }
    
    // 시퀀스 컬렉션을 활용한 알고리즘 예시
    private static <E> void processFromBothEnds(Deque<E> deque) {
        while (deque.size() > 1) {
            E first = deque.removeFirst();
            E last = deque.removeLast();
            
            System.out.println("처리: " + first + " 및 " + last);
            
            // 양쪽에서 제거한 요소를 가공하여 다시 추가할 수 있음
            // 이 예제에서는 단순히 제거만 함
        }
    }
}
