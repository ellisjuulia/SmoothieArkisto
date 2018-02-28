package SmoothieArkisto;

public class AnnosRaakaAine {
    
    Annos annos;
    RaakaAine raakaAine;
    String jarjestys;
    String maara;
    String ohje;
    
    public AnnosRaakaAine (Annos annos, RaakaAine raakaAine, String jarjestys, String maara, String ohje) {
        this.annos = annos;
        this.raakaAine = raakaAine;
        this.jarjestys = jarjestys;
        this.maara = maara;
        this.ohje = ohje;
    }
    
    public Annos getAnnos() {
        return annos;
    }

    public RaakaAine getRaakaAine() {
        return raakaAine;
    }
    
    public String getJarjestys() {
        return jarjestys;
    }
    
    public String getMaara() {
        return maara;
    }

    public String getOhje() {
        return ohje;
    }
    
    public void setAnnos (Annos annos) {
        this.annos = annos;
    }
    
    public void setRaakaAine(RaakaAine raakaAine) {
        this.raakaAine = raakaAine;
    }
    
    public void setJarjestys (String jarjestys) {
        this.jarjestys = jarjestys;
    }
    
    public void setMaara(String maara) {
        this.maara = maara;
    }
    
    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
}
