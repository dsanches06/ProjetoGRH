/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.cell.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class PainelRanking extends StackPane {

    public PainelRanking(BorderPane root, Empresa empresa, Gestores gestora) {

        setAlignment(Pos.CENTER);

        VBox vbox3 = new VBox(20);
        vbox3.setAlignment(Pos.TOP_CENTER);
        mostrarGrafico(root, vbox3, empresa, gestora);
        root.setCenter(vbox3);
    }

    private void mostrarGrafico(BorderPane root, VBox vbox3, Empresa empresa, Gestores gestora) {

        GridPane gridTitulo = new GridPane();
        gridTitulo.setAlignment(Pos.TOP_CENTER);
        gridTitulo.setHgap(10);
        gridTitulo.setVgap(10);

        Text titulo = new Text("Visualizar Ranking");
        titulo.setId("titulo-text");
        gridTitulo.add(titulo, 0, 0, 3, 1);

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

        Button btnConsultar = new Button("Consultar");
        btnConsultar.setPrefSize(200, 30);

        Button btnRetroceder = new Button("Retroceder");
        btnRetroceder.setPrefSize(200, 30);

        VBox vboxBtn = new VBox(15);
        vboxBtn.setAlignment(Pos.TOP_CENTER);
        vboxBtn.getChildren().addAll(btnConsultar, btnRetroceder);

        Separator separador = new Separator();
        separador.setOrientation(Orientation.HORIZONTAL);
        separador.setHalignment(HPos.CENTER);

        //criar um painel
        Pane painelBtn = new BorderComTitulo("Operações", vboxBtn);
        painelBtn.getStyleClass().add("titled-address");

        TableView<Funcionario> tabela = new TableView();
        tabela.setPrefHeight(800);
        tabela.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabela.setEditable(true);

        GridPane grid1 = new GridPane();
        grid1.setAlignment(Pos.TOP_CENTER);
        grid1.setHgap(10);
        grid1.setVgap(10);

        tabela.setOnMouseClicked(e -> {
            Funcionario funcionario = (Funcionario) tabela.getSelectionModel().getSelectedItem();
            if (funcionario != null) {
                VBox dashBoardFuncionario = obterFuncionario(funcionario);
                grid1.getChildren().clear();
                grid1.add(dashBoardFuncionario, 0, 1);
            }
        });

        //criar um painel
        Pane painelFuncionario = new BorderComTitulo("Perfil do Funcionário Avaliado", grid1);
        painelFuncionario.getStyleClass().add("titled-address");
        painelFuncionario.setPrefSize(400, 30);

        //A aplicação deverá permitir consultar o ranking dos jogadores, por estratégia de pontuação.
        CheckBox chkA = new CheckBox("Crescente");
        chkA.setId("chkbox");

        CheckBox chkB = new CheckBox("Decrescente");
        chkB.setId("chkbox");

        chkA.setOnAction((ActionEvent e) -> {
            //desativa checkbox A e B
            chkA.setDisable(true);
            chkB.setDisable(true);
        });

        chkB.setOnAction((ActionEvent e) -> {
            //desativa checkbox A e B
            chkA.setDisable(true);
            chkB.setDisable(true);
        });

        HBox hboxCheckBox = new HBox(60);
        hboxCheckBox.setAlignment(Pos.CENTER);
        hboxCheckBox.getChildren().addAll(chkA, chkB);

        //criar um painel
        Pane painelRanking = new BorderComTitulo("Painel Ordenação", hboxCheckBox);
        painelRanking.getStyleClass().add("titled-address");
        painelRanking.setPrefSize(300, 30);

        VBox vboxPainel = new VBox(10);
        vboxPainel.setAlignment(Pos.TOP_CENTER);
        vboxPainel.getChildren().addAll(painelRanking, separador, painelBtn);
        //Horizontal separator
        Separator separador1 = new Separator();
        separador1.setOrientation(Orientation.VERTICAL);
        separador1.setHalignment(HPos.CENTER);

        Separator separador2 = new Separator();
        separador2.setOrientation(Orientation.VERTICAL);
        separador2.setHalignment(HPos.CENTER);

        Separator separador3 = new Separator();
        separador3.setOrientation(Orientation.VERTICAL);
        separador3.setHalignment(HPos.CENTER);

        Separator separador4 = new Separator();
        separador4.setOrientation(Orientation.HORIZONTAL);
        separador4.setHalignment(HPos.CENTER);

        ImageView imagem = new ImageView(new Image("projetogrh/styles/ranking.png"));
        imagem.setFitHeight(190);
        imagem.setFitWidth(290);

        Label labelQuantidade = new Label("Quantidade Funcionário: 0");

        VBox vboxImagem = new VBox();
        vboxImagem.setAlignment(Pos.TOP_LEFT);
        vboxImagem.getChildren().addAll(labelQuantidade, imagem);

        //criar um painel
        Pane painelImagem = new BorderComTitulo("Ranking", vboxImagem);
        painelImagem.getStyleClass().add("titled-address");
        painelImagem.setPrefSize(300, 30);

        HBox hbox1 = new HBox(15);
        hbox1.getChildren().addAll(
                vboxPainel,
                separador1,
                painelGestor,
                separador2,
                painelFuncionario,
                separador3,
                painelImagem);

        btnConsultar.setOnAction((ActionEvent e) -> {
            if (chkA.isSelected()) {
                tabela.setItems(FXCollections.observableArrayList(empresa.ordenarCrescente()));
                labelQuantidade.setText("Quantidade Funcionário: " + empresa.getFuncionarios().size());
            }//se checkbox B estiver selecionado 
            else if (chkB.isSelected()) {
                tabela.setItems(FXCollections.observableArrayList(empresa.ordenarDecrescente()));
                labelQuantidade.setText("Quantidade Funcionário: " + empresa.getFuncionarios().size());

            } else {
                Dialogo erro = new Dialogo(AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta selecionar o tipo de ordenação");
                tabela.getItems().clear();
                labelQuantidade.setText("Quantidade Funcionário: 0");
                grid1.getChildren().clear();
            }
            tabela.refresh();
            limparCheckBox(chkA, chkB);
        });

        btnRetroceder.setOnAction((ActionEvent e) -> {
            dashBoardGestora(root, empresa, gestora);
        });

        TableColumn<Funcionario, String> colunaNome = new TableColumn("Funcionários");
        TableColumn<Funcionario, String> colunaDepartamento = new TableColumn("Departamento");
        TableColumn colunaClassificacao = new TableColumn("Classificação Total");
        TableColumn<Funcionario, Float> colunaTotalAvaliacao = new TableColumn("Avaliação");
        TableColumn<Funcionario, String> colunaDesempenho = new TableColumn("Desempenho");
        TableColumn<Funcionario, String> colunaAvaliadora = new TableColumn("Avaliador(a)");

        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaDepartamento.setCellValueFactory(new PropertyValueFactory<>("departamento"));
        colunaAvaliadora.setCellValueFactory(new PropertyValueFactory<>("avaliadora"));
        colunaTotalAvaliacao.setCellValueFactory(new PropertyValueFactory<>("totalAvaliacao"));
        colunaDesempenho.setCellValueFactory(new PropertyValueFactory<>("desempenho"));
        colunaClassificacao.getColumns().addAll(colunaTotalAvaliacao, colunaDesempenho);

        tabela.getColumns().addAll(
                colunaNome,
                colunaDepartamento,
                colunaClassificacao,
                colunaAvaliadora);

        VBox vboxPainel2 = new VBox();
        vboxPainel2.setAlignment(Pos.TOP_CENTER);
        vboxPainel2.getChildren().addAll(tabela);

        //criar um painel
        Pane painel2 = new BorderComTitulo("Lista de Funcionários Ordenados por Avaliação", vboxPainel2);
        painel2.getStyleClass().add("titled-address");
        painel2.setPrefSize(1500, 564);

        VBox vbox2 = new VBox(10);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox2.getChildren().addAll(hbox1, separador4, painel2);

        vbox3.getChildren().addAll(gridTitulo, vbox2);
    }

    private StackPane dashBoardGestora(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelDashBoard(root, empresa, gestora);
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

    private void limparCheckBox(CheckBox chkA, CheckBox chkB) {
        //se checkbox A estiver selecionado
        if (chkA.isSelected()) {
            //limpar a checkbox A
            chkA.setSelected(false);
            //desativa checkbox A e B
            chkA.setDisable(false);
            chkB.setDisable(false);
        } //se checkbox B estiver selecionado 
        else if (chkB.isSelected()) {
            //limpar a checkbox B
            chkB.setSelected(false);
            //desativa checkbox A e B
            chkA.setDisable(false);
            chkB.setDisable(false);
        }
    }

}
