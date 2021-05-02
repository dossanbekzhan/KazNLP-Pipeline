package core.tokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest2 {
    public static void main(String[] args) throws IOException {
        String list = KazTokenizer.readJsonFile(new File("/home/beka/Desktop/data.json"));

        List<String> contents = new ArrayList<>();
        JsonArray jsonObject = new JsonParser().parse(list).getAsJsonArray();

        //JsonArray jsonArray = jsonObject.getAsJsonArray();

        for (int i = 0; i < jsonObject.size(); i++) {
            JsonObject json = (JsonObject) jsonObject.get(i);
            //System.out.println(json.get("title"));
            String body = json.get("context").toString();
            contents.add(body.substring(1, body.length() - 1));
        }

        KazTokenizer.writeFile(contents, "resultData.txt");

        System.out.println(contents.toString());

    }
}
