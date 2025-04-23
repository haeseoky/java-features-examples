package java11;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java 11 파일 처리 관련 새로운 메서드 예제
 * 
 * Java 11에서는 Files 클래스에 여러 유용한 메서드들이 추가되었습니다.
 * 파일을 문자열로 읽고 쓰는 작업이 더 쉬워졌습니다.
 */
public class FilesExample {

    public static void main(String[] args) throws IOException {
        System.out.println("Java 11 Files 클래스 새 메서드 예제");
        
        // 테스트 파일 생성
        Path tempFile = Files.createTempFile("java11-example", ".txt");
        
        // 예제 1: 문자열을 파일에 쓰기 (writeString)
        writeStringExample(tempFile);
        
        // 예제 2: 파일을 문자열로 읽기 (readString)
        readStringExample(tempFile);
        
        // 예제 3: 여러 줄을 파일에 쓰기 (write + List)
        writeLines(tempFile);
        
        // 예제 4: 여러 줄 읽기 (readAllLines)
        readLines(tempFile);
        
        // 예제 5: 이전 버전과 새 버전 비교
        compareOldAndNew(tempFile);
        
        // 임시 파일 정리
        Files.deleteIfExists(tempFile);
        System.out.println("\n임시 파일 삭제 완료: " + tempFile);
    }
    
    /**
     * 예제 1: 문자열을 파일에 쓰기 (writeString)
     */
    private static void writeStringExample(Path file) throws IOException {
        System.out.println("\n=== 문자열을 파일에 쓰기 ===");
        
        String content = "안녕하세요!\nJava 11의 Files.writeString 메서드 예제입니다.\n";
        
        // 새 메서드: writeString - 문자열을 파일에 직접 쓰기
        Files.writeString(file, content, StandardOpenOption.WRITE);
        
        System.out.println("파일에 문자열 작성 완료: " + file);
        System.out.println("파일 크기: " + Files.size(file) + " bytes");
    }
    
    /**
     * 예제 2: 파일을 문자열로 읽기 (readString)
     */
    private static void readStringExample(Path file) throws IOException {
        System.out.println("\n=== 파일을 문자열로 읽기 ===");
        
        // 새 메서드: readString - 파일 내용을 단일 문자열로 직접 읽기
        String content = Files.readString(file);
        
        System.out.println("파일 내용 읽기 완료:");
        System.out.println("-----");
        System.out.println(content);
        System.out.println("-----");
    }
    
    /**
     * 예제 3: 여러 줄을 파일에 쓰기
     */
    private static void writeLines(Path file) throws IOException {
        System.out.println("\n=== 여러 줄을 파일에 쓰기 ===");
        
        List<String> lines = List.of(
            "첫 번째 줄 - Java 11 예제",
            "두 번째 줄 - Files 클래스의 새 메서드",
            "세 번째 줄 - 여러 줄 쓰기 테스트",
            "네 번째 줄 - 2023년 작성"
        );
        
        // 여러 줄 쓰기 - 기존 메서드
        Files.write(file, lines, StandardOpenOption.TRUNCATE_EXISTING);
        
        System.out.println("파일에 " + lines.size() + "줄 작성 완료");
    }
    
    /**
     * 예제 4: 여러 줄 읽기
     */
    private static void readLines(Path file) throws IOException {
        System.out.println("\n=== 여러 줄 읽기 ===");
        
        // 모든 줄 읽기
        List<String> lines = Files.readAllLines(file);
        
        System.out.println("파일에서 읽은 줄 수: " + lines.size());
        System.out.println("내용:");
        
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }
        
        // 스트림으로 읽기 및 필터링
        System.out.println("\n'Java'가 포함된 줄만 필터링:");
        List<String> javaLines = Files.lines(file)
                .filter(line -> line.contains("Java"))
                .collect(Collectors.toList());
        
        javaLines.forEach(System.out::println);
    }
    
    /**
     * 예제 5: 이전 버전과 새 버전 비교
     */
    private static void compareOldAndNew(Path file) throws IOException {
        System.out.println("\n=== 이전 버전과 새 버전 비교 ===");
        
        // 테스트용 내용 작성
        String testContent = "Java 11 Files API 테스트";
        Files.writeString(file, testContent);
        
        System.out.println("1. 파일 읽기 - 이전 방식 vs 새 방식");
        
        // 이전 방식 (Java 8)
        String oldWayContent = new String(Files.readAllBytes(file));
        System.out.println("   - 이전 방식: " + oldWayContent);
        
        // 새 방식 (Java 11)
        String newWayContent = Files.readString(file);
        System.out.println("   - 새 방식: " + newWayContent);
        
        System.out.println("\n2. 파일 쓰기 - 이전 방식 vs 새 방식");
        
        // 임시 파일 두 개 생성
        Path oldWayFile = Files.createTempFile("old-way", ".txt");
        Path newWayFile = Files.createTempFile("new-way", ".txt");
        
        String contentToWrite = "파일에 쓰기 테스트\n여러 줄 쓰기";
        
        // 이전 방식
        Files.write(oldWayFile, contentToWrite.getBytes());
        System.out.println("   - 이전 방식으로 쓰기 완료: " + oldWayFile);
        
        // 새 방식
        Files.writeString(newWayFile, contentToWrite);
        System.out.println("   - 새 방식으로 쓰기 완료: " + newWayFile);
        
        // 두 파일의 내용이 동일한지 확인
        boolean areEqual = Files.mismatch(oldWayFile, newWayFile) == -1;
        System.out.println("   - 두 파일의 내용이 동일한가? " + areEqual);
        
        // 임시 파일 정리
        Files.deleteIfExists(oldWayFile);
        Files.deleteIfExists(newWayFile);
    }
}
