package model.zonas;

import model.Terreno;

public abstract class ZonaAgroecologica {
    protected Terreno terreno;
    private String tituloZona;
    private String descricaoZona; // O que é? Para que serve? Qual sua importância para o equilíbrio da propriedade?
    private double tamanhoZonaPercentual;
    private double tamanhoZonaM2;

    // -------------------  CONSTRUTORES  -------------------
    public ZonaAgroecologica(Terreno terreno, String tituloZona, String descricaoZona, int indice){
        this.terreno = terreno;
        this.tituloZona = tituloZona;
        this.descricaoZona = descricaoZona;
        this.tamanhoZonaPercentual = terreno.getListaPercentualZonas().get(indice);
        this.tamanhoZonaM2 = calcularAreaEmM2(tamanhoZonaPercentual, terreno.getTamanhoTerreno());
    }

    // Converte de porcentagem para m2
    public static double calcularAreaEmM2(double percentual, double tamanhoTerreno){
        if (percentual < 0 || percentual > 100 || tamanhoTerreno <= 0){
            return -1;
        }
        return (tamanhoTerreno / 100.0) * percentual;
    }

    // -------------------  NOME DA ZONA  -------------------
    public String getTituloZona() {
        return tituloZona;
    }

    // -------------------  DESCRIÇÃO DA ZONA  -------------------
    public String getDescricaoZona() {
        return descricaoZona;
    }

    // -------------------  TAMANHO DA ZONA EM PORCENTAGEM  -------------------
    public double getTamanhoZonaPercentual() {
        return tamanhoZonaPercentual;
    }

    // -------------------  TAMANHO DA ZONA EM M2  -------------------
    public double getTamanhoZonaM2() {
        return tamanhoZonaM2;
    }

    public String toString(int indice){
        return (indice + 1) + ". " + tituloZona + " - " + tamanhoZonaPercentual + "% (" + tamanhoZonaM2 + "m²)\n" + descricaoZona;
        // 1. Horta Biointensiva e Medicinal - 20% (65m²)          // Exemplo //
    }
}
