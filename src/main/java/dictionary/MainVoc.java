package dictionary;

import java.io.IOException;

public class MainVoc {
    public static void main(String[] args) throws IOException {
        VocabWrite vocabWrite=new VocabWrite("D:\\javaprojects\\toolsKazanalysis\\src\\main\\resources\\dictionary\\lex.txt");
        vocabWrite.readANDwrite();
    }
}
