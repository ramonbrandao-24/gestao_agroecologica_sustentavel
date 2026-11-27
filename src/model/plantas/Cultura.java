package model.plantas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Terreno;
import model.zonas.ZonaHorta;
import model.zonas.ZonaSAF;

public class Cultura {
    private String nomePlanta;
    private double tamanhoMinimoPorPlantaM2;
    private boolean isAutofertil;
    private String avisoPrePlantio;
    private String guiaManejo;

    private ArrayList<String> climasCompativeis = new ArrayList<>();
    private ArrayList<String> solosCompativeis = new ArrayList<>();
    private ArrayList<String> plantasAmigas = new ArrayList<>();
    private ArrayList<String> plantasInimigas = new ArrayList<>();

    public Cultura(
        String nomePlanta,
        double tamanhoMinimoPorPlantaM2,
        boolean isAutofertil,
        String avisoPrePlantio,
        String guiaManejo,
        List<String> climas,
        List<String> solos,
        List<String> amigas,
        List<String> inimigas
    ){
        this.nomePlanta = nomePlanta;
        this.tamanhoMinimoPorPlantaM2 = tamanhoMinimoPorPlantaM2;
        this.isAutofertil = isAutofertil;
        this.avisoPrePlantio = avisoPrePlantio;
        this.guiaManejo = guiaManejo;

        if (climas != null) this.climasCompativeis.addAll(climas);
        if (solos != null) this.solosCompativeis.addAll(solos);
        if (amigas != null) this.plantasAmigas.addAll(amigas);
        if (inimigas != null) this.plantasInimigas.addAll(inimigas);
        }

    public double getAreaMinimaExigidaPorPlantaM2(){
        if(this.isAutofertil){
            return this.tamanhoMinimoPorPlantaM2;
        }
        return this.tamanhoMinimoPorPlantaM2 * 2;
    }

    public int calcularCapacidadeMaxima(double areaM2){
        if (areaM2 < getAreaMinimaExigidaPorPlantaM2()) return 0;
        return (int) (areaM2 / tamanhoMinimoPorPlantaM2);
    }
    
    // Filtra espaço mínimo, porte "GRANDE" em Jardim (<= 20m²), clima e (apenas se validarSolo == true) tipo de solo.
    public boolean passaNaPreSelecao(Terreno terreno, ZonaHorta tamanhoHortaEmM2){
        if (tamanhoHortaEmM2.getTamanhoZonaM2() < getAreaMinimaExigidaPorPlantaM2()){
            return false;
        }

        if (!this.climasCompativeis.isEmpty() && !this.climasCompativeis.contains(terreno.getClimaRegiao())){
            return false;
        }
        return true;
    }

    public boolean passaNaPreSelecao(Terreno terreno, ZonaSAF tamanhoSAFEmM2){
        if (tamanhoSAFEmM2.getTamanhoZonaM2() < getAreaMinimaExigidaPorPlantaM2()){
            return false;
        }

        if (!this.climasCompativeis.isEmpty() && !this.climasCompativeis.contains(terreno.getClimaRegiao())){
            return false;
        }

        if (!this.solosCompativeis.isEmpty() && !this.solosCompativeis.contains(terreno.getTipoSolo())){
            return false;
        }
        return true;
    }
    
    // temConflitoCom(Cultura outra): Verifica se há incompatibilidade/alelopatia entre duas culturas.
    public boolean temConflito(Cultura outraCultura){
        if (outraCultura == null) return false;

        // Verifica nas duas listas (se A é inimiga de B, ou se B é inimiga de A)
        boolean plantaInimiga = this.plantasInimigas.contains(outraCultura.getNomePlanta());
        boolean plantaAtualInimiga = outraCultura.getPlantasInimigas().contains(this.nomePlanta);

        return plantaInimiga || plantaAtualInimiga;
    }

