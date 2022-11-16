public abstract class Tanques{
    protected String nombre;
    protected int salud;
    
    public Tanques(){}
    public String getNombre(){
        return this.nombre;
    }
    public int getSalud(){
        return this.salud;
    }
    public void setSalud(int s){
        this.salud= s;
    }
}
