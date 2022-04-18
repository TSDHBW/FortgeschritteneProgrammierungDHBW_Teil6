/**
 * Klasse zur Generierung eines Objekts für die Rezeptverwaltung. Jede Rezeptverwaltung besitzt ein Array vom Typ
 * BasisRezept, in dem
 * alle erzeugten Rezepte gespeichert werden.
 * @author Timo
 * @version 1
 */
public class Rezeptverwaltung {

    private BasisRezept[] rezepte;
    private int index;

    public Rezeptverwaltung (){

        rezepte = new BasisRezept[4000];
        index = 0;

    }

    public int ermittleAnzahlRezepte (String typ){

        int anzahl = 0;
        for (int i = 0; i < index; i++){

            if (rezepte[i].getTyp().equals(typ)){

                anzahl++;

            }

        }

        return anzahl;

    }

    public int ermittleAnzahlRezepte (){

        return index;

    }

    public void zeigeRezeptAn (String art, String name){

    }

    public BasisRezept getRezept (String name, String typ){

        for (int i = 0; i < index; i++){

            if (rezepte[i].getName().equals(name) && rezepte[i].getTyp().equals(typ)){

                return rezepte[i];

            }

        }

        return null;

    }

    public void sucheRezeptNachZutat(Zutat zutat){

    }

    public BasisRezept[] getRezeptete() {
        return rezepte;
    }

    public void setRezepte (BasisRezept[] rezepte) {
        this.rezepte = rezepte;
    }

    /**
     * Methode zur Aufnahme eines neuen Rezepts in die Rezepteverwaltung. Die Methode prüft zuerst, ob ein Rezept mit
     * gleichem Namen und gleichem Typ bereits in der Rezepteverwaltung existiert. Falls ja, wird das Rezept Zutat
     * aktualisiert. Falls nein, wird geprüft, ob bereits 4000 Rezepte in der Rezeptverwaltung existieren. Falls nein,
     * wird das Rezept aufgenommen
     * @param rezept
     * @return boolean
     */
    public boolean nehmeRezeptAuf (BasisRezept rezept) {

        boolean anfrageAusgefuert = false;
        for (int i = 0; i < index; i++){

            if (rezepte[i].getName().equals(rezept.getName())){
                rezepte[i] = rezept;
                anfrageAusgefuert = true;
            }
        }
        if (anfrageAusgefuert){
            System.out.println("Rezept " + rezept.getName() + " aktualisiert");
        } else {

            if (index < 3999) {

                rezepte[index] = rezept;
                index++;
                System.out.println("Rezept " + rezept.getName() + " neu aufgenommen");
                anfrageAusgefuert = true;

            } else {

                System.out.println("Zutatenverwaltung ist voll");

            }

        }
        return anfrageAusgefuert;
    }

}
