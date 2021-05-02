import core.generate.Generator;

import java.util.Scanner;

public class WordForm {
    public static void main(String[] args) {

        Generator generator = new Generator();
        String word = "адам";

        //if word is noun - ze
        //if word is verb - et
        String type = Generator.NOUN;
        generator.setForms(word, type);

        generator.listForms.forEach(System.out::println);

    }
}
