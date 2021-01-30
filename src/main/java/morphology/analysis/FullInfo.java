package morphology.analysis;

import morphology.affix.Affix;

import java.util.ArrayList;
import java.util.List;

public class FullInfo {
    private String word;
    private String lemma;
    private String POS;
    private List<Affix> affixes;

    public FullInfo(String word, String lemma, String POS, List<Affix> affixes) {
        this.word = word;
        this.lemma = lemma;
        this.POS = POS;
        this.affixes = affixes;
    }

    public FullInfo(String word) {
        this.word = word;
        affixes=new ArrayList<>();
    }
    void  addAffix(List<Affix> affixes){
        this.affixes.addAll(affixes);
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getLem() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public String getPOS() {
        return POS;
    }

    public void setPOS(String POS) {
        this.POS = POS;
    }

    public List<Affix> getAffixes() {
        return affixes;
    }

    public String getLast(){
        return affixes.get(affixes.size()-1).type;
    }


    public boolean isPluralOrDep(){
        if (affixes==null)
            return false;
        if (affixes.size()==1){
            return affixes.get(0).type.equals("КЖ") || affixes.get(0).type.contains("ТЖ-");
        }
        else if(affixes.size()==2){
            return affixes.get(0).type.equals("КЖ") || affixes.get(1).type.contains("ТЖ-");
        }
        return false;
    }
    public boolean containsAff(String type){
        if (affixes==null)
            return false;
        else
            for (Affix affix : affixes) {
                if (affix.type.contains(type)) {
                    return true;
                }
            }
            return false;
    }
    public boolean isEsimsoz(){
        String[] pos = {"zatEsim", "esimdik", "synEsim", "sanEsim"};
        for (String p : pos) {
            if (p.equals(POS))
                return true;
        }
        return false;
    }
    @Override
    public String toString() {
        return "FullInfo{" +
                "word='" + word + '\'' +
                ", lemma='" + lemma + '\'' +
                ", POS='" + POS + '\'' +
                ", affixes=" + affixes +
                '}';
    }
}
