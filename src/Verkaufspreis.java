public interface Verkaufspreis {

    public static final double BASISPREIS = 1.50;

    public abstract double getAufschlag();
    public abstract double ermittelVerkaufspreis();

}
