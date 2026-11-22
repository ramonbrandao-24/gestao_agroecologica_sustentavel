package model.zonas;

public abstract class ZonaAgroecologica {
    public static double calcularAreaEmM2(double percentual, double tamanhoTerreno){
        if (percentual < 0 || percentual > 100 || tamanhoTerreno <= 0){
            return -1;
        }
        return (tamanhoTerreno / 100.0) * percentual;
    }

    
}
