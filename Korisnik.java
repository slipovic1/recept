package ba.unsa.etf.rpr;

import java.util.Objects;

public class Korisnik {
    private String ime;
    private String prezime;
    private String nadimak;

    public Korisnik(String ime, String prezime, String nadimak) throws IllegalArgumentException {
       provjeriValidnostImePrezume(ime);
       provjeriValidnostImePrezume(prezime);
       provjeriValidnostNadimak(nadimak);
        this.ime = ime;
        this.prezime = prezime;
        this.nadimak = nadimak;
    }

    private static void provjeriValidnostImePrezume(String s) throws IllegalArgumentException {
        if(s==null) {
            throw new IllegalArgumentException("Ime, prezime i nadimak ne smiju biti null!");
        }

        if(s.isBlank()){
            throw new IllegalArgumentException("Ime, prezime i nadimak ne smiju biti prazni!");
        }
    }

    private static void provjeriValidnostNadimak(String nadimak) throws IllegalArgumentException {
        if(nadimak==null) {
            throw new IllegalArgumentException("Ime, prezime i nadimak ne smiju biti null!");
        }

        if(nadimak.isBlank()){
            throw new IllegalArgumentException("Ime, prezime i nadimak ne smiju biti prazni!");
        }

        if(nadimak.length() < 5){
            throw new IllegalArgumentException("Nadimak mora imati 5 ili više karaktera!");
        }
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) throws IllegalAccessException {
        provjeriValidnostImePrezume(ime);
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) throws IllegalAccessException {
        provjeriValidnostImePrezume(prezime);
        this.prezime = prezime;
    }

    public String getNadimak() {
        return nadimak;
    }

    public void setNadimak(String nadimak) throws IllegalAccessException {
        provjeriValidnostNadimak(nadimak);
        this.nadimak = nadimak;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " (" + nadimak + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return Objects.equals(ime, korisnik.ime) &&
                Objects.equals(prezime, korisnik.prezime) &&
                Objects.equals(nadimak, korisnik.nadimak);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, nadimak);
    }
}
