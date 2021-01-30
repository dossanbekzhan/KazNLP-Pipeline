package morphology.analysis;

public class Phonetics {
    private char[] first = {'а', 'я', 'о', 'ұ', 'ы', 'у', 'ә', 'ө', 'ү', 'і', 'е', 'э'};
    private char[] second = {'у', 'й', 'и', 'р', 'л'};
    private char[] thrid = {'м', 'н', 'ң', 'ь'};
    private char[] fourth = {'б', 'в', 'г', 'ғ', 'ь', 'к', 'қ', 'п', 'с', 'т', 'ф', 'х', 'ч', 'ц', 'ш', 'щ'};
    private char[] fifth = {'ж', 'з', 'л', 'ь'};


    boolean ishard(String word){
        char [] letters=word.toCharArray();
        for (int i = letters.length - 1; i >=letters.length*0.5; i--) {
            if(letters[i]=='а'||letters[i]=='о'||letters[i]=='ұ'||letters[i]=='ы')
                return true;
        }
        return  false;
    }

    boolean checkCategory(char endLetter, int[] category){
        byte b=getCategory(endLetter);
        for (int i = 0; i < category.length; i++) {
            if(category[i]==b)
                return true;
        }
        return false;
    }
    byte getCategory(char endLetter){
        for (char c : first) {
            if(c==endLetter)
                return 1;
        }
        for (char c : second) {
            if(c==endLetter)
                return 2;
        }
        for (char c : thrid) {
            if(c==endLetter)
                return 3;
        }
        for (char c : fourth) {
            if(c==endLetter)
                return 4;
        }
        for (char c : fifth) {
            if(c==endLetter)
                return 5;
        }
        return -1;
    }
}
