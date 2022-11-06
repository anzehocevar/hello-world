package si.fri.prpo;

public class Uporabnik extends Entiteta {
    private String ime;
    private String priimek;
    private String uporabnisko;

    public Uporabnik(String i, String p, String u){
        this.ime = i;
        this.priimek = p;
        this.uporabnisko = u;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getUporabnisko() {
        return uporabnisko;
    }

    public void setUporabnisko(String uporabnisko) {
        this.uporabnisko = uporabnisko;
    }

}
