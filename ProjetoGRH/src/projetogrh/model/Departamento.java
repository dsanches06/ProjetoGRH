/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.model;

import java.util.*;

/**
 *
 * @author
 */
public class Departamento {

    private String nome;
    private TipoDepartamento tipo;
    private ArrayList<String> listaFuncao;

    public Departamento(String nome, TipoDepartamento tipo) {
        this.nome = nome;
        this.tipo = tipo;
        this.listaFuncao = new ArrayList<>();
    }

    public void adicionarFuncao(String funcao) {
        listaFuncao.add(funcao);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoDepartamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDepartamento tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getListaFuncao() {
        return listaFuncao;
    }

    public void setListaFuncao(ArrayList<String> listaFuncao) {
        this.listaFuncao = listaFuncao;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.tipo);
        hash = 83 * hash + Objects.hashCode(this.listaFuncao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Departamento other = (Departamento) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (!Objects.equals(this.listaFuncao, other.listaFuncao)) {
            return false;
        }
        return true;
    }
   
    
}
