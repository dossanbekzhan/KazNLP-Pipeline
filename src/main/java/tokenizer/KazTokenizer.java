package tokenizer;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import opennlp.tools.tokenize.SimpleTokenizer;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class KazTokenizer {
    public static List<String> getSentences(String text) {
        List<String> result = new ArrayList<>();
        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(text);
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
            String sentence = text.substring(index, bi.current());
            result.add(sentence);
            index = bi.current();
        }
        return result;
    }

    public static String[] tokens(String sentence) {
        //Instantiating SimpleTokenizer class
        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
        //Tokenizing the given sentence
        return simpleTokenizer.tokenize(sentence);
    }


    public static void main(String[] args) throws IOException {
       /* List<File> filesInFolder = getAllFileNameFromDirectory("/home/beka/Documents/news/");
        //  filesInFolder.forEach(System.out::println);
        filesInFolder.forEach(it -> {
            try {
                String jsonText = readJsonFile(it);
                System.out.println(jsonText);
                writeFile(getContents(jsonText), "contents.txt");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });*/
        List<String> result = new ArrayList<>();

        String text = readJsonFile(new File("/home/beka/IdeaProjects/toolsKazanalysis/result.txt"));
        BreakIterator bi = BreakIterator.getSentenceInstance();
        bi.setText(text);
        int index = 0;
        while (bi.next() != BreakIterator.DONE) {
            String sentence = text.substring(index, bi.current());
            result.add(sentence);
            index = bi.current();
        }
        result.forEach(System.out::println);
        writeFile(result, "BreakIterator.txt");
        System.out.println(result.size());
    }

    public static void writeFile(List<String> contents, String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);

        for (String content : contents) {
            if (!content.isEmpty()) {
                fileWriter.write(content);
                fileWriter.write("\n");
            }

        }

        fileWriter.flush();
        fileWriter.close();

    }

    public static List<String> getContents(String jsonText) {
        List<String> contents = new ArrayList<>();
        JsonObject jsonObject = new JsonParser().parse(jsonText).getAsJsonObject();

        JsonObject jsonObject1 = jsonObject.getAsJsonObject("_embedded");
        JsonArray jsonArray = jsonObject1.getAsJsonArray("Article");

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject json = (JsonObject) jsonArray.get(i);
            //System.out.println(json.get("title"));
            String body = json.get("body").toString();
            contents.add(body);
        }

        return contents;

    }


    public static List<File> getAllFileNameFromDirectory(String directory) throws IOException {
        return Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }

    public static List<String> readFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder builder = new StringBuilder();
        List<String> strings = new ArrayList<>();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
        //    line = line.replaceAll("\"\"", "\"");
            // line = line.replaceAll("\\\\n", "\n");
            strings.add(line);
      //      strings.add("\n");
        }
        return strings;
    }


    public static String readJsonFile(File file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            // line = line.replaceAll("\"", "");
            line = line.replaceAll("\\\\n", "\n");
            builder.append(line).append("\n");
        }
        return builder.toString();
    }
}
