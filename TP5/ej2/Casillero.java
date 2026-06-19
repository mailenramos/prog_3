public class Casillero{
    int numero;
    boolean norte;
    boolean sur;
    boolean este;
    boolean oeste;
    
    public Casillero(int numero){
        this.numero=numero;
        this.norte=null;
        this.sur=null;
        this.este=null;
        this.oeste=null;
    }

    public setNorte(boolean b){
        this.norte=b;
    }
    public setSur(boolean b){
        this.norte=b;
    }
    public setEste(boolean b){
        this.norte=b;
    }
    public setOeste(boolean b){
        this.norte=b;
    }
    public List<Casillero> getVecinos(){
        
    }
    
}