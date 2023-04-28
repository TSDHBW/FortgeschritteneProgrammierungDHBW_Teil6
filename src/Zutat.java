public class Zutat implements Verkaufspreis {

    // Nach Erzeugng in DB und vor Erzeugung schauen, ob schon enthalten

    private String name;
    private double preis;

    public Zutat (String name, double preis){

        this.name = name;
        this.preis = preis;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    @Override
    public double getAufschlag() {
        return 0.75;
    }

    @Override
    public double ermittelVerkaufspreis() {
        return preis + getAufschlag();
    }
}
