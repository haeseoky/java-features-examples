package java11;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Java 11 HTTP 클라이언트 API 예제
 * 
 * Java 11에서는 HTTP 클라이언트 API가 표준화되었습니다.
 * 이전에는 HttpURLConnection을 사용하거나 서드파티 라이브러리를 사용해야 했지만,
 * 이제는 자바 표준 라이브러리의 일부로 HTTP 요청을 처리할 수 있습니다.
 */
public class HttpClientExample {

    public static void main(String[] args) throws Exception {
        System.out.println("Java 11 HTTP 클라이언트 API 예제");
        
        // 예제 1: 기본 GET 요청
        simpleGetRequest();
        
        // 예제 2: POST 요청
        postRequest();
        
        // 예제 3: 비동기 요청
        asyncRequest();
        
        // 예제 4: 타임아웃 설정
        withTimeoutRequest();
        
        // 예제 5: 헤더 추가 및 리다이렉트 처리
        headersAndRedirectRequest();
    }
    
    /**
     * 예제 1: 기본 GET 요청
     */
    private static void simpleGetRequest() throws IOException, InterruptedException {
        System.out.println("\n=== 기본 GET 요청 ===");
        
        // HttpClient 생성
        HttpClient client = HttpClient.newHttpClient();
        
        // HttpRequest 생성
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .GET() // GET 메서드 지정 (기본값이므로 생략 가능)
                .build();
        
        // 요청 전송 및 응답 수신
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        // 응답 출력
        System.out.println("상태 코드: " + response.statusCode());
        System.out.println("응답 헤더: " + response.headers());
        System.out.println("응답 본문 (일부): " + response.body().substring(0, Math.min(response.body().length(), 200)) + "...");
    }
    
    /**
     * 예제 2: POST 요청
     */
    private static void postRequest() throws IOException, InterruptedException {
        System.out.println("\n=== POST 요청 ===");
        
        // 요청 본문 데이터
        String requestBody = "{\"name\":\"홍길동\",\"age\":30}";
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("상태 코드: " + response.statusCode());
        System.out.println("응답 본문 (일부): " + response.body().substring(0, Math.min(response.body().length(), 200)) + "...");
    }
    
    /**
     * 예제 3: 비동기 요청
     */
    private static void asyncRequest() throws ExecutionException, InterruptedException {
        System.out.println("\n=== 비동기 요청 ===");
        
        HttpClient client = HttpClient.newHttpClient();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/delay/1")) // 의도적으로 지연된 응답
                .build();
        
        System.out.println("비동기 요청 전송 중...");
        
        // 비동기 요청 전송
        CompletableFuture<HttpResponse<String>> futureResponse = 
                client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        
        // 비동기 처리 콜백
        futureResponse.thenAccept(response -> {
            System.out.println("비동기 응답 수신 완료!");
            System.out.println("상태 코드: " + response.statusCode());
            System.out.println("응답 헤더: " + response.headers().firstValue("content-type").orElse(""));
        });
        
        // 추가 작업을 할 수 있음
        System.out.println("다른 작업을 수행하는 중...");
        
        // 결과 대기 (실제 앱에서는 다른 작업과 조합하여 사용)
        HttpResponse<String> response = futureResponse.get();
        System.out.println("비동기 요청 완료, 응답 본문 길이: " + response.body().length());
    }
    
    /**
     * 예제 4: 타임아웃 설정
     */
    private static void withTimeoutRequest() throws IOException, InterruptedException {
        System.out.println("\n=== 타임아웃 설정 ===");
        
        // HttpClient 생성 시 타임아웃 설정
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(5))
                .build();
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/get"))
                .timeout(Duration.ofSeconds(10)) // 요청별 타임아웃 설정
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("상태 코드: " + response.statusCode());
        System.out.println("응답 수신 완료 (타임아웃 미발생)");
        
        // 매우 짧은 타임아웃으로 예외 상황 시뮬레이션
        try {
            HttpRequest timeoutRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://httpbin.org/delay/3")) // 3초 지연
                    .timeout(Duration.ofMillis(100)) // 100ms 타임아웃 (매우 짧음)
                    .build();
            
            client.send(timeoutRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("예상치 못한 성공"); // 이 코드는 실행되지 않아야 함
        } catch (Exception e) {
            System.out.println("예상된 타임아웃 예외 발생: " + e.getClass().getSimpleName());
        }
    }
    
    /**
     * 예제 5: 헤더 추가 및 리다이렉트 처리
     */
    private static void headersAndRedirectRequest() throws IOException, InterruptedException {
        System.out.println("\n=== 헤더 추가 및 리다이렉트 처리 ===");
        
        // 리다이렉트 정책을 설정한 클라이언트
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL) // 리다이렉트 정책 (NEVER, ALWAYS, NORMAL)
                .build();
        
        // 커스텀 헤더 추가
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/headers"))
                .header("X-Custom-Header", "Custom Value")
                .header("User-Agent", "Java 11 HttpClient Bot")
                .GET()
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("상태 코드: " + response.statusCode());
        System.out.println("응답 본문: " + response.body());
        
        // 리다이렉트 예제
        HttpRequest redirectRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://httpbin.org/redirect/1")) // 1회 리다이렉트
                .build();
        
        HttpResponse<String> redirectResponse = client.send(redirectRequest, HttpResponse.BodyHandlers.ofString());
        
        System.out.println("\n리다이렉트 후 최종 상태 코드: " + redirectResponse.statusCode());
        System.out.println("리다이렉트 후 최종 URI: " + redirectResponse.uri());
        System.out.println("이전 응답 정보: " + redirectResponse.previousResponse().isPresent());
    }
}
