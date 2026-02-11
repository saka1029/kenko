package saka1029.kenko.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Test;

public class TestParser {

    @Test
    public void testParser() throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("data/健康保険法.txt"))) {
            健康保険法読込 p = new 健康保険法読込(reader);
            p.parse();
            p.root.visit((n, d) -> System.out.print(n.toString().indent(d * 4)), 0);
        }
    }

}
