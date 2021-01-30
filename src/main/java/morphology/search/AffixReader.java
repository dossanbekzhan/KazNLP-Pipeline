package morphology.search;

import morphology.affix.AffixInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AffixReader {
    private List<AffixInfo> affixSoftNoun=new ArrayList<>();
    private List<AffixInfo> affixHardNoun=new ArrayList<>();
    private List<AffixInfo> affixSoftVerb=new ArrayList<>();
    private List<AffixInfo> affixHardVerb=new ArrayList<>();


    public List<AffixInfo> getAffixes(boolean isHard) {
        if(isHard)
            return affixHardNoun;
        return affixSoftNoun;
    }
    public List<AffixInfo> getVerbAff(boolean isHard) {
        if(isHard)
            return affixHardVerb;
        return affixSoftVerb;
    }

    public void read(){
        BufferedReader sn = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/affixJSON/noun/affixSoftNoun.json")));
        BufferedReader hn = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/affixJSON/noun/affixHardNoun.json")));
        BufferedReader hv = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/affixJSON/verb/affixHardVerb.json")));
        BufferedReader sv = new BufferedReader(
                new InputStreamReader(
                        this.getClass().getResourceAsStream("/affixJSON/verb/affixSoftVerb.json")));
        JsonParser parser = new JsonParser();

        Gson gson=new Gson();
        JsonArray arraySoft = parser.parse(sn).getAsJsonArray();
        JsonArray arrayHard = parser.parse(hn).getAsJsonArray();
        JsonArray arrayHV = parser.parse(hv).getAsJsonArray();
        JsonArray arraySV = parser.parse(sv).getAsJsonArray();

        for (int i = 0; i < arraySoft.size(); i++) {
            affixSoftNoun.add(gson.fromJson(arraySoft.get(i), AffixInfo.class));
        }
        for (int i = 0; i < arrayHard.size(); i++) {
            affixHardNoun.add(gson.fromJson(arrayHard.get(i), AffixInfo.class));
        }
        for (int i = 0; i < arrayHV.size(); i++) {
            affixSoftVerb.add(gson.fromJson(arraySV.get(i), AffixInfo.class));
        }
        for (int i = 0; i < arraySV.size(); i++) {
            affixHardVerb.add(gson.fromJson(arrayHV.get(i), AffixInfo.class));
        }
//        System.out.println(affixSoftNoun.size());
//        System.out.println(affixHardNoun.size());
//        System.out.println(affixSoftVerb.size());
//        System.out.println(affixHardVerb.size());
    }

}
