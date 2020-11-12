import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Dico {

    //peu être à retirer ?
    private HashSet words = new HashSet();
    private HashMap<String, ArrayList<String>> wordsByTrigramme = new HashMap<>();

    public Dico(String fileName) throws IOException {
        //lecture doc
        String file=fileName;

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

    /*public boolean content(String word){
        return words.contains(word);
    }*/

    public ArrayList<String> similarWords(String word, int number_of_words) throws IOException {
        ArrayList<String> trigrammes = createTrigrammes(word);
        ArrayList<ArrayList<String>> lists = arrayListsOfSimilarsTrigrammes(trigrammes);
        return filterNumberOfdWord(lists, number_of_words);
    }

    /**
     * Create an array of trigramme of word
     * @param word
     * @return
     */
    private static ArrayList<String> createTrigrammes(String word) {
        ArrayList<String> trigrammes = new ArrayList();
        for(int i=0; i<word.length()-2; i++) {
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

    /**
     * Retourne une liste de mots comportant le trigramme donnée
     * @param trigramme
     * @return
     */
    public ArrayList<String> getListWordByTrigramme(String trigramme) {
        return wordsByTrigramme.get(trigramme);
    }


    /**
     *  Retourne un tableau des listes des mots ayant un trigramme commun
     *  en fonction d'un tableau de trigramme donné
     * @param trigrammes
     * @return
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> arrayListsOfSimilarsTrigrammes(ArrayList<String> trigrammes) throws IOException {
        ArrayList<ArrayList<String>> listsOfSimilarsTrigrammes = new ArrayList<>();
        for(String trigramme : trigrammes){
            listsOfSimilarsTrigrammes.add(getListWordByTrigramme(trigramme));
        }
        return listsOfSimilarsTrigrammes;
    }

    /**
     * Retourne un tableau de X mots les plus apperçut dans lists
     * @param lists
     * @param number_of_words
     * @return ArrayList<String>
     */
    public ArrayList<String> filterNumberOfdWord(ArrayList<ArrayList<String>> lists , int number_of_words){
        HashMap <String,Integer> weight = new HashMap<>();
        HashMap <Integer, ArrayList<String>> weightSort = new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();
        int max=1;

        //Crée un hashmap< word, count>
        for(ArrayList<String> list : lists){
            for(String word : list){
                if(! weight.containsKey(word)) {
                    weight.put(word, 1);
                    keys.add(word);
                }
                else {
                    weight.replace(word, weight.get(word) + 1);
                    if(max < weight.get(word)+1)
                        max = weight.get(word)+1;
                }
            }
        }

        //inverse hasmap< mot, nb>  <nb, mots>
        for(String key: keys){
            //on regarde si le poid possède déjà un clef
            if(! weightSort.containsKey(weight.get(key))) {
                ArrayList<String> list = new ArrayList<>();
                list.add(key);
                weightSort.put(weight.get(key),list);
            }
            else {
                weightSort.get(weight.get(key)).add(key);
            }
        }

        ArrayList<String> numberOfWords = new ArrayList<>();
        while(numberOfWords.size()<number_of_words || max==-1){
            for(int i=0; i<  weightSort.get(max).size() && numberOfWords.size()<number_of_words  ; i++)
                numberOfWords.add( weightSort.get(max).get(i));
            max--;
        }
        return  numberOfWords;
    }


}
