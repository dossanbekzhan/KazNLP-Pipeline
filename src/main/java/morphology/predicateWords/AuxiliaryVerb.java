package morphology.predicateWords;

public class AuxiliaryVerb {
    private String []aux={"ал", "бақ", "бар", "баста", "бер", "біл",  "жат","жатыр",
            "жөнел", "жүр", "жібер", "де", "деді", "екен", "емес", "біт",
            "кел", "көр", "қал", "қой",  "отыр","өт" ,"сал", "таста",
            "жазда","тұр","түс","еді", "ашық", "шық","емес"};

    private String [] auxNtoV={"қыл","ет","бол","еді","екен"};

    boolean isAux(String word){
        for (String w : aux) {
            if (w.equals(word))
                return true;
        }
        return false;
    }
    boolean isAuxNtoV(String word){
        for (String w : auxNtoV) {
            if (w.equals(word))
                return true;
        }
        return false;
    }
}
