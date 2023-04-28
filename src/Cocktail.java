public class Cocktail extends BasisRezeptGetraenk implements Verkaufspreis {

    private int alkohlgehalt;
    private boolean alkohlfrei;

    public Cocktail(String name, Zutat[]zutaten, boolean heißGetraenk, boolean zuckerfrei, int alkohlgehalt, boolean alkohlfrei){

        super(name, zutaten, heißGetraenk, zuckerfrei, "Cocktail");
        this.alkohlgehalt = alkohlgehalt;
        this.alkohlfrei = alkohlfrei;

    }

    public String glasMitKundeAbstimmen() {

        return "";

    }

    public int getAlkohlgehalt() {
        return alkohlgehalt;
    }

    public void setAlkohlgehalt(int alkohlgehalt) {
        this.alkohlgehalt = alkohlgehalt;
    }

    public boolean getAlkohlfrei() {
        return alkohlfrei;
    }

    public void setAlkohlfrei(boolean alkohlfrei) {
        this.alkohlfrei = alkohlfrei;
    }

    @Override
    public double getAufschlag() {
        if (alkohlfrei == true){
            return 2.50;
        } else {
            return 3.00;
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
