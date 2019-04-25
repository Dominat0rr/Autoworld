package be.vdab.voertuigen.div;

/*
Maak een enum DIV.
Plaats de enum in een package be.vdab.voertuigen.div.
Deze enum is een singleton, dit wil zeggen dat er slechts één instance is: INSTANCE;
Maat een method getNummerplaat, die nummerplaat objecten terug geeft.
Om de complexiteit rond de nummerplaat te beperken spreken we af dat:
- een nummerplaat start met AAA gevolgd door 3 cijfers. Je start met 001.
  TIP: Lees eens de API documentatie van de format method van String.
- telkens een nieuwe nummerplaat gevraagd wordt, verhoogd de nummer.
- eenmaal aan 999 gekomen mag terug verder gegaan worden met 001.
Zorg ervoor dat DIV nummerplaatobjecten kan maken, maar andere classes (buiten het package
be.vdab.voertuigen.div) niet.
 */

public enum DIV {
    INSTANCE;
    private int nummer = 1;

    public Nummerplaat getNummerplaat() {
        String plaat = String.format("1-AAA-%03d", nummer++);
        //if (nummer >= 999) nummer = 1;
        if (nummer > 999) nummer = 1;
        return new Nummerplaat(plaat);
    }
}