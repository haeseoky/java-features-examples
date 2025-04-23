package java8;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

/**
 * Java 8 날짜와 시간 API 예제
 * 
 * Java 8에서는 java.time 패키지가 추가되어 날짜와 시간 처리가 크게 개선되었습니다.
 * 기존의 java.util.Date와 java.util.Calendar의 단점을 보완한 새로운 API입니다.
 */
public class DateTimeApiExample {

    public static void main(String[] args) {
        // 예제 1: LocalDate, LocalTime, LocalDateTime
        localDateTimeExample();
        
        // 예제 2: Instant, Duration, Period
        instantDurationPeriodExample();
        
        // 예제 3: ZonedDateTime과 시간대 처리
        zonedDateTimeExample();
        
        // 예제 4: 날짜 조정과 계산
        dateCalculationsExample();
        
        // 예제 5: 날짜와 시간 포맷팅
        dateFormattingExample();
    }
    
    /**
     * 예제 1: LocalDate, LocalTime, LocalDateTime 클래스
     * 
     * 타임존이 없는 날짜와 시간을 표현하는 클래스입니다.
     */
    private static void localDateTimeExample() {
        System.out.println("\n=== LocalDate, LocalTime, LocalDateTime 예제 ===");
        
        // 현재 날짜 구하기
        LocalDate today = LocalDate.now();
        System.out.println("오늘 날짜: " + today);
        
        // 특정 날짜 생성하기
        LocalDate birthDay = LocalDate.of(1990, 1, 1);
        System.out.println("생년월일: " + birthDay);
        
        // 날짜 정보 얻기
        System.out.println("\n날짜 정보:");
        System.out.println("연도: " + today.getYear());
        System.out.println("월: " + today.getMonthValue());
        System.out.println("일: " + today.getDayOfMonth());
        System.out.println("요일: " + today.getDayOfWeek());
        System.out.println("월의 길이: " + today.lengthOfMonth() + "일");
        System.out.println("올해는 윤년인가? " + today.isLeapYear());
        
        // 현재 시간 구하기
        LocalTime now = LocalTime.now();
        System.out.println("\n현재 시간: " + now);
        
        // 특정 시간 생성하기
        LocalTime lunchTime = LocalTime.of(12, 30);
        System.out.println("점심 시간: " + lunchTime);
        
        // 시간 정보 얻기
        System.out.println("\n시간 정보:");
        System.out.println("시: " + now.getHour());
        System.out.println("분: " + now.getMinute());
        System.out.println("초: " + now.getSecond());
        System.out.println("나노초: " + now.getNano());
        
        // 현재 날짜와 시간 구하기
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("\n현재 날짜와 시간: " + dateTime);
        
        // 날짜와 시간 결합하기
        LocalDateTime meetingDateTime = LocalDateTime.of(today, lunchTime);
        System.out.println("미팅 날짜와 시간: " + meetingDateTime);
        
        // LocalDateTime에서 날짜와 시간 추출하기
        LocalDate meetingDate = meetingDateTime.toLocalDate();
        LocalTime meetingTime = meetingDateTime.toLocalTime();
        System.out.println("미팅 날짜: " + meetingDate);
        System.out.println("미팅 시간: " + meetingTime);
    }
    
    /**
     * 예제 2: Instant, Duration, Period 클래스
     * 
     * Instant: 기계가 이해하는 시간 (에포크 타임, 1970-01-01T00:00:00Z 부터의 나노초)
     * Duration: 두 시간 사이의 간격
     * Period: 두 날짜 사이의 간격
     */
    private static void instantDurationPeriodExample() {
        System.out.println("\n=== Instant, Duration, Period 예제 ===");
        
        // Instant
        Instant now = Instant.now();
        System.out.println("현재 Instant: " + now);
        System.out.println("에포크 초: " + now.getEpochSecond());
        
        Instant oneHourLater = now.plusSeconds(3600);
        System.out.println("1시간 후 Instant: " + oneHourLater);
        
        // Duration - 시간 기반 간격
        Duration duration = Duration.between(now, oneHourLater);
        System.out.println("\n두 시간 사이의 간격:");
        System.out.println("초 단위: " + duration.getSeconds());
        System.out.println("분 단위: " + duration.toMinutes());
        System.out.println("시간 단위: " + duration.toHours());
        
        // 직접 Duration 생성
        Duration oneDay = Duration.ofDays(1);
        Duration oneMin = Duration.ofMinutes(1);
        Duration tenSeconds = Duration.ofSeconds(10);
        System.out.println("\n직접 생성한 Duration:");
        System.out.println("1일: " + oneDay);
        System.out.println("1분: " + oneMin);
        System.out.println("10초: " + tenSeconds);
        
        // Period - 날짜 기반 간격
        LocalDate today = LocalDate.now();
        LocalDate nextYear = today.plusYears(1);
        
        Period period = Period.between(today, nextYear);
        System.out.println("\n두 날짜 사이의 간격:");
        System.out.println("연도: " + period.getYears());
        System.out.println("월: " + period.getMonths());
        System.out.println("일: " + period.getDays());
        
        // 직접 Period 생성
        Period twoMonthsTenDays = Period.of(0, 2, 10);
        System.out.println("\n2개월 10일의 기간: " + twoMonthsTenDays);
        
        // Period와 Duration 응용
        LocalDate birthDate = LocalDate.of(1990, 1, 1);
        LocalDate currentDate = LocalDate.now();
        
        Period age = Period.between(birthDate, currentDate);
        System.out.println("\n만 나이: " + age.getYears() + "세 " + age.getMonths() + "개월 " + age.getDays() + "일");
        
        // 두 시간 사이의 소요 시간
        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(17, 30);
        
        Duration workDuration = Duration.between(startTime, endTime);
        System.out.println("\n근무 시간: " + workDuration.toHours() + "시간 " 
                + (workDuration.toMinutes() % 60) + "분");
    }
    
