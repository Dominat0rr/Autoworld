package be.vdab.voertuigen.div;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/*
Maak een immutable class nummerplaat (immutable  final fields).
Plaats de class in een package be.vdab.voertuigen.div
Er is één constructor en die aanvaardt een String plaat en heeft default visibility. De String bevat minstens één leesbaar teken zoniet wordt een IllegalArgumentException geworpen.
Voorzie een getPlaat().
Voorzie een toString, een equals en een hashCode.
Zorg ervoor dat nummerplaten in een OutputStream kunnen bewaard worden.
Implementeer de interface Comparable.
 */

public class Nummerplaat implements Comparable<Nummerplaat>, Serializable {
    private static final long serialVersionUID = 1L;
    private final static Path PATH = Paths.get("/data/wagenpark.ser");
    private final String plaat;

    Nummerplaat(String plaat) throws IllegalArgumentException {
        if (isValidPlaat(plaat)) this.plaat = plaat;
        else throw new IllegalArgumentException("plaat mag niet leeg zijn");
    }

    public String getPlaat() {
        return plaat;
    }

    private static boolean isValidPlaat(String plaat) {
        return !plaat.trim().isEmpty() && !StringUtils.isBlank(plaat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nummerplaat that = (Nummerplaat) o;
        return plaat.equals(that.plaat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plaat);
    }

    @Override
    public String toString() {
        return  plaat;
    }

    @Override
    public int compareTo(Nummerplaat o) {
        return this.plaat.compareTo(o.plaat);
    }
}

