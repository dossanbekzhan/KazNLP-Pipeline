package morphology;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.DocumentPreprocessor;
import edu.stanford.nlp.process.PTBTokenizer;
import morphology.analysis.FullInfo;
import morphology.syntax.SentenceStruct;
import morphology.syntax.SyntaxRelationship;
import tokenizer.KazTokenizer;

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
