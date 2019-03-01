/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import projetogrh.ui.questionario.*;
import java.io.*;
import java.util.List;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;
import projetogrh.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class PainelDashBoard extends StackPane {

    public PainelDashBoard(BorderPane root, Empresa empresa, Gestores gestora) {

        setAlignment(Pos.CENTER);

        VBox vbox3 = new VBox(10);
        vbox3.setAlignment(Pos.TOP_CENTER);
        //mostra a dashboard
        mostrarDashBoard(root, vbox3, empresa, gestora);
        //posiciona a gridpane no centro da borderpane
        root.setCenter(vbox3);
    }

    private void mostrarDashBoard(BorderPane root, VBox vbox3, Empresa empresa, Gestores gestora) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Text titulo = new Text("Painel DashBoard");
        titulo.setId("titulo-text");
        grid.add(titulo, 0, 0);

        //obter a imagem do perfil
        ImageView fotoPefil = null;

        switch (gestora.getGenero()) {
            case "F":
                //obter a imagem do perfil feminino
                fotoPefil = new ImageView(new Image("projetogrh/styles/mulher.png"));
                fotoPefil.setFitHeight(60);
                fotoPefil.setFitWidth(60);
                break;
            case "M":
                //obter a imagem do perfil masculino
                fotoPefil = new ImageView(new Image("projetogrh/styles/homem.png"));
                fotoPefil.setFitHeight(60);
                fotoPefil.setFitWidth(60);
                break;
        }

        Text perfilText = new Text(gestora.mostrarInfDashBoard());

        HBox hboxPefil = new HBox(5);
        hboxPefil.getChildren().addAll(fotoPefil, perfilText);

        VBox vboxGestor = new VBox(5);
        vboxGestor.setAlignment(Pos.CENTER);
        vboxGestor.getChildren().addAll(hboxPefil);

        //criar um painel
        Pane painelGestor = new BorderComTitulo("Perfil de Gestor(a) Avaliador(a)", vboxGestor);
        painelGestor.getStyleClass().add("titled-address");
        painelGestor.setPrefSize(220, 30);

        //separator
        Separator separador1 = new Separator();
        separador1.setOrientation(Orientation.VERTICAL);
        separador1.setHalignment(HPos.CENTER);

        Separator separador2 = new Separator();
        separador2.setOrientation(Orientation.VERTICAL);
        separador2.setHalignment(HPos.CENTER);

        Separator separador3 = new Separator();
        separador3.setOrientation(Orientation.HORIZONTAL);
        separador3.setHalignment(HPos.CENTER);

        Button btnCarregarBD = new Button("Carregar Dados BD");
        btnCarregarBD.setPrefSize(220, 30);

        Button btnNovo = new Button("Registar Funcionario");
        btnNovo.setPrefSize(220, 30);

        Button btnRemover = new Button("Remover Funcionario");
        btnRemover.setPrefSize(220, 30);

        Button btnAvaliar = new Button("Avaliar Funcionario");
        btnAvaliar.setPrefSize(220, 30);

        Button btnGrafico = new Button("Visualizar Gráficos");
        btnGrafico.setPrefSize(220, 30);

        Button btnRanking = new Button("Visualizar Ranking");
        btnRanking.setPrefSize(220, 30);

        Button btnSair = new Button("Sair da DashBoard");
        btnSair.setPrefSize(220, 30);

        Button btnImportar = new Button("Importar Ficheiro");
        btnImportar.setPrefSize(220, 30);

        Button btnSalvar = new Button("Salvar Ficheiro");
        btnSalvar.setPrefSize(220, 30);

        VBox vboxBtn1 = new VBox(30);
        vboxBtn1.getChildren().addAll(
                btnCarregarBD,
                btnNovo,
                btnRemover);

        VBox vboxBtn2 = new VBox(30);
        vboxBtn2.getChildren().addAll(
                btnAvaliar,
                btnGrafico,
                btnRanking);

        VBox vboxBtn3 = new VBox(30);
        vboxBtn3.getChildren().addAll(
                btnImportar,
                btnSalvar,
                btnSair);

        HBox hboxBtn = new HBox(25);
        hboxBtn.getChildren().addAll(vboxBtn1, vboxBtn2, vboxBtn3);

        Pane painelBtn = new BorderComTitulo("Operações", hboxBtn);
        painelBtn.getStyleClass().add("titled-address");
        painelBtn.setPrefSize(750, 240);

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.TOP_CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);

        //criar um painel
        Pane painelFuncionario = new BorderComTitulo("Perfil do Funcionário Avaliado", grid1);
        painelFuncionario.getStyleClass().add("titled-address");
        painelFuncionario.setPrefSize(400, 30);

        HBox hbox1 = new HBox(15);
        hbox1.getChildren().addAll(
                painelGestor,
                separador1,
                painelBtn,
                separador2,
                painelFuncionario);

        TableView<Funcionario> tabela = new TableView();
        tabela.setPrefHeight(800);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setEditable(true);

        tabela.setOnMouseClicked(e -> {
            Funcionario funcionario = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                VBox painel = obterFuncionario(funcionario);
                grid1.getChildren().clear();
                grid1.add(painel, 0, 1);
            }
        });

        TableColumn<Funcionario, String> colunaNome = new TableColumn("Funcionários");
        TableColumn<Funcionario, String> colunaAvaliadora = new TableColumn("Avaliador(a)");
        TableColumn<Funcionario, Integer> colunaAssiduidade = new TableColumn("Assiduidade");
        TableColumn<Funcionario, Integer> colunaAutonomia = new TableColumn("Autonómia");
        TableColumn<Funcionario, Integer> colunaCompetencias = new TableColumn("Competências");
        TableColumn<Funcionario, Integer> colunaComunicacao = new TableColumn("Comunicação");
        TableColumn<Funcionario, Integer> colunaCumprimmentoPrazos = new TableColumn("Cumprimento");
        TableColumn<Funcionario, Integer> colunaLideranca = new TableColumn("Liderança");
        TableColumn<Funcionario, Integer> colunaPontualidade = new TableColumn("Pontualidade");
        TableColumn colunaTrabalho = new TableColumn("Trabalho");
        TableColumn<Funcionario, Integer> colunaQualidadeTrabalho = new TableColumn("Qualidade");
        TableColumn<Funcionario, Integer> colunaQuantidadeTrabalho = new TableColumn("Quantidade");
        TableColumn colunaClassificacao = new TableColumn("Classificação Total");
        TableColumn<Funcionario, Float> colunaTotalAvaliacao = new TableColumn("Avaliação");
        TableColumn<Funcionario, String> colunaDesempenho = new TableColumn("Desempenho");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaAvaliadora.setCellValueFactory(new PropertyValueFactory<>("avaliadora"));
        colunaAssiduidade.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoAssiduidade"));
        colunaAutonomia.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoAutonomia"));
        colunaCompetencias.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoCompetencias"));
        colunaComunicacao.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoComunicacao"));
        colunaCumprimmentoPrazos.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoCumprimentoPrazos"));
        colunaLideranca.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoLideranca"));
        colunaPontualidade.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoPontualidade"));
        colunaQualidadeTrabalho.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoQualidadeTrabalho"));
        colunaQuantidadeTrabalho.setCellValueFactory(new PropertyValueFactory<>("valorAvaliacaoQuantidadeTrabalho"));
        colunaTrabalho.getColumns().addAll(colunaQualidadeTrabalho, colunaQuantidadeTrabalho);
        colunaTotalAvaliacao.setCellValueFactory(new PropertyValueFactory<>("totalAvaliacao"));
        colunaDesempenho.setCellValueFactory(new PropertyValueFactory<>("desempenho"));
        colunaClassificacao.getColumns().addAll(colunaTotalAvaliacao, colunaDesempenho);

        tabela.setItems(FXCollections.observableArrayList(empresa.funcionariosOrdenadosPorNome(empresa.getFuncionarios())));
        tabela.refresh();

        tabela.getColumns().addAll(colunaNome,
                colunaAvaliadora,
                colunaAssiduidade,
                colunaAutonomia,
                colunaCompetencias,
                colunaComunicacao,
                colunaCumprimmentoPrazos,
                colunaLideranca,
                colunaPontualidade,
                colunaTrabalho,
                colunaClassificacao);

        btnCarregarBD.setOnAction((ActionEvent e) -> {
            List<Funcionario> lista = empresa.obterFuncionariosBD();
            for (Funcionario f : lista) {
                empresa.adicionarFuncionario(f);
            }
            tabela.setItems(FXCollections.observableArrayList(empresa.funcionariosOrdenadosPorNome(empresa.getFuncionarios())));
            tabela.refresh();
        });

        btnNovo.setOnAction((ActionEvent e) -> {
            painelRegistarFuncionario(root, empresa, gestora);
        });

        btnRemover.setOnAction((ActionEvent e) -> {
            //obter funcionario da tabela
            Funcionario funcionario = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                empresa.removerFuncionario(funcionario);
                tabela.getItems().remove(funcionario);
                grid1.getChildren().clear();
                Dialogo inf = new Dialogo(AlertType.INFORMATION);
                //mostra o dialogo
                inf.mostrarDialogo("INFORMAÇÃO", "Funcionario removido com sucesso");
            } else {
                Dialogo erro = new Dialogo(AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Funcionario não selecionado");
            }
        });

        btnAvaliar.setOnAction((ActionEvent e) -> {
            //obter funcionario da tabela
            Funcionario funcionario = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                painelQuestionarioAssiduidade(root, empresa, gestora, funcionario);
            } else {
                Dialogo erro = new Dialogo(AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Funcionario não selecionado!");
            }
        });

        btnGrafico.setOnAction((ActionEvent e) -> {
            //obter funcionario da tabela
            Funcionario funcionario = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                if (funcionario.getDesempenho().equalsIgnoreCase("N/A")) {
                    Dialogo erro = new Dialogo(AlertType.ERROR);
                    //mostra o dialogo
                    erro.mostrarDialogo("ERRO", "Não é possivel visualizar graficos porque"
                            + "\nfuncionario não foi avaliado");
                } else {
                    painelGrafico(root, empresa, gestora, funcionario);
                }
            } else {
                Dialogo erro = new Dialogo(AlertType.WARNING);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Funcionario não selecionado!");
            }
        });

        btnRanking.setOnAction((ActionEvent e) -> {
            painelRanking(root, empresa, gestora);
        });

        btnImportar.setOnAction((ActionEvent e) -> {
            //cria uma instancia filechooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Importar Dados no Ficheiro EXCEL");
            //cria um filtro de extensão "*.log" e adiciona no file chooser
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXCEL files (*.xls)", "*.xls"));
            //mostrar dialog abrir/carregar
            File file = fileChooser.showOpenDialog(ProjetoGRH.stage);
            //se houver ficheiro
            if (file != null) {
                empresa.importarDados(file);
                tabela.setItems(FXCollections.observableArrayList(empresa.getFuncionarios()));
                tabela.refresh();
                Dialogo inf = new Dialogo(Alert.AlertType.INFORMATION);
                //mostra o dialogo
                inf.mostrarDialogo("INFORMAÇÃO", "Dados Importados do Ficheiro com sucesso");
            } else {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Operação Importar Dados do Ficheiro foi cancelada");
            }
        });

        btnSalvar.setOnAction((ActionEvent e) -> {
            //cria uma instancia filechooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Salvar Dados no Ficheiro EXCEL");
            //cria um filtro de extensão "*.log" e adiciona no file chooser
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("EXCEL files (*.xls)", "*.xls"));
            //mostra dialog salvar 
            File file = fileChooser.showSaveDialog(ProjetoGRH.stage);
            //se não cria um novo
            if (file != null) {
                empresa.gravaFicheiro(file);
                Dialogo inf = new Dialogo(Alert.AlertType.INFORMATION);
                //mostra o dialogo
                inf.mostrarDialogo("INFORMAÇÃO", "Dados Guardados no Ficheiro com sucesso");
            } else {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Operação Salvar Dados no Ficheiro foi cancelada");
            }
        });

        btnSair.setOnAction((ActionEvent e) -> {
            painelInicial(root, empresa);
        });

        VBox vboxPainel2 = new VBox();
        vboxPainel2.setAlignment(Pos.TOP_CENTER);
        vboxPainel2.getChildren().addAll(tabela);

        //criar um painel
        Pane painel2 = new BorderComTitulo("Lista de Funcionários", vboxPainel2);
        painel2.getStyleClass().add("titled-address");
        painel2.setPrefSize(1500, 564);

        VBox vbox2 = new VBox(10);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox2.getChildren().addAll(hbox1, separador3, painel2);

        vbox3.getChildren().addAll(grid, vbox2);
    }

    private StackPane painelInicial(BorderPane root, Empresa empresa) {
        return new PainelInicial(root, empresa);
    }

    private StackPane painelRegistarFuncionario(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelRegistarFuncionario(root, empresa, gestora);
    }

    private StackPane painelQuestionarioAssiduidade(BorderPane root, Empresa empresa, Gestores gestora, Funcionario funcionario) {
        return new PainelQuestionarioAssiduidade(root, empresa, gestora, funcionario);

    }

    private StackPane painelGrafico(BorderPane root, Empresa empresa, Gestores gestora, Funcionario funcionario) {
        return new PainelGrafico(root, empresa, gestora, funcionario);
    }

    private StackPane painelRanking(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelRanking(root, empresa, gestora);
    }

    private VBox obterFuncionario(Funcionario f) {

        //obter a imagem do perfil
        ImageView fotoFuncionario = null;

        switch (f.getGenero()) {
            case "M":
                //obter a imagem do perfil masculino
                fotoFuncionario = new ImageView(new Image("projetogrh/styles/homem.png"));
                fotoFuncionario.setFitHeight(60);
                fotoFuncionario.setFitWidth(60);
                break;
            case "F":
                //obter a imagem do perfil feminino
                fotoFuncionario = new ImageView(new Image("projetogrh/styles/mulher.png"));
                fotoFuncionario.setFitHeight(60);
                fotoFuncionario.setFitWidth(60);
                break;
        }

        Text funcionarioText = new Text(f.mostrarInfDashBoard());

        HBox hboxFuncionario = new HBox(5);
        hboxFuncionario.getChildren().addAll(fotoFuncionario, funcionarioText);

        VBox vboxFuncionario = new VBox(5);
        vboxFuncionario.setAlignment(Pos.TOP_CENTER);
        vboxFuncionario.getChildren().addAll(hboxFuncionario);

        return vboxFuncionario;

    }
}
