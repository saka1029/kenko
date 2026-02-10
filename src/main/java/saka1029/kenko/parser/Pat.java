package saka1029.kenko.parser;

import java.util.Map;

public class Pat {

    public static final String P空白 = "\\h+";
    public static final String P漢数字 = "[〇一二三四五六七八九十]+";
    public static final String P数字 = "[0-9０-９]+";
    public static final String Pイロハ = "["
        + "イロハニホヘトチリヌルヲ"
        + "ワカヨタレソツネナラム"
        + "ウヰノオクヤマケフコエテ"
        + "アサキユメミシヱヒモセスン]+";

    static final Map<Character, Integer> KAN2INT = Map.ofEntries(
        Map.entry('万', 10000), Map.entry('千', 1000), Map.entry('百', 100), Map.entry('十', 10),
        Map.entry('〇', 0), Map.entry('一', 1), Map.entry('二', 2), Map.entry('三', 3), Map.entry('四', 4),
        Map.entry('五', 5), Map.entry('六', 6), Map.entry('七', 7), Map.entry('八', 8), Map.entry('九', 9));

    /**
     * 漢数字表記の数字を整数に変換します。
     * 「一二三」→ 123。位取り表記の数字も変換します。「百二十三」→ 123。
     * 漢数字表記が文法的に正しいかどうかのチェックはしていません。
     * 文法的に誤っていたとしても何らかの値が戻ります。
     * 変化可能な最大値は九万程度か？
     * @param s
     * @return
     */
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

    public static int Iroha2Int(String s) {
        return Pイロハ.indexOf(s.charAt(0));
    }

}
