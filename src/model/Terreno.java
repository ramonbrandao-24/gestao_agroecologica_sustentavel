package model;

public class Terreno {
    private Produtor produtor;
    private String municipio;
    private double tamanhoTerreno;
    private String climaRegiao;
    private String tipoSolo;

    private String denominacaoTerreno;
    private String porteTerreno;
    private String descricaoPorteTerreno;

    public Terreno(Produtor produtor, String municipio, double tamanhoTerreno, String climaRegiao, String tipoSolo) {
        this.produtor = produtor;
        this.municipio = municipio;
        this.tamanhoTerreno = tamanhoTerreno;
        this.climaRegiao = climaRegiao;
        this.tipoSolo = tipoSolo;

        definirPorteTerreno();
    }

    public Terreno(Produtor produtor, double tamanhoTerreno, String climaRegiao, String tipoSolo) {
        this(produtor, " ", tamanhoTerreno, climaRegiao, tipoSolo);
        definirPorteTerreno();
    }

    public void definirPorteTerreno(){
        if (tamanhoTerreno <= 20){
            this.denominacaoTerreno = "Jardim / Canteiro";
            this.porteTerreno = "Microcultivo doméstico";
            this.descricaoPorteTerreno = "Ideal para cultivo vertical, vasos e canteiros biointensivos. Permite consorciar temperos, ervas medicinais, hortaliças de ciclo rápido (alface, cebolinha) e flores atrativas de polinizadores, aplicando compostagem doméstica.";

        } else if(tamanhoTerreno <= 500){
            this.denominacaoTerreno = "Quintal";
            this.porteTerreno = "Subsistência doméstica";
            this.descricaoPorteTerreno = "Espaço excelente para aplicar estratificação agroflorestal em pequena escala: horta diversificada na sombra de frutíferas de pequeno/médio porte (acerola, limão, mamão, banana), além de composteira e criação restrita de pequenas aves (galinhas/codornas) em sistema de piquete";

        } else if(tamanhoTerreno <= 1000){
            this.denominacaoTerreno = "Quintal Amplo / Minichácara";
            this.porteTerreno = "Cultivo intensivo de pequena escala";
            this.descricaoPorteTerreno = "Área de transição ideal para Sistemas Agroflorestais (SAFs) completos, unindo hortaliças, roça de ciclo curto (mandioca, milho, feijão), pomar multiestrato e manejo integrado de pequenos animais (aves ou suínos em pequena escala) para ciclagem de nutrientes.";

        } else if(tamanhoTerreno <= 20000){
            this.denominacaoTerreno = "Chácara";
            this.porteTerreno = "Pequena produção / Familiar";
            this.descricaoPorteTerreno = "Comporta agroflorestas comerciais de múltiplos estratos, horta comercial, meliponicultura (abelhas sem ferrão) e criação rotacionada de aves, suínos ou ovinos/caprinos, garantindo autossuficiência e excedente para feiras agroecológicas.";

        } else if(tamanhoTerreno <= 200000){
            this.denominacaoTerreno = "Sítio";
            this.porteTerreno = "Pequeno porte / Agricultura familiar";
            this.descricaoPorteTerreno = "Voltado para consórcios agroflorestais de médio porte, roçados sustentáveis com adubação verde, fruticultura diversificada e sistemas silvipastoris (criação de animais integrada a árvores de sombra e forrageiras).";

        } else if(tamanhoTerreno <= 500000){
            this.denominacaoTerreno = "Fazenda";
            this.porteTerreno = "Médio a grande porte";
            this.descricaoPorteTerreno = "Área estratégica para Sistemas Agroflorestais mecanizáveis (renques de árvores intercalados com grãos ou pastagens), manejo holístico de rebanhos (pastoreio rotativo) e reserva legal produtiva.";

        } else{
            this.denominacaoTerreno = "Latifúndio / Agroindústria";
            this.porteTerreno = "Grande produção / Agronegócio";
            this.descricaoPorteTerreno = "Extensas áreas geridas sob sistemas de Integração Lavoura-Pecuária-Floresta (ILPF), corredores ecológicos, restauração florestal produtiva e produção sustentável em larga escala com foco em regeneração do solo.";
        }
    }

    public double calcularAreaEmM2(double percentual){
        return (this.tamanhoTerreno / 100) * percentual;
    }
}
