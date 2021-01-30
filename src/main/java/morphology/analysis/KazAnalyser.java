package morphology.analysis;

import morphology.affix.Affix;
import morphology.affix.AffixInfo;
import morphology.search.AffixReader;
import morphology.search.JsonSearch;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class KazAnalyser {

    JsonSearch search=new JsonSearch();
    AffixReader reader=new AffixReader();
    Phonetics phonetics=new Phonetics();

    public KazAnalyser() throws FileNotFoundException {
        reader.read();
    }

    public List<FullInfo> initVersions(String word){

        List<FullInfo> versionList=new ArrayList<>();
        if(word.length()==1) {
            versionList.add(new FullInfo(word, "nul", "PUNC", null));
            return versionList;
        }
        int position=search.search(word);
        if(position!=-1){
            for (String pos : search.getMorph()) {
                versionList.add(new FullInfo(word,word,pos,null));
            }
        }
        FullInfo fi=new FullInfo(word);

        boolean isHard=phonetics.ishard(word);
        for (AffixInfo affix : reader.getAffixes(isHard)) {
            int lenAff=affix.getContent().length();
            if(lenAff>word.length()-1)
                continue;
            String Endword=word.substring(word.length()-lenAff);
            if(Endword.equals(affix.getContent())&&phonetics.checkCategory(word.charAt(word.length()-lenAff-1),affix.getPhonetic())){
                String lemma=word.substring(0,word.length()-lenAff);
                lemma=assimitation(lemma);
                position=search.search(lemma);
                fi.setLemma(lemma);
                fi.addAffix(affix.getAffix());
                if(position!=-1){
                    for (String pos : search.getMorph()) {
                        versionList.add(new FullInfo(word,lemma,pos,affix.getAffix()));
                    }
                }
                else
                    //түбір+етістік жалғауы+есім сөз жалғауы
                    for (AffixInfo verbAff : reader.getVerbAff(isHard)) {
                        int lAff = verbAff.getContent().length();
                        if (lAff > lemma.length() - 1)
                            continue;
                        String ending = lemma.substring(lemma.length() - lAff);
                        if (ending.equals(verbAff.getContent()) && phonetics.checkCategory(lemma.charAt(lemma.length() - lAff - 1), verbAff.getPhonetic())) {
                            String lastLem = lemma.substring(0, lemma.length() - lAff);
                            lastLem=assimitation(lastLem);
                            int index = search.search(lastLem);
                            List<Affix> listAff=new ArrayList<>();
                            listAff.addAll(verbAff.getAffix());
                            listAff.addAll(affix.getAffix());
                            fi.addAffix(verbAff.getAffix());
                            fi.setLemma(lastLem);
                            if (index != -1) {
                                    versionList.add(new FullInfo(word, lastLem, "etistik", listAff));

                            }
                        }
                    }
            }
        }
        String Lem=word;
        for (AffixInfo verbAff : reader.getVerbAff(isHard)) {
            int lAff = verbAff.getContent().length();
            if (lAff > word.length() - 1)
                continue;
            String ending = word.substring(word.length() - lAff);

            if (ending.equals(verbAff.getContent()) && phonetics.checkCategory(word.charAt(word.length() - lAff - 1), verbAff.getPhonetic())) {
                Lem = word.substring(0, word.length() - lAff);
                Lem=assimitation(Lem);
                int index = search.search(Lem);
                fi.addAffix(verbAff.getAffix());
                fi.setLemma(Lem);
                if (index != -1) {
                    for (String pos : search.getMorph()) {
                    if (pos.equals("etistik"))
                        versionList.add(new FullInfo(word, Lem,pos ,verbAff.getAffix()));
                    }
                }
            }
        }

        if (versionList.isEmpty()){
            //System.out.println(word);
            fi.setPOS("X");
            fi.setLemma(Lem);
            versionList.add(fi);
        }
        return versionList;
    }
    private String assimitation(String word){
        char c=word.charAt(word.length()-1);
        if (c=='ғ')
            return word.substring(0,word.length()-1)+'қ';
        if (c=='б')
            return word.substring(0,word.length()-1)+'п';
        if (c=='г')
            return word.substring(0,word.length()-1)+'к';

    return word;
    }

}
