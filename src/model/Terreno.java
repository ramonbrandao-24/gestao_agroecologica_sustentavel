package model;

import java.util.ArrayList;
import model.zonas.*;

public class Terreno {
    private Produtor produtor;
    private String municipio;
    private double tamanhoTerreno;
    private String climaRegiao;
    public static final String TROPICAL_SEMIARIDO = "Tropical Semiárido";
    public static final String TROPICAL_SUBUMIDO = "Tropical Subúmido";
    public static final String TROPICAL_UMIDO = "Tropical Úmido";
    public static final String EQUATORIAL = "Equatorial";
    public static final String SUBTROPICAL = "Subtropical";
    
    private String tipoSolo;
    public static final String ARENOSO = "Arenoso";
    public static final String ARGILOSO = "Argiloso";
    public static final String PEDREGOSO = "Pedregoso";
    public static final String HUMIFERO = "Humífero";
    
    ArrayList<String> informacoesPorteTerreno = new ArrayList <String>();
    ArrayList<Double> percentualZonas = new ArrayList <Double>();

    // -------------------  CONSTRUTORES  -------------------
    public Terreno(Produtor produtor, String municipio, double tamanhoTerreno, String climaRegiao, String tipoSolo) {
        this.produtor = produtor;
        this.municipio = municipio;
        setTamanhoTerreno(tamanhoTerreno);
        setClimaRegiao(climaRegiao);
        setTipoSolo(tipoSolo);
    }

    public Terreno(Produtor produtor, double tamanhoTerreno, String climaRegiao, String tipoSolo) {
        this(produtor, " ", tamanhoTerreno, climaRegiao, tipoSolo);
    }

