package model.animais;

public class Suino extends EspecieAnimal {
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para suínos (mínimo de 40 m² | máx. 1 animal a cada 20,0 m²)
    public Suino() {
        super(
            "Suinos",
            40.0,
            20.0,
            false,
            "Ótimos para preparar e revirar o solo antes de novos plantios; aproveite excedentes da horta na alimentação e direcione os dejetos para biodigestores ou compostagem."
        );
    }
}