    /**
     * 예제 3: ZonedDateTime과 시간대 처리
     * 
     * 타임존이 있는 날짜와 시간을 표현합니다.
     */
    private static void zonedDateTimeExample() {
        System.out.println("\n=== ZonedDateTime과 시간대 처리 예제 ===");
        
        // 현재 시스템 기본 시간대의 날짜와 시간
        ZonedDateTime nowInSystemTz = ZonedDateTime.now();
        System.out.println("현재 시간대 날짜/시간: " + nowInSystemTz);
        
        // 특정 시간대의 현재 날짜와 시간
        ZonedDateTime nowInSeoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        ZonedDateTime nowInNY = ZonedDateTime.now(ZoneId.of("America/New_York"));
        ZonedDateTime nowInLondon = ZonedDateTime.now(ZoneId.of("Europe/London"));
        
        System.out.println("\n각 도시의 현재 시간:");
        System.out.println("서울: " + nowInSeoul.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("뉴욕: " + nowInNY.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("런던: " + nowInLondon.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        
        // 시간대 전환
        LocalDateTime localDateTime = LocalDateTime.of(2023, 1, 1, 12, 0);
        ZonedDateTime seoulDateTime = localDateTime.atZone(ZoneId.of("Asia/Seoul"));
        
        System.out.println("\n서울 시간: " + seoulDateTime);
        
        ZonedDateTime nyDateTime = seoulDateTime.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("뉴욕 시간 (같은 시점): " + nyDateTime);
        
        // ZoneOffset 사용
        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneOffset.of("+09:00"));
        System.out.println("\n+09:00 오프셋 시간: " + offsetDateTime);
        
        // 사용 가능한 모든 시간대 확인
        System.out.println("\n사용 가능한 시간대 일부:");
        ZoneId.getAvailableZoneIds().stream()
                .filter(zoneId -> zoneId.startsWith("Asia") || zoneId.startsWith("Europe"))
                .limit(5)
                .forEach(System.out::println);
    }
    
    /**
     * 예제 4: 날짜 조정과 계산
     * 
     * 날짜와 시간 클래스에서 제공하는 다양한 연산 메서드와 TemporalAdjusters를 활용한 예제입니다.
     */
    private static void dateCalculationsExample() {
        System.out.println("\n=== 날짜 조정과 계산 예제 ===");
        
        LocalDate today = LocalDate.now();
        
        // 날짜 더하기/빼기
        LocalDate tomorrow = today.plusDays(1);
        LocalDate yesterday = today.minusDays(1);
        LocalDate nextMonth = today.plusMonths(1);
        LocalDate previousMonth = today.minusMonths(1);
        
        System.out.println("오늘: " + today);
        System.out.println("내일: " + tomorrow);
        System.out.println("어제: " + yesterday);
        System.out.println("다음 달 같은 날: " + nextMonth);
        System.out.println("지난 달 같은 날: " + previousMonth);
        
        // 일, 월, 년 단위 조정
        LocalDateTime now = LocalDateTime.now();
        System.out.println("\n현재 시간: " + now);
        System.out.println("3시간 30분 후: " + now.plusHours(3).plusMinutes(30));
        System.out.println("2주 전: " + now.minusWeeks(2));
        
        // ChronoUnit을 사용한 날짜 계산
        System.out.println("\nChronoUnit을 사용한 날짜 계산:");
        System.out.println("10일 후: " + today.plus(10, ChronoUnit.DAYS));
        System.out.println("20주 후: " + today.plus(20, ChronoUnit.WEEKS));
        System.out.println("15개월 전: " + today.minus(15, ChronoUnit.MONTHS));
        
        // TemporalAdjusters 사용
        System.out.println("\nTemporalAdjusters 사용:");
        
        // 이번 달의 첫 번째 날
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("이번 달의 첫 번째 날: " + firstDayOfMonth);
        
        // 이번 달의 마지막 날
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("이번 달의 마지막 날: " + lastDayOfMonth);
        
        // 다음 달의 첫 번째 월요일
        LocalDate firstMondayOfNextMonth = today.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY))
                .plusMonths(1);
        System.out.println("다음 달의 첫 번째 월요일: " + firstMondayOfNextMonth);
        
        // 이번 해의 마지막 날
        LocalDate lastDayOfYear = today.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("이번 해의 마지막 날: " + lastDayOfYear);
        
        // 다음 번째 특정 요일 (다음 금요일)
        LocalDate nextFriday = today.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println("다음 금요일: " + nextFriday);
        
        // 두 날짜 사이의 일수 계산
        LocalDate futureDate = LocalDate.of(2024, 12, 31);
        long daysBetween = ChronoUnit.DAYS.between(today, futureDate);
        System.out.println("\n오늘부터 2024년 12월 31일까지: " + daysBetween + "일");
        
        // 생일까지 남은 날짜 계산
        LocalDate birthday = LocalDate.of(today.getYear(), 8, 15);
        if (birthday.isBefore(today) || birthday.isEqual(today)) {
            birthday = birthday.plusYears(1);
        }
        long daysUntilBirthday = ChronoUnit.DAYS.between(today, birthday);
        System.out.println("다음 생일까지 남은 날짜: " + daysUntilBirthday + "일");
    }
    
