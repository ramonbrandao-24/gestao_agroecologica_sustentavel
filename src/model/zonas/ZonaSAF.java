package model.zonas;

import java.util.ArrayList;

import model.Terreno;
import model.SubZonaPlantio;
import model.plantas.Cultura;

public class ZonaSAF extends ZonaAgroecologica{
    ArrayList<Cultura> culturasPermitidasEmSAF = new ArrayList<>(); // Plantas aprovadas na Etapa 1.
    ArrayList<Cultura> culturasSelecionadaEmSAF = new ArrayList<>(); // Plantas aprovadas na Etapa 2.
    ArrayList<SubZonaPlantio> subZonasSAF = new ArrayList<>();
    
    public ZonaSAF(Terreno terreno){
        super(
            terreno,
            "Sistema Agroflorestal",
            "• O que é: Um sistema que imita uma floresta natural, consorciando árvores frutíferas, madeireiras e adubadeiras com lavouras de ciclo curto e médio (como banana, mandioca, milho e feijão) organizadas em diferentes andares de altura e luz.\n" +
            "• Para que serve: Gerar produção contínua e diversificada ao longo de todo o ano (colheitas no curto, médio e longo prazo) e produzir biomassa (folhas e galhos de poda).\n" +
            "• Importância para o equilíbrio da propriedade: É o coração sustentável do terreno; cobre o solo contra o sol forte, retém a umidade da chuva, evita a erosão e regenera a fertilidade natural da terra sem depender de insumos químicos.",
            1
        );
    }

    public ArrayList<Cultura> filtrarPreSelecao(){
        culturasPermitidasEmSAF.clear();

        for (Cultura cultura : Cultura.getCatalogoSAF()){
            if (cultura.isPassaNaPreSelecao(terreno.getClimaRegiao(), super.getTamanhoZonaM2())){
                culturasPermitidasEmSAF.add(cultura);
            }
        }
        return culturasPermitidasEmSAF;
    }

    public  ArrayList<Cultura> filtragemPorUsuario(int indiceCulturaSelecionada){
        if (indiceCulturaSelecionada < 0 || indiceCulturaSelecionada >= culturasPermitidasEmSAF.size()) {
            return culturasSelecionadaEmSAF; // Índice inválido, retorna a lista sem alterações
        }

        if (culturasPermitidasEmSAF.contains(culturasPermitidasEmSAF.get(indiceCulturaSelecionada))){
            if (!culturasSelecionadaEmSAF.contains(culturasPermitidasEmSAF.get(indiceCulturaSelecionada))){
                culturasSelecionadaEmSAF.add(culturasPermitidasEmSAF.get(indiceCulturaSelecionada));
            } else{
                // Essa cultura está permidita, porem já foi adicionada as culturas selecionadas
            }
        } else{
            // Essa cultura não está permidita
        }
        return culturasSelecionadaEmSAF;
    }

	public ArrayList<SubZonaPlantio> adicionarSubZonaEmSAF(int indiceCulturaSelecionada, double percentualSubZona){
        if (indiceCulturaSelecionada < 0 || indiceCulturaSelecionada >= culturasSelecionadaEmSAF.size()) {
            return subZonasSAF; // Índice inválido, retorna a lista sem alterações
        }

        Cultura cultura = culturasSelecionadaEmSAF.get(indiceCulturaSelecionada);
        if (cultura.getAreaMinimaExigidaPorPlantaM2() <= ((percentualSubZona / 100) * super.getTamanhoZonaM2())){
            if (getPercentualTotalAlocadoSAF() + percentualSubZona > 100){
                // Passa de 100%
            } else{
                subZonasSAF.add(
                    new SubZonaPlantio(
                        cultura,
                        percentualSubZona,
                        super.getTamanhoZonaM2()
                    )
                );
            }
        } else{
            // O percentual dessa subZona não é o suficiente para a cultura.getnomePlanta;
        }
        return subZonasSAF;
    }

    public void editarSubZonaSAF(int indiceCulturaSelecionada, double novoPercentualSubZona){
        if (indiceCulturaSelecionada >= 0 && indiceCulturaSelecionada < subZonasSAF.size()){
            SubZonaPlantio subZonaAlvo = subZonasSAF.get(indiceCulturaSelecionada);
            if (getPercentualTotalAlocadoSAF() + novoPercentualSubZona - subZonaAlvo.getSubPercentualZonaPlantasCanteiro() > 100){
                // Passa de 100%
            } else {
                subZonaAlvo.setAtualizarPercentual(novoPercentualSubZona, super.getTamanhoZonaM2());
            }
        } else {
            // indice invalido
        }
    }

    public void removerSubZonaSAF(int indiceCulturaSelecionada){
        if (indiceCulturaSelecionada >= 0 && indiceCulturaSelecionada < subZonasSAF.size()){
            subZonasSAF.remove(indiceCulturaSelecionada);
        } else {
            // indice invalido
        }
    }
	
    // • getPercentualTotalAlocadoSAF()
    public double getPercentualTotalAlocadoSAF(){
        double percentualTotalAlocado = 0;

        for (SubZonaPlantio subZonaAlvo : subZonasSAF){
            percentualTotalAlocado += subZonaAlvo.getSubPercentualZonaPlantasCanteiro();
        }
        return percentualTotalAlocado;
    }

    public double getPercentualTotalOcupadoSAF(){
        double percentualTotalOcupado = 0;
        
        for (SubZonaPlantio subZonaAlvo : subZonasSAF){
            Cultura tamanhoPorPlanta = subZonaAlvo.getCulturaPrincipalCanteiro();
            percentualTotalOcupado += (tamanhoPorPlanta.getAreaMinimaExigidaPorPlantaM2() * subZonaAlvo.getQuantidadeAtualPlantasCanteiro());
        }
        return (percentualTotalOcupado / super.getTamanhoZonaM2()) * 100;
    }
    
    public boolean isZonaTotalmentePreenchida(){
        if (getPercentualTotalAlocadoSAF() == 100){
            return true;
        }
        return false; // Pode ser falso por ser menor que 100 ou por ser maior
    }
    // Guarda a lista de SubZonaPlantio destinadas às árvores, frutíferas e roça consorciada.
    // Consórcio de árvores frutíferas, madeireiras e lavouras (banana, mandioca, milho, feijão).
    // É o coração sustentável da propriedade: produz a médio/longo prazo, protege o solo do sol e gera matéria orgânica através das podas.
}
