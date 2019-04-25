package be.vdab.util;

public enum Maat {
    centimeter(1), decimeter(1000), meter(1_000_000);
    private final int waarde;

    Maat(int waarde) {
        this.waarde = waarde;
    }

    public int getWaarde() {
        return waarde;
    }

    /*CENTIMETER("centimeter"), DECIMETER("decimeter"), METER("meter");
    private final String waarde;

    Maat(String waarde) {
        this.waarde = waarde;
    }

    public String getWaarde() {
        return waarde;
    }*/
}
