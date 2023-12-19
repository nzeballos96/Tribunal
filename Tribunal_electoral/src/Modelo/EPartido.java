package Modelo;

public enum EPartido {

    FRENTE_DE_TODOS("Frente de Todos"),
    JUNTOS_POR_EL_CAMBIO("Juntos por el Cambio"),
    REVOLUCIÓN_CIVIL("Revolución Civil"),
    LIBRES_DEL_SUR("Libres del Sur"),
    PODEMOS("Podemos");


    private String partido;

    private EPartido(String partido) {
        this.partido = partido;
    }

    public String getPartido() {
        return partido;
    }


    public static EPartido fromPartido(String partido) {
        for (EPartido epartido : values()) {
            if (epartido.partido.equals(partido)) {
                return epartido;
            }
        }

        return null;
    }
}

	