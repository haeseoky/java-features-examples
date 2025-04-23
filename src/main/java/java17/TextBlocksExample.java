package java17;

/**
 * Java 15에서 도입되고 Java 17 LTS에서 완전히 지원되는 텍스트 블록(Text Blocks) 예제
 * 
 * 텍스트 블록은 여러 줄의 문자열을 더 읽기 쉽고 유지 관리하기 쉽게 작성할 수 있게 해주는 기능입니다.
 * 특히 HTML, JSON, SQL 등의 코드를 자바 코드 내에 포함시킬 때 유용합니다.
 */
public class TextBlocksExample {

    public static void main(String[] args) {
        // 예제 1: 기본 텍스트 블록
        String textBlock = """
                안녕하세요,
                여러 줄에 걸친
                텍스트 블록입니다.""";
        
        System.out.println("=== 기본 텍스트 블록 ===");
        System.out.println(textBlock);
        
        // 기존 방식과 비교
        String oldWay = "안녕하세요,\n" +
                        "여러 줄에 걸친\n" +
                        "문자열입니다.";
        
        System.out.println("\n=== 기존 방식 ===");
        System.out.println(oldWay);
        
        // 예제 2: HTML 코드
        String html = """
                <!DOCTYPE html>
                <html>
                    <head>
                        <title>텍스트 블록 예제</title>
                    </head>
                    <body>
                        <h1>Java 17 텍스트 블록</h1>
                        <p>여러 줄의 HTML을 쉽게 작성할 수 있습니다.</p>
                    </body>
                </html>
                """;
        
        System.out.println("\n=== HTML 예제 ===");
        System.out.println(html);
        
        // 예제 3: JSON 형식
        String json = """
                {
                    "name": "홍길동",
                    "age": 30,
                    "address": {
                        "city": "서울",
                        "zipCode": "12345"
                    },
                    "phoneNumbers": [
                        "010-1234-5678",
                        "02-987-6543"
                    ]
                }
                """;
        
        System.out.println("\n=== JSON 예제 ===");
        System.out.println(json);
        
        // 예제 4: SQL 쿼리
        String sql = """
                SELECT e.employee_id, e.first_name, e.last_name, d.department_name
                FROM employees e
                JOIN departments d ON e.department_id = d.department_id
                WHERE e.hire_date > '2020-01-01'
                  AND d.location_id IN (1000, 1100, 1200)
                ORDER BY e.last_name, e.first_name;
                """;
        
        System.out.println("\n=== SQL 예제 ===");
        System.out.println(sql);
        
        // 예제 5: 들여쓰기 제어
        String indented = """
                    첫 번째 줄은 들여쓰기가 적용됩니다.
                        두 번째 줄은 더 많이 들여쓰기가 됩니다.
                    다시 원래 들여쓰기로 돌아옵니다.
                """;
        
        System.out.println("\n=== 들여쓰기 예제 ===");
        System.out.println(indented);
        
        // 들여쓰기 제거 예제
        String aligned = """
                첫 번째 줄
                두 번째 줄
                세 번째 줄
                """;
        
        System.out.println("\n=== 들여쓰기 정렬 예제 ===");
        System.out.println(aligned);
        
        // 예제 6: 공백 제어 및 이스케이프 시퀀스
        String spaces = """
                첫 번째 줄 끝에 공백이 있음.     \s
                두 번째 줄 끝에 공백이 없음.
                세 번째 줄은 "따옴표"와 \\백슬래시\\를 포함합니다.
                """;
        
        System.out.println("\n=== 공백 및 이스케이프 예제 ===");
        System.out.println(spaces);
        System.out.println("첫 번째 줄 길이: " + spaces.split("\n")[0].length());
        
        // 예제 7: 문자열 포맷팅과 결합
        String name = "홍길동";
        int age = 30;
        String formattedText = """
                사용자 정보:
                이름: %s
                나이: %d
                """.formatted(name, age);
        
        System.out.println("\n=== 포맷팅 예제 ===");
        System.out.println(formattedText);
        
        // 예제 8: 줄 결합
        String combined = """
                이 줄은 \
                하나의 줄로 \
                결합됩니다.""";
        
        System.out.println("\n=== 줄 결합 예제 ===");
        System.out.println(combined);
        
        // 예제 9: 실제 사용 사례 - 이메일 템플릿
        String recipient = "김고객";
        String product = "Java 17 가이드북";
        String emailTemplate = """
                제목: %s님, %s 구매에 감사드립니다.
                
                %s님 안녕하세요,
                
                저희 서점에서 "%s"를 구매해 주셔서 진심으로 감사드립니다.
                구매하신 상품은 1-2일 내에 발송될 예정입니다.
                
                문의사항이 있으시면 언제든지 회신해 주세요.
                
                감사합니다.
                자바서점 드림
                """.formatted(recipient, product, recipient, product);
        
        System.out.println("\n=== 이메일 템플릿 예제 ===");
        System.out.println(emailTemplate);
        
        // 예제 10: 여러 줄 주석 작성용 텍스트 블록
        String documentation = """
                /**
                 * 이 메서드는 주어진 숫자의 제곱을 계산합니다.
                 *
                 * @param number 제곱할 숫자
                 * @return 숫자의 제곱 값
                 * @throws IllegalArgumentException 숫자가 음수인 경우
                 */
                """;
        
        System.out.println("\n=== 문서화 예제 ===");
        System.out.println(documentation);
    }
}
