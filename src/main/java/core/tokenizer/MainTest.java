package core.tokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) throws IOException {


        // KazTokenizer.writeFile(KazTokenizer.readFile(new File("/home/beka/IdeaProjects/toolsKazanalysis/contents.txt")), "result.txt");
        List<String> kazakhSentences = new ArrayList<>();

        List<String> sentences = KazTokenizer.readFile(new File("/home/beka/Desktop/file.txt"));

        String regex = "^[әіңғүұқөһӘІҢҒҮҰҚӨ]+$";
        for (String sentence : sentences) {
            String[] words = sentence.split(" ");

            if (words.length > 3) {

                for (int i = 1; i < words.length; i++) {
                    char firsCharacter = words[i].charAt(0);

                    if (Character.isUpperCase(firsCharacter)) {
                        if (words[i].matches(regex)) {
                            System.out.println(sentence);
                            kazakhSentences.add(sentence);
                            break;
                        }
                    }
                }
            }
        }

        KazTokenizer.writeFile(kazakhSentences, "kazakhSentences2.txt");

    }
}
