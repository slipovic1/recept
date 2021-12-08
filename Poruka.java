package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.util.Objects;

public class Poruka {
    private Korisnik posiljalac;
    private Korisnik primalac;
    private LocalDateTime datumSlanja;
    private String tekst;
    private StatusPoruke statusPoruke;

    public Poruka(Korisnik posiljalac, Korisnik primalac, LocalDateTime datumSlanja, String tekst) throws NeispravanFormatPoruke {
        provjeriNotNull(posiljalac, "Pošiljalac");
        provjeriNotNull(primalac, "Primalac");
        provjeriNotNull(datumSlanja, "Datum slanja");
        provjeriValidnostTekst(tekst);

        this.primalac = primalac;
        this.posiljalac = posiljalac;
        this.datumSlanja = datumSlanja;
        this.tekst = tekst;
        this.statusPoruke = StatusPoruke.NEPROCITANA;
    }

    public Korisnik getPrimalac() {
        return primalac;
    }

    public void setPrimalac(Korisnik primalac) throws NeispravanFormatPoruke {
        provjeriNotNull(primalac, "Primalac");
        this.primalac = primalac;
    }

    public Korisnik getPosiljalac() {
        return posiljalac;
    }

    public void setPosiljalac(Korisnik posiljalac) throws NeispravanFormatPoruke {
        provjeriNotNull(posiljalac, "Pošiljalac");
        this.posiljalac = posiljalac;
    }

    public LocalDateTime getDatumSlanja() {
        return datumSlanja;
    }

    public void setDatumSlanja(LocalDateTime datumSlanja) throws NeispravanFormatPoruke {
        provjeriNotNull(datumSlanja, "Datum slanja");
        this.datumSlanja = datumSlanja;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) throws NeispravanFormatPoruke {
        provjeriValidnostTekst(tekst);
        this.tekst = tekst;
    }

    public StatusPoruke getStatusPoruke() {
        return statusPoruke;
    }

    public void setStatusPoruke(StatusPoruke statusPoruke) {
        this.statusPoruke = statusPoruke;
    }

    @Override
    public String toString() {
        return "[od: " + posiljalac + " za: " + primalac + " tekst: " + tekst + "]";
    }

    public static void provjeriNotNull(Object objekat, String uloga) throws NeispravanFormatPoruke {
        if(objekat == null){
            throw new NeispravanFormatPoruke(uloga + " ne smije biti null!");
        }
    }

    public static void provjeriValidnostTekst(String tekst) throws NeispravanFormatPoruke {
        if(tekst == null){
            throw new NeispravanFormatPoruke("Tekst ne smije biti null!");
        }

        if(tekst.isBlank()) {
            throw new NeispravanFormatPoruke("Tekst ne smije biti prazan!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Poruka poruka = (Poruka) o;
        return Objects.equals(posiljalac, poruka.posiljalac) &&
                Objects.equals(primalac, poruka.primalac) &&
                Objects.equals(datumSlanja, poruka.datumSlanja) &&
                Objects.equals(tekst, poruka.tekst) &&
                statusPoruke == poruka.statusPoruke;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posiljalac, primalac, datumSlanja, tekst, statusPoruke);
    }
}
