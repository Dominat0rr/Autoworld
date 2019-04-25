package be.vdab.voertuigen;

//import org.apache.commons.collections4.IterableUtils;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.MensException;
import be.vdab.util.mens.Rijbewijs;
import be.vdab.voertuigen.div.DIV;
import be.vdab.voertuigen.div.Nummerplaat;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

public abstract class Voertuig implements Serializable, Comparable<Voertuig> {
    private static final long serialVersionUID = 1L;
    private final Nummerplaat nummerplaat = DIV.INSTANCE.getNummerplaat();
    private String merk;
    private Datum datumEersteIngebruikName;
    private int aankoopprijs;
    private final int zitplaatsen;
    private Mens bestuurder;
    private Set<Mens> inzittenden = new TreeSet<>();

    public Voertuig(String merk, Datum datumEersteIngebruikName, int aankoopprijs, int zitplaatsen, Mens bestuurder, Mens... passagiers) {
        setMerk(merk);
        setDatumEersteIngebruikname(datumEersteIngebruikName);
        setAankoopprijs(aankoopprijs);
        if (isValidAantalZitplaatsen(zitplaatsen))
            this.zitplaatsen = zitplaatsen;
        else throw new IllegalArgumentException("Aantal zitplaatsen mag niet negatief zijn");

        if (isValidRijbewijs(bestuurder)) {
            this.bestuurder = bestuurder;
        }

        for (Mens p : passagiers) {
            if (!this.bestuurder.equals(p)) {
                if (isValidAantalInzittenden(p)) {
                    inzittenden.add(p);
                } else throw new MensException("Is geen valide passagier, of zitplaatsen zijn al vol");
            }
        }
    }

    abstract Rijbewijs[] getToegestaneRijbewijzen();
    abstract int getMAX_ZITPLAATSEN();

    public Nummerplaat getNummerplaat() {
        return nummerplaat;
    }

