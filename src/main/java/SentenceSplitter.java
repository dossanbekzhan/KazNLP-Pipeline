import core.tokenizer.KazTokenizer;

import java.util.List;

public class SentenceSplitter {
    public static void main(String[] args) {
        String text = "Сенбе жұртқа, тұрса да қанша мақтап,\n" +
                "Әуре етеді ішіне қулық сақтап.\n" +
                "Өзіңе сен, өзіңді алып шығар,\n" +
                "Еңбегің мен ақылың екі жақтап.\n" +
                "Өзіңді сенгіштікпен әуре етпе,\n" +
                "Құмарпаз боп мақтанды қуып кетпе.";

        List<String> sentences = KazTokenizer.getSentences(text);

        sentences.forEach(System.out::println);
    }
}
