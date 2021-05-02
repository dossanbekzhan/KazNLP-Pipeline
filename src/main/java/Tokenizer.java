import core.tokenizer.KazTokenizer;

import java.util.List;

public class Tokenizer {
    public static void main(String[] args) {
        String text = "Сенбе жұртқа, тұрса да қанша мақтап.";
        String[] tokens =
                KazTokenizer.tokens(text);

        for (String token : tokens) {
            System.out.println(token);
        }
    }
}
