package be.vdab.voertuigen;

import be.vdab.util.Datum;
import be.vdab.util.Laadbaar;
import be.vdab.util.Volume;
import be.vdab.util.mens.Mens;

import java.awt.*;

/*
Pickup

Deze class in afgeleid van Personenwagen en implementeert Laadbaar.
Voorzie de nodige getters en setter, override de nodige methods.
De class heeft een constructor om alle fields te initialiseren.
 */
public class Pickup extends Personenwagen implements Laadbaar {
    private Volume laadbaarVolume;

    public Pickup(String merk, Datum datumEersteIngebruikName, int aankoopprijs, int zitplaatsen, Color kleur, Volume laadbaarVolume, Mens bestuurder, Mens... passagiers) {
        super(merk, datumEersteIngebruikName, aankoopprijs, zitplaatsen, kleur, bestuurder, passagiers);
        this.laadbaarVolume = laadbaarVolume;
    }

    @Override
    public Volume getLaadvolume() {
        return laadbaarVolume;
    }

    @Override
    public void setLaadvolume(Volume volume) {
        if (volume == null) throw new IllegalArgumentException("Volume mag niet null zijn");
        this.laadbaarVolume = volume;
    }

    @Override
    public String toString() {
        return super.toString() + " " + laadbaarVolume.toString();
    }
}
