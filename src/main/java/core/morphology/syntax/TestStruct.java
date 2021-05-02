package core.morphology.syntax;

import java.io.FileNotFoundException;

public class TestStruct {
    public static void main(String[] args) throws FileNotFoundException {
        SentenceStruct struct=new SentenceStruct();
//TODO
        String []str={ "жаңа","жылдан","бастап", "телефон","қымбат","болады"};

        struct.init_versions(str);
        struct.printVersions();
        int adq=struct.getAdequate();
        SyntaxRelationship relationship=new SyntaxRelationship(struct.versionList.get(adq));
        relationship.print();
        relationship.phrases();
        System.out.println(relationship.phrases());
    }
}
