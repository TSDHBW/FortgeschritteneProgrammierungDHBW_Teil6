import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasse zur Generierung eines Objekts für die Zutatenverwaltung. Jede Zutatenverwaltung besitzt ein Array, in dem
 * alle erzeugten Zutaten gespeichert werden.
 * @author Timo
 * @version 1
 */public class Zutatenverwaltung {

    private Zutat[] zutaten;
    private int index;

    public Zutatenverwaltung (){

        zutaten = new Zutat[100];
        index = 0;

    }

    public Zutat[] getZutaten() {
        return zutaten;
    }

    public void setZutaten(Zutat[] zutaten) {
        this.zutaten = zutaten;
    }

    public Zutat getZutat(String name){

        for (int i = 0; i < index; i++){

            if (zutaten[i].getName().equals(name)){

                return zutaten[i];

            }

        }

        return null;

    }

    public void nehmeZutatAuf (Zutat zutat){

        boolean aktualisiert = false;
        for (int i = 0; i < zutaten.length; i++){

            if (zutaten[i] != null){

                if (zutaten[i].getName().equals(zutat.getName()) == true){

                    // Aktualisieren von Rezept
                    zutaten[i] = zutat;
                    aktualisiert = true;
                    break;

                }
            }
        }

        if (aktualisiert == false){

            for (int i = 0; i < zutaten.length; i++){

                if (zutaten[i] == null){

                    // Hinzufügen von Rezept
                    zutaten[i] = zutat;
                    break;

                }
            }
        }
    }

    public Zutat sucheZutat(){
        Scanner scanner1 = new Scanner(System.in);
        boolean zutatGefunden = false;
        try{
            System.out.println("Name von Zutat: ");
            String name = scanner1.nextLine();
            for (int i = 0; i < zutaten.length; i++){
                if (zutaten[i] != null){
                    if (zutaten[i].getName().equals(name)){
                        zutatGefunden = true;
                        return zutaten[i];
                    }
                }
            }
        } catch (InputMismatchException e){
            System.out.println(e.getMessage());
            System.out.println("Ungeültige Eingabe");
        } finally {
            //scanner.close();
        }
        if (zutatGefunden == false){
            System.out.println("Zutat nicht gefunden");
        }
        return null;
    }

    public void aendereZutatenPreis(){

        Zutat zutat = sucheZutat();
        if (zutat != null) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Neuen Preis eingegeben");
                double preis = scanner.nextDouble();
                zutat.setPreis(preis);
                nehmeZutatAuf(zutat);
            }   catch (InputMismatchException e){
                System.out.println(e.getMessage());
                System.out.println("Kein gültiger Preis.");
            }finally {
                scanner.close();
                System.out.println("Preisänderung abgeschlossen");
            }
        }

    }

}
