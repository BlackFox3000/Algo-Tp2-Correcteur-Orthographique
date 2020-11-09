


Suivit de l'exercice :

1. construire le dictionnaire à partir des mots du fichier dico.txt,
FAIT   -> hashset

2. implémenter le calcul de la distance entre deux mots,
FAIT -> distanceOfWords

3. implémenter la phase de sélection basée sur les trigrammes communs,

Lecture du mot que l'on écrit
FAIT -> construction des trigrammes :   bateau = {bat, ate, tea, eau}
/* Retourne un tableau de trigrammes */
public Arraylist() getTrigramme(String mot)

4. Compare le mot s'il possède un trigramme

-> Récupérer listes des mot ayant un trigramme similaire => stocker dans une liste
public boolean hasTrigramme(String mot, String trigramme)

//parcourt le dictionnaire et retourne une liste de mote ayant un trigramme souhaité
public Arraylist() getWordWithTrigramme(String Trigramme)

//Créer un objet ListesTrigrammes qui contient les listes des trigrammes du mot donné au constructeurs
public ListesTrigrammes(String mot){
    this.mot=mot;
    this.lists = getLists(mot)
}

/* Retourne un ArrayList de list*/
public Arraylist<Arraylist<String>> getLists(String mot)

-> Comparer les listes => récupérer les 100 mots étant les plus présents dans ces listes

/* Créer une hashmap<(String)'mot',(int) 'occurence'> */
public Arraylist<String>  setWeigth(ListesTrigrammes listesTrigrammes)
    //Créer une hashmap stock, int occMaw=0
    //parcourt des listes
        //parcourt d'une liste
           //test si le mot existe dans la hashmap
                    si oui  => augmenter son occurence
                    si non => ajouter et initialister occurence à 1
            // Si mot.occurence > occMaw; occMax= mot.occurence

    //Créer une Arraylist<String>  listTrier
         Pour tout stock
              if(  stock.get(i).get(occurence) == occMax )
                   listTrier.add( stock.get(i).get(mot) );
              if(ListTrier.length()=> 100)
                    return listTrier;
      return listTrier;

4. évaluer le temps de calcul nécessaire pour corriger tous les mots du fichier fautes.txt.

// a voir avec le prof