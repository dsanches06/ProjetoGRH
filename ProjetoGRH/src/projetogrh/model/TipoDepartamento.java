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
public enum TipoDepartamento {
    RECURSOS_HUMANOS, COMERCIAL, SI, FINANCEIRO, PRODUCAO;

    @Override
    public String toString() {
        switch (this) {
            case RECURSOS_HUMANOS:
                return "Recursos Humanos";
            case COMERCIAL:
                return "Comercial";
            case SI:
                return "Sistema de Informação";
            case FINANCEIRO:
                return "Financeiro";
            case PRODUCAO:
                return "Produção";
        }
        return "";
    }
}
