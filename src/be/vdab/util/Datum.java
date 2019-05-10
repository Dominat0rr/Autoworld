package be.vdab.util;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Objects;

public final class Datum implements Serializable, Comparable<Datum> {
    private static final long serialVersionUID = 1L;
    private final int dag;
    private final int maand;
    private final int jaar;
    private final int[] dagenPerMaand = {31,28,31,30,31,30,31,31,30,31,30,31};

    public Datum(int dag, int maand, int jaar) throws DatumException {
        //if (isValidDatum(dag, maand, jaar)) {
        if (isValidDatum(dag, maand, jaar)) {
            this.dag = dag;
            this.maand = maand;
            this.jaar = jaar;
        }
        else throw new DatumException("Datum is niet correct");
    }

    public int getDag() {
        return dag;
    }

    public int getMaand() {
        return maand;
    }

    public int getJaar() {
        return jaar;
    }

   /* private boolean isValidDatum (int dag, int maand, int jaar){
        if (jaar < 1583 || jaar > 4099 || maand < 1 || maand > 12 || dag < 1 || dag > 31) return false;
        if (maand == 4 || maand == 6 || maand == 9 || maand == 11) return (dag <= 30);
        else if (maand == 2) return ((dag <= 29 && isSchrikkeljaar (jaar)) || (dag <= 28 && !isSchrikkeljaar (jaar)));
        return true;
    }*/

    private boolean isValidDatum (int dag, int maand, int jaar) {
        return (jaar >= 1583 && jaar <= 4099 &&
                maand >= 1 && maand <= 12 &&
                dag >= 1 && dag <= dagenPerMaand[maand-1] +
                (isSchrikkeljaar(jaar)&& maand == 2 ? 1 : 0));
    }

    private boolean isSchrikkeljaar (int jaar) {
        return (jaar % 4 == 0 && (!(jaar % 100 == 0) || jaar % 400 == 0));
    }

    @Override
    public String toString() {
        //return dag + "/" + maand + "/" + jaar;

        // formaat : dd/mm/jjjj
        return String.format("%02d/%02d/%4d", dag, maand, jaar);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(dag, maand, jaar);
        return new HashCodeBuilder().append(dag)
                .append(maand)
                .append(jaar)
                .toHashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Datum datum = (Datum) o;
        return dag == datum.dag &&
                maand == datum.maand &&
                jaar == datum.jaar;
    }

    @Override
    public int compareTo(Datum o) {
        // eerst vergelijken op jaar, dan op maand, dan op dag
        int vgl = jaar - o.jaar;
        if (vgl == 0) {
            vgl = maand - o.maand;
            if (vgl == 0) vgl = dag - o.dag;
        }
        return vgl;
    }
}