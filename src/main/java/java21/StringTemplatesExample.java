package java21;

import java.util.List;
import java.util.Map;

/**
 * Java 21 문자열 템플릿(String Templates) 예제 - 프리뷰 기능
 * 
 * 문자열 템플릿은 문자열 보간(interpolation)을 지원하여 변수를 문자열에 쉽게 삽입할 수 있게 합니다.
 * `STR."..."` 형식으로 사용하며, \(...)로 표현식을 삽입할 수 있습니다.
 * 
 * 참고: 이 기능을 사용하려면 --enable-preview 플래그와 함께 Java 21을 사용해야 합니다.
 * javac --release 21 --enable-preview StringTemplatesExample.java
 * java --enable-preview StringTemplatesExample
 */
public class StringTemplatesExample {

    record Person(String name, int age, String job) {}

    public static void main(String[] args) {
        // 기본 변수 사용을 위해 static import 필요
        // import static java.lang.StringTemplate.STR;
        
        // 예제 구현에서는 일반 문자열 연결을 사용하여 동일한 결과를 보여줍니다
        
        // 예제 1: 기본 문자열 템플릿
        String name = "홍길동";
        int age = 30;
        
        // 문자열 템플릿 사용 (실제로는 아래와 같이 작성)
        // String greeting = STR."안녕하세요, \(name)님! 당신은 \(age)세입니다.";
        
        // 일반 문자열 연결
        String greeting = "안녕하세요, " + name + "님! 당신은 " + age + "세입니다.";
        System.out.println(greeting);
        
        // 예제 2: 표현식 사용
        int a = 10;
        int b = 20;
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        // String calculation = STR."\(a) + \(b) = \(a + b)";
        
        // 일반 문자열 연결
        String calculation = a + " + " + b + " = " + (a + b);
        System.out.println(calculation);
        
        // 예제 3: 여러 줄 문자열
        Person person = new Person("김철수", 25, "개발자");
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        /*
        String personInfo = STR."""
            사용자 정보:
              이름: \(person.name())
              나이: \(person.age())
              직업: \(person.job())
            """;
        */
        
        // 일반 문자열 블록
        String personInfo = """
            사용자 정보:
              이름: """ + person.name() + """
              나이: """ + person.age() + """
              직업: """ + person.job() + """
            """;
        System.out.println(personInfo);
        
        // 예제 4: 컬렉션과 함께 사용
        List<String> fruits = List.of("사과", "바나나", "오렌지");
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        // String fruitList = STR."과일 목록: \(String.join(", ", fruits))";
        
        // 일반 문자열 연결
        String fruitList = "과일 목록: " + String.join(", ", fruits);
        System.out.println(fruitList);
        
        // 예제 5: 조건식 사용
        boolean isStudent = true;
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        // String status = STR."상태: \(isStudent ? "학생" : "비학생")";
        
        // 일반 문자열 연결
        String status = "상태: " + (isStudent ? "학생" : "비학생");
        System.out.println(status);
        
        // 예제 6: 포맷팅이 필요한 경우
        double price = 12345.6789;
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        // String formattedPrice = STR."가격: \(String.format("%.2f", price))원";
        
        // 일반 문자열 연결
        String formattedPrice = "가격: " + String.format("%.2f", price) + "원";
        System.out.println(formattedPrice);
        
        // 예제 7: 커스텀 템플릿 프로세서 (실제 코드에서는 아래와 같이 작성)
        /*
        // JSON 포맷팅을 위한 템플릿 프로세서
        String json = FMT."{ \"name\": \"\(name)\", \"age\": \(age) }";
        
        // SQL 쿼리 생성을 위한 템플릿 프로세서
        String tableName = "users";
        String condition = "age > 18";
        String query = SQL."SELECT * FROM \(tableName) WHERE \(condition)";
        */
        
        // 일반 문자열을 사용한 동등한 예제:
        String json = "{ \"name\": \"" + name + "\", \"age\": " + age + " }";
        System.out.println("JSON: " + json);
        
        String tableName = "users";
        String condition = "age > 18";
        String query = "SELECT * FROM " + tableName + " WHERE " + condition;
        System.out.println("SQL: " + query);
        
        // 예제 8: 프로그래밍 언어 코드 생성
        String className = "MyClass";
        List<String> methods = List.of("start", "process", "stop");
        
        // 문자열 템플릿 (실제로는 아래와 같이 작성)
        /*
        String javaCode = STR."""
            public class \(className) {
                \(methods.stream()
                    .map(method -> "public void " + method + "() {}")
                    .collect(java.util.stream.Collectors.joining("\n    ")))
            }
            """;
        */
        
        // 일반 문자열 블록 사용
        StringBuilder methodsStr = new StringBuilder();
        for (String method : methods) {
            if (methodsStr.length() > 0) methodsStr.append("\n    ");
            methodsStr.append("public void ").append(method).append("() {}");
        }
        
        String javaCode = "public class " + className + " {\n    " 
                + methodsStr + "\n}";
        System.out.println("생성된 Java 코드:");
        System.out.println(javaCode);
    }
}
