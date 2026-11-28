package model.zonas;

import model.Terreno;

public class ZonaCiclagem extends ZonaAgroecologica{
    public ZonaCiclagem(Terreno terreno){
        super(
            terreno,
            "Ciclagem de Nutriente",
            "• O que é: A central de reciclagem ecológica da propriedade, composta por leiras de compostagem, minhocário e área de preparo de caldas e biofertilizantes naturais.\n" +
            "• Para que serve: Processar restos de cozinha, sobras de colheita, galhos podados do SAF e esterco dos animais para fabricar adubo orgânico e defensivos naturais.\n" +
            "• Importância para o equilíbrio da propriedade: Zera o desperdício dentro do terreno e garante autonomia financeira ao produtor, substituindo fertilizantes e agrotóxicos comprados fora por insumos gratuitos gerados no próprio ciclo produtivo.",
            3
        );
    }
    // Representa a área de compostagem e bioinsumos.
    // Espaço para leiras de compostagem, minhocário e preparo de caldas naturais.
    // Transforma sobras da horta, podas do SAF e esterco animal em adubo gratuito, zerando o desperdício.
}
