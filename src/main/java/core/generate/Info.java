package core.generate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Info implements Serializable {
    String forma;

    public Info() {
    }

    List<Termination> terminations = new ArrayList<>();

    public Info(String forma, List<Termination> terminations) {
        this.forma = forma;
        this.terminations = terminations;
    }

    public String getForma() {
        return forma;

    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public List<Termination> getTerminations() {
        return terminations;
    }

    public void setTerminations(List<Termination> terminations) {
        this.terminations = terminations;
    }

    @Override
    public String toString() {
        return "Info{" +
                "forma='" + forma + '\'' +
                ", terminations=" + terminations +
                '}';
    }
}