    // Merk
    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) throws IllegalArgumentException {
        if (isValidMerk(merk)) this.merk = merk;
        else throw new IllegalArgumentException("Merk mag niet null zijn!");
    }

    private static boolean isValidMerk(String merk) {
        return !merk.trim().isEmpty() && !StringUtils.isBlank(merk);
    }

    // Datum eerste ingebruikname
    public Datum getDatumEersteIngebruikname() {
        return datumEersteIngebruikName;
    }

    public void setDatumEersteIngebruikname(Datum datumEersteIngebruikName) throws IllegalArgumentException {
        if (isValidDatum(datumEersteIngebruikName)) this.datumEersteIngebruikName = datumEersteIngebruikName;
        else throw new IllegalArgumentException("Datum mag niet null zijn!");
    }

    private static boolean isValidDatum(Datum datum) {
        return datum != null;
    }

    // Aankoopprijs
    public int getAankoopprijs() {
        return aankoopprijs;
    }

    public void setAankoopprijs(int aankoopprijs) throws IllegalArgumentException {
        if (isValidAankoopprijs(aankoopprijs)) this.aankoopprijs = aankoopprijs;
        else throw new IllegalArgumentException("Aankoop prijs mag niet negatief zijn");
    }

    private static boolean isValidAankoopprijs(int aankoopprijs) {
        return aankoopprijs > 0;
    }

    // Zitplaatsen (final)
    public int getZitplaatsen() {
        return zitplaatsen;
    }

    private boolean isValidAantalZitplaatsen(int zitplaatsen) {
        return zitplaatsen > 0 && zitplaatsen <= getMAX_ZITPLAATSEN();
    }

    // Bestuurder
    public Mens getBestuurder() {
        return bestuurder;
    }

    public void setBestuurder(Mens bestuurder) {
        if (/*!this.bestuurder.equals(bestuurder) &&*/ isValidRijbewijs(bestuurder) && isValidAantalInzittenden(bestuurder)) {
            inzittenden.add(this.bestuurder);
            this.bestuurder = bestuurder;
            if (inzittenden.contains((bestuurder))) inzittenden.remove(bestuurder);
        }
        else throw new MensException("Teveel man!");
    }

    public Set<Mens> getInzittenden() {
        return inzittenden;
    }

    //
    public void addIngezetene(Mens passagier) throws MensException {
        if (isValidAantalInzittenden(passagier)) {
            inzittenden.add(passagier);
        }
        else throw new MensException("zitplaatsen zijn al op!");
    }

    public boolean isIngezetene(Mens passagier) {
        return inzittenden.contains(passagier) || passagier.equals(bestuurder);
        //return getIngezetenen().contains(passagier);
        //return (bestuurder.equals(passagier) || inzittenden.contains(passagier));
    }

    public Set<Mens> getIngezetenen() {
        Set<Mens> PassagiersPlusBestuurder = new TreeSet<>(inzittenden);
        PassagiersPlusBestuurder.add(bestuurder);
        return PassagiersPlusBestuurder;
    }

    public Set<Mens> getIngezeteneExclusiefBestuurder() {
        return inzittenden;
    }

    private boolean isValidAantalInzittenden(Mens passagier) {
        if (bestuurder.equals(passagier) || inzittenden.size() + 1 < zitplaatsen)
            return true;

        // Werkt niet?
        //if (getIngezetenen().contains(passagier)) {
        /*if (inzittenden.contains(passagier)) {
            return true;
        }*/

        for (Mens inz : inzittenden) {
            if (inz.equals(passagier)) return true;
        }

        return false;
    }

    private boolean isValidRijbewijs(Mens bestuurder) {
        if (bestuurder.getRijbewijs().length == 0) {
            throw new MensException(bestuurder.toString() + " : heeft geen enkel rijbewijs!");
        }
        else {
            for (Rijbewijs geldig : getToegestaneRijbewijzen()) {
                for (Rijbewijs chauffeurRB : bestuurder.getRijbewijs()) {
                    if(geldig.equals(chauffeurRB)) {
                        return true;
                    }
                }
            }
        }
        throw new MensException (bestuurder.toString () + " : ongeldig rijbewijs");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voertuig voertuig = (Voertuig) o;
        return aankoopprijs == voertuig.aankoopprijs &&
                zitplaatsen == voertuig.zitplaatsen &&
                Objects.equals(nummerplaat, voertuig.nummerplaat) &&
                Objects.equals(merk, voertuig.merk) &&
                Objects.equals(datumEersteIngebruikName, voertuig.datumEersteIngebruikName) &&
                Objects.equals(bestuurder, voertuig.bestuurder) &&
                Objects.equals(inzittenden, voertuig.inzittenden);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nummerplaat, merk, datumEersteIngebruikName, aankoopprijs, zitplaatsen, bestuurder, inzittenden);
    }

    @Override
    public int compareTo(Voertuig o) {
        return this.nummerplaat.compareTo(o.nummerplaat);
    }

    public static Comparator<Voertuig> getMerkComparator() {
        return new Comparator<Voertuig>() {
            @Override
            public int compare(Voertuig o1, Voertuig o2) {
                return o1.merk.compareTo(o2.merk);
            }
        };
    }

    public static Comparator<Voertuig> getAankoopprijsComparator() {
        return new Comparator<Voertuig>() {
            @Override
            public int compare(Voertuig o1, Voertuig o2) {
                return o1.aankoopprijs - o2.aankoopprijs;
            }
        };
    }

    @Override
    public String toString() {
        String str = nummerplaat + " " + merk + " " + datumEersteIngebruikName + " "  + aankoopprijs + " ";
        String strBestuurder = bestuurder.getRijbewijzen().toString().replace("[", "(").replace("]", ")");
        str += bestuurder.getNaam() + strBestuurder;
        if (inzittenden.size() > 0) str += " [";
        for (Iterator<Mens> it = inzittenden.iterator(); it.hasNext();) {
            str += it.next().getNaam();
            if (it.hasNext()) str += ", ";
            else str += "]";
        }
        return str;
    }
}
