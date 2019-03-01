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
public class Funcionario extends Pessoa implements Comparable<Funcionario> {

    private String avaliadora;
    private String desempenho;
    private String totalAvaliacao;
    private float valorAvaliacaoAssiduidade;
    private float valorAvaliacaoAutonomia;
    private float valorAvaliacaoCompetencias;
    private float valorAvaliacaoComunicacao;
    private float valorAvaliacaoCumprimentoPrazos;
    private float valorAvaliacaoLideranca;
    private float valorAvaliacaoPontualidade;
    private float valorAvaliacaoQualidadeTrabalho;
    private float valorAvaliacaoQuantidadeTrabalho;

    public Funcionario(String nome, String tipoGenero, String localidade, String email, String departamento, String funcao, int idade) {
        super(nome, tipoGenero, localidade, email, departamento, funcao, idade);
        this.avaliadora = "N/A";
        this.totalAvaliacao = "0";
        this.desempenho = "N/A";
        this.valorAvaliacaoAssiduidade = 0.0f;
        this.valorAvaliacaoAutonomia = 0.0f;
        this.valorAvaliacaoCompetencias = 0.0f;
        this.valorAvaliacaoComunicacao = 0.0f;
        this.valorAvaliacaoCumprimentoPrazos = 0.0f;
        this.valorAvaliacaoLideranca = 0.0f;
        this.valorAvaliacaoPontualidade = 0.0f;
        this.valorAvaliacaoQualidadeTrabalho = 0.0f;
        this.valorAvaliacaoQuantidadeTrabalho = 0.0f;
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
                .append(this.getFuncao())
                .append("\nAvaliacão Total: ")
                .append(this.getTotalAvaliacao())
                .append("\nDesempenho: ")
                .append(this.getDesempenho())
                .append("\nAvaliador(a): ")
                .append(this.getAvaliadora());
        return str.toString();
    }

    public void calcularAvaliacao(TipoAvaliacao tipoAvaliacao, float total) {
        switch (tipoAvaliacao) {
            case ASSIDUIDADE:
                setValorAvaliacaoAssiduidade(total);
                break;
            case AUTONOMIA:
                setValorAvaliacaoAutonomia(total);
                break;
            case COMPETENCIAS:
                setValorAvaliacaoCompetencias(total);
                break;
            case COMUNICACAO:
                setValorAvaliacaoComunicacao(total);
                break;
            case CUMPRIMENTO_DE_PRAZOS:
                setValorAvaliacaoCumprimentoPrazos(total);
                break;
            case LIDERANCA:
                setValorAvaliacaoLideranca(total);
                break;
            case PONTUALIDADE:
                setValorAvaliacaoPontualidade(total);
                break;
            case QUALIDADE_TRABALHO:
                setValorAvaliacaoQualidadeTrabalho(total);
                break;
            case QUANTIDADE_TRABALHO:
                setValorAvaliacaoQuantidadeTrabalho(total);
                break;
        }
    }

    public void calcularDesempenho(Gestores gestora) {

        float valor1 = getValorAvaliacaoAssiduidade() + getValorAvaliacaoAutonomia();
        float valor2 = getValorAvaliacaoCompetencias() + getValorAvaliacaoComunicacao();
        float valor3 = getValorAvaliacaoCumprimentoPrazos() + getValorAvaliacaoLideranca();
        float valor4 = getValorAvaliacaoPontualidade() + getValorAvaliacaoQualidadeTrabalho();
        float valor5 = getValorAvaliacaoQuantidadeTrabalho();

        float valorTotal = (valor1 + valor2 + valor3 + valor4 + valor5) / 9;

        if (valorTotal >= 0.0f && valorTotal < 2.0f) {
            setDesempenho(TipoDesempenho.MAU.toString());
        } else if (valorTotal >= 2.0f && valorTotal <  3.0f) {
            setDesempenho(TipoDesempenho.INSUFICIENTE.toString());
        } else if (valorTotal >= 3.0f && valorTotal < 4.0f) {
            setDesempenho(TipoDesempenho.SUFICIENTE.toString());
        } else if (valorTotal >= 4.0f && valorTotal < 4.5f) {
            setDesempenho(TipoDesempenho.BOM.toString());
        } else if (valorTotal >= 4.5f && valorTotal <= 5.0f) {
            setDesempenho(TipoDesempenho.MUITOBOM.toString());
        }
        setTotalAvaliacao(String.format("%.2f", valorTotal));
        setAvaliadora(gestora.getNome());
    }

