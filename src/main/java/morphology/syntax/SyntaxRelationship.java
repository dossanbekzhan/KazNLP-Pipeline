package morphology.syntax;

import morphology.analysis.FullInfo;
import morphology.pc.PreposiConjunc;
import morphology.predicateWords.PredicateWords;

public class SyntaxRelationship {
    int [][]adjacencyMatrix;
    FullInfo[] sentence;
    boolean []isVerb;
    boolean []isBusy;
    int c=0;
    public SyntaxRelationship(FullInfo[] sentence) {
        this.sentence = sentence;
        adjacencyMatrix=new int[sentence.length][sentence.length];
        isBusy=new boolean[sentence.length];
        isVerb= PredicateWords.isverb(sentence);
        init();
    }

    void init(){
        complementary();
        findNegotiation();//1)	Қиысу – согласование – negotiation
        findDominationPhrases();//Меңгеру – управление – domination #2
        findSubordination();//3)	Матасу – соподчинение  – subordination #3
        findConvergence();//4)	Жанасу – конвергенция
        findAdjunction();//Қабысу – примыкание – adjunction #5 ------ тек қатар кездесетін қабысуларды ғана
    }
    //kosymsha:  shylau #6
    private void complementary() {
        for (int i = 1; i <sentence.length-2 ; i++) {
            System.out.println(sentence[i].getWord());
            if(sentence[i].getPOS().equals("shylau")){
                int position= PreposiConjunc.getAccordance(sentence[i-1],sentence[i].getLem(),sentence[i+1]);
                if (position==-2)
                    continue;
                else if(position==2){
                    adjacencyMatrix[i][i-1]=6;
                    adjacencyMatrix[i][i+1]=6;
                    c++;
                }
                else {
                    adjacencyMatrix[i][i+position]=6;
                    isBusy[i+position]=true;
                    c++;
                }
            }
        }
    }
    //1)	Қиысу – согласование – negotiation
    private void findNegotiation() {
        for (int i=0;i<sentence.length-1;i++){
            if (sentence[i].isPluralOrDep()){
                adjacencyMatrix[sentence.length - 1][i] = 1;
                c++;
                return;
            }
        }
        for (int i = 0; i < sentence.length - PredicateWords.findPW(sentence).size(); i++) {
            if (sentence[i].getPOS().equals("zatEsim")&&(sentence[i].getAffixes()==null) ) {
                adjacencyMatrix[sentence.length - 1][i] = 1;
                c++;
                return;
            }
        }

    }
    //Меңгеру – управление – domination #2
    private void findDominationPhrases() {
        int bound=0;
        for (int i = 1; i < isVerb.length; i++) {
            if (isVerb[i]){
                for (int j=bound;j<i;j++){
                    if (!isBusy[j]&&checkCaseAffix5(sentence[j])){
                        adjacencyMatrix[i][j]=2;
                        bound=i+1;
                        c++;
                    }
                }
            }
        }

    }
    //PROBLEM: кейде ТС жалғауы жасырын тұрады, оны табу үшін семантикалық талдау қажет
    private boolean checkCaseAffix5(FullInfo f){
        String[] dominationAffix = {"ТС","ЖС","ШС","КС","БС"};//меңгерудің бағыыңқы сыңарлары жалғауы <сөз+ТС/ЖС/ШС/КС/БС><етістік>
        if(f.getAffixes()==null)
            return false;
                for (String affix : dominationAffix)
                    if (f.containsAff(affix))
                        return true;
        return false;
    }
    //3)	Матасу – соподчинение  – subordination #3
    private void findSubordination() {
            /*
             ******арасында басқа сөздер кездесетін жағдайды қарастыру қажет
             */
            int j=0;
        for (int i = 1; i < sentence.length; i++) {
            if (sentence[i].containsAff("ТЖ-")){
                while (j<i){
                    if (!isBusy[j]&&sentence[j].containsAff("ІС")){
                        adjacencyMatrix[i][j]=3;
                        isBusy[j]=true;
                        c++;
                        break;
                    }
                    j++;
                }
            }
        }
    }
    //Қабысу – примыкание – adjunction #5 ------ тек қатар кездесетін қабысуларды ғана
    private void findAdjunction(){
         // еш жалғаусыз қабысу
            //<сөз+ТС/ЖС/ШС/КС>< есімше>
            //<үстеу ><зе/сын/сан/есімдік >
            //< есімше><зе/сын/сан/есімдік >
            //< есімше><зе/сын/сан/есімдік >
            //<syn esim><zat esim>
            //-------------------//
            /*<сын/сан ><есімше >
            <үстеу/көсемше/ ><етістік >
            <зе+БС >< шылау>< есімше>*/
            //------------------//
        for (int i = 0; i < sentence.length-1; i++) {
            if (isBusy[i])
                continue;
            if (sentence[i+1].isEsimsoz()){
                if (sentence[i].getAffixes()==null&&sentence[i].getPOS().equals("usteu")){
                adjacencyMatrix[i+1][i]=5;
                    c++;
                    isBusy[i]=true;
            }
                if (sentence[i].getAffixes()!=null){
                    if(sentence[i].getAffixes().size()==1&&sentence[i].containsAff("Есімше")){
                        adjacencyMatrix[i+1][i]=5;
                        isBusy[i]=true;
                        c++;

                    }
                }
            }
            else if (sentence[i].getAffixes()==null&&sentence[i].getPOS().equals("synEsim")&&sentence[i+1].getPOS().equals("zatEsim")){
                adjacencyMatrix[i+1][i]=5;
                isBusy[i]=true;
                c++;

            }
        }
    }
    //4)	Жанасу – конвергенция – convergence #4
    private void findConvergence() {
        int j=0;
        for (int i = 1; i < isVerb.length; i++) {
            if (!isVerb[i])
                continue;
                while (j<i){
                    if (!isBusy[j])
                        if (sentence[j].containsAff("Көсемше")||sentence[j].getPOS().equals("usteu")&&sentence[j].getAffixes()==null){
                            adjacencyMatrix[i][j]=4;
                            isBusy[j]=true;
                            c++;
                            break;
                        }
                    j++;
            }
        }
    }
    public void print(){
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                System.out.print(adjacencyMatrix[i][j]+"\t");
            }
            System.out.println();
        }

    }
    public String phrases(){
        String txt="";
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            for (int j = 0; j < adjacencyMatrix[i].length; j++) {
                if (adjacencyMatrix[i][j] > 0)
                    txt+=sentence[j].getWord() + "_" + sentence[i].getWord()+"  "+adjacencyMatrix[i][j]+" \n";
            }
        }
        return txt;
    }
}
