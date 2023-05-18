import java.io.*;

/**
 * Klasse zur Erzeugung des Datenmodells
 * @author Timo
 * @version 1.0
 */
public class MVCModel {

    Rezeptverwaltung rezeptverwaltung;
    Kundenverwaltung kundenverwaltung;
    Zutatenverwaltung zutatenverwaltung;
    Bestellung[] bestellungen;

    public MVCModel(){

        init();

    }

    public void init(){

        rezeptverwaltung = new Rezeptverwaltung();
        kundenverwaltung = new Kundenverwaltung();
        zutatenverwaltung = new Zutatenverwaltung();
        bestellungen = new Bestellung[1000];

        System.out.println("Start Initialisierung des Datenmodells. Eingelesene Rezepte:");
        rezepteEinlesen();
        rezeptverwaltung.zeigeAlleRezepteAn();
        System.out.println("Initialisieren des Datenmodells abgeschlossen");

    }

    public void rezepteSpeichern (){

        String[] limoRezepte = new String[20000]; // Annahme = max. 20 Zutaten je Rezept
        int limoRezepteIndex = 0;
        BasisRezept[] rezepte = rezeptverwaltung.getRezepte();

        for (int i = 0; i < rezepte.length; i++){

            if (rezepte[i] != null && rezepte[i].getTyp() == "Limonade"){

                Limonade limonade = (Limonade) rezepte[i];
                limoRezepte[limoRezepteIndex] = "Rezept"
                        + ";" + limonade.getName()
                        + ";" + limonade.getTyp()
                        + ";" + limonade.isHeißGetraenk()
                        + ";" + limonade.isZuckerfrei()
                        + ";" + limonade.getFruchtgeschmack()
                        + ";" + limonade.getHerstellungInHouse();
                limoRezepteIndex++;
                for (int j = 0; j < limonade.getZutaten().length; j++){

                    Zutat zutat = limonade.getZutaten()[j];

                    if (limonade.getZutaten()[j] != null){
                        limoRezepte[limoRezepteIndex] = "Zutat" + ";" + zutat.getName() + ";" + zutat.getPreis();
                        limoRezepteIndex++;
                    }
                }
            }
        }

        try {

            FileWriter fileWriter = new FileWriter("limorezepte.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < limoRezepte.length; i++){

                if (limoRezepte[i] != null){

                    bufferedWriter.write(limoRezepte[i]);
                    bufferedWriter.newLine();

                }
            }
            bufferedWriter.close();

        } catch (IOException e){

            System.out.println(e.getMessage());

        }
    }

    public void rezepteEinlesen(){

        String[] limoRezepte = new String[20000]; // Annahme = max. 20 Zutaten je Rezept
        int limoRezepteIndex = 0;

        try {

            FileReader fileReader = new FileReader("limorezepte.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String zeile;

            while ((zeile = bufferedReader.readLine()) !=null){
                limoRezepte[limoRezepteIndex] = zeile;
                limoRezepteIndex++;
            }

        } catch (FileNotFoundException e){

            System.out.println("Datei nicht gefunden: " + e.getMessage());

        } catch (IOException e) {

            System.out.println(e.getMessage());

        }

        for (int i = 0; i < limoRezepte.length; i++){

            if (limoRezepte[i] != null){

                String[] rezeptInfo = limoRezepte[i].split(";");

                    if (rezeptInfo[0].equals("Rezept")){

                        Zutat[] zutaten = new Zutat[20]; // Annahme: Max 20 Zutaten
                        int zutatenIndex = 0;

                        for (int a = i+1; a < limoRezepte.length; a++){

                            if (limoRezepte[a] != null){

                                String[] zutatenInfo = limoRezepte[a].split(";");

                                if (zutatenInfo[0].equals("Zutat")){

                                    zutaten[zutatenIndex] = new Zutat(zutatenInfo[1], Double.parseDouble(zutatenInfo[2]));
                                    zutatenIndex++;

                                } else {

                                    break;

                                }
                            }
                        }

                        Limonade limonade = new Limonade(rezeptInfo[1], zutaten,
                                Boolean.parseBoolean(rezeptInfo[3]),
                                Boolean.parseBoolean(rezeptInfo[4]),
                                rezeptInfo[5],
                                Boolean.parseBoolean(rezeptInfo[6]));
                        rezeptverwaltung.nehmeRezeptAuf(limonade);
                    }
                }
            }
        }
}