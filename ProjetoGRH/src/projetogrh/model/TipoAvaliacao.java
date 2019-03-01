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
public enum TipoAvaliacao {
    ASSIDUIDADE,
    AUTONOMIA,
    COMPETENCIAS,
    COMUNICACAO,
    CUMPRIMENTO_DE_PRAZOS,
    LIDERANCA,
    PONTUALIDADE,
    QUALIDADE_TRABALHO,
    QUANTIDADE_TRABALHO;

    @Override
    public String toString() {
        switch (this) {
            case ASSIDUIDADE:
                return "Assiduidade";
            case AUTONOMIA:
                return "Autonomia";
            case COMPETENCIAS:
                return "Competencias";
            case COMUNICACAO:
                return "Comunicação";
            case CUMPRIMENTO_DE_PRAZOS:
                return "Cumprimento Prazos";
            case LIDERANCA:
                return "Lideranca";
            case PONTUALIDADE:
                return "Pontualidade";
            case QUALIDADE_TRABALHO:
                return "Qualidade";
            case QUANTIDADE_TRABALHO:
                return "Quantidade";
        }
        return "";
    }
}
