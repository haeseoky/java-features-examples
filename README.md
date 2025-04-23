# Java 버전별 기능 예제 프로젝트

이 프로젝트는 Java의 다양한 버전에서 추가된 중요한 기능들을 예제 코드로 보여주는 목적으로 만들어졌습니다. Java 8부터 Java 21까지의 주요 기능들을 실제 코드와 함께 학습할 수 있습니다.

## 프로젝트 구조

```
src/main/java/
├── com.ocean.javarelease/
│   └── JavaReleaseApplication.java (스프링 부트 애플리케이션 진입점)
├── java8/
│   ├── DateTimeApiExample.java (날짜 및 시간 API)
│   ├── InterfaceEnhancementsExample.java (인터페이스 개선 기능)
│   └── LambdaExample.java (람다 표현식 및 함수형 인터페이스)
└── java21/
    ├── VirtualThreadsExample.java (가상 스레드)
    └── RecordPatternsExample.java (레코드 패턴)
```

## 주요 기능 설명

### Java 8 기능

#### 1. 람다 표현식과 함수형 인터페이스 (LambdaExample.java)
- 기본 람다 표현식 문법과 사용법
- 함수형 인터페이스 (Predicate, Consumer, Function, Supplier)
- 메서드 레퍼런스 활용
- 실전 예제를 통한 람다 응용
- Optional 클래스 활용

#### 2. 날짜와 시간 API (DateTimeApiExample.java)
- LocalDate, LocalTime, LocalDateTime 클래스
- Instant, Duration, Period 클래스
- ZonedDateTime과 시간대 처리
- 날짜 조정과 계산
- 날짜와 시간 포맷팅

#### 3. 인터페이스 개선 기능 (InterfaceEnhancementsExample.java)
- 기본 메서드(default method)
- 정적 메서드(static method)
- 다중 상속과 기본 메서드 충돌 해결
- 함수형 인터페이스
- 실제 사용 사례

### Java 21 기능

#### 1. 가상 스레드 (VirtualThreadsExample.java)
- 단일 가상 스레드 생성
- Thread.Builder를 사용한 가상 스레드 생성
- 대량의 가상 스레드 생성 및 실행
- 플랫폼 스레드와 가상 스레드 비교
- 가상 스레드 실행 상태 확인

#### 2. 레코드 패턴 (RecordPatternsExample.java)
- 기본 레코드 패턴
- 중첩 레코드 패턴
- switch 표현식과 레코드 패턴 활용
- 복잡한 중첩 패턴
- var 패턴 변수와 when 절 조합

## 실행 방법

이 프로젝트는 Spring Boot 애플리케이션으로 구성되어 있습니다. 다음 명령어로 실행할 수 있습니다:

```bash
./gradlew bootRun
```

각 예제 클래스는 독립적으로 실행 가능하며, 메인 메서드를 포함하고 있습니다. 예제를 개별적으로 실행하려면:

```bash
./gradlew run --args="java8.LambdaExample"
./gradlew run --args="java8.DateTimeApiExample"
./gradlew run --args="java8.InterfaceEnhancementsExample"
./gradlew run --args="java21.VirtualThreadsExample"
./gradlew run --args="java21.RecordPatternsExample"
```

## 기술 스택

- Java 21
- Spring Boot 3.4.4
- Gradle

## 라이센스

MIT License

## 기여 방법

1. 이 저장소를 포크합니다
2. 기능 브랜치를 생성합니다 (`git checkout -b feature/amazing-feature`)
3. 변경 사항을 커밋합니다 (`git commit -m 'Add some amazing feature'`)
4. 브랜치에 푸시합니다 (`git push origin feature/amazing-feature`)
5. Pull Request를 생성합니다