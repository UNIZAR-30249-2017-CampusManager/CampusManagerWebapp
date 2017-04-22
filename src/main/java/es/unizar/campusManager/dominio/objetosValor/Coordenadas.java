package es.unizar.campusManager.dominio.objetosValor;

public class Coordenadas {

    private final String x;
    private final String y;
    private final String sistemaReferenciaCoordenadas;

    public Coordenadas(String x, String y, String sistemaReferenciaCoordenadas) {
        this.x = x;
        this.y = y;
        this.sistemaReferenciaCoordenadas = sistemaReferenciaCoordenadas;
    }

    public String getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    public String getSistemaReferenciaCoordenadas() {
        return sistemaReferenciaCoordenadas;
    }

    @Override
    public String toString() {
        return "{\"_class\":\"Coordenadas\", " +
                "\"x\":" + (x == null ? "null" : "\"" + x + "\"") + ", " +
                "\"y\":" + (y == null ? "null" : "\"" + y + "\"") + ", " +
                "\"sistemaReferenciaCoordenadas\":" + (sistemaReferenciaCoordenadas == null ? "null" : "\"" + sistemaReferenciaCoordenadas + "\"") +
                "}";
    }
}
