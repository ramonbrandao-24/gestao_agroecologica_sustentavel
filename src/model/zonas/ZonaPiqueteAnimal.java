package model.zonas;

import model.Terreno;

public class ZonaPiqueteAnimal extends ZonaAgroecologica{
    public ZonaPiqueteAnimal(Terreno terreno, double tamanhoZona, double tamanhoTerreno){
        super(
            terreno,
            "Piquetes e Integração Animal",
            "• O que é: Espaços cercados, sombreados e manejados de forma rotacionada para a criação ética de animais (aves, suínos, caprinos/ovinos ou bovinos) e tanques de piscicultura.\n" +
            "• Para que serve: Produzir proteína animal (ovos, leite, carne, peixe) garantindo bem-estar, espaço adequado e alimentação natural aos animais.\n" +
            "• Importância para o equilíbrio da propriedade: Integra a criação animal às plantas, fornecendo esterco rico e água de tanque carregada de nutrientes para adubar a horta e o SAF, além de ajudar no controle natural de insetos e ervas espontâneas.",
            2
        );
    }
    // Guarda a lista de LoteAnimal (as espécies e quantidades de animais que o produtor escolheu criar).


    // Definir o que ele pode ter com base no percentual que ele escolheu do terreno, ja sabendo os animais que ele quer ter e dizendo a quantidade apropriada
    // public void listarAnimaisPermitidos(double percentualAnimal){
    //     if (calcularAreaEmM2(percentualAnimal) >= 4){
    //         // Aves Pequenas
    //     }
    // }

    // Aqui peganos o percentual ja escolhido, o usuario divide ele entre os animais permitidos e falamos quantos de cada especie ele pode ter com base no espaço
    public void quantidadeAnimaisPermitidos(double percentualAnimal){
        // Exemplo: percentualAnimal = 60% e ele quer 10% para Galinha (calcularAreaEmM2(60) * 0.1), 35% para Porcos (calcularAreaEmM2(60) * 0.35) e 55% Caprinos (calcularAreaEmM2(60) * 0.55)
    }
}
