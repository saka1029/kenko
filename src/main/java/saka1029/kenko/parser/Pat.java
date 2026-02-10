package saka1029.kenko.parser;

import java.util.Map;

public class Pat {

    public static final String 空白 = "\\h+";
    public static final String 漢数字 = "[〇一二三四五六七八九十]+";

    static final Map<Character, Integer> KAN2INT = Map.ofEntries(
        Map.entry('百', 100) , Map.entry('十', 10), Map.entry('〇', 0),
        Map.entry('一', 1), Map.entry('二', 2), Map.entry('三', 3), Map.entry('四', 4),
        Map.entry('五', 5), Map.entry('六', 6), Map.entry('七', 7), Map.entry('八', 8),
        Map.entry('九', 9));

    public static int Kan2Int(String s) {
        int total = 0, current = 0;
        for (int i = 0, size = s.length(); i < size; ++i) {
            Integer d = KAN2INT.get(s.charAt(i));
            if (d == null) {
                throw new NumberFormatException();
            } else if (d < 10) {
                current = current * 10 + d;
            } else if (current > 0) {
                total += current * d;
                current = 0;
            } else {
                total += d;
            }
        }
        total += current;
        return total;
    }

    public static String 漢数字正規化(String s) {
        // s = 正規化(s);
        s = s.replaceAll("(^|[+-_x])十([+-_x])", "$110$2");  // 単独の「十」→「10」
        s = s.replaceAll("(^|[+-_x])十$", "$110");           // 単独の「十」→「10」
        s = s.replaceAll("(^|[+-_x])十", "$11");             // 先頭の「十」→「1」
        s = s.replaceAll("十($|[+-_x])", "0$1");               // 末尾の「十」→>「0」
        s = s.replaceAll("十", "");                          // 中間の「十」→「>」
        s = s.replaceAll("一", "1");
        s = s.replaceAll("ニ", "2"); // カナの「ニ」
        s = s.replaceAll("二", "2");
        s = s.replaceAll("三", "3");
        s = s.replaceAll("四", "4");
        s = s.replaceAll("五", "5");
        s = s.replaceAll("六", "6");
        s = s.replaceAll("七", "7");
        s = s.replaceAll("八", "8");
        s = s.replaceAll("九", "9");
        return s;
    }

}
