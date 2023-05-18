import javax.imageio.IIOException;
import java.io.*;

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
        Limonade zitronenlimo = (Limonade) model.rezeptverwaltung.getRezept("Zitronenlimo", "Limonade");
        Verkaufspreis[] speisen1 = {caipirinha, zitronenlimo};
        //System.out.println("Gesamtpreis Speisen: " + ermittleGesamtpreis(speisen1));
        //System.out.println("Gesamtpreis Kochbox: " + ermittleGesamtpreis(speisen1, true));
        model.rezeptverwaltung.nehmeRezeptAuf(caipirinha);
        //model.rezeptverwaltung.nehmeRezeptAuf(zitronenlimo);
        Cocktail cocktail = (Cocktail)model.rezeptverwaltung.getRezept("Caipirinha", "Cocktail");
        //System.out.println(cocktail.getName());
        //aktualisiereRezept(cocktail);
        //werteRezeptverwaltungAus();
        //model.zutatenverwaltung.aendereZutatenPreis();
        //System.out.println("Gesamtpreis Speisen: " + ermittleGesamtpreis(speisen1));
        //System.out.println("Gesamtpreis Kochbox: " + ermittleGesamtpreis(speisen1, true));
        //read();
        //write();
        //model.rezepteSpeichern();
        Limonade orangenlimo = new Limonade("Orangenlimo", zutaten, false, false, "Organe", true);
        model.rezeptverwaltung.nehmeRezeptAuf(orangenlimo);
        model.rezepteSpeichern();

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
        model.zutatenverwaltung.nehmeZutatAuf(zutat);
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

    public double ermittleGesamtpreis(Verkaufspreis[]speisen, boolean kochbox) {

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

    public void read (){

        try {

            FileReader fileReaderAbsolut = new FileReader("C:\\Users\\Timo\\IdeaProjects\\Java Masterclass\\FortgeschritteneProgrammierungDHBW_Teil6\\IODatei.txt");
            FileReader fileReaderRelativ = new FileReader("..\\IODatei.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReaderRelativ);
            String zeile;

            while ((zeile = bufferedReader.readLine()) !=null){
                System.out.println(zeile);
            }

        } catch (FileNotFoundException e){

            System.out.println("Datei nicht gefunden: " + e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

    }

    public void write (){

        try {

            FileWriter fileWriter = new FileWriter("IODatei1.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("Rezepte:");
            bufferedWriter.newLine();
            bufferedWriter.write("Caipirinha");
            bufferedWriter.newLine();
            bufferedWriter.write("Zitronenlimo");
            //bufferedWriter.close();

        } catch (IOException e){

            System.out.println(e.getMessage());

        }

    }
}
