public class Limonade extends BasisRezeptGetraenk implements Verkaufspreis {

    private String fruchtgeschmack;
    private Boolean herstellungInHouse;

    public Limonade (String name, Zutat[]zutaten, boolean heißGetraenk, boolean zuckerfrei, String fruchtgeschmack, boolean herstellungInHouse){

        super(name, zutaten, heißGetraenk, zuckerfrei, "Limonade");
        this.fruchtgeschmack = fruchtgeschmack;
        this.herstellungInHouse = herstellungInHouse;

    }

    public boolean eiswuerfelMitKundeAbstimmen() {

        return true;

    }

    public String getFruchtgeschmack() {
        return fruchtgeschmack;
    }

    public void setFruchtgeschmack(String fruchtgeschmack) {
        this.fruchtgeschmack = fruchtgeschmack;
    }

    public Boolean getHerstellungInHouse() {
        return herstellungInHouse;
    }

    public void setHerstellungInHouse(Boolean herstellungInHouse) {
        this.herstellungInHouse = herstellungInHouse;
    }

    @Override
    public double getAufschlag() {
        if (herstellungInHouse == true){
            return 2.50;
        } else {
            return 2.0;
        }
    }

    @Override
    public double ermittelVerkaufspreis() {
        double verkaufspreis = 0.0;
        for (int i = 0; i < getZutaten().length; i++){
            if (getZutaten()[i] != null){
                verkaufspreis = verkaufspreis + getZutaten()[i].getPreis();
            }
        }
        verkaufspreis = verkaufspreis + getAufschlag() + BASISPREIS;
        return verkaufspreis;
    }

}
