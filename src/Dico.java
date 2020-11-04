import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Dico {

    public HashSet mots = new HashSet();

    public Dico() throws IOException {
        //lecture doc
        String file="src/dico.txt";

        //créer dictionnaire
        Buffer document = new Buffer();
        ArrayList<String> lignes = document.main(file);
        for(Object ligne:lignes){
            mots.add(ligne);
        }
    }
}
