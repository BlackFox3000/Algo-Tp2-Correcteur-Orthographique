import java.io.IOException;
import java.util.ArrayList;

public class main {

    /**
     * 1. construire le dictionnaire à partir des mots du fichier dico.txt,
     * 2. implémenter le calcul de la distance entre deux mots,
     * 3. implémenter la phase de sélection basée sur les trigrammes communs,
     * 4. évaluer le temps de calcul nécessaire pour corriger tous les mots du fichier fautes.txt.
     */

     public static void main(String[] args) throws IOException {

        // Test test=new Test();
        long start = System.nanoTime();
         Dico dico= new Dico("src/dico.txt");
         ArrayList<String> similars = dico.similarWords("bateau",100);

         for(String similar : similars)
             System.out.println(similar);
         long end = System.nanoTime();
         System.out.println("Temp:"+(end-start));
         //  String mot1 = "boulle", mot2 = "bil";
         // System.out.println("Distance: "+ distanceOfWords(mot1,mot2));




    }
    //au vue de la fonction peu être inutile?
    public boolean hasTrigramme(String mot, String trigramme){
         return mot.contains(trigramme);
    }

    /**
     * Retourne le poid le plus petit contenu dans le tableau
     * @param tab
     * @param indexLine
     * @param indexColumn
     * @return
     */
    public static int getLittleWeight(ArrayList[] tab, int indexLine, int indexColumn){
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


}
