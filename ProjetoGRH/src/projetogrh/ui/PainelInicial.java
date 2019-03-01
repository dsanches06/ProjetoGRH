/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.application.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.Empresa;

/**
 *
 * @author
 */
public class PainelInicial extends StackPane {

    public PainelInicial(BorderPane root, Empresa empresa) {

        setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        //menu principal
        menuPrincipal(root, grid, empresa);
        //coloca no centro da borderpane
        root.setCenter(grid);
    }

    private void menuPrincipal(BorderPane root, GridPane grid, Empresa empresa) {

        Text titulo = new Text("Projeto GRH - Avaliação de Desempenho");
        titulo.setId("titulo-text");
        grid.add(titulo, 0, 0, 3, 1);

        //imagem central
        ImageView logo = new ImageView(new Image("projetogrh/styles/logo.jpg"));
        logo.setFitHeight(650);
        logo.setFitWidth(650);
        logo.setPreserveRatio(true);
        logo.setSmooth(true);
        logo.setCache(true);

        //botao entrar
        Button btnEntrar = new Button("Entrar");
        btnEntrar.setPrefSize(220, 30);
        btnEntrar.setOnAction((ActionEvent e) -> {
            painelLogin(root, empresa);
        });

        //botao sair
        Button btnSair = new Button("Sair");
        btnSair.setPrefSize(220, 30);
        btnSair.setOnAction((ActionEvent e) -> {
            Platform.exit();
        });

        HBox hbox = new HBox(30);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(btnEntrar, btnSair);

        Pane painelBtn = new BorderComTitulo("Bem-vindo", hbox);
        painelBtn.getStyleClass().add("titled-address");
        painelBtn.setPrefSize(450, 100);

        //vbox 
        VBox vbox = new VBox(50);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(logo, painelBtn);
        grid.add(vbox, 0, 8);
    }

    private StackPane painelLogin(BorderPane root, Empresa empresa) {
        return new PainelLogin(root, empresa);
    }
}
