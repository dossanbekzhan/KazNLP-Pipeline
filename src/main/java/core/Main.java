package core;

import core.morphology.analysis.FullInfo;
import core.morphology.analysis.KazAnalyser;
import core.morphology.search.JsonSearch;
import core.morphology.syntax.SentenceStruct;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
/*        KazAnalyser analyser=new KazAnalyser();
        System.out.println(analyser.initVersions("адамдағы"));
        System.out.println("—".length());
        String text = "Қаланың аумағы — 682 шаршы километр. Іле бойы жазығының т.б. оңтүстігін ала Тянь-Шань тау селімдерінің солтүстігінде орналасқан. Республика жерінің оңтүстік-шығыс бөлігіндегі Іле Алатауының солтүстік беткей баурайында, теңіз деңгейінен 700-1000 метр жоғары Үлкен және Кіші Алматы өзендері аңғарларына орналасқан.";
        for (String sentence : KazTokenizer.getSentences(text)) {
            for (String token : KazTokenizer.tokens(sentence)) {
                System.out.print(token + ", ");
            }
            System.out.println();
        }
        String s = "";
        for (FullInfo[] analysis : TextAnalysis.getAnalysis(text)) {
            *//*System.out.println("==========================");
            for (FullInfo fullInfo : analysis) {
                System.out.println(fullInfo);
            }*//*
            Gson gson = new Gson();
            s = gson.toJson(analysis);
        }
        System.out.println(s);*/

        JsonSearch jsonSearch = new JsonSearch();
        KazAnalyser analyser = new KazAnalyser();
        SentenceStruct struct=new SentenceStruct();

        int foundCount = 0;
        int notFoundCount = 0;

        String textString = readFile("text.txt");
        String[] strings = textString.split(" ");

        for (String text : strings) {

            text = text.toLowerCase().replaceAll("\\s*\\p{Punct}+\\s*$", "");
            List<FullInfo> fullInfo = analyser.initVersions(text);

            if (jsonSearch.search(fullInfo.get(0).getLem()) != -1) {
                System.out.println("Found word: " + text);
                System.out.println("lemma: " + fullInfo.get(0).getLem() + " " + fullInfo.get(0).getPOS());
                foundCount++;
            } else {
                System.out.println("Not found word: " + text);
                System.out.println("lemma: " + fullInfo.get(0).getLem() + " " + fullInfo.get(0).getPOS());
                notFoundCount++;
            }
        }

        System.out.println("Found word count: " + foundCount);
        System.out.println("Not found word count: " + notFoundCount);
    }

    public static String readFile(String fileName) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bf.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
