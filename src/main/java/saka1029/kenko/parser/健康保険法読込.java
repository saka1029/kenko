package saka1029.kenko.parser;

import java.io.BufferedReader;
import static saka1029.kenko.parser.Pat.*;

public class 健康保険法読込 extends Parser {

    static final Type.IdFunc KAN_ID = node -> "" + Kan2Int(node.header);
    static final Type.IdFunc IROHA_ID = node -> "" + Iroha2Int(node.header);
    static final Type.IdFunc NUM_ID = node -> node.header;
    public static Type 章 = new Type("章", "第(?<H>" + P漢数字 + ")章" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 節 = new Type("節", "第(?<H>" + P漢数字 + ")節" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 款 = new Type("款", "第(?<H>" + P漢数字 + ")款" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 条 = new Type("条", "第(?<H>" + P漢数字 + ")条" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 数字 = new Type("数字", "(?<H>" + P数字 + ")条" + P空白 + "(?<T>.*)", NUM_ID);
    public static Type 漢数字 = new Type("漢数字", "(?<H>" + P漢数字 + ")" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type イロハ = new Type("イロハ", "(?<H>" + Pイロハ + ")" + P空白 + "(?<T>.*)", IROHA_ID);

    public 健康保険法読込(BufferedReader reader) {
        super(reader);
        types.add(章);
        types.add(節);
        types.add(款);
        types.add(条);
        types.add(数字);
        types.add(漢数字);
        types.add(イロハ);
    }

}
