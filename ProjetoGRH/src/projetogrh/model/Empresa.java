/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.model;

import java.io.*;
import java.text.*;
import java.util.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;

/**
 *
 * @author
 */
public class Empresa implements Serializable {

    private List<Gestores> gestores;
    private List<Funcionario> funcionarios;
    private final List<Departamento> departamentos;

    public Empresa() {
        this.gestores = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.departamentos = new ArrayList<>();
        criarDepartamento();
    }

    public boolean adicionarGestor(Gestores gestora) {
        if (gestores.contains(gestora) != true) {
            gestores.add(gestora);
            return true;
        }
        return false;
    }

    public boolean removerGestor(Gestores gestora) {
        if (gestores.contains(gestora) == true) {
            gestores.remove(gestora);
            return true;
        }
        return false;
    }

    public boolean isEmailExiste(String email) {
        if (gestores.size() > 0) {
            for (Gestores gestora : gestores) {
                if (gestora.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        if (funcionarios.size() > 0) {
            for (Funcionario funcionario : funcionarios) {
                if (funcionario.getEmail().equalsIgnoreCase(email)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Gestores obterGestora(String email, String palavraChave) {
        if (gestores.size() > 0) {
            for (Gestores gestora : gestores) {
                if ((isEmailExiste(email) == true)
                        && (gestora.getPassword().equalsIgnoreCase(palavraChave))) {
                    return gestora;
                }
            }
        }
        return null;
    }

    public boolean autenticarSe(Gestores gestora) {
        Gestores gestoraAux;
        gestoraAux = obterGestora(gestora.getEmail(), gestora.getPassword());
        if (gestoraAux != null) {
            gestoraAux.autenticarSe(gestora.getEmail(), gestora.getPassword());
            return true;
        }
        return false;
    }

    public boolean adicionarFuncionario(Funcionario funcionario) {
        if (funcionario != null) {
            if (isFuncionarioExiste(funcionario) != true) {
                funcionarios.add(funcionario);
                return true;
            } else {
                Funcionario f = obterFuncionario(funcionario);
                removerFuncionario(f);
                adicionarFuncionario(funcionario);
            }
        }
        return false;
    }

    private boolean isFuncionarioExiste(Funcionario funcionario) {
        Funcionario f = obterFuncionario(funcionario);
        return (f != null) && (f.equals(funcionario));
    }

    private Funcionario obterFuncionario(Funcionario funcionario) {
        for (Funcionario func_aux : funcionarios) {
            if (func_aux.getNome().equalsIgnoreCase(funcionario.getNome())
                    && (func_aux.getIdade() == funcionario.getIdade())
                    && (func_aux.getEmail().equalsIgnoreCase(funcionario.getEmail()))) {
                return func_aux;
            }
        }
        return null;
    }

    public boolean removerFuncionario(Funcionario f) {
        if (f != null) {
            if (isFuncionarioExiste(f) == true) {
                funcionarios.remove(f);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "";
        for (Gestores f : gestores) {
            if (f != null) {
                result += f.mostrarInfDashBoard();
            }
        }
        for (Funcionario f : funcionarios) {
            if (f != null) {
                result += f.mostrarInfDashBoard();
            }
        }
        return result;
    }

    public List<Gestores> getGestores() {
        return gestores;
    }

    public void setGestores(List<Gestores> gestores) {
        this.gestores = gestores;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public void gravaFicheiro(File file) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Lista de Funcionarios");
            Row cabecalho = sheet.createRow(0);

            cabecalho.createCell(0).setCellValue("Funcionários");
            cabecalho.createCell(1).setCellValue("Genero");
            cabecalho.createCell(2).setCellValue("Localidade");
            cabecalho.createCell(3).setCellValue("Email");
            cabecalho.createCell(4).setCellValue("Idade");
            cabecalho.createCell(5).setCellValue("Departamento");
            cabecalho.createCell(6).setCellValue("Função");
            cabecalho.createCell(7).setCellValue("Avaliador(a)");
            cabecalho.createCell(8).setCellValue("Assiduidade");
            cabecalho.createCell(9).setCellValue("Autonomia");
            cabecalho.createCell(10).setCellValue("Competencias");
            cabecalho.createCell(11).setCellValue("Comunicacao");
            cabecalho.createCell(12).setCellValue("Cumprimento Prazos");
            cabecalho.createCell(13).setCellValue("Lideranca");
            cabecalho.createCell(14).setCellValue("Pontualidade");
            cabecalho.createCell(15).setCellValue("Qualidade Trabalho");
            cabecalho.createCell(16).setCellValue("Quantidade Trabalho");
            cabecalho.createCell(17).setCellValue("Avaliação");
            cabecalho.createCell(18).setCellValue("Desempenho");

            for (int i = 0; i < 19; i++) {
                CellStyle style = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 11);
                style.setFont(font);
                style.setVerticalAlignment(CellStyle.ALIGN_CENTER);
                cabecalho.getCell(i).setCellStyle(style);
            }
            int rownum = 0;
            List<Funcionario> ordenarFuncionario = funcionariosOrdenadosPorNome(funcionarios);
            for (Funcionario funcionario : ordenarFuncionario) {
                Row coluna = sheet.createRow(++rownum);
                salvarDados(coluna, funcionario);
            }

            for (int i = 0; i < 19; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            workbook.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void salvarDados(Row coluna, Funcionario funcionario) {

        Cell cellNome = coluna.createCell(0);
        cellNome.setCellValue(funcionario.getNome());

        Cell cellGenero = coluna.createCell(1);
        cellGenero.setCellValue(funcionario.getGenero());

        Cell cellLocalidade = coluna.createCell(2);
        cellLocalidade.setCellValue(funcionario.getLocalidade());

        Cell cellEmail = coluna.createCell(3);
        cellEmail.setCellValue(funcionario.getEmail());

        Cell cellIdade = coluna.createCell(4);
        cellIdade.setCellValue(funcionario.getIdade());

        Cell cellDepartamento = coluna.createCell(5);
        cellDepartamento.setCellValue(funcionario.getDepartamento());

        Cell cellFuncao = coluna.createCell(6);
        cellFuncao.setCellValue(funcionario.getFuncao());

        Cell cellAvaliadora = coluna.createCell(7);
        cellAvaliadora.setCellValue(funcionario.getAvaliadora());

        Cell cellAssiduidade = coluna.createCell(8);
        cellAssiduidade.setCellValue(funcionario.getValorAvaliacaoAssiduidade());

        Cell cellAutonomia = coluna.createCell(9);
        cellAutonomia.setCellValue(funcionario.getValorAvaliacaoAutonomia());

        Cell cellCompetencias = coluna.createCell(10);
        cellCompetencias.setCellValue(funcionario.getValorAvaliacaoCompetencias());

        Cell cellComunicacao = coluna.createCell(11);
        cellComunicacao.setCellValue(funcionario.getValorAvaliacaoComunicacao());

        Cell cellCumprimentoPrazos = coluna.createCell(12);
        cellCumprimentoPrazos.setCellValue(funcionario.getValorAvaliacaoCumprimentoPrazos());

        Cell cellLideranca = coluna.createCell(13);
        cellLideranca.setCellValue(funcionario.getValorAvaliacaoLideranca());

        Cell cellPontualidade = coluna.createCell(14);
        cellPontualidade.setCellValue(funcionario.getValorAvaliacaoPontualidade());

        Cell cellQualidadeTrabalho = coluna.createCell(15);
        cellQualidadeTrabalho.setCellValue(funcionario.getValorAvaliacaoQualidadeTrabalho());

        Cell cellQuantidadeTrabalho = coluna.createCell(16);
        cellQuantidadeTrabalho.setCellValue(funcionario.getValorAvaliacaoQuantidadeTrabalho());

        Cell cellAvaliacao = coluna.createCell(17);
        cellAvaliacao.setCellValue(funcionario.getTotalAvaliacao());

        Cell cellDesempenho = coluna.createCell(18);
        cellDesempenho.setCellValue(funcionario.getDesempenho());
    }

    private boolean isRowEmpty(Row coluna, int ultimaCell) {
        for (int c = coluna.getFirstCellNum(); c < ultimaCell; c++) {
            Cell cell = coluna.getCell(c, Row.CREATE_NULL_AS_BLANK);
            if (cell != null && cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                return false;
            }
        }
        return true;
    }

    private List<ArrayList<Object>> lerFicheiro(File file) {
        List<ArrayList<Object>> listaFuncionarios = new ArrayList<>();
        FileInputStream inputStream;
        HSSFWorkbook workbook;
        HSSFSheet firstSheet;
        Iterator<Row> iterator;
        int maxDataCount = 0;

        try {
            inputStream = new FileInputStream(file);
            workbook = new HSSFWorkbook(inputStream);
            firstSheet = workbook.getSheetAt(0);
            iterator = firstSheet.iterator();

            while (iterator.hasNext()) {
                Row nextRow = iterator.next();

                if (nextRow.getRowNum() == 0) {
                    maxDataCount = nextRow.getLastCellNum();
                    continue;
                }

                if (this.isRowEmpty(nextRow, maxDataCount)) {
                    break;
                }

                ArrayList<Object> singleRows = new ArrayList<>();

                for (int cn = 0; cn < maxDataCount; cn++) {

                    Cell cell = nextRow.getCell(cn, Row.CREATE_NULL_AS_BLANK);

                    switch (cell.getCellType()) {

                        case Cell.CELL_TYPE_NUMERIC:
                            singleRows.add(cell.getNumericCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            singleRows.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            singleRows.add(null);
                            break;
                    }
                }
                listaFuncionarios.add(singleRows);
            }
            inputStream.close();
            workbook.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return listaFuncionarios;
    }

    private List<Funcionario> obterFuncionarioFicheiro(File file) {
        DecimalFormat df = new DecimalFormat("#.##");
        List<Funcionario> lista = new ArrayList<>();
        List<ArrayList<Object>> listaFuncionarios = lerFicheiro(file);
        for (ArrayList<Object> obter : listaFuncionarios) {

            String nome = obter.get(0).toString();
            String genero = obter.get(1).toString();
            String localidade = obter.get(2).toString();
            String email = obter.get(3).toString();
            int idade = Integer.parseInt(df.format(obter.get(4)));
            String departamento = obter.get(5).toString();
            String funcao = obter.get(6).toString();

            Funcionario funcionario = new Funcionario(nome, genero, localidade, email, departamento, funcao, idade);

            funcionario.setAvaliadora((String) obter.get(7).toString());
            funcionario.setValorAvaliacaoAssiduidade(Integer.parseInt(df.format(obter.get(8))));
            funcionario.setValorAvaliacaoAutonomia(Integer.parseInt(df.format(obter.get(9))));
            funcionario.setValorAvaliacaoCompetencias(Integer.parseInt(df.format(obter.get(10))));
            funcionario.setValorAvaliacaoComunicacao(Integer.parseInt(df.format(obter.get(11))));
            funcionario.setValorAvaliacaoCumprimentoPrazos(Integer.parseInt(df.format(obter.get(12))));
            funcionario.setValorAvaliacaoLideranca(Integer.parseInt(df.format(obter.get(13))));
            funcionario.setValorAvaliacaoPontualidade(Integer.parseInt(df.format(obter.get(14))));
            funcionario.setValorAvaliacaoQualidadeTrabalho(Integer.parseInt(df.format(obter.get(15))));
            funcionario.setValorAvaliacaoQuantidadeTrabalho(Integer.parseInt(df.format(obter.get(16))));
            funcionario.setTotalAvaliacao((String) obter.get(17).toString());
            funcionario.setDesempenho(obter.get(18).toString());

            lista.add(funcionario);
        }
        return lista;
    }

    public void importarDados(File file) {
        List<Funcionario> ordenarFuncionario = funcionariosOrdenadosPorNome(obterFuncionarioFicheiro(file));
        for (Funcionario f : ordenarFuncionario) {
            adicionarFuncionario(f);
        }
    }

    public List<Funcionario> funcionariosOrdenadosPorNome(List<Funcionario> listaFuncionarios) {
        List<Funcionario> lista = new ArrayList<>();
        for (Funcionario f : listaFuncionarios) {
            lista.add(f);
        }
        Collections.sort(lista);
        return lista;
    }

    public List<String> obterLocalidade() {
        List<String> lista = new ArrayList<>();
        lista.add("Almada");
        lista.add("Aveiro");
        lista.add("Barreiro");
        lista.add("Coimbra");
        lista.add("Faro");
        lista.add("Lisboa");
        lista.add("Montijo");
        lista.add("Porto");
        lista.add("Seixal");
        lista.add("Setúbal");
        return lista;
    }

    public List<String> obterGenero() {
        List<String> lista = new ArrayList<>();
        lista.add("F");
        lista.add("M");
        return lista;
    }

    public List<String> obterTipoDepartamento() {
        List<String> lista = new ArrayList<>();
        lista.add(TipoDepartamento.COMERCIAL.toString());
        lista.add(TipoDepartamento.FINANCEIRO.toString());
        lista.add(TipoDepartamento.PRODUCAO.toString());
        lista.add(TipoDepartamento.RECURSOS_HUMANOS.toString());
        lista.add(TipoDepartamento.SI.toString());
        Collections.sort(lista);
        return lista;
    }

    private void criarDepartamento() {

        Departamento rh = new Departamento("Recursos Humanos", TipoDepartamento.RECURSOS_HUMANOS);
        rh.adicionarFuncao("Gerente");
        rh.adicionarFuncao("Coordenador(a)");
        rh.adicionarFuncao("Analista de Pessoal");
        rh.adicionarFuncao("Especialista");

        Departamento comercial = new Departamento("Comercial", TipoDepartamento.COMERCIAL);
        comercial.adicionarFuncao("Gerente");
        comercial.adicionarFuncao("Supervisor(a)");
        comercial.adicionarFuncao("Assistente");
        comercial.adicionarFuncao("Promotor(a)");

        Departamento si = new Departamento("Sistema de Informação", TipoDepartamento.SI);
        si.adicionarFuncao("Administrador(a)");
        si.adicionarFuncao("Consultor(a)");
        si.adicionarFuncao("Programador(a)");
        si.adicionarFuncao("Gestor(a) de Projeto");
        si.adicionarFuncao("Analista de SI");

        Departamento financeiro = new Departamento("Financeiro", TipoDepartamento.FINANCEIRO);
        financeiro.adicionarFuncao("Gerente");
        financeiro.adicionarFuncao("Tesouraria");
        financeiro.adicionarFuncao("Contabilista");
        financeiro.adicionarFuncao("Supervisor(a)");

        Departamento producao = new Departamento("Produção", TipoDepartamento.PRODUCAO);
        producao.adicionarFuncao("Gerente");
        producao.adicionarFuncao("Operador Montagem");
        producao.adicionarFuncao("Operador Acabamento");
        producao.adicionarFuncao("Operador Manutenção");

        //adicionar na lista de departamentos
        departamentos.add(rh);
        departamentos.add(comercial);
        departamentos.add(si);
        departamentos.add(financeiro);
        departamentos.add(producao);
    }

    public Departamento obterDepartamento(String tipo) {
        for (Departamento departamento : departamentos) {
            if (departamento.getNome().equalsIgnoreCase(tipo)) {
                return departamento;
            }
        }
        return null;
    }

    public List<Funcionario> obterFuncionariosBD() {
        List<Funcionario> lista = new ArrayList<>();
        lista.add(new Funcionario("Ana Carolina", "F", "Porto", "ana.carolina@ips.pt", "Comercial", "Assistente", 22));
        lista.add(new Funcionario("André Fernandes", "M", "Setúbal", "andre@ips.pt", "Financeiro", "Contabilista", 30));
        lista.add(new Funcionario("Cléria Laurinda", "F", "Setúbal", "cleria@ips.pt", "Produção", "Operador Acabamento", 24));
        lista.add(new Funcionario("Edvaldo Fernandes", "M", "Faro", "edvaldo@ips.pt", "Comercial", "Supervisor(a)", 27));
        lista.add(new Funcionario("Engrácia Jungo", "F", "Barreiro", "engracia@ips.pt", "Sistema de Informação", "Consultor(a)", 26));
        lista.add(new Funcionario("Fabio Pacheco", "M", "Aveiro", "fabio.pacheco@ips.pt", "Produção", "Operador Manutenção", 22));
        lista.add(new Funcionario("João Felipe", "M", "Lisboa", "joao@ips.pt", "Sistema de Informação", "Programador(a)", 29));
        lista.add(new Funcionario("Lourdes Pina", "F", "Seixal", "lourdes@ips.pt", "Financeiro", "Supervisor(a)", 31));
        lista.add(new Funcionario("Manuel André", "M", "Montijo", "manuel@ips.pt", "Comercial", "Promotor(a)", 33));
        lista.add(new Funcionario("Paulo Afonso", "M", "Almada", "paulo.afonso@ips.pt", "Sistema de Informação", "Analista de SI", 23));
        lista.add(new Funcionario("Zezinho Soares", "M", "Seixal", "zezinho@ips.pt", "Produção", "Operador Montagem", 28));
        return lista;
    }

    // Bubble Sort Algorithm in Ascending Order
    public Funcionario[] crescente(Funcionario[] array) {
        Funcionario temp;
        float valor1;
        float valor2;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                valor1 = Float.parseFloat(array[j - 1].getTotalAvaliacao().replace(',', '.'));
                valor2 = Float.parseFloat(array[j].getTotalAvaliacao().replace(',', '.'));
                if (valor1 > valor2) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public ArrayList<Funcionario> ordenarCrescente() {
        if (funcionarios != null) {
            ArrayList<Funcionario> lista = new ArrayList<>();
            Funcionario[] func_aux = funcionarios.toArray(new Funcionario[funcionarios.size()]);
            Funcionario[] func_aux1 = crescente(func_aux);
            for (Funcionario f : func_aux1) {
                lista.add(f);
            }
            return lista;
        }
        return null;
    }

    // Bubble Sort Algorithm in Descending Order
    private Funcionario[] decrescente(Funcionario[] array) {
        Funcionario temp;
        float valor1;
        float valor2;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 1; j < array.length - i; j++) {
                valor1 = Float.parseFloat(array[j - 1].getTotalAvaliacao().replace(',', '.'));
                valor2 = Float.parseFloat(array[j].getTotalAvaliacao().replace(',', '.'));
                if (valor1 < valor2) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    public ArrayList<Funcionario> ordenarDecrescente() {
        if (funcionarios != null) {
            ArrayList<Funcionario> lista = new ArrayList<>();
            Funcionario[] func_aux = funcionarios.toArray(new Funcionario[funcionarios.size()]);
            Funcionario[] func_aux1 = decrescente(func_aux);
            for (Funcionario f : func_aux1) {
                lista.add(f);
            }
            return lista;
        }
        return null;
    }

}
