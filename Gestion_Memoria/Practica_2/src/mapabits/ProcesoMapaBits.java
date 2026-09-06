package mapabits;

import comun.Proceso;

public class ProcesoMapaBits extends Proceso {
    private int indiceInicio;

    public ProcesoMapaBits(int id, int tiempo, int tam) {
        super(id, tiempo, tam);
    }

    public int getIndiceInicio() {
        return indiceInicio;
    }

    public void setIndiceInicio(int indiceInicio) {
        this.indiceInicio = indiceInicio;
    }
}
