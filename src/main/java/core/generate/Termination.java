package core.generate;

import java.io.Serializable;

public class Termination implements Serializable {
    String term;
    String type;

    public Termination(String term, String type) {
        this.term = term;
        this.type = type;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Termination{" +
                "term='" + term + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
