import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

// KLasa reprezentująca listę filmów
public class Movies {

     //deklarujemy ArrayList movies która będzie przechowywała tytuły filmów w <String>


    private ArrayList <String> movies;

    /**
     *Konstrutor klasowy który przechowuje tytuły filmów z pliku w ArrayLIst
     * Gdy program nie znajdzie pliku źródłowego, informuje o tym użytkownika.
     * parametr: pathname          Path do pliku zawierającego listę filmów (w tym wypadku Movies.txt)
     */
    public Movies(String pathname) {
        movies = new ArrayList();
        File file = new File("./Movies.txt");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                movies.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
        }
    }
     // Metoda która generuje losową liczbę od 0 do (n= movies.size()), a następnie zwraca nam tytuł odpowiadający miejscem na liście wygenerowanej liczbie.
    public String getRandomMovie() {
        int movieIndex = (int) (Math.random() * movies.size());
        return movies.get(movieIndex);
    }
}