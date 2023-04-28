/**
 * Klasse MVCController ist für die Erzeugung des User Interface und des Datenmodells verantwortlich.
 * Zusätzlich werden alle Benutzeraktionen im User Interface in Form von ActionEvents behandelt.
 * @author Timo
 * @version 1.0
 */
public class MVCController {

    private MVCView view;
    private MVCModel model;

    public MVCController() {
        //Erzeugung des User Interface durch ein Objekt der Klasse MVCView
        this.view = new MVCView(this);
        //Erzeugung des Datenmodells durch ein Objekt der Klasse MVCModel
        this.model = new MVCModel();
        legeRezeptAn();
    }

    public void legeRezeptAn (){

        Zutat limette = legeZutatan("Limette", 1.00);
        Zutat zucker = legeZutatan("Zucker", 2.50);
        Zutat[] zutaten = {limette, zucker};
        Cocktail caipirinha = new Cocktail("Caipirinha", zutaten, true, false, 2, false);
        Limonade zitronenlimo = new Limonade("Zitronenlimo", zutaten, false, false, "Limette", true);
        Verkaufspreis[] speisen1 = {caipirinha, zitronenlimo};
        System.out.println("Gesamtpreis: " + ermittleGesamtpreis(speisen1));
        model.rezeptverwaltung.nehmeRezeptAuf(caipirinha);
        model.rezeptverwaltung.nehmeRezeptAuf(zitronenlimo);
        Cocktail cocktail = (Cocktail)model.rezeptverwaltung.getRezept("Caipirinha", "Cocktail");
        System.out.println(cocktail.getName());
        aktualisiereRezept(cocktail);
        werteRezeptverwaltungAus();


    }

    public void aktualisiereRezept (BasisRezept rezept) {

        Cocktail cocktail = (Cocktail)rezept;
        cocktail.setAlkohlfrei(true);
        model.rezeptverwaltung.nehmeRezeptAuf(cocktail);
        Cocktail caipirinha = (Cocktail)model.rezeptverwaltung.getRezept("Caipirinha", "Cocktail");
        System.out.println(caipirinha.getAlkohlfrei());

    }

    public Zutat legeZutatan (String name, double preis){

        Zutat zutat = new Zutat(name, preis);
        model.zutatenverwaltung.setZutat(zutat);
        return zutat;

    }

    public void werteRezeptverwaltungAus (){

        System.out.println("----");
        System.out.println("Anzahl Rezepte: " + model.rezeptverwaltung.ermittleAnzahlRezepte());
        System.out.println("Anzahl Cocktailrezepte: " + model.rezeptverwaltung.ermittleAnzahlRezepte("Cocktail"));
        System.out.println("Anzahl Limorezepte: " + model.rezeptverwaltung.ermittleAnzahlRezepte("Limonade"));

    }

    public double ermittleGesamtpreis (Verkaufspreis[]speisen){

        System.out.println("----");
        System.out.println("Gesamtpreis:");
        double gesamtpreis = 0.00;
        for (int i = 0; i < speisen.length; i++){
            gesamtpreis = gesamtpreis + speisen[i].ermittelVerkaufspreis();
        }
        return gesamtpreis;


    }

    public double ermittleGesamtpreisNeu(Verkaufspreis[]speisen, boolean kochbox) {

        System.out.println("----");
        System.out.println("Gesamtpreis:");
        double verkaufspreis = 0.0;
        if (kochbox == false) {

            for (int i = 0; i < speisen.length; i++) {
                if (speisen[i] != null) {
                    verkaufspreis = verkaufspreis + speisen[i].ermittelVerkaufspreis();
                }
            }

        } else {

            for (int i = 0; i < speisen.length; i++){
                if (speisen[i] != null){
                    BasisRezept rezept = (BasisRezept) speisen[i];
                    for (int j = 0 ; j < rezept.getZutaten().length; j++){
                        if (rezept.getZutaten()[j] != null){
                            verkaufspreis = verkaufspreis + rezept.getZutaten()[j].ermittelVerkaufspreis();
                        }
                    }
                    verkaufspreis = verkaufspreis + speisen[i].BASISPREIS;
                }
            }
        }
        return verkaufspreis;
    }

}
