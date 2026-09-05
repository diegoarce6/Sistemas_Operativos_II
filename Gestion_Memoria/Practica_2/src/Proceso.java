public class Proceso {
    private int id;
    private int tiempo;
    private int tam;


    Proceso(int id, int tiempo, int tam) {
        this.id = id;
        this.tiempo = tiempo;
        this.tam = tam;
    }

    
    public int getId() {
        return id;
    }

    public int getTam() {
        return tam;
    }

    public int getTiempo() {
        return tiempo;
    }
    

    @Override
    public String toString() {
        return "Proceso_" + id + " tiempo: " + tiempo + " seg" + " tamanio: " + tam + "kb" ;
    }

}
