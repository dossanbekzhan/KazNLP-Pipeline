package morphology.syntax;

import morphology.analysis.FullInfo;
import morphology.analysis.KazAnalyser;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class SentenceStruct {

    public List<FullInfo[]> versionList = new ArrayList<>();

    public FullInfo[] init_versions(String[] sentence) throws FileNotFoundException {
        FullInfo[]tmp=new FullInfo[sentence.length];
        versionList.add(tmp);
        KazAnalyser analysis=new KazAnalyser()  ;
        for (int n = 0; n < sentence.length; n++) {
            List<FullInfo>version=analysis.initVersions(sentence[n]);
            int sizeVers=version.size();
            List<FullInfo[]> t=new ArrayList<>();
            t.addAll(versionList);
            for (FullInfo[] fullInfos : versionList) {
                fullInfos[n]=version.get(0);
            }
            for(int i=1;i<sizeVers;i++){
                int kk=t.size();
                for (int j=0;j<kk;j++) {
                    FullInfo[]x= new FullInfo[sentence.length];
                    for(int r=0;r<x.length;r++)

                        try{ x[r]=t.get(j)[r];}
                        catch (Exception e){}

                    x[n]=version.get(i);
                    versionList.add(x);
                }
            }
        }
        return versionList.get(getAdequate());
    }
    public int getAdequate(){
        int imax=0,max=0;
        for (int i = 0; i < versionList.size(); i++) {
            SyntaxRelationship syntaxPhrase=new SyntaxRelationship(versionList.get(i));
            syntaxPhrase.init();
            int k=syntaxPhrase.c;
            if(max<k){
                max=k;
                imax=i;
            }
        }
        //System.out.println("Have found phrases: " + max);
        return imax;
    }
    public void printVersions(){
        for (FullInfo[] fullInfos : versionList) {

            for (FullInfo fullInfo : fullInfos) {
                System.out.println(fullInfo);
            }
            System.out.println("===========================");
        }
    }
}
