package mapabits;

public class GestorMemoria {
    private final byte[] mapa;
    private final int totalBits;

    public GestorMemoria(int bytes) {
        this.mapa = new byte[bytes];
        this.totalBits = bytes * 8;
    }

    public synchronized boolean asignarMemoria(ProcesoMapaBits p) {
        int consecutivos = 0;
        int indiceInicio = -1;

        // Buscar bit a bit para encontrar espacio para el proceso
        for (int i = 0; i < totalBits; i++) {
            int indiceByte = i / 8;
            int indiceBit = i % 8; // itera ciclicamente de 0 al 7

            // Verificar si el bit del byte esta ocupado
            boolean ocupado = (mapa[indiceByte] & (1 << indiceBit)) != 0;

            // Si no esta ocupado, cuenta los bits disponibles consecutivos para asignarle
            // memoria al proceso
            if (!ocupado) {
                // Guardar la primera posicion de los consecutivos
                if (consecutivos == 0) {
                    indiceInicio = i;
                }
                consecutivos++;

                // Si encuentra espacio, prende los bits consecutivos
                if (consecutivos == p.getTam()) {
                    p.setIndiceInicio(indiceInicio);

                    for (int j = indiceInicio; j < indiceInicio + p.getTam(); j++) {
                        int indiceByteAsignado = j / 8;
                        int indiceBitAsignado = j % 8;
                        mapa[indiceByteAsignado] = (byte) (mapa[indiceByteAsignado] | (1 << indiceBitAsignado));
                    }

                    return true; // Memoria asignada con exito

                }
            } else { // Si no, reinicia la cuenta
                consecutivos = 0;
            }

        }

        System.out.println("Sin espacio en la memoria");
        return false;
    }

    public synchronized void liberarMemoria(ProcesoMapaBits p) {
        for (int i = p.getIndiceInicio(); i < p.getIndiceInicio() + p.getTam(); i++) {
            int indiceByte = i / 8;
            int indiceBit = i % 8;

            // Apagar los bits asignados al proceso
            mapa[indiceByte] = (byte) (mapa[indiceByte] & ~(1 << indiceBit));
        }
        System.out.println("Proceso_" + p.getId() + " termino. Libero " + p.getTam() + " kb.");
    }

}
