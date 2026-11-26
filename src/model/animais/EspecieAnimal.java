package model.animais;

public abstract class EspecieAnimal {
    private String nomeEspecie;
    private double areaMinimaM2;
    private double m2PorAnimal;
    private boolean exigeTanque = false;
    private String dicaIntegracao;

    // -------------------  CONSTRUTORES  -------------------
    // Inicializa os parâmetros técnicos e éticos específicos de cada espécie animal
    public EspecieAnimal(String nomeEspecie, double areaMinimaM2, double m2PorAnimal, boolean exigeTanque, String dicaIntegracao){
        this.nomeEspecie = nomeEspecie;
        this.areaMinimaM2 = areaMinimaM2;
        this.m2PorAnimal = m2PorAnimal;
        this.exigeTanque = exigeTanque;
        this.dicaIntegracao = dicaIntegracao;
    }

    // -------------------  MÉTODOS DE ESPÉCIE  -------------------
    // Calcula a lotação máxima permitida na área (m²), respeitando a trava de espaço mínimo
    public int calcularCapacidadeMaxima(double areaM2){
        if (areaM2 < this.areaMinimaM2){
            return 0;
        }
        return (int) (areaM2 / m2PorAnimal);
    }

    // -------------------  NOME DA ESPÉCIE  -------------------
    // Retorna o nome da espécie animal (ex: Bovino, Galinha Caipira, Peixe)
    public String getNomeEspecie(){
        return this.nomeEspecie;
    }

    // -------------------  ÁREA MÍNIMA EXIGIDA (M²)  -------------------
    // Retorna a área mínima obrigatória em metros quadrados para criar a espécie
    public double getAreaMinimaM2(){
        return this.areaMinimaM2;
    }

    // -------------------  DENSIDADE POR ANIMAL (M²)  -------------------
    // Retorna o espaço mínimo em metros quadrados exigido por cada indivíduo
    public double getM2PorAnimal() {
        return this.m2PorAnimal;
    }

    // -------------------  EXIGÊNCIA DE TANQUE  -------------------
    // Indica se a espécie exige construção de tanque/lâmina d'água (true para piscicultura)
    public boolean isExigeTanque(){
        return exigeTanque;
    }

    // -------------------  DICA DE INTEGRAÇÃO AGROECOLÓGICA  -------------------
    // Retorna a recomendação técnica de manejo integrado da espécie na propriedade
    public String getDicaIntegracao(){
        return dicaIntegracao;
    }
}
