package model;

import java.util.ArrayList;
import model.plantas.Cultura;

// Crio objeto subZona que se acopla a caracteristicas e requisitos de uma Planta/Cultura, montando um cotivo de coperação e diverso, como os principios de Agrofloresta, Sustentabilidade
public class SubZonaPlantio {
    private String nomeSubZonaPlantasCanteiro;
    private Cultura culturaPrincipalCanteiro;
    private double subPercentualZonaPlantasCanteiro;
    private double areaDestinadaZonaPlantasCanteiroM2;
    private int quantidadeMinimaPlantasCanteiro;
    private int quantidadeAtualPlantasCanteiro;
    private int quantidadeMaximaPlantasCanteiro;

    // Culturas que estão em consorcio
    ArrayList<Cultura> culturasConsorciadasCanteiro = new ArrayList<>();
    ArrayList<Integer> quantidadesConsorciadas = new ArrayList<>();
    // Plantas companheiras adicionadas na entrelinha e suas quantidades (50% da densidade).

    public SubZonaPlantio(Cultura culturaPrincipalCanteiro, double subPercentualZonaPlantasCanteiro, double tamanhoZonaPrincipalM2){
        this.nomeSubZonaPlantasCanteiro = "Canteiro de " + culturaPrincipalCanteiro.getNomePlanta();
        this.culturaPrincipalCanteiro = culturaPrincipalCanteiro;
        setAtualizarPercentual(subPercentualZonaPlantasCanteiro, tamanhoZonaPrincipalM2);
    }

    public void setAtualizarPercentual(double novoPercentual, double tamanhoZonaPrincipalM2){
        this.subPercentualZonaPlantasCanteiro = novoPercentual;
        this.areaDestinadaZonaPlantasCanteiroM2 = (novoPercentual / 100) * tamanhoZonaPrincipalM2;
        this.quantidadeMinimaPlantasCanteiro = this.culturaPrincipalCanteiro.isAutofertil() ? 1 : 2;
        this.quantidadeAtualPlantasCanteiro = quantidadeMinimaPlantasCanteiro;
        this.quantidadeMaximaPlantasCanteiro = culturaPrincipalCanteiro.calcularCapacidadeMaxima(this.areaDestinadaZonaPlantasCanteiroM2);
    }

    public void setQuantidadeAtualPlantasCanteiro(int novaQuantidade){
        if (novaQuantidade < this.quantidadeMinimaPlantasCanteiro){
            this.quantidadeAtualPlantasCanteiro = quantidadeMinimaPlantasCanteiro;
        } else if(novaQuantidade > this.quantidadeMaximaPlantasCanteiro){
            this.quantidadeAtualPlantasCanteiro = quantidadeMaximaPlantasCanteiro;
        } else{
            this.quantidadeAtualPlantasCanteiro = novaQuantidade;
        }
    }
    // Vamos usar Numeric Stepper(JSpinner), então esse metodo vai ter sobrecarga
    // Parâmetros: (valorInicial, minimo, maximo, passo)
    // SpinnerNumberModel modelo = new SpinnerNumberModel(quantidadeAtualPlantasCanteiro, quantidadeMinimaPlantasCanteiro, quantidadeMaximaPlantasCanteiro, 1);
    
    //Bloqueia se houver conflito (temConflitoCom) ou falta de espaço mínimo; se passar, adiciona na lista de consórcios calculando 50% da densidade.
    // public void adicionarConsorciadaCanteiro (Cultura companheira){
    //     if (!culturaPrincipalCanteiro.temConflito(companheira) && this.areaDestinadaZonaPlantasCanteiroM2 >= companheira.getAreaMinimaExigidaPorPlantaM2()){
    //         int qtdMax = (int) (companheira.calcularCapacidadeMaxima(this.areaDestinadaZonaPlantasCanteiroM2) * 0.5);
    //         int qtdMin = companheira.isAutofertil() ? 1 : 2;
    //         int qtdFinal = Math.max(qtdMin, qtdMax);

    //         culturasConsorciadasCanteiro.add(companheira);
    //         quantidadesConsorciadas.add(qtdFinal);
    //     } // Tenho que validar se ele que uma cultura companheira, posso sobrecarregar a função, se ele passar um numero ao inves da cultura, quer dizer que ele não quer consorcio
    // }

    // A tela chama este método quando o usuário MARCA um Checkbox
    // public void adicionarConsorciadaCanteiro(Cultura companheira) {
    //     int qtdMinima = companheira.isAutofertil() ? 1 : 2;
    //     culturasConsorciadasCanteiro.add(companheira);
    //     quantidadesConsorciadas.add(qtdMinima); // Começa no mínimo para o JSpinner
    // }
    
    public void removerConsorcio(int indice) {
        if (indice >= 0 && indice < culturasConsorciadasCanteiro.size()) {
            culturasConsorciadasCanteiro.remove(indice);
            quantidadesConsorciadas.remove(indice);
        }
    }

    public String getNomeSubZonaPlantasCanteiro(){ return nomeSubZonaPlantasCanteiro;}
    public Cultura getCulturaPrincipalCanteiro(){ return culturaPrincipalCanteiro;}
    public double getSubPercentualZonaPlantasCanteiro(){ return subPercentualZonaPlantasCanteiro;}
    public double getAreaDestinadaZonaPlantasCanteiroM2(){ return areaDestinadaZonaPlantasCanteiroM2;}
    public int getQuantidadeMinimaPlantasCanteiro(){ return quantidadeMinimaPlantasCanteiro;}
    public int getQuantidadeAtualPlantasCanteiro(){ return quantidadeAtualPlantasCanteiro;}
    public int getQuantidadeMaximaPlantasCanteiro(){ return quantidadeMaximaPlantasCanteiro;}
}
