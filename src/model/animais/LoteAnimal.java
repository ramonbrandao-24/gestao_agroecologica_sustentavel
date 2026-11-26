package model.animais;

public class LoteAnimal {
    private EspecieAnimal especie;
    private double subPercentualZona;
    private double areaDestinadaM2;
    private int capacidadeMaxima;
    private int quantidadeAtual;

    // -------------------  CONSTRUTORES  -------------------
    public LoteAnimal(EspecieAnimal especie, double subPercentualZona, double areaZonaTotalM2){
        this.especie = especie;
        this.subPercentualZona = subPercentualZona;
        this.areaDestinadaM2 = areaZonaTotalM2 * (subPercentualZona / 100.0);
        this.capacidadeMaxima = especie.calcularCapacidadeMaxima(this.areaDestinadaM2);
        this.quantidadeAtual = 0;
    }

    // -------------------  MÉTODOS DE LOTE  -------------------
    // Adiciona ou remove animais respeitando o mínimo de 0 e a trava ética de capacidade máxima
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

    // Valida se a área destinada a este lote atinge o espaço mínimo exigido pela espécie
    public boolean isEspacoSuficiente() {
        return this.areaDestinadaM2 >= this.especie.getAreaMinimaM2();
    }

    // Valida previamente se um percentual atingirá o espaço mínimo antes de criar o lote definitivo
    public static boolean isEspacoSuficiente(EspecieAnimal especie, double subPercentualZona, double areaZonaTotalM2) {
        return (areaZonaTotalM2 * (subPercentualZona / 100.0)) >= especie.getAreaMinimaM2();
    }

    // -------------------  ESPÉCIE ANIMAL  -------------------
    // Retorna o objeto da espécie vinculada a este lote
    public EspecieAnimal getEspecie() {
        return this.especie;
    }
    
    // -------------------  SUB-PERCENTUAL DA ZONA  -------------------
    // Retorna o percentual da Zona de Piquetes destinado a esta espécie
    public double getSubPercentualZona2(){
        return this.subPercentualZona;
    }

    // -------------------  ÁREA DESTINADA EM M²  -------------------
    // Retorna a área real em metros quadrados calculada para este lote
    public double getAreaDestinadaM2(){
        return this.areaDestinadaM2;
    }

    // -------------------  CAPACIDADE MÁXIMA DE ANIMAIS  -------------------
    // Retorna a lotação máxima de animais suportada na área deste lote
    public int getCapacidadeMaxima(){
        return this.capacidadeMaxima;
    }

    // -------------------  QUANTIDADE ATUAL DE ANIMAIS  -------------------
    // Retorna a quantidade atual de animais cadastrados neste lote
    public int getQuantidadeAtual(){
        return this.quantidadeAtual;
    }
}
