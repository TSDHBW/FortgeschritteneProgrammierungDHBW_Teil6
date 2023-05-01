import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasse zur Generierung eines Objekts für die Rezeptverwaltung. Jede Rezeptverwaltung besitzt je ein Array für Rezepte für Pastagerichte, Fleischgerichte,
 * COcktais und Limonaden
 * @author Timo
 * @version 1
 */
public class Rezeptverwaltung {

    private BasisRezept[] rezepte;

    public Rezeptverwaltung (){

        rezepte = new BasisRezept[1000];

    }

    public void nehmeRezeptAuf (BasisRezept rezept){

        boolean aktualisiert = false;
        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                if (rezepte[i].getName().equals(rezept.getName()) && rezepte[i].getTyp().equals(rezept.getTyp())){

                    // Aktualisieren von Rezept
                    rezepte[i] = rezept;
                    aktualisiert = true;
                    break;

                }
            }
        }

        if (aktualisiert == false){

            for (int i = 0; i < rezepte.length; i++){

                if (rezepte[i] == null){

                    // Hinzufügen von Rezept
                    rezepte[i] = rezept;
                    break;

                }
            }
        }
    }

    public void zeigeAlleRezepteAn () {

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                System.out.println(rezepte[i].getName());

            }
        }

    }

    public void loescheRezept (String rezeptname, String rezeptart){

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                if (rezepte[i].getName().equals(rezeptname) && rezepte[i].getTyp().equals(rezeptart)){

                    rezepte[i] = null;

                }
            }
        }
    }

    public void zeigeRezeptAn (String rezeptname, String rezeptart){

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                if (rezepte[i].getName().equals(rezeptname) && rezepte[i].getTyp().equals(rezeptart)){

                    System.out.println("Anzeige von Rezept:");
                    System.out.println("Name: " + rezepte[i].getName());
                    System.out.println("Rezeptart: " + rezepte[i].getTyp());
                    System.out.print("Zutaten: ");
                    for (int j = 0; j < rezepte[i].getZutaten().length; j++){

                        System.out.print(rezepte[i].getZutaten()[j].getName() + " ");

                    }
                }
            }
        }
    }

    public void zeigeRezeptAn(){

        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Index eingeben: ");
            int index = scanner.nextInt();
            // IndexOutOfBoundsException kann verhindert werden, indem geprüft wird, ob
            // der eingegebene Index innerhalb des Index-Range des Rezepte Array ist
            // if (index >= 0 & index < rezepte.length)
            if (rezepte[index] != null){
                System.out.println("Anzeige von Rezept:");
                System.out.println("Name: " + rezepte[index].getName());
                System.out.println("Rezeptart: " + rezepte[index].getTyp());
                System.out.print("Zutaten: ");
                for (int j = 0; j < rezepte[index].getZutaten().length; j++){
                    if (rezepte[index].getZutaten()[j] != null){
                        System.out.print(rezepte[index].getZutaten()[j].getName() + " ");
                    }
                }
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            System.out.println("Ungültigen Index!");
        }catch (InputMismatchException e){
            System.out.println(e.getMessage());
            System.out.println("Ungültige Eingabe");
        }finally {
            //scanner.close();
        }
    }

    public int ermittleAnzahlRezepte (String rezeptart){

        int anzahl = 0;

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                if (rezepte[i].getTyp().equals(rezeptart)){

                    anzahl++;

                }
            }
        }
        return anzahl;
    }

    public int ermittleAnzahlRezepte (){

        int anzahl = 0;

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                anzahl++;

            }
        }
        return anzahl;
    }

    public BasisRezept getRezept(String rezeptname, String rezeptart){

        boolean aktualisiert = false;
        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null){

                if (rezepte[i].getName().equals(rezeptname) && rezepte[i].getTyp().equals(rezeptart)){

                    return rezepte[i];

                }
            }
        }

        if (aktualisiert == false){
            System.out.println("Rezept nicht gefunden");
        }
        return null;

    }

    public BasisRezept[] getRezepte() {
        return rezepte;
    }

    public void setRezepte(BasisRezept[] rezepte) {
        this.rezepte = rezepte;
    }
}
