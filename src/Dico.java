import java.io.IOException;
import java.util.*;

public class Dico {

    //peu être à retirer ?
    public HashSet words = new HashSet();
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
            if(list!=null)
            for(String word : list){
                if( ! weight.containsKey(word)) {
                    weight.put(word, 1);
                    keys.add(word);
                }
                else {
                    weight.replace(word, weight.get(word) + 1);
                    if(max < weight.get(word))
                        max = weight.get(word);
                }
            }
        }


        ArrayList<Integer> keysInt = new ArrayList<>();
        //inverse hasmap< mot, nb>  <nb, mots>
        for(String key: keys){
            //on regarde si le poid possède déjà un clef
            if(! weightSort.containsKey(weight.get(key))) {
                ArrayList<String> list = new ArrayList<>();
                list.add(key);
                weightSort.put(weight.get(key),list);
                keysInt.add(weight.get(key));
            }
            else {
                weightSort.get(weight.get(key)).add(key);
            }
        }

        ArrayList<String> numberOfWords = new ArrayList<>();
        while(numberOfWords.size()<number_of_words && max!=0) {
            if (keysInt.contains(max))
                for (int i = 0; i < weightSort.get(max).size() && numberOfWords.size() < number_of_words; i++)
                    numberOfWords.add(weightSort.get(max).get(i));
            max--;
        }
        return  numberOfWords;
    }

    /**
     * Retourne le poid le plus petit contenu dans le tableau
     * @param tab
     * @param indexLine
     * @param indexColumn
     * @return
     */
    public int getLittleWeight(ArrayList[] tab, int indexLine, int indexColumn){
        ArrayList<Integer> poids = new ArrayList();
        //initialiser à 0
        int poid=-1;
        //test cases précédentes
        if(indexLine == 0 && indexColumn == 0)
            return 0;
        if(indexColumn != 0)
            poids.add( (Integer) tab[indexLine].get(indexColumn-1) );
        if( indexLine != 0)
            poids.add( (Integer) tab[indexLine-1].get(indexColumn));
        if(indexLine != 0 && indexColumn != 0)
            poids.add( (Integer) tab[indexLine-1].get(indexColumn-1));
        for(int i=0; i< poids.size() ; i++){
            if( poid>poids.get(i) ||poid == -1)
                poid = poids.get(i);
        }
        return  poid;
    }

    public int distanceOfWords(String mot1, String mot2){
        /** 4) Calculer la distance entre deux mots, voir algo td4 exercice 2 **/

        ArrayList[] nbOpperations = new ArrayList[mot1.length()];

        //parcourir des lettre du mot1 avec index i
        for (int i = 0; i < mot1.length(); i++) {
            // on crée les lignes de notre table
            nbOpperations[i] = new ArrayList<Integer>();
            //parcourir des lettre du mot23 avec index j
            for (int j = 0; j < mot2.length(); j++) {
                int poid = getLittleWeight(nbOpperations, i, j);

             //   System.out.println("comparaison entre : "+mot1.charAt(i)+" et "+ mot2.charAt(j));
                // Annalise de la comparaison de lettre
                if (mot1.charAt(i) != mot2.charAt(j))
                    poid++;
             //   System.out.println("case:"+poid);
                //attribution du poid nécessaire à cette position
                nbOpperations[i].add(poid);
            }
        }

        return (int) nbOpperations[mot1.length() - 1].get(mot2.length()-1);
    }

    /**
     * Retourne un nombre "precision" de mot parmis une liste en fonction de leur distance
     * @param words liste de mots
     * @param precision nombre de mot donné en correction
     * @return
     */
    public ArrayList<String> foundBestSimilars(ArrayList<String> words , int precision,String originalWord){
        HashMap<Integer,ArrayList<String>> distances = new HashMap<>();
        List<Integer> keys = new ArrayList<>();

        for(String word : words) {
            int key = distanceOfWords(word, originalWord);
            if(!distances.containsKey(key)) {
                ArrayList<String> mots = new ArrayList<>();
                mots.add(word);
                distances.put(key, mots);
                keys.add(key);
            }else
                distances.get(key).add(word);

        }
        keys.sort(Collections.reverseOrder());
        ArrayList<String> result = new ArrayList<>();
        for(int i=1; i<keys.size() && result.size()<precision; i++)
            if(keys.contains(i))
                for (int k = 0; k < distances.get(keys.get(i)).size()
                        && result.size() < precision
                        && k < distances.get(i).size(); k++) {
                    result.add(distances.get(i).get(k));
                }

        return result;
    }

    /**
     * Est ce que le mot est présent dans le dictionaire
     * @param string
     * @return
     */
    public boolean isContain(String string){
        return words.contains(string);
    }

    public void correctWord(String stringOriginal) throws IOException{

        if(isContain(stringOriginal))
            System.out.println(stringOriginal+":correct:");
        else{
            ArrayList<String> wordsSimilar = similarWords(stringOriginal,100);
            ArrayList<String> strings =  foundBestSimilars(wordsSimilar,5,stringOriginal);
            String suggestion ="";
            for(String word : strings)
                suggestion = word+" ; "+ suggestion;
            suggestion = suggestion.substring(0,suggestion.length()-2);
            System.out.println(stringOriginal+" :faux: suggestion { "+suggestion+"}");
        }
    }

    public void correctWords(String filename) throws IOException {
        Buffer document = new Buffer();
        ArrayList<String> lignes = document.main(filename);
        for (String word : lignes)
            correctWord(word);
    }
}
