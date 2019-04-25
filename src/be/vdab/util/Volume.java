package be.vdab.util;

import java.io.Serializable;
import java.util.Objects;

public final class Volume implements Serializable, Comparable<Volume> {
    private static final long serialVersionUID = 1L;
    private final int breedte;
    private final int hoogte;
    private final int diepte;
    private final Maat maat;

    /**
     * Constructor Volume
     * @param hoogte
     * @param breedte
     * @param diepte
     * @param maat
     */
    public Volume(int hoogte, int breedte, int diepte, Maat maat) throws VolumeException {
        if (breedte >= 0 && hoogte >= 0 && diepte >= 0) {
            this.hoogte = hoogte;
            this.breedte = breedte;
            this.diepte = diepte;
            this.maat = maat;
        }
        else throw new VolumeException("Volume mag niet negatief zijn");
    }

    public int getBreedte() {
        return breedte;
    }

    public int getHoogte() {
        return hoogte;
    }

    public int getDiepte() {
        return diepte;
    }

    public Maat getMaat() {
        return maat;
    }

    public long getVolume() {
        return breedte * hoogte * diepte;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return this.getVolume() == volume.getVolume() && maat == volume.maat;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volume volume = (Volume) o;
        return breedte == volume.breedte &&
                hoogte == volume.hoogte &&
                diepte == volume.diepte &&
                maat == volume.maat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(breedte, hoogte, diepte, maat);
    }

    /*@Override
    public int compareTo(Volume o) {
        return this.maat.compareTo(o.maat);
    }*/

    @Override
    public int compareTo(Volume o) {
        long thisVolume = this.getVolume();
        long otherVolume = o.getVolume();

        /*switch (this.maat) {
            case decimeter :
                thisVolume *= 1000L;
                break;
            case meter :
                thisVolume *= 1000_000L;
                break;
        }*/
        thisVolume *= this.getMaat().getWaarde();

        /*switch (o.getMaat()) {
            case decimeter :
                otherVolume *= 1000L;
                break;
            case meter :
                otherVolume *= 1000_000L;
                break;
        }*/
        otherVolume *= o.getMaat().getWaarde();

        if (thisVolume == otherVolume) {
            return 0;
        }
        return (thisVolume >  otherVolume ? 1 : -1);
    }

    @Override
    public String toString() {
        return hoogte + "(h)x" + breedte + "(b)x" + diepte + "(d) " + getMaat();
    }
}
