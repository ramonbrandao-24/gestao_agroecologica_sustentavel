package model.zonas;

import model.Terreno;

public class ZonaSAF extends ZonaAgroecologica{
    public ZonaSAF(Terreno terreno, double tamanhoZona, double tamanhoTerreno){
        super(
            terreno,
            "Sistema Agroflorestal",
            "• O que é: Um sistema que imita uma floresta natural, consorciando árvores frutíferas, madeireiras e adubadeiras com lavouras de ciclo curto e médio (como banana, mandioca, milho e feijão) organizadas em diferentes andares de altura e luz.\n" +
            "• Para que serve: Gerar produção contínua e diversificada ao longo de todo o ano (colheitas no curto, médio e longo prazo) e produzir biomassa (folhas e galhos de poda).\n" +
            "• Importância para o equilíbrio da propriedade: É o coração sustentável do terreno; cobre o solo contra o sol forte, retém a umidade da chuva, evita a erosão e regenera a fertilidade natural da terra sem depender de insumos químicos.",
            1
        );
    }
    // Guarda a lista de SubZonaPlantio destinadas às árvores, frutíferas e roça consorciada.
}
