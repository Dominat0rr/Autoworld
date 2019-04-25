package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.mens.Mens;
import be.vdab.util.mens.Rijbewijs;

import java.awt.*;

public class Personenwagen extends Voertuig {
    private static final long serialVersionUID = 1L;
    private static final int MAX_ZITPLAATSEN = 8;
    private Color kleur;

    public Personenwagen(String merk, Datum datumEersteIngebruikName, int aankoopprijs, int zitplaatsen, Color kleur, Mens bestuurder, Mens... passagiers) {
        super(merk, datumEersteIngebruikName, aankoopprijs, zitplaatsen, bestuurder, passagiers);
        setKleur(kleur);
    }

    @Override
    protected Rijbewijs[] getToegestaneRijbewijzen() {
        return new Rijbewijs[]{Rijbewijs.B, Rijbewijs.BE};
    }

    @Override
    protected int getMAX_ZITPLAATSEN() {
        return MAX_ZITPLAATSEN;
    }

    public Color getKleur() {
        return kleur;
    }

    public void setKleur(Color kleur) throws IllegalArgumentException {
        if (isValidKleur(kleur)) this.kleur = kleur;
        else throw new IllegalArgumentException("Kleur mag niet null zijn");
    }

    private static boolean isValidKleur(Color kleur) {
        return kleur != null;
    }

    @Override
    public String toString() {
        return super.toString() + " " + getZitplaatsen();
    }
}
