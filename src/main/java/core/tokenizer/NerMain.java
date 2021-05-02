package core.tokenizer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class NerMain {
    public static void main(String[] args) throws IOException {

        List<String> listOfSentence = readFile("kazakhSentences.txt");
        List<String> sql_init = new ArrayList<>();

        for (String s : listOfSentence) {
            int a = s.length();
            // int length = "34308099-b9e5-4351-a358-223f8b89a368,false,false,".length();
            //String result = s.substring(length, a);

            System.out.println(SQL_START + s + SQL_END);
            sql_init.add(SQL_START + s + SQL_END);
        }

        KazTokenizer.writeFile(sql_init, "sql_init.txt");


    }


    public static List<String> readFile(String fileName) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader rd = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8));
        String line;
        while ((line = rd.readLine()) != null) {
            list.add(line);
        }
        return list;
    }

    public static final String SQL_START = "insert into sentence(text) value ('";
    public static final String SQL_END = "');";

}
