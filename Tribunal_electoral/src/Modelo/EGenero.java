package Modelo;

public enum EGenero {

    MASCULINO("Masculino"),
    FEMENINO("Femenino"),
    OTRO("Otro");

    private String genero;

    EGenero(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public static EGenero fromGenero(String genero) {
        for (EGenero egenero : values()) {
            if (egenero.genero.equals(genero)) {
                return egenero;
            }
        }
        return null;
    }
	
}
