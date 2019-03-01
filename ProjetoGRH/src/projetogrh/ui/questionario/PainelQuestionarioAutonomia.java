/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui.questionario;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.*;
import projetogrh.ui.*;

/**
 *
 * @author
 */
public class PainelQuestionarioAutonomia extends StackPane {

    private int contador1 = 0;
    private int contador2 = 0;
    private int contador3 = 0;
    private int contador4 = 0;
    private int contador5 = 0;

    public PainelQuestionarioAutonomia(BorderPane root, Empresa empresa, Gestores gestora, Funcionario funcionario) {

        setAlignment(Pos.CENTER);

        VBox vbox3 = new VBox(30);
        vbox3.setAlignment(Pos.TOP_CENTER);
        //mostra a dashboard
        mostrarQuestionario(root, vbox3, empresa, gestora, funcionario);
        //posiciona a gridpane no centro da borderpane
        root.setCenter(vbox3);
    }

    private void mostrarQuestionario(BorderPane root, VBox vbox3, Empresa empresa, Gestores gestora, Funcionario funcionario) {

        GridPane gridTitulo = new GridPane();
        gridTitulo.setAlignment(Pos.TOP_CENTER);
        gridTitulo.setHgap(10);
        gridTitulo.setVgap(10);

        Text titulo = new Text("Questionário sobre Autonomia");
        titulo.setId("titulo-text");
        gridTitulo.add(titulo, 0, 0, 3, 1);

        //obter a imagem do perfil
        ImageView fotoFuncionario = null;

        switch (funcionario.getGenero()) {
            case "F":
                //obter a imagem do perfil feminino
                fotoFuncionario = new ImageView(new Image("projetogrh/styles/mulher.png"));
                fotoFuncionario.setFitHeight(60);
                fotoFuncionario.setFitWidth(60);
                break;
            case "M":
                //obter a imagem do perfil masculino
                fotoFuncionario = new ImageView(new Image("projetogrh/styles/homem.png"));
                fotoFuncionario.setFitHeight(60);
                fotoFuncionario.setFitWidth(60);
                break;
        }

        Text funcionarioText = new Text(funcionario.mostrarInfDashBoard());

        HBox hboxFuncionario = new HBox(5);
        hboxFuncionario.getChildren().addAll(fotoFuncionario, funcionarioText);

        //criar um painel
        Pane painelFuncionario = new BorderComTitulo("Perfil do Funcionário Avaliado", hboxFuncionario);
        painelFuncionario.getStyleClass().add("titled-address");
        painelFuncionario.setPrefSize(300, 30);

        //obter a imagem do perfil
        ImageView fotoGestor = null;

        switch (gestora.getGenero()) {
            case "F":
                //obter a imagem do perfil feminino
                fotoGestor = new ImageView(new Image("projetogrh/styles/mulher.png"));
                fotoGestor.setFitHeight(60);
                fotoGestor.setFitWidth(60);
                break;
            case "M":
                //obter a imagem do perfil masculino
                fotoGestor = new ImageView(new Image("projetogrh/styles/homem.png"));
                fotoGestor.setFitHeight(60);
                fotoGestor.setFitWidth(60);
                break;
        }

        Text gestorText = new Text(gestora.mostrarInfDashBoard());

        HBox hboxGestor = new HBox(5);
        hboxGestor.getChildren().addAll(fotoGestor, gestorText);

        //criar um painel
        Pane painelPerfil = new BorderComTitulo("Perfil de Gestor(a) Avaliador(a)", hboxGestor);
        painelPerfil.getStyleClass().add("titled-address");
        painelPerfil.setPrefSize(300, 30);

        //Vertical separator
        Separator separador3 = new Separator();
        separador3.setOrientation(Orientation.HORIZONTAL);
        separador3.setHalignment(HPos.CENTER);

        Separator separador4 = new Separator();
        separador4.setOrientation(Orientation.HORIZONTAL);
        separador4.setHalignment(HPos.CENTER);

        VBox vboxPerfil = new VBox(8);
        vboxPerfil.getChildren().addAll(painelFuncionario, separador3, painelPerfil, separador4);

        //Horizontal separator
        Separator separador2 = new Separator();
        separador2.setOrientation(Orientation.HORIZONTAL);
        separador2.setHalignment(HPos.CENTER);

        Button btnLimpar = new Button("Limpar Questionário");
        btnLimpar.setPrefSize(200, 30);

        Button btnCancelar = new Button("Cancelar");
        btnCancelar.setPrefSize(200, 30);

        Button btnAvancar = new Button("Avançar");
        btnAvancar.setPrefSize(200, 30);

        //Vertical separator
        Separator separadorVertical = new Separator();
        separadorVertical.setOrientation(Orientation.VERTICAL);
        separadorVertical.setHalignment(HPos.CENTER);

        GridPane gridDireito = new GridPane();
        gridDireito.setAlignment(Pos.TOP_CENTER);
        gridDireito.setHgap(10);
        gridDireito.setVgap(10);

        Label pergunta1 = new Label("01  -  Assume as responsabilidades"
                + "\n\t perante as chefias?");
        CheckBox chk11 = new CheckBox("Mau");
        CheckBox chk12 = new CheckBox("Insuficiente");
        CheckBox chk13 = new CheckBox("Suficiente");
        CheckBox chk14 = new CheckBox("Bom");
        CheckBox chk15 = new CheckBox("Muito Bom");

        chk11.setOnAction((ActionEvent e) -> {
            chk11.setDisable(true);
            chk12.setDisable(true);
            chk13.setDisable(true);
            chk14.setDisable(true);
            chk15.setDisable(true);
            contador1 += 1;
        });

        chk12.setOnAction((ActionEvent e) -> {
            chk11.setDisable(true);
            chk12.setDisable(true);
            chk13.setDisable(true);
            chk14.setDisable(true);
            chk15.setDisable(true);
            contador2 += 2;
        });

        chk13.setOnAction((ActionEvent e) -> {
            chk11.setDisable(true);
            chk12.setDisable(true);
            chk13.setDisable(true);
            chk14.setDisable(true);
            chk15.setDisable(true);
            contador3 += 3;
        });

        chk14.setOnAction((ActionEvent e) -> {
            chk11.setDisable(true);
            chk12.setDisable(true);
            chk13.setDisable(true);
            chk14.setDisable(true);
            chk15.setDisable(true);
            contador4 += 4;
        });

        chk15.setOnAction((ActionEvent e) -> {
            chk11.setDisable(true);
            chk12.setDisable(true);
            chk13.setDisable(true);
            chk14.setDisable(true);
            chk15.setDisable(true);
            contador5 += 5;
        });

        HBox hboxCheck1 = new HBox(30);
        hboxCheck1.getChildren().addAll(chk11, chk12, chk13, chk14, chk15);

        HBox hbox1 = new HBox(191);
        hbox1.getChildren().addAll(pergunta1, hboxCheck1);

        Label pergunta2 = new Label("02  -  Retira lições dos erros e"
                + "\n\t ultrapassa os fracassos"
                + "\n\t sem culpar os outros?");
        CheckBox chk21 = new CheckBox("Mau");
        CheckBox chk22 = new CheckBox("Insuficiente");
        CheckBox chk23 = new CheckBox("Suficiente");
        CheckBox chk24 = new CheckBox("Bom");
        CheckBox chk25 = new CheckBox("Muito Bom");

        chk21.setOnAction((ActionEvent e) -> {
            chk21.setDisable(true);
            chk22.setDisable(true);
            chk23.setDisable(true);
            chk24.setDisable(true);
            chk25.setDisable(true);
            contador1 += 1;
        });

        chk22.setOnAction((ActionEvent e) -> {
            chk21.setDisable(true);
            chk22.setDisable(true);
            chk23.setDisable(true);
            chk24.setDisable(true);
            chk25.setDisable(true);
            contador2 += 2;
        });

        chk23.setOnAction((ActionEvent e) -> {
            chk21.setDisable(true);
            chk22.setDisable(true);
            chk23.setDisable(true);
            chk24.setDisable(true);
            chk25.setDisable(true);
            contador3 += 3;
        });

        chk24.setOnAction((ActionEvent e) -> {
            chk21.setDisable(true);
            chk22.setDisable(true);
            chk23.setDisable(true);
            chk24.setDisable(true);
            chk25.setDisable(true);
            contador4 += 4;
        });

        chk25.setOnAction((ActionEvent e) -> {
            chk21.setDisable(true);
            chk22.setDisable(true);
            chk23.setDisable(true);
            chk24.setDisable(true);
            chk25.setDisable(true);
            contador5 += 5;
        });

        HBox hboxCheck2 = new HBox(30);
        hboxCheck2.getChildren().addAll(chk21, chk22, chk23, chk24, chk25);

        HBox hbox2 = new HBox(228);
        hbox2.getChildren().addAll(pergunta2, hboxCheck2);

        Label pergunta3 = new Label("03  -  Age com autonomia e responde"
                + "\n\t pelos riscos assumidos"
                + "\n\t resultados atingidos?");
        CheckBox chk31 = new CheckBox("Mau");
        CheckBox chk32 = new CheckBox("Insuficiente");
        CheckBox chk33 = new CheckBox("Suficiente");
        CheckBox chk34 = new CheckBox("Bom");
        CheckBox chk35 = new CheckBox("Muito Bom");

        chk31.setOnAction((ActionEvent e) -> {
            chk31.setDisable(true);
            chk32.setDisable(true);
            chk33.setDisable(true);
            chk34.setDisable(true);
            chk35.setDisable(true);
            contador1 += 1;
        });

        chk32.setOnAction((ActionEvent e) -> {
            chk31.setDisable(true);
            chk32.setDisable(true);
            chk33.setDisable(true);
            chk34.setDisable(true);
            chk35.setDisable(true);
            contador2 += 2;
        });

        chk33.setOnAction((ActionEvent e) -> {
            chk31.setDisable(true);
            chk32.setDisable(true);
            chk33.setDisable(true);
            chk34.setDisable(true);
            chk35.setDisable(true);
            contador3 += 3;
        });

        chk34.setOnAction((ActionEvent e) -> {
            chk31.setDisable(true);
            chk32.setDisable(true);
            chk33.setDisable(true);
            chk34.setDisable(true);
            chk35.setDisable(true);
            contador4 += 4;
        });

        chk35.setOnAction((ActionEvent e) -> {
            chk31.setDisable(true);
            chk32.setDisable(true);
            chk33.setDisable(true);
            chk34.setDisable(true);
            chk35.setDisable(true);
            contador5 += 5;
        });

        HBox hboxCheck3 = new HBox(30);
        hboxCheck3.getChildren().addAll(chk31, chk32, chk33, chk34, chk35);

        HBox hbox3 = new HBox(178);
        hbox3.getChildren().addAll(pergunta3, hboxCheck3);

        Label pergunta4 = new Label("04  -  Procura, em todas as circunstâncias"
                + "\n\t marcar a sua diferença?");
        CheckBox chk41 = new CheckBox("Mau");
        CheckBox chk42 = new CheckBox("Insuficiente");
        CheckBox chk43 = new CheckBox("Suficiente");
        CheckBox chk44 = new CheckBox("Bom");
        CheckBox chk45 = new CheckBox("Muito Bom");

        chk41.setOnAction((ActionEvent e) -> {
            chk41.setDisable(true);
            chk42.setDisable(true);
            chk43.setDisable(true);
            chk44.setDisable(true);
            chk45.setDisable(true);
            contador1 += 1;
        });

        chk42.setOnAction((ActionEvent e) -> {
            chk41.setDisable(true);
            chk42.setDisable(true);
            chk43.setDisable(true);
            chk44.setDisable(true);
            chk45.setDisable(true);
            contador2 += 2;
        });

        chk43.setOnAction((ActionEvent e) -> {
            chk41.setDisable(true);
            chk42.setDisable(true);
            chk43.setDisable(true);
            chk44.setDisable(true);
            chk45.setDisable(true);
            contador3 += 3;
        });

        chk44.setOnAction((ActionEvent e) -> {
            chk41.setDisable(true);
            chk42.setDisable(true);
            chk43.setDisable(true);
            chk44.setDisable(true);
            chk45.setDisable(true);
            contador4 += 4;
        });

        chk45.setOnAction((ActionEvent e) -> {
            chk41.setDisable(true);
            chk42.setDisable(true);
            chk43.setDisable(true);
            chk44.setDisable(true);
            chk45.setDisable(true);
            contador5 += 5;
        });

        HBox hboxCheck4 = new HBox(30);
        hboxCheck4.getChildren().addAll(chk41, chk42, chk43, chk44, chk45);

        HBox hbox4 = new HBox(142);
        hbox4.getChildren().addAll(pergunta4, hboxCheck4);

        Label pergunta5 = new Label("05  -  Reage bem às críticas dos outros e"
                + "\n\t às adversidades?");
        CheckBox chk51 = new CheckBox("Mau");
        CheckBox chk52 = new CheckBox("Insuficiente");
        CheckBox chk53 = new CheckBox("Suficiente");
        CheckBox chk54 = new CheckBox("Bom");
        CheckBox chk55 = new CheckBox("Muito Bom");

        chk51.setOnAction((ActionEvent e) -> {
            chk51.setDisable(true);
            chk52.setDisable(true);
            chk53.setDisable(true);
            chk54.setDisable(true);
            chk55.setDisable(true);
            contador1 += 1;
        });

        chk52.setOnAction((ActionEvent e) -> {
            chk51.setDisable(true);
            chk52.setDisable(true);
            chk53.setDisable(true);
            chk54.setDisable(true);
            chk55.setDisable(true);
            contador2 += 2;
        });

        chk53.setOnAction((ActionEvent e) -> {
            chk51.setDisable(true);
            chk52.setDisable(true);
            chk53.setDisable(true);
            chk54.setDisable(true);
            chk55.setDisable(true);
            contador3 += 3;
        });

        chk54.setOnAction((ActionEvent e) -> {
            chk51.setDisable(true);
            chk52.setDisable(true);
            chk53.setDisable(true);
            chk54.setDisable(true);
            chk55.setDisable(true);
            contador4 += 4;
        });

        chk55.setOnAction((ActionEvent e) -> {
            chk51.setDisable(true);
            chk52.setDisable(true);
            chk53.setDisable(true);
            chk54.setDisable(true);
            chk55.setDisable(true);
            contador5 += 5;
        });

        HBox hboxCheck5 = new HBox(30);
        hboxCheck5.getChildren().addAll(chk51, chk52, chk53, chk54, chk55);

        HBox hbox5 = new HBox(152);
        hbox5.getChildren().addAll(pergunta5, hboxCheck5);

        btnLimpar.setOnAction((ActionEvent e) -> {
            contador1 = 0;
            contador2 = 0;
            contador3 = 0;
            contador4 = 0;
            contador5 = 0;

            chk11.setSelected(false);
            chk12.setSelected(false);
            chk13.setSelected(false);
            chk14.setSelected(false);
            chk15.setSelected(false);

            chk21.setSelected(false);
            chk22.setSelected(false);
            chk23.setSelected(false);
            chk24.setSelected(false);
            chk25.setSelected(false);

            chk31.setSelected(false);
            chk32.setSelected(false);
            chk33.setSelected(false);
            chk34.setSelected(false);
            chk35.setSelected(false);

            chk41.setSelected(false);
            chk42.setSelected(false);
            chk43.setSelected(false);
            chk44.setSelected(false);
            chk45.setSelected(false);

            chk51.setSelected(false);
            chk52.setSelected(false);
            chk53.setSelected(false);
            chk54.setSelected(false);
            chk55.setSelected(false);

            chk11.setDisable(false);
            chk12.setDisable(false);
            chk13.setDisable(false);
            chk14.setDisable(false);
            chk15.setDisable(false);

            chk21.setDisable(false);
            chk22.setDisable(false);
            chk23.setDisable(false);
            chk24.setDisable(false);
            chk25.setDisable(false);

            chk31.setDisable(false);
            chk32.setDisable(false);
            chk33.setDisable(false);
            chk34.setDisable(false);
            chk35.setDisable(false);

            chk41.setDisable(false);
            chk42.setDisable(false);
            chk43.setDisable(false);
            chk44.setDisable(false);
            chk45.setDisable(false);

            chk51.setDisable(false);
            chk52.setDisable(false);
            chk53.setDisable(false);
            chk54.setDisable(false);
            chk55.setDisable(false);
        });

        btnCancelar.setOnAction((ActionEvent e) -> {
            int total = contador1 + contador2 + contador3 + contador4 + contador5;
            float mediaAutonomia = total / 5;
            funcionario.calcularAvaliacao(TipoAvaliacao.AUTONOMIA, mediaAutonomia);
            funcionario.calcularDesempenho(gestora);
            retrocederDashBoard(root, empresa, gestora);
        });

        btnAvancar.setOnAction((ActionEvent e) -> {
            if (!chk11.isSelected() && !chk12.isSelected()
                    && !chk13.isSelected() && !chk14.isSelected()
                    && !chk15.isSelected()) {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta preencher a Pergunta 01");
            } else if (!chk21.isSelected() && !chk22.isSelected()
                    && !chk23.isSelected() && !chk24.isSelected()
                    && !chk25.isSelected()) {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta preencher a Pergunta 02");
            } else if (!chk31.isSelected() && !chk32.isSelected()
                    && !chk33.isSelected() && !chk34.isSelected()
                    && !chk35.isSelected()) {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta preencher a Pergunta 03");
            } else if (!chk41.isSelected() && !chk42.isSelected()
                    && !chk43.isSelected() && !chk44.isSelected()
                    && !chk45.isSelected()) {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta preencher a Pergunta 04");
            } else if (!chk51.isSelected() && !chk52.isSelected()
                    && !chk53.isSelected() && !chk54.isSelected()
                    && !chk55.isSelected()) {
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta preencher a Pergunta 05");
            } else {
                int total = contador1 + contador2 + contador3 + contador4 + contador5;
                float mediaAutonomia = total / 5;
                funcionario.calcularAvaliacao(TipoAvaliacao.AUTONOMIA, mediaAutonomia);
                painelQuestionarioCompetencias(root, empresa, gestora, funcionario);
            }
        });

        VBox vboxDireito = new VBox(25);
        vboxDireito.setAlignment(Pos.TOP_LEFT);
        vboxDireito.getChildren().addAll(
                hbox1,
                hbox2,
                hbox3,
                hbox4,
                hbox5);

        Pane painel = new BorderComTitulo("Perguntas", vboxDireito);
        painel.getStyleClass().add("titled-address");
        painel.setPrefSize(1000, 400);

        HBox hboxBtn = new HBox(75);
        hboxBtn.setAlignment(Pos.CENTER);
        hboxBtn.getChildren().addAll(btnLimpar, btnCancelar, btnAvancar);

        Pane painelBtn = new BorderComTitulo("Operações", hboxBtn);
        painelBtn.getStyleClass().add("titled-address");

        //Vertical separator
        Separator separadorHorizontal = new Separator();
        separadorHorizontal.setOrientation(Orientation.HORIZONTAL);
        separadorHorizontal.setHalignment(HPos.CENTER);

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(painel,
                separadorHorizontal,
                painelBtn);

        HBox hboxGrid = new HBox(20);
        hboxGrid.setAlignment(Pos.TOP_CENTER);
        hboxGrid.getChildren().addAll(vboxPerfil, separadorVertical, vbox);

        vbox3.getChildren().addAll(gridTitulo, hboxGrid);

    }

    private StackPane painelQuestionarioCompetencias(BorderPane root, Empresa empresa, Gestores gestora, Funcionario funcionario) {
        return new PainelQuestionarioCompetencias(root, empresa, gestora, funcionario);
    }

    private StackPane retrocederDashBoard(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelDashBoard(root, empresa, gestora);
    }

}
