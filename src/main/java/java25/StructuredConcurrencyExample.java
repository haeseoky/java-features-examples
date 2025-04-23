package java25;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Java 25 예상 기능: 구조화된 동시성(Structured Concurrency) - JEP 453
 * 
 * 구조화된 동시성은 Java 19에서 프리뷰로 도입되어 Java 25에서 정식으로 채택될 가능성이 높은 기능입니다.
 * 이 기능은 여러 작업을 동시에 실행하고 취소, 타임아웃, 예외 처리 등을 포함한 수명 주기 관리를 간소화합니다.
 */
public class StructuredConcurrencyExample {

    public static void main(String[] args) throws Exception {
        System.out.println("=== Java 25 구조화된 동시성(Structured Concurrency) 예제 ===");

        // 예제 1: 간단한 구조화된 동시성 사용 예시
        System.out.println("\n=== 간단한 병렬 작업 예제 ===");
        exampleWithoutStructuredConcurrency();
        
        // Java 25에서 구조화된 동시성 사용 (예상 코드)
        /* 
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // 두 개의 작업을 병렬로 시작
            Future<String> user = scope.fork(() -> findUser("user123"));
            Future<List<Order>> orders = scope.fork(() -> fetchOrders("user123"));
            
            // 모든 작업이 완료될 때까지 대기
            scope.join();
            
            // 예외 발생 여부 확인
            scope.throwIfFailed();
            
            // 결과 처리
            processUserOrders(user.resultNow(), orders.resultNow());
        }
        */
        
        // 예제 2: 다중 작업 처리 시뮬레이션
        System.out.println("\n=== 다중 작업 처리 시뮬레이션 ===");
        simulateMultipleTaskProcessing();
        
        // 예제 3: 가상 스레드와 구조화된 동시성
        System.out.println("\n=== 가상 스레드와 병렬 처리 ===");
        processWithVirtualThreads();
    }
    
