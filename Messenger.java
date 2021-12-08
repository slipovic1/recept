package ba.unsa.etf.rpr;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Messenger {
    private List<Poruka> poruke;

    public Messenger(){
        poruke = new ArrayList<>();
    }

    public List<Poruka> dajSvePoruke(){
        return poruke;
    }

    public void posaljiPoruku(Korisnik posiljalac, Korisnik primalac, String tekst) throws NeispravanFormatPoruke {
        poruke.add(new Poruka(posiljalac, primalac, LocalDateTime.now(), tekst));
    }

    public void posaljiPoruku(Poruka poruka){
        poruke.add(poruka);
    }

    public void posaljiPoruke(List<Poruka> poruke){
        this.poruke.addAll(poruke);
    }

    public void ponistiSlanje(Poruka poruka) throws NeispravnaAkcija {
        int index = poruke.indexOf(poruka);

        if(index == -1){
            throw new NeispravnaAkcija("Nije moguće poništiti slanje poruke koja nije nikada poslana!");
        }

        if(poruke.get(index).getStatusPoruke().equals(StatusPoruke.PROCITANA)){
            throw new NeispravnaAkcija("Nije moguće poništiti slanje poruke koja je već pročitana!");
        }

        poruke.remove(poruka);
    }

    public void procitajPoruku(Poruka poruka) throws NeispravnaAkcija {
        int index = poruke.indexOf(poruka);

        if(index == -1){
            throw new NeispravnaAkcija("Nije moguće pročitati poruku koja nije nikada poslana!");
        }

        poruke.get(index).setStatusPoruke(StatusPoruke.PROCITANA);
    }

    public void oznaciKaoNeprocitano(List<Poruka> poruke) throws NeispravnaAkcija {
        if(poruke.stream().anyMatch(poruka -> !this.poruke.contains(poruka))){
            throw new NeispravnaAkcija("Neke od poruka koje pokušavate označiti kao nepročitane nisu poslane!");
        }

        poruke.stream().forEach(poruka -> {
            int index = this.poruke.indexOf(poruka);
            this.poruke.get(index).setStatusPoruke(StatusPoruke.NEPROCITANA);
        });
    }

    public Map<Korisnik, List<Poruka>> dajNeprocitanePoruke(){
        Map<Korisnik, List<Poruka>> mapa = new HashMap<>();
        for (Poruka poruka : poruke){
            if(!mapa.containsKey(poruka.getPrimalac())){
                mapa.put(poruka.getPrimalac(), new ArrayList<>());
            }

            if(poruka.getStatusPoruke().equals(StatusPoruke.NEPROCITANA)) {
                mapa.get(poruka.getPrimalac()).add(poruka);
            }
        }

        return mapa;
    }

    public List<Poruka> dajPristiglePorukeZaKorisnika(Korisnik korisnik){
        return poruke
                .stream()
                .filter(poruka -> poruka.getPrimalac().equals(korisnik))
                .collect(Collectors.toList());
    }

    public List<Poruka> dajPorukeZaKorisnika(Korisnik korisnik, StatusPoruke statusPoruke){
        return  dajPristiglePorukeZaKorisnika(korisnik)
                .stream()
                .filter(poruka -> poruka.getStatusPoruke().equals(statusPoruke))
                .collect(Collectors.toList());
    }

    public List<Poruka> filtrirajPoruke(Predicate<Poruka> kriterij){
        return poruke
                .stream()
                .filter(kriterij)
                .collect(Collectors.toList());
    }

    public List<Poruka> dajStarijeOd(Korisnik korisnik, LocalDateTime datum){
        return filtrirajPoruke(poruka ->
                poruka.getPosiljalac().equals(korisnik) &&
                        (poruka.getDatumSlanja().isBefore(datum)) || poruka.getDatumSlanja().equals(datum));
    }

    @Override
    public String toString() {
        return poruke
                .stream()
                .map(Poruka::toString)
                .collect(Collectors.joining("\n"));
    }
}
