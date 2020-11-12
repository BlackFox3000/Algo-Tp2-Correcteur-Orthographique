import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Test {

    public Test() throws IOException {
       // createTrigrammesTEST();
        // buildTEST();
       // getListWordByTrigrammeTEST();
        arrayListsOfSimilarsTrigrammesTEST();
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

    /**
     *  Retourne un tableau des listes des mots ayant un trigramme commun
     *  en fonction d'un tableau de trigramme donné
     * @param trigrammes
     * @return
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> arrayListsOfSimilarsTrigrammes(ArrayList<String> trigrammes, HashMap<String, ArrayList<String>> wordsByTrigramme) throws IOException {
        ArrayList<ArrayList<String>> listsOfSimilarsTrigrammes = new ArrayList<>();
        for(String trigramme : trigrammes){
            listsOfSimilarsTrigrammes.add(getListWordByTrigramme(trigramme, wordsByTrigramme));
        }
        return listsOfSimilarsTrigrammes;
    }

    public ArrayList<String> getListWordByTrigramme(String trigramme, HashMap<String, ArrayList<String>> wordsByTrigramme) {
        return wordsByTrigramme.get(trigramme);
    }


    public static HashMap<String,ArrayList<String>> build(String word, HashMap<String, ArrayList<String>> wordsByTrigramme) {
        ArrayList<String> trigrammes = (createTrigrammes(word));
        for (String trigramme : trigrammes) {
            if (!wordsByTrigramme.containsKey(trigramme)) {
                ArrayList<String> words = new ArrayList<>();
                words.add(word);
                wordsByTrigramme.put(trigramme, words);
            } else
                wordsByTrigramme.get(trigramme).add(word);
        }
        return wordsByTrigramme;

    }


    // ========== Les fonctions de TEST ==========
    /**
     * test : ok
     */
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
    /**
     * test : ok
     */
    private static void buildTEST() throws IOException {
        // Dico dico = new Dico("src/test.txt");
        HashMap<String, ArrayList<String>> wordsByTrigramme = new HashMap<>();
        wordsByTrigramme =  build("fille",wordsByTrigramme);
        wordsByTrigramme= build("filet",wordsByTrigramme);
        System.out.println("build= "+wordsByTrigramme);


    }
    /**
     * test : ok
     */
    private void getListWordByTrigrammeTEST(){
        HashMap<String, ArrayList<String>> wordsByTrigramme = new HashMap<>();
        String[] test = {"cote" ,"tacot", "abricot"};
        wordsByTrigramme.put("cot", createArrayWith(test));
        String[] test2 = {"motard" ,"batard", "tetard"};
        wordsByTrigramme.put("tard", createArrayWith(test2));
        String[] test3 = {"bateau" ,"batard", "batiment"};
        wordsByTrigramme.put("bat", createArrayWith(test3));
        printArray(getListWordByTrigramme("bat",wordsByTrigramme));
    }
    /**
     * test : ?
     */
    private void arrayListsOfSimilarsTrigrammesTEST() throws IOException {
        ArrayList<String> trigrammes= new ArrayList<>() ;
        trigrammes.add("bat");
        trigrammes.add("cot");
        HashMap<String, ArrayList<String>> wordsByTrigramme = new HashMap<>();
        String[] test = {"cote" ,"tacot", "abricot"};
        wordsByTrigramme.put("cot", createArrayWith(test));
        String[] test2 = {"motard" ,"batard", "tetard"};
        wordsByTrigramme.put("tard", createArrayWith(test2));
        String[] test3 = {"bateau" ,"batard", "batiment"};
        wordsByTrigramme.put("bat", createArrayWith(test3));
        System.out.println( arrayListsOfSimilarsTrigrammes( trigrammes, wordsByTrigramme) );
    }

    // ============= Tools =========

    public static void printArray(ArrayList<String> arrayList){
        System.out.println("===============================");
        for(Object object: arrayList)
            System.out.println(object);
        System.out.println("===============================");
    }
    public static ArrayList<String> createArrayWith(String[] strings){
        return new ArrayList<>(Arrays.asList(strings));
    }



}
