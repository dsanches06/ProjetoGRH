/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh;

import projetogrh.ui.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class ProjetoGRH extends Application {

    public static Stage stage;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Empresa empresa = new Empresa();

        painelInicial(root, empresa);
        Scene scene = new Scene(root, 1300, 700);
        scene.getStylesheets().add("projetogrh/styles/estilos.css");

        primaryStage.setTitle("Projeto GRH - Avaliação e Desempenho");
        primaryStage.getIcons().add(new Image("projetogrh/styles/favicon.png"));
        primaryStage.setScene(scene);
        primaryStage.show();
        stage = primaryStage;
    }

    private StackPane painelInicial(BorderPane root, Empresa empresa) {
        return new PainelInicial(root, empresa);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
