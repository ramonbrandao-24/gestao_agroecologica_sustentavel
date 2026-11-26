package model.animais;

public class Caprino extends EspecieAnimal{
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para ovinos e caprinos (mínimo de 150 m² | máx. 1 animal a cada 60,0 m²)
    public Caprino() {
        super(
            "Ovinos e Caprinos",
            150.0,
            60.0,
            false,
            "Excelente alternativa ao gado no clima semiárido; podem fazer o controle de plantas espontâneas em pomares adultos (desde que os troncos estejam protegidos)."
        );
    }
}
