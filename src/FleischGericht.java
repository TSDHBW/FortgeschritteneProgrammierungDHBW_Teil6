public class FleischGericht extends BasisRezeptEssen implements Verkaufspreis{

    private String fleischsorte;
    private int zielKerntemperatur;
    private int zubereitungstemperatur;

    public FleischGericht(String name, Zutat[]zutaten, boolean vegetarisch, boolean vegan, String fleischsorte, int zielKerntemperatur, int zubereitungstemperatur){

        super(name, zutaten, vegetarisch, vegan, "FleischGericht");
        this.fleischsorte = fleischsorte;
        this.zielKerntemperatur = zielKerntemperatur;
        this.zubereitungstemperatur = zubereitungstemperatur;

    }

    public String gargradMitKundeAbstimmen() {

        return "";

    }

    public String getFleischsorte() {
        return fleischsorte;
    }

    public void setFleischsorte(String fleischsorte) {
        this.fleischsorte = fleischsorte;
    }

    public int getZielKerntemperatur() {
        return zielKerntemperatur;
    }

    public void setZielKerntemperatur(int zielKerntemperatur) {
        this.zielKerntemperatur = zielKerntemperatur;
    }

    public int getZubereitungstemperatur() {
        return zubereitungstemperatur;
    }

    public void setZubereitungstemperatur(int zubereitungstemperatur) {
        this.zubereitungstemperatur = zubereitungstemperatur;
    }

    @Override
    public double getAufschlag() {
        return 5.0;
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
