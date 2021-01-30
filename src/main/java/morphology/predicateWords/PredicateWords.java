package morphology.predicateWords;

import morphology.analysis.FullInfo;

import java.util.ArrayList;
import java.util.List;
public class PredicateWords {
    public static boolean[]isverb(FullInfo[] sentence){
        boolean[] verb=new boolean[sentence.length];
        for (int i = 0; i < sentence.length; i++) {
            if (sentence[i].getPOS()==null)
                continue;
            if(sentence[i].getPOS().equals("etistik"))
                verb[i]=true;
        }
        return verb;
    }

    public static List<Integer> findPW(FullInfo[] sentence) {
        AuxiliaryVerb auxiliary = new AuxiliaryVerb();
        List<Integer> position = new ArrayList<>();
        int pointer = 1;
        int len=sentence.length;
        if(auxiliary.isAuxNtoV(sentence[len - pointer].getLem())){
            position.add(sentence.length - pointer);
            pointer++       ;
            position.add(sentence.length - pointer);
        }
        else if (auxiliary.isAux(sentence[len - pointer].getLem())) {
            position.add(sentence.length - pointer);
            pointer++;
        }
        for(int i=0;i<2;i++)
            if(sentence[len - pointer].getPOS().equals("etistik")){
                position.add(sentence.length - pointer);
                pointer++;
            }else
                break;

        return position;
    }
}
