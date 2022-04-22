public class Rezeptverwaltung1{ // { // 1 Punkt
                                private BasisRezept[] rezepte;
                                        private int index;
                                        public Rezeptverwaltung1 (){
    rezepte = new BasisRezept[4000];
    index = 0;
}

    public int ermittleAnzahlRezepte (String type){
        int anzahl = 0;
        for (int i = 0; i < index; i++){
            if (rezepte[i].getTyp().equals(type)){ // type // 2 Punkte
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
                return rezepte[i]; // [i] // 1 Punkt
            }
        }
        return null; //return null;  //- 3 Punkte
    }
    public void sucheRezeptNachZutat(Zutat zutat){
    }
    public BasisRezept[] getRezeptete() {
        return rezepte; // [] zu viel // 1 Punkt
    }
    public void setRezepte (BasisRezept[] rezepte) { // BasisRezept // 1 Punkt
        this.rezepte = rezepte;
    }
    public boolean nehmeRezeptAuf (BasisRezept rezept) {
        boolean anfrageAusgefuert = false;
        for (int i = 0; i < index;i++){ // i++ // 1 Punkt
            if (rezepte[i].getName().equals(rezept.getName())){ // ( fehlt // 2 Punkte
                rezepte[i] = rezept; // rezepte mit e // 1 Punkt
                anfrageAusgefuert = true;
            }
        }
        if (anfrageAusgefuert){
            System.out.println("Rezept " + rezept.getName() + " aktualisiert");
        } else {
            if (index < 3999){ //; // 1 Punkt
                rezepte[index] = rezept;
            index++;
            System.out.println("Rezept " + rezept.getName() + " neu aufgenommen"); // get-Methode fehlt // 2 Punkte
            anfrageAusgefuert = true;
            } else {
                System.out.println("Zutatenverwaltung ist voll"); // ; // 1 Punkt
            }
         }
    return anfrageAusgefuert; //return anfrageAusgefuert; // 3 Punkte
}
}
