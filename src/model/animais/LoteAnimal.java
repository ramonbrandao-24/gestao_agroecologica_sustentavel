package model.animais;

public class LoteAnimal {
    // Atributos: EspecieAnimal especie, subPercentualZona (ex: 10%), areaDestinadaM2, capacidadeMaxima, quantidadeAtual.
    private EspecieAnimal especie;
    private double subPercentualZona;
    private double areaDestinadaM2;
    private int capacidadeMaxima;
    private int quantidadeAtual;

    public LoteAnimal(EspecieAnimal especie, double subPercentualZona, double areaZonaTotalM2){
        this.especie = especie;
        this.subPercentualZona = subPercentualZona;
        this.areaDestinadaM2 = areaZonaTotalM2 * (subPercentualZona / 100.0);
        this.capacidadeMaxima = especie.calcularCapacidadeMaxima(this.areaDestinadaM2);
        this.quantidadeAtual = 0;
    }

    public boolean isEspacoSuficiente() {
        return this.areaDestinadaM2 >= this.especie.getAreaMinimaM2();
    }
    
    public void alterarQuantidade(int quantidade){
        int novaQtd = this.quantidadeAtual + quantidade;
        if (novaQtd < 0){
            this.quantidadeAtual = 0;
        } else{
            if (novaQtd > getCapacidadeMaxima()){
                System.out.println("Ação Proibida: Capacidade Máxima Excedida! Máximo permitido: " + this.capacidadeMaxima);
            } else {
                this.quantidadeAtual = novaQtd;
            }
        }
    }
    
    public double getSubPercentualZona2(){
        return this.subPercentualZona;
    }

    public double getAreaDestinadaM2(){
        return this.areaDestinadaM2;
    }

    public int getCapacidadeMaxima(){
        return this.capacidadeMaxima;
    }

    public int getQuantidadeAtual(){
        return this.quantidadeAtual;
    }
}
