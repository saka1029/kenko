package saka1029.kenko.parser;

public class Pat {

    public static final String 空白 = "\\h+";
    public static final String 漢数字 = "[〇一二三四五六七八九十]+";

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
