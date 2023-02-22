import java.util.Scanner;
//klasa reprezentująca grę guess the movie, inicjalizujemy w niej zmienne odpowiadające za atrybuty gry
public class Game {
    private String movieToGuess;
    private int pointsLost;
    private String wrongLetters;
    private String rightLetters;
    private boolean gameWon;

    /**
     * KOnstruktor klasowy który nawiązuję połączenie z objektem movies w pliku Movies. Nadajemy również początkowe
     * wartości atrybutom gry
     * pointsLost = utracone do tej pory punkty;
     * rightLetters = odgadnięte litery, które znajdują się w tytule filmu (zarówno upperCase i lowerCase);
     * wrongLetters = odgadnięte litery, których nie było w tytule filmu;
     * gameWon = warunek, którego spełnienie oznacza wygraną.
     * wywołujemy metodę z klasy Movies getRandomMovie() która pobiera tytuł wylosowanego filmu. trim() daje nam pewność,
     * że na początku i końcu tytułu nie będzie spacjiz
     *
     * parametr: pathname          Path do pliku zawierające liste filmów
     */
    public Game(String pathname) {
        Movies movies = new Movies(pathname);
        movieToGuess = movies.getRandomMovie().trim();
        pointsLost = 0;
        rightLetters = "";
        wrongLetters = "";
        gameWon = false;
    }

    //A następnie metoda która zwraca nam tytuł tego filmu.
    public String getMovieTitle() {
        return movieToGuess;
    }

    /**
     * Metoda która zastępuje wszystkie litery filmu (chyba że jakie ś zostały już odgadnięte) znakiem "_".
     * Jeśli jakieś zostały odgadnięte, zastępuje znakiem "_" tylko nieodgadnięte jeszcze litery.
     * Zwrot: String z tytułem filmu z ukrytymi wszystkimi nieodgadniętymi literami.
     */
    public String getHiddenMovieTitle() {
        if(rightLetters.equals("")){
            return movieToGuess.replaceAll("[a-zA-Z]", "_");
        }
        else{
            return movieToGuess.replaceAll("[a-zA-Z&&[^" + rightLetters +"]]", "_");
        }
    }

    /**
     * Metoda zwracająca litery (tak, abyśmy mogli śledzić błędne wybory) odgadniętę, których nie było w tytule filmu,
     *
     * Zwrot: String z literami odgadniętymi błędnie. litery te są odzdzielone spacją.
     */
    public String getWrongLetters() {
        return wrongLetters;
    }

    /**
    Metoda zwracająca nam wartość true gdy gra jest wygrana. w każdym innym przypadku (gra trwa lub została przegrana) wartość to false.
     */
    public boolean WonGame() {
        return gameWon;
    }

    /**
     * Metoda która sprawdza ilość punktóœ zebranych za błędnę próby odgadnięcia. gdy ilość tych punktów jest równa 10, gra
     * zwraca komunikat o przegranej
     * W innym wypadku sprawdza czy w odgadywanym tytulu zostały jakieś znaki "_". Jeśli ich nie znajdzie, zwraca gameWon = true,
     * co jest równoznaczne z wyświetleniem komunikatu o wygranej
     *
     * Zwrot: true gdy gra się skończyła(points lost > 10 || brak "_" w tytule), false gdy jeszcze trwa(warunki niespełnione).
     */
    public boolean gameEnded() {
        if (pointsLost >= 10) {
            return true;
        }

        if(!getHiddenMovieTitle().contains("_")) {
            gameWon = true;
            return true;
        }
        return false;
    }

    /**
     * Metoda któ©a (1) prosi gracza o wprowadzenie litery; (2) zmienia ją na lowerCase; (3) prosi o podanie innej litery jeśli:
     * (a) wprowadzony znak nie jest literą
     * (b) jeśli litera jest już zawarta w zbiorze liter odgadniętych poprawnie lub błędnie (gracz wprowadza ją 2gi raz)
     * (4) zwrot: jeśli wprowadzona dana nie spełnia 2 powyższych warunków, metoda zwraca wprowadzoną literę
     dla następnej metody.
     */
    private String inputLetter(){

        System.out.println("Guess a letter:");
        Scanner scanner = new Scanner(System.in);
        String letter = scanner.nextLine().toLowerCase();

        if(!letter.matches("[a-z]")){
            System.out.println("That is not a letter.");
            return inputLetter();
        }
        else if(wrongLetters.contains(letter) || rightLetters.contains(letter)){
            System.out.println("You already guessed that letter.");
            return inputLetter();
        }
        else return letter;
    }

    /**
     * Metoda, która  (1) pyta gracza o literę (a) Jeśli gracz odgadnął literę poprawnie, dodaje ją do String  rightLetters
     *  która zawiera litery odgadnięte poprawnie (uperCase i lowerCase),
     *  (b) inaczej zwiększa integer pointslost o 1 oraz dodaje litere do String wrongLetters
     */
    public void guessLetter() {

        String guessedLetter = inputLetter();

        if (movieToGuess.toLowerCase().contains(guessedLetter)) {
            rightLetters += guessedLetter + guessedLetter.toUpperCase();
        }
        else {
            pointsLost++;
            wrongLetters += " " + guessedLetter;
        }
    }
}