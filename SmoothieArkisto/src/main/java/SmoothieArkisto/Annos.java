package SmoothieArkisto;

public class Annos {
    
    Integer id;
    String nimi;
    
    public Annos (Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }
    
    public Integer getId() {
        return id;
    }

    public String getNimi() {
        return nimi;
    }
    
    public void setId (Integer id) {
        this.id = id;
    }
    
    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
}
