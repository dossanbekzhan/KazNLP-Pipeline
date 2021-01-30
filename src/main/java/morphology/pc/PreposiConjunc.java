package morphology.pc;

import morphology.analysis.FullInfo;

import java.util.HashMap;
import java.util.Map;
//class for Preposition and Conjunction

public class PreposiConjunc {
    //ЖАЛҒАУЛЫҚ: coordinating
    //ыңғайластық: мен, бен, пен, менен, пенен, бенен, да, де та, те, әрі, және
    //қарсылықты: бірақ, алайда, дегенмен, әйтпесе, әйткенмен
    //талғаулықты әлде, біресе, не, немесе, болмаса, яки кейде;
    //себеп: себебі, өйткені, сондықтан.
    //шартты: егер, егерде, онда
    static Map<String,Integer> COORDINATING;
    static {
        COORDINATING=new HashMap<>();
        COORDINATING.put("мен",2);
        COORDINATING.put("бен",2);
        COORDINATING.put("пен",2);
        COORDINATING.put("менен",2);
        COORDINATING.put("пенен",2);
        COORDINATING.put("бенен",2);
        COORDINATING.put("және",2);
        COORDINATING.put("бірақ",0);
        COORDINATING.put("алайда",0);
        COORDINATING.put("дегенмен",0);
        COORDINATING.put("әйтпесе",0);
        COORDINATING.put("әйткенмен",0);
        COORDINATING.put("біресе",1);
        COORDINATING.put("не",2);
        COORDINATING.put("әлде",2);
        COORDINATING.put("немесе",2);
        COORDINATING.put("болмаса",2);
        COORDINATING.put("яки",2);
        COORDINATING.put("себебі",0);
        COORDINATING.put("өйткені",0);
        COORDINATING.put("сондықтан",0);
        COORDINATING.put("егер",0);
        COORDINATING.put("егерде",0);
        COORDINATING.put("онда",0);
    }


    //СЕПТЕУЛІК: subordinating
    //НЕГӘЗ ТҰЛҒАЛЫ СӨЗБЕН: үшән, сайын, сияқты, секілді, тәрізді, туралы, турасында, жайлы, жайында, арқылы
    //бойы, бойымен, бойынша, шақты, қаралы
    //БС: дейін, шейін, қарай, таман, салым, тарта, жуық, таяу
    //ШС: гөрі, бері, кейін, соң, бұрын, бетер
    //КС: қатар, бірге
    static Map<String,String> SUBORDINATING;
    static {
        SUBORDINATING =new HashMap<>();
        SUBORDINATING.put("дейін","БС");
        SUBORDINATING.put("шейін","БС");
        SUBORDINATING.put("қарай","БС");
        SUBORDINATING.put("таман","БС");
        SUBORDINATING.put("салым","БС");
        SUBORDINATING.put("жуық","БС");
        SUBORDINATING.put("таяу","БС");
        SUBORDINATING.put("гөрі","ШС");
        SUBORDINATING.put("бері","ШС");
        SUBORDINATING.put("кейін","ШС");
        SUBORDINATING.put("соң","ШС");
        SUBORDINATING.put("бұрын","ШС");
        SUBORDINATING.put("бетер","ШС");
        SUBORDINATING.put("қатар","КС");
        SUBORDINATING.put("бірге","КС");
    }
    //ДЕМЕУЛІК: ordinating
    //сұраулық: ма, ме, ба, бе, па, пе, ше
    //нақтылау: қой, ғой
    //шектеу: ғана, қана
    static Map<String,Integer> ORDINATING;
    static {
        ORDINATING = new HashMap<>();
        ORDINATING.put("ма",-1);
        ORDINATING.put("ме",-1);
        ORDINATING.put("ба",-1);
        ORDINATING.put("бе",-1);
        ORDINATING.put("па",-1);
        ORDINATING.put("пе",-1);
        ORDINATING.put("ше",-1);
        ORDINATING.put("қой",-1);
        ORDINATING.put("ғой",-1);
        ORDINATING.put("ғана",-1);
        ORDINATING.put("қана",-1);
    }
    public static int getAccordance(FullInfo before, String PCword, FullInfo after){
    if (COORDINATING.containsKey(PCword)){
    if(COORDINATING.get(PCword)==2){
        if(before.getPOS().equals("X"))
            before.setPOS(after.getPOS());
        else if(after.getPOS().equals("X"))
            after.setPOS(before.getPOS());
    }
    return COORDINATING.get(PCword);
    }
    else if (SUBORDINATING.containsKey(PCword))
        if (before.getLast().equals(SUBORDINATING.get(PCword)))
        return -1;
    else if (ORDINATING.containsKey(PCword))
        return -1;
    return -2;//not found
    }
}
