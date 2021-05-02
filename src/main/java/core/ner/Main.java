package core.ner;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        List<String> sentences = readFile("DashaText2.txt");
        List<String> builder = new ArrayList<>();

        sentences.forEach(System.out::println);

        for (String sentence : sentences) {
            String[] texts = sentence.trim().split(" ");
            System.out.println("sentence: " + sentence);
            for (String text : texts) {
                if (text.contains("<B-") || text.contains("<I-")) {

                    text = text.replaceAll("<B-LOC>", "");
                    text = text.replaceAll("</B-LOC>", " B-LOC");
                    text = text.replaceAll("<I-LOC>", "");
                    text = text.replaceAll("</I-LOC>", " I-LOC");

                    text = text.replaceAll("<B-PER>", "");
                    text = text.replaceAll("</B-PER>", " B-PER");
                    text = text.replaceAll("<I-PER>", "");
                    text = text.replaceAll("</I-PER>", " I-PER");

                    text = text.replaceAll("<B-ORG>", "");
                    text = text.replaceAll("</B-ORG>", " B-ORG");
                    text = text.replaceAll("<I-ORG>", "");
                    text = text.replaceAll("</I-ORG>", " I-ORG");

                    builder.add(text);

                } else {
                    text += " O";
                    builder.add(text);
                }
            }
        }
        writeFile(builder, "DashaText2Result.txt");
    }


    public static List<String> readFile(String fileName) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        List<String> sentences = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            if (!line.isEmpty()) {
                line = line.substring(0, line.length() - 1);
                line = line.replaceAll("\\\\\"", "");
                line = line.trim();
                sentences.add(line);

                sentences.add("\n");
            }
        }
        return sentences;
    }

    public static void writeFile(List<String> contents, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);

        for (String content : contents) {
            if (!content.isEmpty()) {
                if (content.equals(" O")) {
                    fileWriter.write("\n");
                } else {
                    fileWriter.write(content);
                    fileWriter.write("\n");
                }

            }
        }

        fileWriter.flush();
        fileWriter.close();

    }
}
