package be.vdab.schoolgerief;

import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Objects;

/*
Boekentas

De class Boekentas zit in het package be.vdab.schoolgerief en implementeert Laadbaar.
De class heeft twee fields, laadvolume en kleur (een String).
Voorzien een constructor met parameters om de fields te initialiseren.
Voorzie de nodige getters en setters en override de nodige methods.
Zorg ervoor dat Boekentassen in een OutputStream kunnen bewaard worden.
Voorzie een toString, equals en HashCode.
De equals maak je op basis van de laadvolume en kleur.
Laadvolume en kleur moeten ingevuld worden, zoniet wordt een IllegalArgumentException gethrowd.
 */
public class Boekentas implements Laadbaar, Serializable {
    //private final static Path PATH = Paths.get("/data/wagenpark.dat");
    private static final long serialVersionUID = 1L;
    private String kleur;
    private Volume laadVolume;

    public Boekentas(String kleur, Volume laadVolume) throws IllegalArgumentException {
        setKleur(kleur);
        if (laadVolume != null) setLaadvolume(laadVolume);
        else throw new IllegalArgumentException("Laadvolume mag niet leeg zijn");
    }

    // laadvolume
    @Override
    public Volume getLaadvolume() {
        return laadVolume;
    }

    @Override
    public void setLaadvolume(Volume laadVolume) {
        if (laadVolume == null) throw new IllegalArgumentException("Volume mag niet null zijn");
        this.laadVolume = laadVolume;
    }

    // kleur
    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        //if (kleur != null && !kleur.trim().isEmpty() && !StringUtils.isBlank(kleur)) this.kleur = kleur;
        if (isValidKleur(kleur)) this.kleur = kleur;
        else throw new IllegalArgumentException("Kleur mag niet leeg zijn");
    }

    private static boolean isValidKleur(String kleur) {
        return (kleur != null && !kleur.trim().isEmpty() && !StringUtils.isBlank(kleur));
    }

    @Override
    public String toString() {
        return "boekentas " + kleur + " " + laadVolume.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Boekentas boekentas = (Boekentas) o;
        return kleur.equals(boekentas.kleur) &&
                laadVolume.equals(boekentas.laadVolume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kleur, laadVolume);
    }
}
