package model.zonas;

import model.Terreno;

public class ZonaPreservacao extends ZonaAgroecologica {
    public ZonaPreservacao(Terreno terreno, double tamanhoZona, double tamanhoTerreno){
        super(
            terreno,
            "Preservação e Biodiversidade",
            "• O que é: Uma área protegida destinada à vegetação nativa, corredores ecológicos, barreiras quebra-vento e meliponicultura (criação de abelhas sem ferrão).\n" +
            "• Para que serve: Proteger nascentes e encostas, barrar ventos quentes e ressecantes e abrigar a fauna silvestre benéfica.\n" +
            "• Importância para o equilíbrio da propriedade: Cria um microclima mais fresco e úmido que protege as lavouras contra secas, além de manter populações ativas de abelhas (que aumentam a produção de frutos) e predadores naturais (joaninhas, pássaros, sapos) que controlam pragas biologicamente.",
            4
        );
    }
    // Representa a área de mata nativa, corredores ecológicos ou abelhas sem ferrão.
}
