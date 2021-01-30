package morphology.affix;

/**
 * Created by User on 13.12.2018.
 */
public class Affix {
    public String aff;
    public String type;

    public Affix(String content, String type) {
        this.aff = content;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Affix{" +
                "aff='" + aff + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
