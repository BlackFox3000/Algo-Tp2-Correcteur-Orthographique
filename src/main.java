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
        System.out.println("ça marche pas");
         System.out.println("s"=="s");
       // Dico dico= new Dico();

         String mot1 = "boulle", mot2 = "bil";

         System.out.println("Distance: "+ distanceOfWords(mot1,mot2));


    }


    public static int distanceOfWords(String mot1, String mot2){
        /** 4) Calculer la distance entre deux mots, voir algo td4 exercice 2 **/

        ArrayList[] nbOpperations = new ArrayList[mot1.length()];

        //parcourir des lettre du mot1 avec index i
        for (int i = 0; i < mot1.length(); i++) {
            // on crée les lignes de notre table
            nbOpperations[i] = new ArrayList<Integer>();
            //parcourir des lettre du mot23 avec index j
            for (int j = 0; j < mot2.length(); j++) {
                int poid = getLittleWeight(nbOpperations, i, j);

                System.out.println("comparaison entre : "+mot1.charAt(i)+" et "+ mot2.charAt(j));
                // Annalise de la comparaison de lettre
                if (mot1.charAt(i) != mot2.charAt(j))
                    poid++;
                System.out.println("case:"+poid);
                //attribution du poid nécessaire à cette position
                nbOpperations[i].add(poid);
            }
        }

        return (int) nbOpperations[mot1.length() - 1].get(mot2.length()-1);
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
