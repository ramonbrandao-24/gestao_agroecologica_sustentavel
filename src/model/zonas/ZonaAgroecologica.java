package model.zonas;

import model.Terreno;

public abstract class ZonaAgroecologica {
    protected Terreno terreno;
    private String tituloZona;
    private String descricaoZona; // O que é? Para que serve? Qual sua importância para o equilíbrio da propriedade?
    private double tamanhoZonaPercentual;
    private double tamanhoZonaM2;

    // -------------------  CONSTRUTORES  -------------------
    // Inicializa a zona buscando o percentual na lista de Terreno e calculando sua área real (m²)
    public ZonaAgroecologica(Terreno terreno, String tituloZona, String descricaoZona, int indice){
        this.terreno = terreno;
        this.tituloZona = tituloZona;
        this.descricaoZona = descricaoZona;
        this.tamanhoZonaPercentual = terreno.getListaPercentualZonas().get(indice);
        this.tamanhoZonaM2 = calcularAreaEmM2(tamanhoZonaPercentual, terreno.getTamanhoTerreno());
    }

    // -------------------  MÉTODOS DE CÁLCULO  -------------------
    // Converte o percentual da zona para metros quadrados (m²) com base no tamanho total do terreno
    public static double calcularAreaEmM2(double percentual, double tamanhoTerreno){
        if (percentual < 0 || percentual > 100 || tamanhoTerreno <= 0){
            return -1;
        }
        return (tamanhoTerreno / 100.0) * percentual;
    }

    // -------------------  TÍTULO DA ZONA  -------------------
    // Retorna o nome oficial da zona agroecológica
    public String getTituloZona() {
        return tituloZona;
    }

    // -------------------  DESCRIÇÃO DA ZONA  -------------------
    // Retorna a explicação educativa (O que é, para que serve e importância)
    public String getDescricaoZona() {
        return descricaoZona;
    }

    // -------------------  TAMANHO DA ZONA EM PORCENTAGEM  -------------------
    // Retorna o percentual do terreno destinado a esta zona (0% a 100%)
    public double getTamanhoZonaPercentual() {
        return tamanhoZonaPercentual;
    }

    // -------------------  TAMANHO DA ZONA EM M²  -------------------
    // Retorna a área real em metros quadrados destinada a esta zona
    public double getTamanhoZonaM2() {
        return tamanhoZonaM2;
    }

    // -------------------  EXIBIÇÃO FORMATADA  -------------------
    // Formata o resumo da zona com numeração, percentual, área em m² e descrição educativa
    public String toString(int indice){
        return (indice + 1) + ". " + tituloZona + " - " + tamanhoZonaPercentual + "% (" + tamanhoZonaM2 + "m²)\n" + descricaoZona;
        // 1. Horta Biointensiva e Medicinal - 20% (65m²)          // Exemplo //
    }
}