    /**
     * 예제 5: 날짜와 시간 포맷팅
     * 
     * DateTimeFormatter를 사용하여 날짜와 시간을 다양한 형식으로 표시하는 예제입니다.
     */
    private static void dateFormattingExample() {
        System.out.println("\n=== 날짜와 시간 포맷팅 예제 ===");
        
        LocalDateTime now = LocalDateTime.now();
        
        // 기본 제공 포맷터 사용
        System.out.println("\n기본 제공 포맷터:");
        System.out.println("ISO_LOCAL_DATE: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("ISO_LOCAL_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println("ISO_LOCAL_DATE_TIME: " + now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        
        // 패턴을 사용한 포맷팅
        System.out.println("\n커스텀 패턴 포맷터:");
        
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        System.out.println("한국식 날짜 형식: " + now.format(formatter1));
        
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println("날짜 및 시간: " + now.format(formatter2));
        
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 E요일", Locale.KOREAN);
        System.out.println("요일 포함 한국어 형식: " + now.format(formatter3));
        
        DateTimeFormatter formatter4 = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
        System.out.println("영어 전체 형식: " + now.format(formatter4));
        
        // 날짜/시간 파싱
        System.out.println("\n문자열에서 날짜/시간 파싱:");
        
        String dateStr = "2023-06-15";
        LocalDate parsedDate = LocalDate.parse(dateStr);
        System.out.println("기본 형식으로 파싱된 날짜: " + parsedDate);
        
        String dateTimeStr = "2023/06/15 14:30:00";
        LocalDateTime parsedDateTime = LocalDateTime.parse(
                dateTimeStr, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
        System.out.println("커스텀 형식으로 파싱된 날짜/시간: " + parsedDateTime);
        
        // 날짜 정보 포맷팅
        System.out.println("\n다양한 날짜 정보 포맷팅:");
        
        DateTimeFormatter complexFormatter = DateTimeFormatter.ofPattern(
                "yyyy년 MM월 dd일 (E) a hh시 mm분 ss초", Locale.KOREAN);
        System.out.println("복합 형식: " + now.format(complexFormatter));
        
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        DateTimeFormatter zoneFormatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss z VV", Locale.KOREAN);
        System.out.println("시간대 정보 포함: " + zonedDateTime.format(zoneFormatter));
        
        // 시간 영역별 포맷팅
        System.out.println("\n시간 영역별 포맷팅:");
        
        DateTimeFormatter quarterFormatter = DateTimeFormatter.ofPattern("yyyy년 QQQ", Locale.KOREAN);
        System.out.println("분기: " + now.format(quarterFormatter));
        
        DateTimeFormatter weekFormatter = DateTimeFormatter.ofPattern("yyyy년 w주차", Locale.KOREAN);
        System.out.println("주차: " + now.format(weekFormatter));
    }
}
