import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class Buffer
{

    /**
     * Retourne un ArrayList constituer des lignes du fichiers donné en argv
     * @param pathFile String
     * @return ArrayList<String>
     * @throws IOException File not found
     */
    public ArrayList<String> main(String pathFile) throws IOException
    {
        BufferedReader lecteurAvecBuffer = null;
        String ligne;
        ArrayList<String> lignes = new ArrayList<>();

        try
        {
            //@todo retirer le chemin "..."
            //retirer src si sous linux
            lecteurAvecBuffer = new BufferedReader(new FileReader(pathFile));
        }
        catch(FileNotFoundException exc)
        {
            System.out.println("Erreur d'ouverture");
        }
        assert lecteurAvecBuffer != null;
        while ((ligne = lecteurAvecBuffer.readLine()) != null) {
            lignes.add(ligne);
            /*@todo à retirer une fois fini */
            System.out.println(ligne);
        }
        lecteurAvecBuffer.close();
        return lignes;
    }

}