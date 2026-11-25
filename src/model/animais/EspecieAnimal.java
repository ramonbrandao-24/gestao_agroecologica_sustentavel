package model.animais;

public abstract class EspecieAnimal {
    private String nomeEspecie;
    private double areaMinimaM2;
    private double m2PorAnimal;
    private boolean exigeTanque = false;
    private String dicaIntegracao;

    public EspecieAnimal(String nomeEspecie, double areaMinimaM2, double m2PorAnimal, boolean exigeTanque, String dicaIntegracao){
        this.nomeEspecie = nomeEspecie;
        this.areaMinimaM2 = areaMinimaM2;
        this.m2PorAnimal = m2PorAnimal;
        this.dicaIntegracao = dicaIntegracao;
    }

    // Calcular capacidade maxima para cada area, com base no animal
    public int calcularCapacidadeMaxima(double areaM2){
        if (areaM2 < this.areaMinimaM2){
            return 0;
        }
        return (int) (areaM2 / m2PorAnimal);
    }

    public String getNomeEspecie(){
        return this.nomeEspecie;
    }

    public double getAreaMinimaM2(){
        return this.areaMinimaM2;
    }
    public boolean isExigeTanque(){
        return exigeTanque;
    }

    public String getDicaIntegracao(){
        return dicaIntegracao;
    }
}
