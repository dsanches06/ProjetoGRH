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
public class Gestores extends Pessoa {

    private String password;

    public Gestores(String nome, String tipoGenero, String localidade, String email, String palavraPasse, String departamento, String funcao, int idade) {
        super(nome, tipoGenero, localidade, email, departamento, funcao, idade);
        this.password = palavraPasse;
    }

    public boolean autenticarSe(String email, String palavraPasse) {
        return email.equalsIgnoreCase(getEmail())
                && palavraPasse.equalsIgnoreCase(getPassword());
    }

    public String mostrarInfDashBoard() {
        StringBuilder str = new StringBuilder();
        str.append(this.getNome())
                .append("\n\nGenero: ")
                .append(this.getGenero())
                .append("\nLocalidade: ")
                .append(this.getLocalidade())
                .append("\nIdade: ")
                .append(this.getIdade())
                .append("\nDepartamento: ")
                .append(this.getDepartamento())
                .append("\nFunção: ")
                .append(this.getFuncao());

        return str.toString();
    }

    @Override
    public String getFuncao() {
        return super.getFuncao();
    }

    @Override
    public String getGenero() {
        return super.getGenero();
    }

    @Override
    public String getNome() {
        return super.getNome();
    }

    @Override
    public String getLocalidade() {
        return super.getLocalidade();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public int getIdade() {
        return super.getIdade();
    }

    @Override
    public String getDepartamento() {
        return super.getDepartamento();
    }

    public String getPassword() {
        return password;
    }

}
