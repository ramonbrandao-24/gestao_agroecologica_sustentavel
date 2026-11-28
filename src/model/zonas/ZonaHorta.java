package model.zonas;

import model.SubZonaPlantio;
import model.Terreno;
import model.plantas.Cultura;

public class ZonaHorta extends ZonaAgroecologica{
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
    
    // • Atributos:
	// • culturasPermitidas (ArrayList<Cultura>): Plantas aprovadas na Etapa 1.
	// • subZonas (ArrayList<SubZonaPlantio>): Canteiros (Horta) ou Talhões (SAF) criados.
    // • Métodos:
	// • filtrarPreSelecao(): Varre getCatalogoHorta() (passando validarSolo = false) ou getCatalogoSAF() (passando validarSolo = true) e preenche culturasPermitidas.
	// • adicionarSubZona(Cultura cultura, double percentual): Cria a SubZonaPlantio, valida isEspacoSuficiente() e retorna as sugestões de plantasAmigas.
	// • editarSubZona(int indice, double novoPercentual) e removerSubZona(int indice): Permitem editar o planejamento a qualquer momento.
	// • getPercentualTotalAlocado() e isZona100PorCentoPreenchida(): Garantem a trava de 100%.

}
