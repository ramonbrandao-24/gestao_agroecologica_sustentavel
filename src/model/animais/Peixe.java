package model.animais;

public class Peixe extends EspecieAnimal {
    // -------------------  CONSTRUTORES  -------------------
    // Define os parâmetros técnicos para piscicultura (mínimo de 6 m² | máx. 4 peixes/m² [0,25 m²/peixe] | exige tanque)
    public Peixe() {
        super(
            "Peixe",
            6.0,
            0.25,
            true,
            "Atenção: exige construção de tanque. Integre com a horta (aquaponia ou fertirrigação), usando a água das trocas rica em nutrientes para irrigar as plantas."
        );
    }
}
