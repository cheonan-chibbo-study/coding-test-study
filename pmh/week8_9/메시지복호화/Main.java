package week8_9.메시지복호화;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();

        String filePath = "C:\\Users\\ParkMinHyun\\Desktop\\nossi_gui_java_window_week7\\nossi_gui_java_window_week7\\tc1.txt";
        List<String> lines = Files.readAllLines(Path.of(filePath));

        String m = "";
        String k = "";
        String expected = "";
        int tcNum = 0;
        int passCount = 0;

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("testcase")) {
                tcNum++;
            } else if (line.startsWith("m=")) {
                m = extractValue(line);
            } else if (line.startsWith("k=")) {
                k = extractValue(line);
            } else if (line.startsWith("result=")) {
                expected = extractValue(line);

                String actual = sol.solution(m, k);
                boolean pass = actual.equals(expected);

                if (pass) passCount++;

                System.out.println("[" + tcNum + "] " + (pass ? "PASS" : "FAIL"));
                System.out.println("m 길이 = " + m.length());
                System.out.println("k 길이 = " + k.length());
                System.out.println("expected 길이 = " + expected.length());
                System.out.println("actual 길이 = " + actual.length());

                if (!pass) {
                    System.out.println("m 앞부분 = " + preview(m));
                    System.out.println("k 앞부분 = " + preview(k));
                    System.out.println("expected 앞부분 = " + preview(expected));
                    System.out.println("actual 앞부분 = " + preview(actual));
                }

                System.out.println();
            }
        }

        System.out.println("총 통과: " + passCount + " / " + tcNum);
    }

    private static String extractValue(String line) {
        int firstQuote = line.indexOf("\"");
        int lastQuote = line.lastIndexOf("\"");
        return line.substring(firstQuote + 1, lastQuote);
    }

    private static String preview(String s) {
        if (s.length() <= 50) return s;
        return s.substring(0, 50) + "...";
    }
}