    // 기존 방식 (ExecutorService 사용)
    private static void exampleWithoutStructuredConcurrency() throws Exception {
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            // 두 개의 작업을 병렬로 시작
            Future<String> userFuture = executor.submit(() -> findUser("user123"));
            Future<List<Order>> ordersFuture = executor.submit(() -> fetchOrders("user123"));
            
            // 결과 취득
            String user = userFuture.get();
            List<Order> orders = ordersFuture.get();
            
            // 결과 처리
            processUserOrders(user, orders);
        }
    }
    
    // 여러 작업을 동시에 처리하는 시뮬레이션
    private static void simulateMultipleTaskProcessing() throws Exception {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Result>> futures = new ArrayList<>();
            
            // 여러 작업 제출
            for (int i = 0; i < 5; i++) {
                int taskId = i;
                futures.add(executor.submit(() -> processTask(taskId)));
            }
            
            // 결과 수집 및 처리
            List<Result> results = new ArrayList<>();
            for (Future<Result> future : futures) {
                try {
                    results.add(future.get());
                } catch (Exception e) {
                    System.out.println("작업 실패: " + e.getMessage());
                }
            }
            
            // 결과 요약
            System.out.println("처리된 작업 수: " + results.size());
            results.forEach(result -> 
                System.out.println("작업 " + result.taskId() + " 결과: " + result.status()));
        }
        
        // Java 25 구조화된 동시성 사용 예상 코드
        /*
        try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
            // 여러 작업 병렬 실행
            List<Future<Result>> futures = new ArrayList<>();
            
            for (int i = 0; i < 5; i++) {
                int taskId = i;
                futures.add(scope.fork(() -> processTask(taskId)));
            }
            
            // 모든 작업 완료 대기
            scope.join();
            
            // 결과 처리
            List<Result> results = new ArrayList<>();
            for (Future<Result> future : futures) {
                try {
                    results.add(future.resultNow());
                } catch (Exception e) {
                    System.out.println("작업 실패: " + e.getMessage());
                }
            }
            
            // 결과 요약
            System.out.println("처리된 작업 수: " + results.size());
            results.forEach(result -> 
                System.out.println("작업 " + result.taskId() + " 결과: " + result.status()));
        }
        */
    }
    
    // 가상 스레드를 사용한 병렬 처리
    private static void processWithVirtualThreads() throws Exception {
        // 대용량 데이터 시뮬레이션
        List<String> items = List.of(
            "item1", "item2", "item3", "item4", "item5",
            "item6", "item7", "item8", "item9", "item10"
        );
        
        // 가상 스레드를 사용한 병렬 처리
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<Map<String, Object>>> futures = new ArrayList<>();
            
            for (String item : items) {
                futures.add(executor.submit(() -> processItem(item)));
            }
            
            // 결과 수집
            List<Map<String, Object>> results = new ArrayList<>();
            for (Future<Map<String, Object>> future : futures) {
                results.add(future.get());
            }
            
            // 결과 출력
            System.out.println("처리된 항목: " + results.size());
            for (int i = 0; i < 3 && i < results.size(); i++) {
                System.out.println("항목 " + (i + 1) + " 결과: " + results.get(i));
            }
            if (results.size() > 3) {
                System.out.println("... 그 외 " + (results.size() - 3) + "개 항목");
            }
        }
        
        // Java 25 구조화된 동시성 예상 코드
        /*
        try (var scope = new StructuredTaskScope<Map<String, Object>>()) {
            // 모든 항목에 대해 병렬 처리 시작
            for (String item : items) {
                scope.fork(() -> processItem(item));
            }
            
            // 모든 처리 완료 또는 3초 타임아웃 대기
            scope.joinUntil(Instant.now().plusSeconds(3));
            
            // 결과 수집
            List<Map<String, Object>> results = scope.results();
            
            // 결과 출력
            System.out.println("처리된 항목: " + results.size());
            for (int i = 0; i < 3 && i < results.size(); i++) {
                System.out.println("항목 " + (i + 1) + " 결과: " + results.get(i));
            }
            if (results.size() > 3) {
                System.out.println("... 그 외 " + (results.size() - 3) + "개 항목");
            }
        }
        */
    }
    
    // 사용자 정보 조회 시뮬레이션
    private static String findUser(String userId) throws InterruptedException {
        System.out.println("사용자 조회 중: " + userId);
        Thread.sleep(500); // 네트워크 지연 시뮬레이션
        return "User: " + userId + " (홍길동)";
    }
    
    // 주문 정보 조회 시뮬레이션
    private static List<Order> fetchOrders(String userId) throws InterruptedException {
        System.out.println("주문 정보 조회 중: " + userId);
        Thread.sleep(700); // 데이터베이스 지연 시뮬레이션
        return List.of(
            new Order(1, "상품A", 10000),
            new Order(2, "상품B", 20000)
        );
    }
    
    // 결과 처리 시뮬레이션
    private static void processUserOrders(String user, List<Order> orders) {
        System.out.println("처리 결과:");
        System.out.println("- " + user);
        System.out.println("- 주문 " + orders.size() + "건:");
        orders.forEach(order -> 
            System.out.println("  * " + order.orderNumber() + ": " + order.productName() + " (" + order.amount() + "원)"));
    }
    
    // 작업 처리 시뮬레이션
    private static Result processTask(int taskId) throws InterruptedException {
        System.out.println("작업 " + taskId + " 처리 중...");
        Thread.sleep((long) (Math.random() * 1000)); // 무작위 처리 시간
        
        // 간혹 실패하는 작업 시뮬레이션
        if (taskId == 3 && Math.random() < 0.5) {
            throw new RuntimeException("작업 " + taskId + " 처리 중 오류 발생");
        }
        
        return new Result(taskId, "완료");
    }
    
    // 항목 처리 시뮬레이션
    private static Map<String, Object> processItem(String item) throws InterruptedException {
        System.out.println("항목 처리 중: " + item);
        Thread.sleep((long) (Math.random() * 500)); // 처리 시간 시뮬레이션
        
        Map<String, Object> result = new HashMap<>();
        result.put("item", item);
        result.put("processedAt", System.currentTimeMillis());
        result.put("status", "success");
        
        return result;
    }
    
    // 주문 정보를 나타내는 레코드
    record Order(int orderNumber, String productName, int amount) {}
    
    // 작업 결과를 나타내는 레코드
    record Result(int taskId, String status) {}
}