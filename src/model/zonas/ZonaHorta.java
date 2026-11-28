package model.zonas;

import java.util.ArrayList;
import model.Terreno;
import model.plantas.Cultura;
import model.SubZonaPlantio;

public class ZonaHorta extends ZonaAgroecologica{
    ArrayList<Cultura> culturasPermitidasEmHorta = new ArrayList<>(); // Plantas aprovadas na Etapa 1.
    ArrayList<Cultura> culturasSelecionadaEmHorta = new ArrayList<>(); // Plantas aprovadas na Etapa 2.
    ArrayList<SubZonaPlantio> subZonasHorta = new ArrayList<>(); // Canteiros (Horta) ou Talhões (SAF) criados.

    public ZonaHorta(Terreno terreno){
        super(
            terreno,
            "Horta Biointensiva e Medicinal",
            "• O que é: Uma área de cultivo intensivo e diversificado em canteiros próximos à residência, dedicada a plantas de ciclo curto como verduras, temperos, ervas medicinais e PANCs (Plantas Alimentícias Não Convencionais).\n" +
            "• Para que serve: Produzir alimentos frescos de colheita rápida e semanal para o consumo direto da família ou venda imediata em feiras locais.\n" +
            "• Importância para o equilíbrio da propriedade: Aproveita ao máximo pequenos espaços e recebe o adubo orgânico e a água fértil gerados nas outras zonas, fechando o ciclo de nutrientes perto de casa.",
            0
        );
    }
    
	public ArrayList<Cultura> filtrarPreSelecao(){
        culturasPermitidasEmHorta.clear();

        for (Cultura cultura : Cultura.getCatalogoHorta()){
            if (cultura.isPassaNaPreSelecao(terreno.getClimaRegiao(), super.getTamanhoZonaM2())){
                culturasPermitidasEmHorta.add(cultura);
            }
        }
        return culturasPermitidasEmHorta;
    }

    public  ArrayList<Cultura> filtragemPorUsuario(int indiceCulturaSelecionada){
        if (indiceCulturaSelecionada < 0 || indiceCulturaSelecionada >= culturasPermitidasEmHorta.size()) {
            return culturasSelecionadaEmHorta; // Índice inválido, retorna a lista sem alterações
        }

        if (culturasPermitidasEmHorta.contains(culturasPermitidasEmHorta.get(indiceCulturaSelecionada))){
            if (!culturasSelecionadaEmHorta.contains(culturasPermitidasEmHorta.get(indiceCulturaSelecionada))){
                culturasSelecionadaEmHorta.add(culturasPermitidasEmHorta.get(indiceCulturaSelecionada));
            } else{
                // Essa cultura está permidita, porem já foi adicionada as culturas selecionadas
            }
        } else{
            // Essa cultura não está permidita
        }
        return culturasSelecionadaEmHorta;
    }

	public ArrayList<SubZonaPlantio> adicionarSubZonaEmHorta(int indiceCulturaSelecionada, double percentualSubZona){
        if (indiceCulturaSelecionada < 0 || indiceCulturaSelecionada >= culturasSelecionadaEmHorta.size()) {
            return subZonasHorta; // Índice inválido, retorna a lista sem alterações
        }
        
        Cultura cultura = culturasSelecionadaEmHorta.get(indiceCulturaSelecionada);
        if (cultura.getAreaMinimaExigidaPorPlantaM2() <= ((percentualSubZona / 100) * super.getTamanhoZonaM2())){
            if (getPercentualTotalAlocadoHorta() + percentualSubZona > 100){
                // Passa de 100%
            } else{
                subZonasHorta.add(
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
        return subZonasHorta;
    }

    public void editarSubZonaHorta(int indiceCulturaSelecionada, double novoPercentualSubZona){
        if (indiceCulturaSelecionada >= 0 && indiceCulturaSelecionada < subZonasHorta.size()){
            SubZonaPlantio subZonaAlvo = subZonasHorta.get(indiceCulturaSelecionada);
            if (getPercentualTotalAlocadoHorta() + novoPercentualSubZona - subZonaAlvo.getSubPercentualZonaPlantasCanteiro() > 100){
                // Passa de 100%
            } else {
                subZonaAlvo.setAtualizarPercentual(novoPercentualSubZona, super.getTamanhoZonaM2());
            }
        } else {
            // indice invalido
        }
    }

    public void removerSubZonaHorta(int indiceCulturaSelecionada){
        if (indiceCulturaSelecionada >= 0 && indiceCulturaSelecionada < subZonasHorta.size()){
            subZonasHorta.remove(indiceCulturaSelecionada);
        } else {
            // indice invalido
        }
    }
	
    // • getPercentualTotalAlocadoHorta()
    public double getPercentualTotalAlocadoHorta(){
        double percentualTotalAlocado = 0;

        for (SubZonaPlantio subZonaAlvo : subZonasHorta){
            percentualTotalAlocado += subZonaAlvo.getSubPercentualZonaPlantasCanteiro();
        }
        return percentualTotalAlocado;
    }

    public double getPercentualTotalOcupadoHorta(){
        double percentualTotalOcupado = 0;
        
        for (SubZonaPlantio subZonaAlvo : subZonasHorta){
            Cultura tamanhoPorPlanta = subZonaAlvo.getCulturaPrincipalCanteiro();
            percentualTotalOcupado += (tamanhoPorPlanta.getAreaMinimaExigidaPorPlantaM2() * subZonaAlvo.getQuantidadeAtualPlantasCanteiro());
        }
        return (percentualTotalOcupado / super.getTamanhoZonaM2()) * 100;
    }
    
    public boolean isZonaTotalmentePreenchida(){
        if (getPercentualTotalAlocadoHorta() == 100){
            return true;
        }
        return false; // Pode ser falso por ser menor que 100 ou por ser maior
    }
}
