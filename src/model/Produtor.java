package model;

public class Produtor {
    private String nomeCompleto;
    private String documentoProprietario;
    private String objetivoTerreno;
    public static final String CONSUMO_FAMILIAR = "Consumo Familiar";
    public static final String VENDA_REGULAR = "Venda Regular";
    public static final String VENDA_ENTRESSAFRA = "Venda na Entressafra";

    // -------------------  CONSTRUTOR  -------------------
    public Produtor(String nomeCompleto, String documentoProprietario, String objetivoTerreno){
        setNomeCompleto(nomeCompleto);
        setDocumentoProprietario(documentoProprietario);
        setObjetivoTerrenoValido(objetivoTerreno);
    }
    
    // -------------------  MÉTODOS DE PRODUTOR  -------------------

    // -------------------  NOME COMPLETO DO PRODUTOR  -------------------
    // Mostra o valor que está em nomeCompleto
    public String getNomeCompleto(){
        return this.nomeCompleto;
    }
    
    // Editar o valor que está em nomeCompleto
    public void setNomeCompleto(String nomeCompleto){
        if (nomeCompleto != null && !nomeCompleto.isBlank()){
            this.nomeCompleto = nomeCompleto;
        }
    }
    
    // -------------------  DOCUMENTO DO PRODUTOR  -------------------
    // Mostra o valor que está em documentoProprietario
    public String getDocumentoProprietario(){
        return this.documentoProprietario;
    }
    
    // Editar o valor que está em documentoProprietario
    public void setDocumentoProprietario(String documentoProprietario){
        if(documentoProprietario != null && !documentoProprietario.isBlank()){
            this.documentoProprietario = documentoProprietario;
        }
    }

    // -------------------  OBJETIVO DE PRODUTOR PARA O TERRENO  -------------------
    // Mostra o valor que está em objetivoTerreno
    public String getObjetivoTerreno(){
        return this.objetivoTerreno;
    }

    // Valores fixos para objetivoTerreno
    public void setObjetivoTerrenoValido(String objetivoTerreno){
        if (CONSUMO_FAMILIAR.equals(objetivoTerreno) || VENDA_REGULAR.equals(objetivoTerreno) || VENDA_ENTRESSAFRA.equals(objetivoTerreno)){
            this.objetivoTerreno = objetivoTerreno;
        }
    }

    // Obs.:
    // isBlank() é um método que retorna true se o texto estiver vazio ou contiver apenas espaços em branco
}