    // -------------------  MÉTODOS DE TERRENO  -------------------
    // Método que adicionandoInformacoesPorteTerreno e adicionandoPercentualZonas
    public void definirPorteTerreno() {
        if (tamanhoTerreno <= 20) {
            adicionandoInformacoesPorteTerreno(
                "Jardim / Canteiro",
                "Microcultivo doméstico",
                "Ideal para cultivo vertical, vasos e canteiros biointensivos. Permite consorciar temperos, ervas medicinais, hortaliças de ciclo rápido (alface, cebolinha) e flores atrativas de polinizadores, aplicando compostagem doméstica."
            );
            adicionandoPercentualZonas(
                80,         // 1. Horta Biointensiva e Medicinal (80%)
                0,              // 2. Sistema Agroflorestal (0%)
                0,   // 3. Piquetes e Integração Animal (0%)
                15,        // 4. Ciclagem de Nutrientes (15%)
                5  // 5. Preservação e Biodiversidade (5%)
            );
        } else if (tamanhoTerreno <= 500) {
            adicionandoInformacoesPorteTerreno(
                "Quintal",
                "Subsistência doméstica",
                "Espaço excelente para aplicar estratificação agroflorestal em pequena escala: horta diversificada na sombra de frutíferas de pequeno/médio porte (acerola, limão, mamão, banana), além de composteira e criação restrita de pequenas aves (galinhas/codornas) em sistema de piquete."
            );
            adicionandoPercentualZonas(
                35,         // 1. Horta Biointensiva e Medicinal (35%)
                40,             // 2. Sistema Agroflorestal (40%)
                10,  // 3. Piquetes e Integração Animal (10%)
                10,        // 4. Ciclagem de Nutrientes (10%)
                5  // 5. Preservação e Biodiversidade (5%)
            );
        } else if (tamanhoTerreno <= 1000) {
            adicionandoInformacoesPorteTerreno(
                "Quintal Amplo / Minichácara",
                "Cultivo intensivo de pequena escala",
                "Área de transição ideal para Sistemas Agroflorestais (SAFs) completos, unindo hortaliças, roça de ciclo curto (mandioca, milho, feijão), pomar multiestrato e manejo integrado de pequenos animais (aves ou suínos em pequena escala) para ciclagem de nutrientes."
            );
            adicionandoPercentualZonas(
                25,          // 1. Horta Biointensiva e Medicinal (25%)
                45,              // 2. Sistema Agroflorestal (45%)
                15,   // 3. Piquetes e Integração Animal (15%)
                10,         // 4. Ciclagem de Nutrientes (10%)
                5   // 5. Preservação e Biodiversidade (5%)
            );
        } else if (tamanhoTerreno <= 20000) {
            adicionandoInformacoesPorteTerreno(
                "Chácara",
                "Pequena produção / Familiar",
                "Comporta agroflorestas comerciais de múltiplos estratos, horta comercial, meliponicultura (abelhas sem ferrão) e criação rotacionada de aves, suínos ou ovinos/caprinos, garantindo autossuficiência e excedente para feiras agroecológicas."
            );
            adicionandoPercentualZonas(
                15,          // 1. Horta Biointensiva e Medicinal (15%)
                45,              // 2. Sistema Agroflorestal (45%)
                20,   // 3. Piquetes e Integração Animal (20%)
                5,          // 4. Ciclagem de Nutrientes (5%)
                15  // 5. Preservação e Biodiversidade (15%)
            );
        } else if (tamanhoTerreno <= 200000) {
            adicionandoInformacoesPorteTerreno(
                "Sítio",
                "Pequeno porte / Agricultura familiar",
                "Voltado para consórcios agroflorestais de médio porte, roçados sustentáveis com adubação verde, fruticultura diversificada e sistemas silvipastoris (criação de animais integrada a árvores de sombra e forrageiras)."
            );
            adicionandoPercentualZonas(
                10,          // 1. Horta Biointensiva e Medicinal (10%)
                45,              // 2. Sistema Agroflorestal (45%)
                20,   // 3. Piquetes e Integração Animal (20%)
                5,          // 4. Ciclagem de Nutrientes (5%)
                20  // 5. Preservação e Biodiversidade (20%)
            );
        } else if (tamanhoTerreno <= 500000) {
            adicionandoInformacoesPorteTerreno(
                "Fazenda",
                "Médio a grande porte",
                "Área estratégica para Sistemas Agroflorestais mecanizáveis (renques de árvores intercalados com grãos ou pastagens), manejo holístico de rebanhos (pastoreio rotativo) e reserva legal produtiva."
            );
            adicionandoPercentualZonas(
                5,           // 1. Horta Biointensiva e Medicinal (5%)
                45,              // 2. Sistema Agroflorestal (45%)
                25,   // 3. Piquetes e Integração Animal (25%)
                5,          // 4. Ciclagem de Nutrientes (5%)
                20  // 5. Preservação e Biodiversidade (20%)
            );
        } else {
            adicionandoInformacoesPorteTerreno(
                "Latifúndio / Agroindústria",
                "Grande produção / Agronegócio",
                "Extensas áreas geridas sob sistemas de Integração Lavoura-Pecuária-Floresta (ILPF), corredores ecológicos, restauração florestal produtiva e produção sustentável em larga escala com foco em regeneração do solo."
            );
            adicionandoPercentualZonas(
                2,           // 1. Horta Biointensiva e Medicinal (2%)
                48,              // 2. Sistema Agroflorestal (48%)
                25,   // 3. Piquetes e Integração Animal (25%)
                5,          // 4. Ciclagem de Nutrientes (5%)
                20  // 5. Preservação e Biodiversidade (20%)
            );
        }
    }
    
    // -------------------  MUNICIPIO  -------------------
    // Mostra o valor que está em municipio
    public String getMunicipio(){
        return this.municipio;
    }
    
    // Editar o valor que está em municipio
    public void setMunicipio(String municipio){
        if (municipio != null && !municipio.isBlank()){
            this.municipio = municipio;
        }
    }
    
    // -------------------  TAMANHO DO TERRENO  -------------------
    // Mostra o valor que está em tamanhoTerreno
    public double getTamanhoTerreno(){
        return this.tamanhoTerreno;
    }
    
    // Editar o valor que está em tamanhoTerreno
    public void setTamanhoTerreno(double tamanhoTerreno){
        if (tamanhoTerreno > 0) {
            this.tamanhoTerreno = tamanhoTerreno;
            definirPorteTerreno();
        }
    }
    
