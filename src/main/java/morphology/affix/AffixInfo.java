package morphology.affix;

import java.util.*;

public class AffixInfo {
    private String content;
    private int[] phonetic;
    private List<Affix>affix=new ArrayList<Affix>();

    public AffixInfo(String content, int[] phonetic, List<Affix> affix ) {
        this.content = content;
        this.phonetic = phonetic;
        this.affix = affix;
    }

    public String getContent() {
        return content;
    }

    public int[] getPhonetic() {
        return phonetic;
    }

    public List<Affix> getAffix() {
        Set<Affix> s= new HashSet<Affix>();
        s.addAll(affix);
        affix = new ArrayList<Affix>();
        affix.addAll(s);
        return affix;
    }

    @Override
    public String toString() {
        return "AffixInfo{" +
                "content='" + content + '\'' +
                ", phonetic=" + Arrays.toString(phonetic) +
                ", affix=" + affix +
                '}';
    }
}
