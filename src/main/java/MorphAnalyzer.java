import com.google.gson.Gson;
import core.morphology.TextAnalysis;
import core.morphology.analysis.FullInfo;

import java.io.FileNotFoundException;

public class MorphAnalyzer {
    public static void main(String[] args) {
        String text = "cенбе жұртқа, тұрса да қанша мақтап.";

        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (FullInfo[] analysis : TextAnalysis.getAnalysis(text)) {
                Gson gson = new Gson();
                stringBuilder.append(gson.toJson(analysis));
                stringBuilder.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(stringBuilder);


    }
}
