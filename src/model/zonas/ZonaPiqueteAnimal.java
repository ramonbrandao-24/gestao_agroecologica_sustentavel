package model.zonas;

import java.util.ArrayList;
import model.Terreno;
import model.animais.*;

public class ZonaPiqueteAnimal extends ZonaAgroecologica {
    // Etapa 1: Espécies que passaram na pré-seleção (atingem a área mínima na área total da zona)
    private ArrayList<EspecieAnimal> especiesPermitidas = new ArrayList<>();

    // Etapa 2: Todos os lotes finais na ordem fixa (escolhidos com seus % e bloqueados/não escolhidos com 0%)
    private ArrayList<LoteAnimal> tamanhoSubLotesFinais = new ArrayList<>();

    // Catálogo em ordem fixa crescente de área mínima:
    // [0] AvesPequenas (4m²), [1] Peixe (6m²), [2] Galinha (15m²), [3] Suíno (40m²), [4] Caprino (150m²), [5] Bovino (5000m²)
    private ArrayList<EspecieAnimal> especieAnimaisSubZona = new ArrayList<>();

    // -------------------  CONSTRUTORES  -------------------
    // Inicializa a Zona de Piquetes (índice 2) e executa automaticamente a pré-seleção de espécies
    public ZonaPiqueteAnimal(Terreno terreno) {
        super(
            terreno,
            "Piquetes e Integração Animal",
            "• O que é: Espaços cercados, sombreados e manejados de forma rotacionada para a criação ética de animais (aves, suínos, caprinos/ovinos ou bovinos) e tanques de piscicultura.\n" +
            "• Para que serve: Produzir proteína animal (ovos, leite, carne, peixe) garantindo bem-estar, espaço adequado e alimentação natural aos animais.\n" +
            "• Importância para o equilíbrio da propriedade: Integra a criação animal às plantas, fornecendo esterco rico e água de tanque carregada de nutrientes para adubar a horta e o SAF, além de ajudar no controle natural de insetos e ervas espontâneas.",
            2
        // super.getTamanhoZonaPercentual()
        // super.getTamanhoZonaM2()
        );
        filtrarPreSelecao();
    }

    // -------------------  ETAPA 1: PRÉ-SELEÇÃO DE ESPÉCIES  -------------------
    // Filtra quais espécies atingem a área mínima na área total da zona e inicializa os 6 lotes com 0%
    public void filtrarPreSelecao() {
        especiesPermitidas.clear();
        tamanhoSubLotesFinais.clear();
        especieAnimaisSubZona.clear();

        especieAnimaisSubZona.add(new AvesPequenas());
        especieAnimaisSubZona.add(new Peixe());
        especieAnimaisSubZona.add(new Galinha());
        especieAnimaisSubZona.add(new Suino());
        especieAnimaisSubZona.add(new Caprino());
        especieAnimaisSubZona.add(new Bovino());

        for (EspecieAnimal especie : especieAnimaisSubZona) {
            // Se a área total da Zona Animal >= Tamanho Minimo para Especie[especie]
            if (super.getTamanhoZonaM2() >= especie.getAreaMinimaM2()) {
                especiesPermitidas.add(especie);
            } else{
                tamanhoSubLotesFinais.add(new LoteAnimal(especie, 0.0, super.getTamanhoZonaM2()));
            }
        }
    }

    // -------------------  VALIDAÇÃO DE BLOQUEIO (INTERFACE)  -------------------
    // Verifica se a espécie no índice informado está bloqueada por falta de espaço e exibe aviso
    public boolean isEspecieBloqueada(int indice) {
        if (indice < 0 || indice >= especieAnimaisSubZona.size()) {
            return true;
        }

        EspecieAnimal especie = especieAnimaisSubZona.get(indice);
        if (!especiesPermitidas.contains(especie)) {
            System.out.println(
                "Campo bloqueado: " + especie.getNomeEspecie() +
                " não tem espaço suficiente para viver na Zona de Animais (Área atual: " +
                String.format("%.2f", super.getTamanhoZonaM2()) + " m² | Mínimo exigido: " +
                String.format("%.2f", especie.getAreaMinimaM2()) + " m²)."
            );
            return true;
        }
        return false;
    }

    // -------------------  ETAPA 2: DEFINIÇÃO DAS SUB-ZONAS  -------------------
    // Valida o espaço mínimo da sub-zona escolhida pelo usuário e atualiza o lote na posição fixa
    public String definirSubPercentualEspecie(int indice, double subPercentual) {
        if (indice < 0 || indice >= especieAnimaisSubZona.size()) {
            throw new IllegalArgumentException("Índice de espécie inválido: " + indice);
        }

        if (subPercentual < 0 || subPercentual > 100) {
            throw new IllegalArgumentException("O percentual deve estar entre 0 e 100.");
        }

        EspecieAnimal especie = especieAnimaisSubZona.get(indice);

        if (isEspecieBloqueada(indice)) {
            throw new IllegalStateException(
                    especie.getNomeEspecie() + " não tem espaço suficiente na Zona de Animais (Área atual: " +
                            String.format("%.2f", super.getTamanhoZonaM2()) + " m² | Mínimo exigido: " +
                            String.format("%.2f", especie.getAreaMinimaM2()) + " m²).");
        }

        if (subPercentual > 0 && !LoteAnimal.isEspacoSuficiente(especie, subPercentual, super.getTamanhoZonaM2())) {
            throw new IllegalArgumentException(
                    subPercentual + "% (" +
                            String.format("%.2f", super.getTamanhoZonaM2() * (subPercentual / 100.0)) +
                            " m²) é menor que o mínimo exigido de " + especie.getAreaMinimaM2() +
                            " m² para " + especie.getNomeEspecie() + ".");
        }

        // Garante que a soma dos percentuais não ultrapasse 100%
        double totalSemEste = getPercentualTotalAlocado() - tamanhoSubLotesFinais.get(indice).getSubPercentualZona2();
        if (totalSemEste + subPercentual > 100) {
            throw new IllegalArgumentException(
                    "A soma dos percentuais ultrapassaria 100% (já alocado em outras espécies: " + totalSemEste + "%).");
        }

        LoteAnimal loteAtualizado = new LoteAnimal(especie, subPercentual, super.getTamanhoZonaM2());
        tamanhoSubLotesFinais.set(indice, loteAtualizado);

        return "OK: " + especie.getNomeEspecie() + " atualizado para " + subPercentual + "% (" +
                String.format("%.2f", loteAtualizado.getAreaDestinadaM2()) + " m² | Máx: " +
                loteAtualizado.getCapacidadeMaxima() + " animais).";
    }

    // -------------------  VALIDAÇÃO DE LOTAÇÃO PERCENTUAL (100%)  -------------------
    // Soma os percentuais alocados em todas as sub-zonas para validar se fechou em 100%
    public double getPercentualTotalAlocado() {
        double total = 0.0;
        for (LoteAnimal lote : tamanhoSubLotesFinais) {
            total += lote.getSubPercentualZona2();
        }
        return total;
    }

    // -------------------  GETTER DA LISTA  -------------------
    // Retorna a lista final contendo os 6 lotes na ordem fixa com suas áreas e capacidades máximas
    public ArrayList<LoteAnimal> getTamanhoSubLotesFinais() {
        return tamanhoSubLotesFinais;
    }
}
