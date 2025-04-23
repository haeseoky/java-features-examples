# Java 버전별 기능 예제 프로젝트

이 프로젝트는 Java의 다양한 버전에서 추가된 중요한 기능들을 예제 코드로 보여주는 목적으로 만들어졌습니다. Java 8부터 Java 25까지의 주요 기능들을 실제 코드와 함께 학습할 수 있습니다.

## 프로젝트 구조

```
src/main/java/
├── com.ocean.javarelease/
│   └── JavaReleaseApplication.java (스프링 부트 애플리케이션 진입점)
├── java8/
│   ├── DateTimeApiExample.java (날짜 및 시간 API)
│   ├── InterfaceEnhancementsExample.java (인터페이스 개선 기능)
│   └── LambdaExample.java (람다 표현식 및 함수형 인터페이스)
├── java11/
│   ├── CollectionExample.java (컬렉션 API 개선)
│   ├── FilesExample.java (Files 클래스 개선)
│   └── HttpClientExample.java (HTTP 클라이언트)
├── java17/
│   ├── InstanceofPatternMatchingExample.java (instanceof 패턴 매칭)
│   ├── RecordExample.java (레코드)
│   ├── SealedClassesExample.java (봉인 클래스)
│   └── TextBlocksExample.java (텍스트 블록)
├── java21/
│   ├── PatternMatchingForSwitchExample.java (스위치 패턴 매칭)
│   ├── RecordPatternsExample.java (레코드 패턴)
│   ├── SequencedCollectionsExample.java (시퀀스 컬렉션)
│   ├── StringTemplatesExample.java (문자열 템플릿)
│   └── VirtualThreadsExample.java (가상 스레드)
└── java25/
    ├── EnhancedPatternMatchingExample.java (향상된 패턴 매칭)
    ├── ForeignMemoryApiExample.java (외부 메모리 API)
    ├── StringTemplatesExample.java (문자열 템플릿 개선)
    ├── StructuredConcurrencyExample.java (구조화된 동시성)
    ├── ValueObjectsExample.java (값 객체)
    └── VectorApiExample.java (벡터 API)
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

### Java 11 기능

#### 1. HTTP 클라이언트 API (HttpClientExample.java)
- 기본 GET/POST 요청 처리
- 비동기 요청 처리
- 타임아웃 설정
- 헤더 추가 및 리다이렉트 처리

#### 2. 컬렉션 API 개선 (CollectionExample.java)
- 컬렉션을 배열로 변환 (toArray 개선)
- List.of(), Set.of(), Map.of() 등 정적 팩토리 메서드
- Predicate.not() 메서드

#### 3. Files 클래스 개선 (FilesExample.java)
- Files.readString() 및 Files.writeString() 메서드
- Path 인터페이스 개선

### Java 17 기능

#### 1. 레코드 (RecordExample.java)
- 불변 데이터 클래스 선언
- 컴팩트 생성자
- 레코드 중첩
- 제네릭 레코드

#### 2. 봉인 클래스 (SealedClassesExample.java)
- 상속 계층 제한
- sealed, non-sealed, final 키워드
- 봉인 인터페이스
- 봉인 클래스와 패턴 매칭

#### 3. 텍스트 블록 (TextBlocksExample.java)
- 여러 줄 문자열 리터럴
- 들여쓰기 및 포맷팅
- 이스케이프 시퀀스

#### 4. instanceof 패턴 매칭 (InstanceofPatternMatchingExample.java)
- 타입 검사와 변수 바인딩 통합
- 불필요한 캐스팅 제거
- 조건문 간소화

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

#### 3. 스위치 패턴 매칭 (PatternMatchingForSwitchExample.java)
- 타입 패턴
- null 값 처리
- when 조건절 활용
- 레코드 패턴과 결합

#### 4. 문자열 템플릿 (StringTemplatesExample.java)
- 보간 표현식
- 포맷팅 템플릿
- 멀티라인 템플릿
- 템플릿 프로세서

#### 5. 시퀀스 컬렉션 (SequencedCollectionsExample.java)
- 순서가 있는 컬렉션 API
- 첫 번째/마지막 요소 접근
- 역순 뷰
- 새로운 메서드: getFirst(), getLast(), addFirst(), addLast()

### Java 25 예상 기능

#### 1. 구조화된 동시성 (StructuredConcurrencyExample.java)
- 작업 범위와 수명주기 관리
- 병렬 작업 조율
- 가상 스레드와의 통합
- 작업 타임아웃 및 취소

#### 2. 향상된 패턴 매칭 (EnhancedPatternMatchingExample.java)
- 레코드 패턴 개선
- 배열 패턴
- 디스트럭처링 패턴

#### 3. 값 객체 (ValueObjectsExample.java)
- 불변 값 타입
- 메모리 최적화
- 기본형 클래스와의 통합

#### 4. 외부 메모리 API (ForeignMemoryApiExample.java)
- 네이티브 메모리 접근
- 비힙 메모리 관리
- 메모리 매핑 최적화

#### 5. 벡터 API (VectorApiExample.java)
- SIMD 명령어 활용
- 벡터 연산 최적화
- 성능 향상 예제

## 실행 방법

이 프로젝트는 Spring Boot 애플리케이션으로 구성되어 있습니다. 다음 명령어로 실행할 수 있습니다:

```bash
./gradlew bootRun
```

각 예제 클래스는 독립적으로 실행 가능하며, 메인 메서드를 포함하고 있습니다. 예제를 개별적으로 실행하려면:

```bash
# Java 8 예제
./gradlew run --args="java8.LambdaExample"
./gradlew run --args="java8.DateTimeApiExample"
./gradlew run --args="java8.InterfaceEnhancementsExample"

# Java 11 예제
./gradlew run --args="java11.HttpClientExample"
./gradlew run --args="java11.CollectionExample"
./gradlew run --args="java11.FilesExample"

# Java 17 예제
./gradlew run --args="java17.RecordExample"
./gradlew run --args="java17.SealedClassesExample"
./gradlew run --args="java17.TextBlocksExample"
./gradlew run --args="java17.InstanceofPatternMatchingExample"

# Java 21 예제
./gradlew run --args="java21.VirtualThreadsExample"
./gradlew run --args="java21.RecordPatternsExample"
./gradlew run --args="java21.PatternMatchingForSwitchExample"
./gradlew run --args="java21.StringTemplatesExample"
./gradlew run --args="java21.SequencedCollectionsExample"

# Java 25 예상 기능 예제
./gradlew run --args="java25.StructuredConcurrencyExample"
./gradlew run --args="java25.EnhancedPatternMatchingExample"
./gradlew run --args="java25.ValueObjectsExample"
./gradlew run --args="java25.ForeignMemoryApiExample"
./gradlew run --args="java25.VectorApiExample"
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