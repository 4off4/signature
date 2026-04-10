package goods.au.sha256.service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StringDecodeUtil {
    public static void main(String[] args) {
        // 입력을 받기 위한 Scanner 객체 생성
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== URL 인코딩 문자열 디코더 ===");
        System.out.println("변환할 문자열을 입력하고 Enter를 누르세요 (종료하려면 'exit' 입력):");

        while (true) {
            System.out.print("\n입력: ");
            String input = scanner.nextLine();

            // 'exit' 입력 시 프로그램 종료
            if ("exit".equalsIgnoreCase(input)) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                // URL 디코딩 수행
                String decoded = URLDecoder.decode(input, StandardCharsets.UTF_8.name());
                System.out.println("결과: " + decoded);
            } catch (Exception e) {
                System.out.println("오류: 올바른 인코딩 형식이 아닙니다. (" + e.getMessage() + ")");
            }
        }

        scanner.close();
    }
}
