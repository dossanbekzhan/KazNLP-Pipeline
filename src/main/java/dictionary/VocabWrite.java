package dictionary;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import morphology.affix.AffixInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class VocabWrite {
    private String filePath;
    public VocabWrite(String filePath) {
        this.filePath = filePath;
    }
    void readANDwrite() throws IOException {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            HashMap<String, Set<String>>mapLem=new HashMap<>();
            while ((line = reader.readLine()) != null) {
                String[]str=line.replaceAll(" ","").split("/");
                if (mapLem.containsKey(str[0])){
                    Set<String>ls=mapLem.get(str[0]);
                    ls.add(str[1].toString());
                    mapLem.put(str[0],ls);
                }
                else {
                    HashSet<String> ls=new HashSet<>();
                    ls.add(str[1].toString());
                    mapLem.put(str[0],ls);
                }
            }
        reader.close();
        JSONArray array=new JSONArray();
        for (String s : mapLem.keySet()) {
            JSONObject object=new JSONObject();
            object.put("pos",mapLem.get(s));
            object.put("word",s);
            array.add(object);
        }
        System.out.println(array.size());
        JSONObject object=new JSONObject();
        object.put("",array);
            try (FileWriter file = new FileWriter("D:\\javaprojects\\toolsKazanalysis\\src\\main\\resources\\dictionary\\lex.json")) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(array);

                file.write(json);
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
