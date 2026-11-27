package model.zonas;

import model.SubZonaPlantio;
import model.Terreno;

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
    
    // Guarda a lista de SubZonaPlantio destinadas a hortaliças e temperos.
    // Canteiros de ciclo curto (verduras, temperos, chás e PANCs).
    // Exige irrigação diária e fica próxima à casa; gera colheitas semanais e recebe o composto orgânico pronto.
}
