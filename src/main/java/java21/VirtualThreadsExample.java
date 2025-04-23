package java21;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Java 21 가상 스레드(Virtual Threads) 예제
 * 
 * 가상 스레드는 경량 스레드로 I/O 작업이 많은 고성능 동시성 애플리케이션을 더 쉽게 작성할 수 있게 해줍니다.
 * 플랫폼 스레드보다 훨씬 적은 리소스를 사용하며, 수백만 개의 가상 스레드를 생성할 수 있습니다.
 */
public class VirtualThreadsExample {

    public static void main(String[] args) throws InterruptedException {
        // 예제 1: 단일 가상 스레드 생성
        Thread vt = Thread.startVirtualThread(() -> {
            System.out.println("Hello from Virtual Thread: " + Thread.currentThread());
            System.out.println("Is Virtual: " + Thread.currentThread().isVirtual());
        });
        vt.join();
        
        // 예제 2: Thread.Builder를 사용한 가상 스레드 생성
        Thread namedVt = Thread.ofVirtual()
                .name("custom-vt")
                .start(() -> {
                    System.out.println("Hello from named Virtual Thread: " + Thread.currentThread());
                });
        namedVt.join();
        
        // 예제 3: 많은 수의 가상 스레드 생성
        int taskCount = 10_000;
        AtomicInteger counter = new AtomicInteger();
        
        long startTime = System.currentTimeMillis();
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, taskCount).forEach(i -> {
                executor.submit(() -> {
                    // 간단한 작업 시뮬레이션
                    try {
                        Thread.sleep(Duration.ofMillis(10));
                        counter.incrementAndGet();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    return i;
                });
            });
        } // executor.close() 자동 호출
        
        long endTime = System.currentTimeMillis();
        System.out.println("완료된 작업 수: " + counter.get());
        System.out.println("소요 시간: " + (endTime - startTime) + "ms");
        
        // 예제 4: 가상 스레드 특성 비교
        Thread platformThread = Thread.ofPlatform().start(() -> {
            System.out.println("Platform Thread: " + Thread.currentThread());
        });
        
        Thread virtualThread = Thread.ofVirtual().start(() -> {
            System.out.println("Virtual Thread: " + Thread.currentThread());
        });
        
        platformThread.join();
        virtualThread.join();
        
        // 예제 5: 가상 스레드 실행 상태 확인
        System.out.println("\n가상 스레드 실행 상태 확인:");
        
        Thread vThread = Thread.startVirtualThread(() -> {
            System.out.println("가상 스레드 내부: " + Thread.currentThread());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        
        System.out.println("가상 스레드 상태: " + vThread.getState());
        vThread.join();
    }
}
