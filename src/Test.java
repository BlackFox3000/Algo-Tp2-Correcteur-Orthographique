import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public Test() throws IOException {

        createTrigrammesTEST();
        buildTEST();
    }


    // ========== Fonction à tester personnalisées  ==========
    /**
     * Create an array of trigramme of word
     * @param word
     * @return
     */
    private static ArrayList<String> createTrigrammes(String word) {
        ArrayList<String> trigrammes = new ArrayList();
        for(int i=0; i<word.length()-2; i++) {
            trigrammes.add(word.substring(i, i + 3));
            System.out.println(trigrammes.get(i));
        }
        return trigrammes;
    }



    // ========== Les fonctions de TEST ==========
    public static void createTrigrammesTEST(){
        ArrayList<String> words= createTrigrammes("bateau");
        ArrayList<String> results =new ArrayList<>();
        results.add("bat");
        results.add("ate");
        results.add("tea");
        results.add("eau");
        printArray(words);
        System.out.println("createTrigrammesTEST: "+(words == results));
    }

    private void buildTEST() throws IOException {
        Dico dico = new Dico("src/test.txt");
    }

    // ============= Tools =========

    public static void printArray(ArrayList<String> arrayList){
        System.out.println("===============================");
        for(Object object: arrayList)
            System.out.println(object);
        System.out.println("===============================");
    }



}