    // ------------------- CATÁLOGOS SEPARADOS (HORTA E SAF) -------------------
    public static ArrayList<Cultura> getCatalogoHorta() {
        ArrayList<Cultura> horta = new ArrayList<>();

        horta.add(
            new Cultura(
                "Coentro",
                0.04,
                true,
                "",
                "Espaçamento 0,20m x 0,20m. Colheita contínua a partir de 35 dias. Ideal para Consumo Familiar e Venda Regular (giro semanal rápido).",
                List.of(),
                List.of(),
                Arrays.asList("Alface", "Tomate", "Cenoura", "Couve", "Pimentão"),
                Arrays.asList("Feijão", "Fava")
            )
        );

        horta.add(
            new Cultura(
                "Cebolinha",
                0.04,
                true,
                "",
                "Espaçamento 0,20m x 0,20m (propagação por touceira). Cortar folhas externas deixando a base. Ideal para Consumo Familiar e Venda Regular.",
                List.of(),
                List.of(),
                Arrays.asList("Alface", "Tomate", "Cenoura", "Beterraba", "Couve", "Morango"),
                Arrays.asList("Feijão", "Fava")
            )
        );

        horta.add(
            new Cultura(
                "Maxixe",
                1.0,
                true,
                "",
                "Espaçamento 1,0m x 1,0m. Planta rasteira muito rústica. Ideal para Consumo Familiar e Venda Regular regional.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Milho", "Feijão", "Mandioca"),
                Arrays.asList("Batata-Inglesa", "Melancia", "Melão")
            )
        );

