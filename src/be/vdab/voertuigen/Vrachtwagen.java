package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;

/*
Vrachtwagen

Deze class in afgeleid van voertuig en implementeert Laadbaar.
De class heeft 3 fields laadvolume, de int maximaalToegelatenMassa en de int aantalAssen.
Voorzie de nodige getters en setter, override de nodige methods.
Een vrachtwagen heeft maximum 3 zitplaatsen, anders ontstaat er een IllegalArgumentException.
 */
public class Vrachtwagen extends Voertuig implements Laadbaar {
    private Volume laadvolume;
    private int maximaalToegelatenMassa;
    private int aantalAssen;

    public Vrachtwagen(String merk, Datum datumEersteIngebruikName, int aankoopprijs, int zitplaatsen, Volume laadvolume, int maximaalToegelatenMassa,
                       int aantalAssen, Mens bestuurder, Mens... passagiers) {
        super(merk, datumEersteIngebruikName, aankoopprijs, zitplaatsen, bestuurder, passagiers);
        setLaadvolume(laadvolume);
        setMaximaalToegelatenMassa(maximaalToegelatenMassa);
        setAantalAssen(aantalAssen);
    }

    @Override
    Rijbewijs[] getToegestaneRijbewijzen() {
        //return new Rijbewijs[]{Rijbewijs.D, Rijbewijs.DE};
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }

    @Override
    int getMAX_ZITPLAATSEN() {
        return 3;
    }

    // Laadbaar
    @Override
    public Volume getLaadvolume() {
        return laadvolume;
    }

    @Override
    public void setLaadvolume(Volume laadvolume) {
        if (laadvolume == null) throw new IllegalArgumentException("Volume mag niet null zijn");
        this.laadvolume = laadvolume;
    }

    // maximaal toegelaten massa
    public int getMaximaalToegelatenMassa() {
        return maximaalToegelatenMassa;
    }

    public void setMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        if (isValidMaximaalToegelatenMassa(maximaalToegelatenMassa)) this.maximaalToegelatenMassa = maximaalToegelatenMassa;
        else throw new IllegalArgumentException("maximaal toegelaten massa mag niet negatief zijn!");
    }

    private static boolean isValidMaximaalToegelatenMassa(int maximaalToegelatenMassa) {
        return maximaalToegelatenMassa > 0;
    }

    // aantal assen
    public int getAantalAssen() {
        return aantalAssen;
    }

    public void setAantalAssen(int aantalAssen) {
        if (isValidAantalAssen(aantalAssen)) this.aantalAssen = aantalAssen;
        else throw new IllegalArgumentException("Aantal assen mag niet minder dan twee zijn!");
    }

    private static boolean isValidAantalAssen(int aantalAssen) {
        return aantalAssen >= 2;
    }

    @Override
    public String toString() {
        return super.toString() + " assen:" + aantalAssen + ", maximaal toegelaten massa:" + maximaalToegelatenMassa + ", laadvolume:" + laadvolume.toString();
    }
}
