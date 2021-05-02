package core.generate;

public class Charater {
    String word;
    boolean isZhuan=false;
    char endLetter;
    boolean endDauysty;
    static char[] zhuan = {'а', 'о', 'ұ', 'ы', 'я'};
    static char[] zhinishke = {'ә', 'ө', 'ү', 'і', 'е', 'э'};


    public void setWord(String word) {
        this.word = word;
        endDauysty=false;
        endLetter=word.charAt(word.length()-1);
        endDauysty=endIsDauysty(endLetter);
        isZhuan();
    }
    void isZhuan(){
        int len=word.length();
        if (len >= 3)
            word = word.substring(len - 3, len);
        else if(word.contains("у")){
            isZhuan=true;
            return;
        };
        for (char c : word.toCharArray()) {
            for (char i : zhuan)
                if (i == c) {
                    isZhuan = true;
                    break;
                }

        }
    }
    boolean endIsDauysty(char endLetter) {
        for (char i : zhuan)
            if (endLetter == i) return true;
        for (char i : zhinishke)
            if (endLetter == i) return true;

     return false;
    }


}
