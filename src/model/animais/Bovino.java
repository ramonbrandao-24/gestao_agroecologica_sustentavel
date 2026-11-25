package model.animais;

public class Bovino extends EspecieAnimal{
    public Bovino() {
        super(
            "Bovino",
            5000.0,
            2500.0,
            false,
            "Utilize pastoreio rotativo ou sistema silvipastoril, integrando árvores no pasto para gerar sombra, bem-estar animal e recuperação do solo."
        );
    }
}
