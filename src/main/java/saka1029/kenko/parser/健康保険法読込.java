package saka1029.kenko.parser;

import java.io.BufferedReader;
import static saka1029.kenko.parser.Pat.*;

public class 健康保険法読込 extends Parser {

    static final Type.IdFunc KAN_ID = n -> "" + Kan2Int(n);
    static final Type.IdFunc KAN_NO_ID = n -> KanNo2Str(n);
    static final Type.IdFunc IROHA_ID = n -> "" + Iroha2Int(n);
    static final Type.IdFunc NUM_ID = n -> Zen2Han(n);
    public static Type 目次 = new Type("目次", "(?<H>)(?<T>目次)", n -> "#");
    public static Type 章 = new Type("章", "(?<H>第" + P漢数字 + "章)" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 節 = new Type("節", "(?<H>第" + P漢数字 + "節)" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 款 = new Type("款", "(?<H>第" + P漢数字 + "款)" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type 条 = new Type("条", "(?<H>第" + P漢数字 + "条(の" + P漢数字 + ")*)" + P空白 + "(?<T>.*)", KAN_NO_ID);
    public static Type 数字 = new Type("数字", "(?<H>" + P数字 + ")" + P空白 + "(?<T>.*)", NUM_ID);
    public static Type 漢数字 = new Type("漢数字", "(?<H>" + P漢数字 + ")" + P空白 + "(?<T>.*)", KAN_ID);
    public static Type イロハ = new Type("イロハ", "(?<H>" + Pイロハ + ")" + P空白 + "(?<T>.*)", IROHA_ID);
    public static Type 注釈 = new Type("注釈", "[(（](?<H>)(?<T>.*)[)）]", node -> "#");

    public 健康保険法読込(BufferedReader reader) {
        super(reader);
        types.add(目次);
        types.add(章);
        types.add(節);
        types.add(款);
        types.add(条);
        types.add(数字);
        types.add(漢数字);
        types.add(イロハ);
        types.add(注釈);
    }
    
    // void 目次(Node parent) {
    //     while (eat(章)) {
    //         Node sho = parent.addChild(eaten);
    //         while (eat(節)) {
    //             Node setu = sho.addChild(eaten);
    //             while (eat(款)) {
    //                 setu.addChild(eaten);
    //             }
    //         }
    //     }
    // }

    void 漢数字(Node parent) {
        while (eat(漢数字)) {
            Node kans = parent.addChild(eaten);
            while (eat(イロハ))
                kans.addChild(eaten);
        }
    }

    void 条or注釈(Node parent) {
        while (true) {
            if (is(条)) {
                while (eat(条)) {
                    Node jo = parent.addChild(eaten);
                    while (true) {
                        if (is(数字))
                            while (eat(数字)) {
                                Node suji = jo.addChild(eaten);
                                漢数字(suji);
                            }
                        else if (is(漢数字))
                            漢数字(jo);
                        else
                            break;
                    }
                }
            } else if (is(注釈)) {
                while (eat(注釈))
                    parent.addChild(eaten);
            } else
                break;
        }
    }

    @Override
    void parseMain(Node parent) {
        while (eat(注釈))
            parent.addChild(eaten);
        while (eat(章)) {
            Node syo = parent.addChild(eaten);
            条or注釈(syo);
            while (eat(節)) {
                Node setu = parent.addChild(eaten);
                条or注釈(setu);
                while (eat(款)) {
                    Node kan = parent.addChild(eaten);
                    条or注釈(kan);
                }
            }
        }
    }

}
