package model;

import model.plantas.Cultura;

public class SubZonaPlantio {
    //  • Atributos:
    // 	• nomeSubZona (String): Ex: "Canteiro 1 - Tomate" ou "Talhão 1 - Milho".
    // 	• culturaPrincipal (Cultura): A cultura base da sub-zona.
    // 	• subPercentualZona (double): Percentual da zona destinado a este canteiro/talhão.
    // 	• areaDestinadaM2 (double): Área calculada em \(\text{m}^{2}\).
    // 	• quantidadeMinimaPlantas (int): 1 se isAutofertil == true, ou 2 se isAutofertil == false.
    // 	• quantidadeAtualPlantas (int): Já inicia automaticamente com quantidadeMinimaPlantas (ou seja, inicia em 2 para polinização cruzada).
    // 	• capacidadeMaximaPlantas (int): Lotação máxima de pés da cultura principal na área.
    // 	• culturasConsorciadas (ArrayList<Cultura>) e quantidadesConsorciadas (ArrayList<Integer>): Plantas companheiras adicionadas na entrelinha e suas quantidades (50% da densidade).
    // • Métodos:
    // 	• atualizarPercentual(double novoPercentual, double areaZonaM2): Recalcula \(\text{m}^{2}\) e lotação máxima ao editar o percentual, ajustando quantidadeAtualPlantas caso o novo espaço fique menor que a quantidade anterior.
    // 	• alterarQuantidadePlantas(int variacao): Soma ou subtrai unidades. Se tentar diminuir abaixo de 2 (quando !isAutofertil), bloqueia e emite o aviso de polinização cruzada; se tentar passar do máximo, bloqueia por falta de espaço.
    // 	• isEspacoSuficiente(): Verifica se areaDestinadaM2 >= culturaPrincipal.getAreaMinimaExigidaM2().
    // 	• adicionarConsorcio(Cultura companheira): Bloqueia se houver conflito (temConflitoCom) ou falta de espaço mínimo; se passar, adiciona na lista de consórcios calculando 50% da densidade.
    // 	• removerConsorcio(int indice): Remove uma planta consorciada.


}
