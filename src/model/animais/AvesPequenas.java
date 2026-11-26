package model.animais;

public class AvesPequenas extends EspecieAnimal{
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para codornas e pequenas aves (mínimo de 4 m² | máx. 1 ave a cada 0,5 m²)
    public AvesPequenas() {
        super(
            "Codornas / Aves Pequenas",
            4.0,
            0.5,
            false,
            "Ideais para espaços reduzidos; utilize o esterco rico em nitrogênio após compostagem como adubo orgânico potente para canteiros de hortaliças."
        );
    }
}
