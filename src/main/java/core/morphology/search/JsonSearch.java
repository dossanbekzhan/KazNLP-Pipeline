package core.morphology.search;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JsonSearch {
    private List<String> morph = new ArrayList<>();
    static JSONArray jsonArray = new JSONArray();

    public JsonSearch() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(this.getClass().getResourceAsStream("/dictionary/lex.json"))));
        Object fileObjects = JSONValue.parse(reader);
        jsonArray = (JSONArray) fileObjects;
    }

//    static {
//
//        InputStream jsonFile=null;
//        try {
//            jsonFile = JsonSearch.class.getResourceAsStream("lemmas.json");
//        } catch (Exception e){
//        }
//        Reader readerJson = new InputStreamReader(jsonFile);
//        Object fileObjects= JSONValue.parse(readerJson);
//        jsonArray=(JSONArray)fileObjects;
//    }

    public int search(String s) {
        int position = -1;
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jo = (JSONObject) jsonArray.get(i);
            if (s.equals(jo.get("word"))) {
                morph = (List) jo.get("pos");
                return i;
            }
        }
        return position;
    }

    public List<String> getMorph() {
        return morph;
    }
}
