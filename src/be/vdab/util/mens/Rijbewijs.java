package be.vdab.util.mens;

public enum Rijbewijs {
    A, B, BE, C, CE, D, DE;

    @Override
    public String toString() {
        String str = super.toString();
        if (str.contains("E")) str = str.substring(0,1) + "+" + str.substring(1);
        return str;
    }
}
