/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.application.Platform;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class PainelLogin extends StackPane {

    private String email;
    private String password;

    public PainelLogin(BorderPane root, Empresa empresa) {
        setAlignment(Pos.CENTER);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        menuLogin(root, grid, empresa);
        root.setCenter(grid);
    }

    private void menuLogin(BorderPane root, GridPane grid, Empresa empresa) {

        Text titulo = new Text("Menu de Autenticação");
        titulo.setId("titulo-text");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 3, 1);

        Label emailLabel = new Label("Email");
        emailLabel.setId("label");

        TextField emailTextField = new TextField();
        emailTextField.getStyleClass().add("textfield");
        emailTextField.setPrefSize(245, 30);

        Label label = new Label("@grh.pt");
        label.setId("label");

        HBox hboxTextfield = new HBox(3);
        hboxTextfield.getChildren().addAll(emailTextField, label);

        HBox hboxEmail = new HBox(40);
        hboxEmail.getChildren().addAll(emailLabel, hboxTextfield);

        Label passwordLabel = new Label("Password");
        passwordLabel.setId("label");

        PasswordField passwordBox = new PasswordField();
        passwordBox.getStyleClass().add("textfield");
        passwordBox.setPrefSize(300, 30);

        HBox hboxPassword = new HBox(10);
        hboxPassword.getChildren().addAll(passwordLabel, passwordBox);

        Button btnLogin = new Button("Autenticar-se");
        btnLogin.setPrefSize(200, 30);

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefSize(200, 30);

        HBox hbBtn = new HBox(10);
        hbBtn.getChildren().addAll(btnLogin, btnLimpar);

        VBox vbBtn = new VBox(20);
        vbBtn.getChildren().addAll(hboxEmail, hboxPassword, hbBtn);

        //criar um painel
        Pane painel1 = new BorderComTitulo("Autenticação", vbBtn);
        painel1.getStyleClass().add("titled-address");
        painel1.setPrefSize(450, 100);
        grid.add(painel1, 0, 8);

        Button btnRegistar = new Button("Novo");
        btnRegistar.setPrefSize(200, 30);

        //botao sair
        Button btnRetroceder = new Button("Retroceder");
        btnRetroceder.setPrefSize(220, 30);

        HBox hbtnRegistar = new HBox(10);
        hbtnRegistar.setAlignment(Pos.CENTER);
        hbtnRegistar.getChildren().addAll(btnRegistar, btnRetroceder);

        //criar um painel
        Pane painel2 = new BorderComTitulo("Registar Gestor(a)", hbtnRegistar);
        painel2.getStyleClass().add("titled-address");
        painel2.setPrefSize(450, 100);
        grid.add(painel2, 0, 11);

        btnLogin.setOnAction((ActionEvent e) -> {
            if ((emailTextField.getText().length() > 0) && (passwordBox.getText().length() > 0)) {
                email = emailTextField.getText().concat(label.getText());
                password = passwordBox.getText();
                Gestores gestora = empresa.obterGestora(email, password);
                if (empresa.getGestores().contains(gestora) == true) {
                    if (empresa.autenticarSe(gestora) == true) {
                        Dialogo inf = new Dialogo(AlertType.INFORMATION);
                        inf.mostrarDialogo("INFORMAÇÃO", "Login efectuado com sucesso");
                        //mostra o painel menu de utilizador
                        dashBoardGestora(root, empresa, gestora);
                    }
                }//se nao existir 
                else {
                    //cria o dialogo de erro
                    Dialogo erro = new Dialogo(AlertType.ERROR);
                    //mostra o dialogo
                    erro.mostrarDialogo("ERRO", "Email e/ou Password inválido!");
                    //limpa o nickname inserido
                    emailTextField.clear();
                    //limpa a password inserida
                    passwordBox.clear();
                }
            } else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Deve preencher email com Password");
                //limpa o nickname inserido
                emailTextField.clear();
                //limpa a password inserida
                passwordBox.clear();
            }
        });

        btnLimpar.setOnAction((ActionEvent e) -> {
            //limpa o nickname inserido
            emailTextField.clear();
            //limpa a password inserida
            passwordBox.clear();
        });

        btnRegistar.setOnAction((ActionEvent e) -> {
            painelRegistarGestora(root, empresa);
        });

        btnRetroceder.setOnAction((ActionEvent e) -> {
           painelInicial(root, empresa) ;
        });
    }

    private StackPane painelRegistarGestora(BorderPane root, Empresa empresa) {
        return new PainelRegistarGestores(root, empresa);
    }

    private StackPane dashBoardGestora(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelDashBoard(root, empresa, gestora);
    }
	
	
    private StackPane painelInicial(BorderPane root, Empresa empresa) {
        return new PainelInicial(root, empresa);
    }

}
