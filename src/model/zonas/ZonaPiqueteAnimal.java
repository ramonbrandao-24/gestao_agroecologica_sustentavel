package model.zonas;

public class ZonaPiqueteAnimal {
    // 3. Piquetes e Integração Animal
    // Área cercada e sombreada destinada à criação ética e rotacionada de animais (aves, suínos, caprinos/ovinos ou bovinos) e tanques de piscicultura. Garante o bem-estar animal com espaço adequado e transforma o esterco e a água dos tanques em biofertilizantes ricos para nutrir a horta e o SAF, reduzindo custos com adubos externos.

    // Filha da zona. Guarda a lista de LoteAnimal (as espécies e quantidades de animais que o produtor escolheu criar).


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
