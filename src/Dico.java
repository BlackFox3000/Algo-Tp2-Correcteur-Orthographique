import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Dico {

    private HashSet words = new HashSet();
    private HashMap<String, ArrayList<String>> wordsByTrigramme;

    public Dico() throws IOException {
        //lecture doc
        String file="src/dico.txt";

        //créer dictionnaire
        Buffer document = new Buffer();
        ArrayList<String> lignes = document.main(file);
        for(Object word:lignes){
            //HashSet
            words.add(word);
            //Hasmap
            build((String) word);
        }
    }

    public boolean content(String word){
        return words.contains(word);
    }

    /**
     * Create an array of trigramme of word
     * @param word
     * @return
     */
    private static ArrayList<String> createTrigrammes(String word) {
        ArrayList<String> trigrammes = new ArrayList();
        for(int i=0; i<word.length()-3; i++) {
            trigrammes.add(word.substring(i, i + 3));
        }
        return trigrammes;
    }

    /**
     * Ajoute a une Hashmap<Trigramme , Arraylist<String>) le mot en fonction de ses trigrammes
     * @param word
     */
    public void build(String word){
        ArrayList<String> trigrammes = (createTrigrammes(word));
        for(String trigramme : trigrammes){
            if(! wordsByTrigramme.containsKey(trigramme)) {
                ArrayList<String> words = new ArrayList<>();
                words.add(word);
                wordsByTrigramme.put(trigramme, words);
            }
            else
                wordsByTrigramme.get(trigramme).add(word);
        }
    }


}