        horta.add(
            new Cultura(
                "Batata-Doce",
                0.30,
                true,
                "",
                "Espaçamento 0,80m x 0,35m (propagação por ramas). Alta rusticidade e ótima para Venda na Entressafra. Ramas verdes ricas em proteína alimentam Suínos, Aves e Bovinos.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Milho", "Feijão", "Mandioca"),
                List.of()
            )
        );

        horta.add(
            new Cultura(
                "Tomate",
                0.50,
                true,
                "Em regiões de Semiárido, exige irrigação regular e atenção ao calor intenso.",
                "Espaçamento 1,0m x 0,50m. Fazer tutoramento (estacas/fitilho) e desbrota lateral semanal. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                List.of(),
                Arrays.asList("Cebolinha", "Coentro (Cheiro-verde)", "Cenoura", "Couve"),
                Arrays.asList("Batata-Inglesa", "Milho", "Pepino", "Pimentão")
            )
        );

        horta.add(
            new Cultura(
                "Pimenta",
                0.40,
                true,
                "",
                "Espaçamento 0,80m x 0,50m. Alta durabilidade; excelente para Consumo Familiar, Venda Regular e conservas/desidratação na Entressafra.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.SEMIARIDO, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Coentro (Cheiro-verde)", "Cebolinha", "Cenoura", "Alface"),
                Arrays.asList("Feijão", "Fava", "Tomate", "Batata-Inglesa")
            )
        );

        horta.add(
            new Cultura(
                "Pimentão",
                0.40,
                true,
                "Em regiões de Semiárido, exige irrigação frequente.",
                "Espaçamento 0,80m x 0,50m. Fazer tutoramento leve para evitar quebra dos galhos. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.SEMIARIDO, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Coentro (Cheiro-verde)", "Cebolinha", "Cenoura", "Alface"),
                Arrays.asList("Feijão", "Fava", "Tomate", "Batata-Inglesa")
            )
        );

        horta.add(
            new Cultura(
                "Alface",
                0.09,
                true,
                "Artifício: Em regiões muito quentes (Tropical/Semiárido), utilizar sombrite 50% ou cultivo protegido.",
                "Espaçamento 0,30m x 0,30m. Regas diárias leves sem encharcar as folhas. Giro rápido (30 a 45 dias) para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL),
                List.of(),
                Arrays.asList("Cenoura", "Cebolinha", "Beterraba", "Couve", "Morango"),
                Arrays.asList("Salsa")
            )
        );

        horta.add(
            new Cultura(
                "Cebola",
                0.03,
                true,
                "Em regiões de Semiárido, exige irrigação controlada e suspensão da água antes da colheita para cura.",
                "Espaçamento 0,25m x 0,12m. Bulbo curado armazenável, excelente para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL),
                List.of(),
                Arrays.asList("Alface", "Tomate", "Beterraba", "Cenoura", "Morango"),
                Arrays.asList("Feijão", "Fava")
            )
        );

        horta.add(
            new Cultura(
                "Pepino",
                0.50,
                true,
                "",
                "Espaçamento 1,0m x 0,50m. Tutoramento vertical em tela/fitilho economiza espaço e melhora a qualidade. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL),
                List.of(),
                Arrays.asList("Milho", "Feijão", "Alface", "Cebolinha"),
                Arrays.asList("Tomate", "Batata-Inglesa", "Melão", "Melancia", "Jerimum")
            )
        );

        horta.add(
            new Cultura(
                "Couve",
                0.35,
                true,
                "Artifício: Em climas mais quentes, utilizar sombrite para manter folhas macias.",
                "Espaçamento 0,70m x 0,50m. Colheita contínua das folhas baixeiras. Folhas externas alimentam Aves e Suínos.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Cebolinha", "Alface", "Beterraba", "Cenoura", "Tomate"),
                Arrays.asList("Morango", "Couve-flor", "Repolho")
            )
        );

        horta.add(
            new Cultura(
                "Repolho",
                0.25,
                true,
                "Artifício: Fora de clima ameno, exige cultivares de verão ou estufa.",
                "Espaçamento 0,50m x 0,50m. Ideal para Consumo Familiar e Venda Regular; folhas externas servem de alimento para Aves e Suínos.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Cebolinha", "Alface", "Beterraba", "Cenoura"),
                Arrays.asList("Morango", "Couve-flor", "Tomate")
            )
        );

        horta.add(
            new Cultura(
                "Beterraba",
                0.04,
                true,
                "",
                "Espaçamento 0,20m x 0,20m. Manter umidade constante no canteiro. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Alface", "Cebolinha", "Couve", "Repolho"),
                Arrays.asList("Feijão", "Milho")
            )
        );

        horta.add(
            new Cultura(
                "Cenoura",
                0.03,
                true,
                "Dica de Solo: Afofar bem o canteiro (30 cm de profundidade) para evitar raízes tortas.",
                "Espaçamento 0,20m x 0,15m. Ideal para Consumo Familiar e Venda Regular; ramas verdes alimentam Aves e Suínos.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Alface", "Tomate", "Cebolinha", "Coentro (Cheiro-verde)", "Couve"),
                Arrays.asList("Salsa")
            )
        );

        horta.add(
            new Cultura(
                "Salsa",
                0.04,
                true,
                "",
                "Espaçamento 0,20m x 0,20m. Germinação lenta (15 a 25 dias); manter canteiro úmido. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Tomate", "Milho"),
                Arrays.asList("Alface", "Cenoura")
            )
        );

        horta.add(
            new Cultura(
                "Couve-flor",
                0.40,
                true,
                "Artifício: Exige estufa ou cultivares tropicais de verão fora de regiões frias.",
                "Espaçamento 0,80m x 0,50m. Cobrir a cabeça com as próprias folhas antes da colheita para manter branca. Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.SUBTROPICAL, Terreno.TROPICAL_ALTITUDE),
                List.of(),
                Arrays.asList("Cebolinha", "Alface", "Beterraba", "Cenoura"),
                Arrays.asList("Morango", "Tomate", "Repolho")
            )
        );

        horta.add(
            new Cultura(
                "Batata-Inglesa",
                0.25,
                true,
                "AVISO OBRIGATÓRIO: Usar batata-semente certificada (nunca de mercado) e fazer amontoa (cobrir tubérculos para evitar solanina tóxica).",
                "Espaçamento 0,80m x 0,30m. Chegar terra no pé aos 30 dias. Ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL),
                List.of(),
                Arrays.asList("Feijão", "Milho", "Couve", "Repolho"),
                Arrays.asList("Tomate", "Jerimum", "Pepino", "Melancia")
            )
        );

        horta.add(
            new Cultura(
                "Alho",
                0.02,
                true,
                "",
                "Espaçamento 0,20m x 0,10m (propagação por bulbilhos). Bulbo curado armazenável, ótimo para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.SUBTROPICAL, Terreno.TROPICAL_ALTITUDE),
                List.of(),
                Arrays.asList("Morango", "Tomate", "Alface", "Beterraba", "Cenoura"),
                Arrays.asList("Feijão", "Fava", "Couve", "Repolho")
            )
        );

        horta.add(
            new Cultura(
                "Morango",
                0.09,
                true,
                "Artifício: Exige estufa climatizada ou cultivo suspenso fora de climas amenos.",
                "Espaçamento 0,30m x 0,30m. Cobrir canteiro com palha/mulching para evitar contato do fruto com o solo. Alto valor para Venda Regular.",
                Arrays.asList(Terreno.SUBTROPICAL, Terreno.TROPICAL_ALTITUDE, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Alface", "Cebolinha", "Feijão"),
                Arrays.asList("Couve", "Repolho", "Couve-flor", "Tomate")
            )
        );

        return horta;
    }
    
    public static ArrayList<Cultura> getCatalogoSAF() {
        ArrayList<Cultura> saf = new ArrayList<>();

        saf.add(
            new Cultura(
                "Milho",
                0.25,
                true,
                "",
                "Plantar em bloco (0,80m x 0,30m) para polinização pelo vento. Ideal para Consumo Familiar, Venda Regular e Venda na Entressafra (grão seco). Grãos e palhada alimentam Aves, Suínos, Caprinos e Bovinos.",
                List.of(),
                List.of(),
                Arrays.asList("Feijão", "Fava", "Jerimum", "Mandioca", "Batata-Doce"),
                Arrays.asList("Tomate")
            )
        );

        saf.add(
            new Cultura(
                "Feijão",
                0.10,
                true,
                "",
                "Espaçamento 0,50m x 0,20m. Base alimentar e excelente para grão seco na Entressafra. Fixa nitrogênio no solo; palhada e rama alimentam Caprinos e Bovinos.",
                List.of(),
                List.of(),
                Arrays.asList("Milho", "Mandioca", "Jerimum", "Batata-Doce"),
                Arrays.asList("Cebolinha", "Alho", "Cebola")
            )
        );

        saf.add(
            new Cultura(
                "Fava",
                0.50,
                true,
                "",
                "Espaçamento 1,0m x 0,50m (trepadeira; usa o milho como tutor vivo). Alta resistência e armazenamento para Venda na Entressafra. Folhagem e vagens alimentam ruminantes.",
                List.of(),
                List.of(),
                Arrays.asList("Milho", "Jerimum", "Mandioca"),
                Arrays.asList("Cebolinha", "Alho", "Cebola")
            )
        );

        saf.add(
            new Cultura(
                "Jerimum",
                4.0,
                true,
                "",
                "Espaçamento 2,0m x 2,0m. Cobre e protege o solo; ótima durabilidade pós-colheita para Entressafra. Frutos e ramas alimentam Suínos, Aves e Bovinos.",
                List.of(),
                List.of(),
                Arrays.asList("Milho", "Feijão", "Fava", "Mandioca"),
                Arrays.asList("Batata-Inglesa")
            )
        );

        saf.add(
            new Cultura(
                "Mandioca",
                1.0,
                true,
                "AVISO OBRIGATÓRIO: Diferenciar Mandioca-mansa/Macaxeira (culinária) de Mandioca-brava (tóxica sem processamento).",
                "Espaçamento 1,0m x 1,0m (propagação por manivas). Reserva viva no solo para Venda na Entressafra. Raízes e parte aérea triturada alimentam Suínos, Aves e Bovinos.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Feijão", "Fava", "Milho", "Jerimum", "Batata-Doce"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Aceroleira",
                12.0,
                true,
                "",
                "Espaçamento 3,0m x 4,0m. Autofértil (1 planta já produz, mas 2 aumentam a carga). Ideal para Consumo Familiar, Venda Regular e polpa congelada na Entressafra.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Feijão", "Bananeira"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Seriguela",
                25.0,
                false,
                "Exige no mínimo 2 árvores (50 m²) para garantir polinização cruzada.",
                "Espaçamento 5,0m x 5,0m. Frutifica no período seco, sendo excelente para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO),
                List.of(),
                Arrays.asList("Feijão", "Fava", "Mandioca"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Laranja",
                16.0,
                true,
                "Evitar solos encharcados (risco de gomose); em Semiárido exige irrigação.",
                "Espaçamento 4,0m x 4,0m. Realizar poda de limpeza anual pós-colheita. Ideal para Consumo Familiar, Venda Regular e Entressafra (variedades tardias).",
                Arrays.asList(Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO, Terreno.ARENOSO),
                Arrays.asList("Feijão", "Fava", "Jerimum", "Abacaxi"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Limão",
                16.0,
                true,
                "Evitar solos encharcados; em Semiárido exige irrigação.",
                "Espaçamento 4,0m x 4,0m. Fazer poda de abertura de copa e limpeza. Produz o ano todo, sendo excelente para Venda na Entressafra.",
                Arrays.asList(Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO, Terreno.ARENOSO),
                Arrays.asList("Feijão", "Fava", "Jerimum", "Batata-Doce"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Tangerina",
                16.0,
                true,
                "Evitar solos encharcados; em Semiárido exige irrigação.",
                "Espaçamento 4,0m x 4,0m. Fazer raleio de frutos e poda de limpeza. Ideal para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO, Terreno.ARENOSO),
                Arrays.asList("Feijão", "Jerimum", "Abacaxi"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Coqueiro",
                49.0,
                false,
                "Exige no mínimo 2 árvores (98 m²) para polinização cruzada; em Semiárido exige irrigação.",
                "Espaçamento 7,0m x 7,0m. Estrato emergente (não plantar árvores mais altas que façam sombra nele). Excelente para água de coco na seca/entressafra e coco seco.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.ARENOSO, Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Abacaxi", "Bananeira", "Mandioca", "Feijão", "Jerimum"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Mangueira",
                64.0,
                true,
                "",
                "Espaçamento 8,0m x 8,0m. Fazer poda de abertura de copa após a safra (copa adulta gera sombra densa). Consumo Familiar, Venda Regular e Entressafra.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.ARENOSO, Terreno.ARGILOSO, Terreno.HUMIFERO),
                Arrays.asList("Feijão", "Mandioca", "Abacaxi"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Goiabeira",
                20.0,
                true,
                "Em Semiárido exige irrigação; ensacar frutos para proteção contra mosca-das-frutas.",
                "Espaçamento 4,0m x 5,0m. A poda de frutificação permite programar a colheita para a Entressafra. Consumo Familiar, Venda Regular e polpa/doces.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO, Terreno.ARENOSO),
                Arrays.asList("Feijão", "Jerimum"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Maracujá",
                9.0,
                false,
                "Exige no mínimo 2 plantas (18 m²) por ser autoincompatível; em Semiárido exige irrigação.",
                "Espaçamento 3,0m x 3,0m em espaldeira com arame. Atrair mamangavas ou fazer polinização manual. Venda Regular e polpa congelada na Entressafra.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARENOSO, Terreno.ARGILOSO),
                Arrays.asList("Feijão", "Abacaxi", "Bananeira"),
                Arrays.asList("Pepino", "Melão", "Jerimum")
            )
        );

        saf.add(
            new Cultura(
                "Bananeira",
                9.0,
                true,
                "Em Semiárido exige irrigação regular.",
                "Espaçamento 3,0m x 3,0m (partenocárpica: 1 touceira já produz). Fazer desbaste deixando família (mãe, filha e neta). Folhas e pseudocaule alimentam Bovinos, Caprinos e Suínos.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Cafeeiro", "Cacau", "Feijão", "Mandioca", "Mamão"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Mamão",
                6.0,
                false,
                "Exige no mínimo 2 plantas (12 m²) para sexagem e polinização; evitar solos argilosos encharcados.",
                "Espaçamento 2,5m x 2,5m. Produção contínua a partir de 8 meses, ideal para Consumo Familiar e Venda Regular.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARENOSO),
                Arrays.asList("Bananeira", "Feijão", "Jerimum", "Abacaxi"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Melancia",
                4.0,
                false,
                "Exige no mínimo 2 plantas (8 m²) para polinização por abelhas; em Semiárido exige irrigação.",
                "Espaçamento 2,0m x 2,0m. Excelente rentabilidade para Venda Regular e Venda na Entressafra no Semiárido irrigado.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.TROPICAL, Terreno.EQUATORIAL, Terreno.TROPICAL_ATLANTICO),
                Arrays.asList(Terreno.ARENOSO, Terreno.HUMIFERO),
                Arrays.asList("Milho", "Feijão", "Cebolinha"),
                Arrays.asList("Batata-Inglesa", "Pepino", "Jerimum", "Maxixe")
            )
        );

        saf.add(
            new Cultura(
                "Melão",
                1.5,
                false,
                "Exige no mínimo 2 plantas (3 m²) para polinização por abelhas; em Semiárido exige irrigação.",
                "Espaçamento 1,5m x 1,0m. Alto valor comercial para Venda Regular e Venda na Entressafra no Semiárido irrigado.",
                Arrays.asList(Terreno.SEMIARIDO, Terreno.TROPICAL, Terreno.EQUATORIAL, Terreno.TROPICAL_ATLANTICO),
                Arrays.asList(Terreno.ARENOSO, Terreno.HUMIFERO),
                Arrays.asList("Milho", "Feijão", "Cebolinha"),
                Arrays.asList("Batata-Inglesa", "Pepino", "Jerimum", "Maxixe")
            )
        );

        saf.add(
            new Cultura(
                "Abacaxi",
                0.40,
                true,
                "Evitar solos argilosos encharcados (risco de podridão radicular); em Semiárido exige irrigação.",
                "Espaçamento 0,90m x 0,45m. A indução floral permite programar a colheita para Venda na Entressafra.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.SEMIARIDO),
                Arrays.asList(Terreno.ARENOSO, Terreno.HUMIFERO),
                Arrays.asList("Coqueiro", "Mamão", "Feijão", "Mandioca"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Chuchu",
                9.0,
                false,
                "Exige no mínimo 2 covas (18 m²) para polinização entre flores masculinas e femininas; exige construção de latada.",
                "Espaçamento 3,0m x 3,0m em latada/caramanchão. Frutos excedentes e ramas alimentam Suínos e Aves.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.SUBTROPICAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Alface", "Salsa"),
                Arrays.asList("Jerimum", "Pepino", "Melão")
            )
        );

        saf.add(
            new Cultura(
                "Abacateiro",
                64.0,
                false,
                "Exige no mínimo 2 árvores (128 m² - Grupo Floral A + B) para polinização cruzada; evitar solos argilosos compactados.",
                "Espaçamento 8,0m x 8,0m. Realizar poda de controle de altura para facilitar a colheita. Ideal para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL, Terreno.SUBTROPICAL),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARENOSO),
                Arrays.asList("Cafeeiro", "Bananeira", "Feijão"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Cafeeiro",
                3.0,
                true,
                "",
                "Espaçamento 2,5m x 1,2m. Fazer poda de decote e esqueletamento; grão seco armazenável é excelente para Consumo Familiar e Venda na Entressafra.",
                Arrays.asList(Terreno.TROPICAL_ALTITUDE, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Bananeira", "Abacateiro", "Feijão"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Cacau",
                9.0,
                false,
                "Exige no mínimo 2 árvores (18 m²) para polinização cruzada e sombreamento obrigatório (sol pleno queima as folhas).",
                "Espaçamento 3,0m x 3,0m. Usar Bananeira como sombra provisória e Jaca/Mangueira como sombra definitiva. Amêndoa seca ideal para Venda na Entressafra.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Bananeira", "Jaca", "Mangueira"),
                List.of()
            )
        );

        saf.add(
            new Cultura(
                "Jaca",
                81.0,
                false,
                "Exige no mínimo 2 árvores (162 m²) para polinização cruzada.",
                "Espaçamento 9,0m x 9,0m. Árvore-mãe de sombra no SAF; caroços cozidos e polpa excedente alimentam Suínos e Bovinos.",
                Arrays.asList(Terreno.EQUATORIAL, Terreno.TROPICAL_ATLANTICO, Terreno.TROPICAL),
                Arrays.asList(Terreno.HUMIFERO, Terreno.ARGILOSO),
                Arrays.asList("Cacau", "Cafeeiro", "Bananeira"),
                List.of()
            )
        );
        return saf;
    }

    public String getNomePlanta() { return nomePlanta; }
    public double getTamanhoMinimoPorPlantaM2() { return tamanhoMinimoPorPlantaM2; }
    public boolean isAutofertil() { return isAutofertil; }
    public String getAvisoPrePlantio() { return avisoPrePlantio; }
    public String getGuiaManejo() { return guiaManejo; }
    public ArrayList<String> getPlantasAmigas() { return plantasAmigas; }
    public ArrayList<String> getPlantasInimigas() { return plantasInimigas; }
}