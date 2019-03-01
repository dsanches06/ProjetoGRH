/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.model;

/**
 *
 * @author
 */
public enum TipoDesempenho {
    MAU, INSUFICIENTE, SUFICIENTE, BOM, MUITOBOM;

    @Override
    public String toString() {
        switch (this) {
            case MAU:
                return "MAU";
            case INSUFICIENTE:
                return "INSUFICIENTE";
            case SUFICIENTE:
                return "SUFICIENTE";
            case BOM:
                return "BOM";
            case MUITOBOM:
                return "MUITO BOM";
        }
        return "";
    }
}
