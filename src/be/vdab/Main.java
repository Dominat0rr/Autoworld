package be.vdab;

import be.vdab.schoolgerief.Boekentas;
import be.vdab.util.*;
import be.vdab.util.mens.Mens;
import be.vdab.voertuigen.Personenwagen;
import be.vdab.voertuigen.Pickup;
import be.vdab.voertuigen.Voertuig;
import be.vdab.voertuigen.Vrachtwagen;

import java.awt.*;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.SortedSet;
import java.util.TreeSet;

import static be.vdab.util.mens.Rijbewijs.*;

public class Main {

    public static void main(String[] args) throws VolumeException, DatumException {
        SortedSet<Voertuig> voertuigen = new TreeSet<>();
        final Volume VOLUME10;
        final Volume VOLUME12;
        final Datum datum;
        final Datum datum2;
        datum = new Datum(1, 2, 3456);
        datum2 = new Datum(1, 2, 2134);
        final int GETAL4=4;
        final int ZITPLAATSEN_4=4;
        final String datumString;
        final String GETAL4_STRING;
        final int ZITPLAATSEN5=5;
        final Color KLEUR=Color.PINK;

        // bestuurders
        Mens BESTUURDER_BBECCE = new Mens("Ammelie",B,BE,C,CE);
        Mens BESTUURDER_BBE = new Mens("Babette-Emanuella",B,BE);

        /*
        Creëer een sortedset van voertuigen en voorzie hierin minstens een zestal voertuigen (2
        personenwagens, 2 pickups en 2 vrachtwagens). Geef ze weer op het scherm.
         */
        try {
            VOLUME10 = new Volume(10, 10, 10, Maat.decimeter);
            VOLUME12 = new Volume(12, 12, 12, Maat.decimeter);
            Personenwagen pw1 = new Personenwagen("opel", datum, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBECCE);
            Personenwagen pw2 = new Personenwagen("volkswagen", datum2, 100,ZITPLAATSEN_4, KLEUR, BESTUURDER_BBE);
            Vrachtwagen vw1 = new Vrachtwagen("Scania", datum2, 46000, 3, VOLUME12, 13500, 3, BESTUURDER_BBECCE);
            Vrachtwagen vw2 = new Vrachtwagen("DAF", datum, 36000, 3, VOLUME10, 7500, 2, BESTUURDER_BBECCE);
            Pickup pickup1 = new Pickup("Cadilac", datum, 100, GETAL4, KLEUR, VOLUME10,BESTUURDER_BBECCE);
            Pickup pickup2 = new Pickup("Dodge", datum2, 100, GETAL4, KLEUR, VOLUME12,BESTUURDER_BBECCE);
            //voertuigen.add(new Vrachtwagen("DAF", datum, 36000, 3, VOLUME10, 7500, 2, BESTUURDER_BBECCE));
            //voertuigen.add(new Vrachtwagen("Scania", datum2, 46000, 3, VOLUME12, 13500, 3, BESTUURDER_BBECCE));
            voertuigen.add(vw1);
            voertuigen.add(vw2);

            voertuigen.add(pw1);
            voertuigen.add(pw2);
            voertuigen.add(pickup1);
            voertuigen.add(pickup2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Voertuigen: ");
        for (Voertuig v : voertuigen) {
            System.out.println(v.toString());
        }

        /*
        Bewaar de voertuigen in een bestand wagenpark.dat.
         */
        // Opslaan naar bestand
        File file = new File("/data/wagenpark.dat");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(voertuigen);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        Lees het bestand wagenpark.dat terug in in een sortedset en geef ze weer op het scherm. Alle
        voertuigen zouden terug ingelezen en weergegeven moeten zijn.
         */
        SortedSet<Voertuig> voertuigen2 = new TreeSet<>();
        final Path PATH = Paths.get("/data/wagenpark.dat");

        // lees bestand
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            voertuigen2 = (SortedSet<Voertuig>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // print geleesde voertuigen uit
        System.out.println();
        System.out.println("Uitgeleesde voertuigen: ");
        //System.out.println(voertuigen2.size());
        for (Voertuig v : voertuigen2) {
            System.out.println(v.toString());
        }

        /*
        Maak vervolgens enkele boekentas-objecten aan en geef ze weer op het scherm.
         */
        Boekentas boekentassen[] = new Boekentas[3];
        try {
            boekentassen[0] = new Boekentas("groen", new Volume(30, 40, 20, Maat.centimeter));
            boekentassen[1] = new Boekentas("rood", new Volume(40, 50, 30, Maat.centimeter));
            boekentassen[2] = new Boekentas("zwart", new Volume(50, 60, 40, Maat.centimeter));
        }
        catch (VolumeException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Boekentassen: ");
        for (Boekentas b : boekentassen) {
            System.out.println(b.toString());
        }

        /*
        Maak een array van het interfacetype Laadbaar. Vul deze met enkele voertuig objecten en
        boekentas objecten en geef de inhoud van de array weer op het scherm. Toon tenslotte het totale
        laadvolume van deze laadbaar objecten.
         */
        final Volume VOLUME20 = new Volume(20, 20, 20, Maat.decimeter);
        Pickup pickup1 = new Pickup("Cadilac", datum, 100, GETAL4, KLEUR, VOLUME20,BESTUURDER_BBECCE);
        Vrachtwagen vw1 = new Vrachtwagen("Scania", datum2, 46000, 3, VOLUME20, 13500, 3, BESTUURDER_BBECCE);

        Laadbaar[] laadbareDingen = new Laadbaar[3];
        laadbareDingen[0] = boekentassen[0];
        laadbareDingen[1] = pickup1;
        laadbareDingen[2] = vw1;

        System.out.println();
        System.out.println("Laadbare dingen: ");
        for (Object obj : laadbareDingen) {
            System.out.println(obj);
        }
    }
}