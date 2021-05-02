package core.generate;

public class Main {
    public static void main(String[] args) {
        Generator generator = new Generator();
//        generator.setForms("ойна","et");
//        for (Info i : generator.listForms)
//            System.out.println(i);
//        System.out.println(generator.listForms.size());
//        generator.listForms.clear();
        generator.setForms("су", "ze");
        System.out.println(generator.listForms.size());
        for (Info i : generator.listForms)
            System.out.println(i);
    }
}
