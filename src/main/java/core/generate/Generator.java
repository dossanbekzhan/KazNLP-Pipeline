package core.generate;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    public List<Info> listForms = new ArrayList<>();
    WordForms wordForms = new WordForms();

    public final static String VERB = "et";
    public final static String NOUN = "ze";


    public void setForms(String word, String morph) {

        wordForms.charater.setWord(word);

        if (morph.equals("ze") || morph.equals("se")) {
            Info info = new Info();
            info.forma = word;
            listForms.addAll(esim(info, true, true, morph));
            if (morph.equals("se")) {
                for (Info i : wordForms.shyrai(info)) {
                    listForms.add(i);
                    listForms.addAll(wordForms.addDep(i));
                    listForms.addAll(wordForms.addDep(wordForms.addPlural(i)));
                }

            }

        } else if (morph.equals("et")) {
            Info info = new Info();
            info.forma = word;
            listForms.addAll(wordForms.rai(info));
            listForms.addAll(wordForms.kosemshe(info, true));

            //esimshe - есім сөз
            final List<Info> infos = wordForms.esimshe(info);
            for (Info i : infos) {
                listForms.add(i);
                listForms.addAll(esim(i, true, true, morph));
            }
        } else {

        }
    }


    List<Info> esim(Info i, boolean zhndy, boolean sanalady, String mor) {
        List<Info> listForms = new ArrayList<Info>();
        Info info = i;
        if (zhndy || !mor.equals("ze"))
            listForms.addAll(wordForms.addPers(info));

        //plural
        Info plural = wordForms.addPlural(info);
        listForms.add(plural);

        //case
        listForms.addAll(wordForms.addCase(info, true));

        //pl+case
        listForms.addAll(wordForms.addCase(plural, true));


        //dep
        int start = listForms.size();
        listForms.addAll(wordForms.addDep(info));
        int end = listForms.size() - 1;

        //dep+case
        for (int k = start + 1; k <= end; k++) {
            listForms.addAll(wordForms.addCase(listForms.get(k), false));
        }

        // сөз +көп.ж.+ тәу.ж.
        start = listForms.size();
        listForms.addAll(wordForms.addDep(plural));
        end = listForms.size() - 1;

        //сөз +көп.ж.+ тәу.ж.+сеп.ж.
        for (int k = start; k <= end; k++) {
            listForms.addAll(wordForms.addCase(listForms.get(k), false));
        }
        return listForms;
    }

}
