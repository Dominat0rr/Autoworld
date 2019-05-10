package be.vdab.util.mens;

import java.io.Serializable;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import static org.apache.commons.lang3.StringUtils.join;

public class Mens implements Serializable, Comparable<Mens> {
    private String naam;
    private Set<Rijbewijs> rijbewijzen = new TreeSet<>();

    /**
     *
     * @param naam
     */
    public Mens(String naam) {
        this.naam = naam;
    }

    /***
     *
     * @param naam
     * @param rijbewijzen
     */
    public Mens(String naam, Rijbewijs... rijbewijzen) {
        this.naam = naam;

        for (Rijbewijs rb : rijbewijzen) {
            this.rijbewijzen.add(rb);
        }
    }

    public String getNaam() {
        return naam;
    }

    public Set<Rijbewijs> getRijbewijzen() {
        //return rijbewijzen;
        return Collections.unmodifiableSet(rijbewijzen);
    }

    public Rijbewijs[] getRijbewijs() {
        return rijbewijzen.toArray(new Rijbewijs[rijbewijzen.size()]);
    }

    @Override
    public String toString() {
        //return naam + ", " + rijbewijzen;

        /*String str = naam;

        if (rijbewijzen.size() > 0) {
            str += "(";
            for (Iterator<Rijbewijs> it = rijbewijzen.iterator(); it.hasNext();) {
                str += it.next().toString();
                if (it.hasNext()) str += ", ";
                else str += ")";
            }
        }
        return str;*/

        if (rijbewijzen.isEmpty()) return naam;
        else return new StringBuilder().append(naam)
                    .append("(")
                    .append(join(rijbewijzen, ", "))
                    .append(")")
                    .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mens mens = (Mens) o;
        return Objects.equals(naam, mens.naam) &&
                Objects.equals(rijbewijzen, mens.rijbewijzen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, rijbewijzen);
    }

    @Override
    public int compareTo(Mens o) {
        int vgl = this.naam.compareTo(o.naam);
        /*if (vgl == 0) {
            for (Iterator<Rijbewijs> rb = rijbewijzen.iterator(); vgl == 0 && rb.hasNext() ; ) {
                vgl = rb.next().compareTo(o.rijbewijzen.iterator().next());
            }
        }*/
        return vgl;
    }
}
