package model.animais;

public class Bovino extends EspecieAnimal{
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para bovinos em pastoreio rotativo (mínimo de 5.000 m² | máx. 1 animal a cada 2.500,0 m²)
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
