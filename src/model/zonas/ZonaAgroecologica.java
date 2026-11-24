package model.zonas;

public abstract class ZonaAgroecologica {
    //  acompanhado do percentual sugerido e da conversão automática em metros quadrados (m²)

    private String tituloZona;
    private String descricaoZona; // O que é? Para que serve? Qual sua importância para o equilíbrio da propriedade?
    private double tamanhoZona;
    private double tamanhoZonaM2;

    public ZonaAgroecologica(String tituloZona, String descricaoZona, double tamanhoZona, double tamanhoTerreno){
        this.tituloZona = tituloZona;
        this.descricaoZona = descricaoZona;
        this.tamanhoZona = tamanhoZona;
        this.tamanhoZonaM2 = calcularAreaEmM2(tamanhoZona, tamanhoTerreno);
    }

    public static double calcularAreaEmM2(double percentual, double tamanhoTerreno){
        if (percentual < 0 || percentual > 100 || tamanhoTerreno <= 0){
            return -1;
        }
        return (tamanhoTerreno / 100.0) * percentual;
    }
}