    public String getAvaliadora() {
        return avaliadora;
    }

    public void setAvaliadora(String avaliadora) {
        this.avaliadora = avaliadora;
    }

    public String getDesempenho() {
        return desempenho;
    }

    public void setDesempenho(String desempenho) {
        this.desempenho = desempenho;
    }

    public String getTotalAvaliacao() {
        return totalAvaliacao;
    }

    public void setTotalAvaliacao(String totalAvaliacao) {
        this.totalAvaliacao = totalAvaliacao;
    }

    public float getValorAvaliacaoAssiduidade() {
        return valorAvaliacaoAssiduidade;
    }

    public void setValorAvaliacaoAssiduidade(float valorAvaliacaoAssiduidade) {
        this.valorAvaliacaoAssiduidade = valorAvaliacaoAssiduidade;
    }

    public float getValorAvaliacaoAutonomia() {
        return valorAvaliacaoAutonomia;
    }

    public void setValorAvaliacaoAutonomia(float valorAvaliacaoAutonomia) {
        this.valorAvaliacaoAutonomia = valorAvaliacaoAutonomia;
    }

    public float getValorAvaliacaoCompetencias() {
        return valorAvaliacaoCompetencias;
    }

    public void setValorAvaliacaoCompetencias(float valorAvaliacaoCompetencias) {
        this.valorAvaliacaoCompetencias = valorAvaliacaoCompetencias;
    }

    public float getValorAvaliacaoComunicacao() {
        return valorAvaliacaoComunicacao;
    }

    public void setValorAvaliacaoComunicacao(float valorAvaliacaoComunicacao) {
        this.valorAvaliacaoComunicacao = valorAvaliacaoComunicacao;
    }

    public float getValorAvaliacaoCumprimentoPrazos() {
        return valorAvaliacaoCumprimentoPrazos;
    }

    public void setValorAvaliacaoCumprimentoPrazos(float valorAvaliacaoCumprimentoPrazos) {
        this.valorAvaliacaoCumprimentoPrazos = valorAvaliacaoCumprimentoPrazos;
    }

    public float getValorAvaliacaoLideranca() {
        return valorAvaliacaoLideranca;
    }

    public void setValorAvaliacaoLideranca(float valorAvaliacaoLideranca) {
        this.valorAvaliacaoLideranca = valorAvaliacaoLideranca;
    }

    public float getValorAvaliacaoPontualidade() {
        return valorAvaliacaoPontualidade;
    }

    public void setValorAvaliacaoPontualidade(float valorAvaliacaoPontualidade) {
        this.valorAvaliacaoPontualidade = valorAvaliacaoPontualidade;
    }

    public float getValorAvaliacaoQualidadeTrabalho() {
        return valorAvaliacaoQualidadeTrabalho;
    }

    public void setValorAvaliacaoQualidadeTrabalho(float valorAvaliacaoQualidadeTrabalho) {
        this.valorAvaliacaoQualidadeTrabalho = valorAvaliacaoQualidadeTrabalho;
    }

    public float getValorAvaliacaoQuantidadeTrabalho() {
        return valorAvaliacaoQuantidadeTrabalho;
    }

    public void setValorAvaliacaoQuantidadeTrabalho(float valorAvaliacaoQuantidadeTrabalho) {
        this.valorAvaliacaoQuantidadeTrabalho = valorAvaliacaoQuantidadeTrabalho;
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
    public String getFuncao() {
        return super.getFuncao();
    }

    @Override
    public String getDepartamento() {
        return super.getDepartamento();
    }

    @Override
    public int compareTo(Funcionario f) {
        return getNome().compareTo(f.getNome());
    }

}