    // -------------------  CLIMA DA REGIÃO  -------------------
    // Mostra o valor que está em climaRegiao
    public String getClimaRegiao(){
        return this.climaRegiao;
    }

    // Valores fixos para setClimaRegiao
    public void setClimaRegiao(String climaRegiao){
        if (TROPICAL_SEMIARIDO.equals(climaRegiao) || TROPICAL_SUBUMIDO.equals(climaRegiao) || TROPICAL_UMIDO.equals(climaRegiao) ||
        EQUATORIAL.equals(climaRegiao) || SUBTROPICAL.equals(climaRegiao)){
            this.climaRegiao = climaRegiao;
        }
    }

    // -------------------  TIPO DE SOLO  -------------------
    // Mostra o valor que está em tipoSolo
    public String getTipoSolo(){
        return this.tipoSolo;
    }

    // Valores fixos para setTipoSolo
    public void setTipoSolo(String tipoSolo){
        if (ARENOSO.equals(tipoSolo) || ARGILOSO.equals(tipoSolo) || PEDREGOSO.equals(tipoSolo) || HUMIFERO.equals(tipoSolo)){
            this.tipoSolo = tipoSolo;
        }
    }
    
    // -------------------  INFORMAÇÕES DO PORTE DO TERRENO  -------------------
    // Mostra o valor que está em informacoesPorteTerreno
    public String getInformacoesPorteTerreno(){
        if (informacoesPorteTerreno == null || informacoesPorteTerreno.isEmpty()){
            return "Nenhuma informação cadastrada.";
        }

        return  "Denominação: " + informacoesPorteTerreno.get(0) + "\n" +
                "Porte: " + informacoesPorteTerreno.get(1) + "\n" +
                "Descrição: " + informacoesPorteTerreno.get(2);
    }

    // Adiciona informações do porte do terreno
    public void adicionandoInformacoesPorteTerreno( String denominacaoTerreno, String porteTerreno, String descricaoPorteTerreno){
        informacoesPorteTerreno.clear();
        informacoesPorteTerreno.add(denominacaoTerreno);
        informacoesPorteTerreno.add(porteTerreno);
        informacoesPorteTerreno.add(descricaoPorteTerreno);
    }
    
    // -------------------  PERCENTUAL DA ZONA  -------------------
    // Mostra os percentuais e a área real (m²) de cada zona
    public String getPercentualZonas(){
        if (percentualZonas  == null || percentualZonas .isEmpty()){
            return "Nenhuma informação cadastrada.";
        }

        return  "1. Horta Biointensiva e Medicinal: " + percentualZonas.get(0) + "% (" + ZonaAgroecologica.calcularAreaEmM2(percentualZonas.get(0), tamanhoTerreno) + ")" + "\n" +
                "2. Sistema Agroflorestal: " + percentualZonas.get(1) + "% (" + ZonaAgroecologica.calcularAreaEmM2(percentualZonas.get(1), tamanhoTerreno) + ")" + "\n" +
                "3. Piquetes e Integração Animal: " + percentualZonas.get(2) + "% (" + ZonaAgroecologica.calcularAreaEmM2(percentualZonas.get(2), tamanhoTerreno) + ")" + "\n" +
                "4. Ciclagem de Nutrientes: " + percentualZonas.get(3) + "% (" + ZonaAgroecologica.calcularAreaEmM2(percentualZonas.get(3), tamanhoTerreno) + ")" + "\n" +
                "5. Preservação e Biodiversidade: " + percentualZonas.get(4) + "% (" + ZonaAgroecologica.calcularAreaEmM2(percentualZonas.get(4), tamanhoTerreno) + ")";
    }

    // Adiciona percentual da zona
    public void adicionandoPercentualZonas( double hortaBiointensiva, double agroflorestal, double piquetesIntegraçãoAnimal, double ciclagemNutrientes, double preservacao_Biodiversidade){
        percentualZonas.clear();
        percentualZonas.add(hortaBiointensiva);
        percentualZonas.add(agroflorestal);
        percentualZonas.add(piquetesIntegraçãoAnimal);
        percentualZonas.add(ciclagemNutrientes);
        percentualZonas.add(preservacao_Biodiversidade);
    }
}