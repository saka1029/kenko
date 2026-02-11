package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TestParser {

    static class P extends 健康保険法読込 {
        public P(BufferedReader reader) {
            super(reader);
        }

        void parse() {
            get();
            while (node != null) {
                root.addChild(node);
                get();
            }
            System.out.println("root:");
            System.out.println(root);
            System.out.println("children:");
            for (Node node : root.children)
                System.out.println(node);
        }
    }

    @Test
    public void testParser() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("data/健康保険法.txt"))) {
            P p = new P(reader);
            p.parse();
        }
    }

}
