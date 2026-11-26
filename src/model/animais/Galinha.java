package model.animais;

public class Galinha extends EspecieAnimal {
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para galinhas caipiras (mínimo de 15 m² | máx. 1 ave a cada 3,0 m²)
    public Galinha() {
        super(
            "Galinhas Caipiras",
            15.0,
            3.0,
            false,
            "Excelentes para o sistema, ajudando no controle natural de pragas e na adubação direta de pomares e áreas de descanso."
        );
    }
}
