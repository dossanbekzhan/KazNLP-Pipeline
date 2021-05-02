package core.morphology;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import core.morphology.analysis.FullInfo;
import core.morphology.syntax.SentenceStruct;
import core.tokenizer.KazTokenizer;

public class TextAnalysis {
    public static List<FullInfo[]> getAnalysis(String text) throws FileNotFoundException {
        List<FullInfo[]> result = new ArrayList<>();

        List<String> sentences = KazTokenizer.getSentences(text);

        for (String sentence : sentences) {
            SentenceStruct struct = new SentenceStruct();
            struct.init_versions(KazTokenizer.tokens(sentence));
            int adq = struct.getAdequate();
            result.add(struct.versionList.get(adq));
        }
        return result;
    }

}
