/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class PainelRegistarGestores extends StackPane {

    private String nomeGestora;
    private String genero;
    private String localidade;
    private String email;
    private String password;
    private String departamento;
    private String funcao;
    private int idade;
    private ObservableList<String> tipoFuncao;

    public PainelRegistarGestores(BorderPane root, Empresa empresa) {
        setAlignment(Pos.TOP_CENTER);
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        formulario(root, grid, empresa);
        root.setCenter(grid);
    }

    private void formulario(BorderPane root, GridPane grid, Empresa empresa) {

        Text titulo = new Text("Painel Registar Gestores");
        titulo.setId("titulo-text");
        titulo.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(titulo, 0, 0, 4, 1);

        Label nome = new Label("Nome");
        nome.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        nome.setId("label");

        TextField nomeTextField = new TextField();
        nomeTextField.getStyleClass().add("textfield");
        nomeTextField.setPrefSize(530, 30);

        HBox hboxNome = new HBox(69);
        hboxNome.getChildren().addAll(nome, nomeTextField);

        Label emailLabel = new Label("Email");
        emailLabel.setId("label");

        Label passwordLabel = new Label("Password");
        passwordLabel.setId("label");

        TextField passwordBox = new TextField();
        passwordBox.getStyleClass().add("textfield");
        passwordBox.setPrefSize(400, 30);

        HBox hboxPassword = new HBox(40);
        hboxPassword.getChildren().addAll(passwordLabel, passwordBox);

        TextField emailTextField = new TextField();
        emailTextField.getStyleClass().add("textfield");
        emailTextField.setPrefSize(400, 30);

        Label label = new Label("@grh.pt");
        label.setId("label");

        HBox hboxTextfield = new HBox(3);
        hboxTextfield.getChildren().addAll(emailTextField, label);

        HBox hboxEmail = new HBox(71);
        hboxEmail.getChildren().addAll(emailLabel, hboxTextfield);

        Label labelLocalidade = new Label("Localidade");
        labelLocalidade.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        labelLocalidade.setId("label");

        ObservableList<String> tipoLocalidade = FXCollections.observableArrayList(empresa.obterLocalidade());
        ComboBox<String> comboBoxLidade = new ComboBox<>();
        comboBoxLidade.setPrefSize(400, 30);
        comboBoxLidade.setItems(tipoLocalidade);
        comboBoxLidade.getStyleClass().add("textfield");

        HBox hboxLocalidade = new HBox(32);
        hboxLocalidade.getChildren().addAll(labelLocalidade, comboBoxLidade);

        Label generoLabel = new Label("Género");
        generoLabel.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        generoLabel.setId("label");

        ObservableList<String> tipoGenero = FXCollections.observableArrayList(empresa.obterGenero());
        ComboBox<String> comboBoxGenero = new ComboBox<>();
        comboBoxGenero.setPrefSize(400, 30);
        comboBoxGenero.setItems(tipoGenero);
        comboBoxGenero.getStyleClass().add("textfield");

        HBox hboxGenero = new HBox(60);
        hboxGenero.getChildren().addAll(generoLabel, comboBoxGenero);

        Label labelDepartamento = new Label("Departamento");
        labelDepartamento.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        labelDepartamento.setId("label");

        ObservableList<String> tipoDepartamento = FXCollections.observableArrayList(empresa.obterTipoDepartamento());
        ComboBox<String> comboBoxDepartamento = new ComboBox<>();
        comboBoxDepartamento.setPrefSize(400, 30);
        comboBoxDepartamento.setItems(tipoDepartamento);
        comboBoxDepartamento.getStyleClass().add("textfield");

        HBox hboxDepartamento = new HBox(10);
        hboxDepartamento.getChildren().addAll(labelDepartamento, comboBoxDepartamento);

        Label labelFuncao = new Label("Função");
        labelFuncao.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        labelFuncao.setId("label");

        ComboBox<String> comboBoxFuncao = new ComboBox<>();
        comboBoxFuncao.setPrefSize(400, 30);
        comboBoxFuncao.getStyleClass().add("textfield");

        HBox hboxFuncao = new HBox(60);
        hboxFuncao.getChildren().addAll(labelFuncao, comboBoxFuncao);

        comboBoxDepartamento.setOnAction((ActionEvent e) -> {
            String tipodepartamento = comboBoxDepartamento.getSelectionModel().getSelectedItem();
            //obter o departamento
            Departamento obterDepartamento = empresa.obterDepartamento(tipodepartamento);
            if (obterDepartamento != null) {
                //faz um switch
                switch (obterDepartamento.getNome()) {
                    case "Recursos Humanos":
                        tipoFuncao = FXCollections.observableArrayList(obterDepartamento.getListaFuncao());
                        break;
                    case "Comercial":
                        tipoFuncao = FXCollections.observableArrayList(obterDepartamento.getListaFuncao());
                        break;
                    case "Sistema de Informação":
                        tipoFuncao = FXCollections.observableArrayList(obterDepartamento.getListaFuncao());
                        break;
                    case "Financeiro":
                        tipoFuncao = FXCollections.observableArrayList(obterDepartamento.getListaFuncao());
                        break;
                    case "Produção":
                        tipoFuncao = FXCollections.observableArrayList(obterDepartamento.getListaFuncao());
                        break;
                }//mostra apenas as funcções para cada departamento
                comboBoxFuncao.setItems(tipoFuncao);
            }
        });

        Label labelIdade = new Label("Idade");
        labelIdade.setFont(Font.font("Cambria", FontWeight.NORMAL, 14));
        labelIdade.setId("label");

        TextField idadeTextField = new TextField();
        idadeTextField.getStyleClass().add("textfield");
        idadeTextField.setPrefSize(400, 30);

        HBox hboxIdade = new HBox(72);
        hboxIdade.getChildren().addAll(labelIdade, idadeTextField);

        VBox vboxFormulario = new VBox(20);
        vboxFormulario.getChildren().addAll(
                hboxNome,
                hboxEmail,
                hboxPassword,
                hboxLocalidade,
                hboxGenero,
                hboxDepartamento,
                hboxFuncao,
                hboxIdade);

        Pane painel1 = new BorderComTitulo("Formulário", vboxFormulario);
        painel1.getStyleClass().add("titled-address");
        grid.add(painel1, 0, 2);

        Button btnLimpar = new Button("Limpar");
        btnLimpar.setPrefSize(200, 30);

        Button btnCriar = new Button("Registar Gestores");
        btnCriar.setPrefSize(210, 30);

        Button btnRetroceder = new Button("Retroceder");
        btnRetroceder.setPrefSize(200, 30);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().addAll(btnCriar, btnLimpar, btnRetroceder);

        Pane painel2 = new BorderComTitulo("Operações", hbBtn);
        painel2.getStyleClass().add("titled-address");
        grid.add(painel2, 0, 4);

        /**
         * Evento do botão para limpar os dados preenchidos
         */
        btnLimpar.setOnAction((ActionEvent e) -> {
            nomeTextField.clear();
            emailTextField.clear();
            passwordBox.clear();
            comboBoxLidade.getSelectionModel().clearSelection();
            idadeTextField.clear();
            comboBoxGenero.getSelectionModel().clearSelection();
            comboBoxFuncao.getSelectionModel().clearSelection();

        });

        /**
         * Evento do botao para criar utilizador
         */
        btnCriar.setOnAction((ActionEvent e) -> {

            //apenas se estiver preenchida
            if (nomeTextField.getText().length() > 0) {
                nomeGestora = nomeTextField.getText();
            }//e se não estiver preenchida 
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                erro.mostrarDialogo("ERRO", "Nome tem de estar preenchido");
                nomeGestora = null;
            }

            //apenas se estiver preenchida
            if (emailTextField.getText().length() > 0) {
                if (empresa.isEmailExiste(emailTextField.getText().concat(label.getText())) == true) {
                    Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                    erro.mostrarDialogo("ERRO", "Este email já se encontra registado");
                    emailTextField.clear();
                    email = null;
                } else {
                    email = emailTextField.getText().concat(label.getText());
                }
            }//e se não estiver preenchida 
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                erro.mostrarDialogo("ERRO", "Email tem de estar preenchido");
                email = null;
            }

            //apenas se estiver preenchida
            if (passwordBox.getText().length() > 0) {
                password = passwordBox.getText();
            }//e se não estiver preenchida 
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                erro.mostrarDialogo("ERRO", "Password tem de estar preenchido");
                password = null;
            }

            //apenas se for selecionado
            if (comboBoxLidade.getSelectionModel().isEmpty() != true) {
                //obter o item selecionado da comboBox
                localidade = comboBoxLidade.getSelectionModel().getSelectedItem();

            }//e se não for selecionada
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta selecionar tipo de localidade");
                //genero fica a nulo
                localidade = null;
            }

            //apenas se for selecionado
            if (comboBoxGenero.getSelectionModel().isEmpty() != true) {
                //obter o item selecionado da comboBox
                genero = comboBoxGenero.getSelectionModel().getSelectedItem();

            }//e se não for selecionada
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta selecionar tipo de genero");
                //genero fica a nulo
                genero = null;
            }

            //apenas se for selecionado
            if (comboBoxDepartamento.getSelectionModel().isEmpty() != true) {
                //obter o item selecionado da comboBox
                departamento = comboBoxDepartamento.getSelectionModel().getSelectedItem();

            }//e se não for selecionada
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta selecionar tipo de departamento");
                //genero fica a nulo
                departamento = null;
            }

            //apenas se for selecionado
            if (comboBoxFuncao.getSelectionModel().isEmpty() != true) {
                //obter o item selecionado da comboBox
                funcao = comboBoxFuncao.getSelectionModel().getSelectedItem();

            }//e se não for selecionada
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                //mostra o dialogo
                erro.mostrarDialogo("ERRO", "Falta selecionar tipo de função");
                //genero fica a nulo
                funcao = null;
            }

            //apenas se estiver preenchida
            if (idadeTextField.getText().length() > 0) {
                idade = Integer.valueOf(idadeTextField.getText());
            }//e se não estiver preenchida 
            else {
                //cria o dialogo de erro
                Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                erro.mostrarDialogo("ERRO", "Idade tem de estar preenchido");
                idade = 0;
            }

            //validação de dados
            if ((nomeGestora != null)
                    && (genero != null)
                    && (email != null)
                    && (password != null)
                    && (localidade != null)
                    && (departamento != null)
                    && (funcao != null)
                    && (idade > 0)) {
                Gestores gestora = new Gestores(nomeGestora, genero, localidade, email, password, departamento, funcao, idade);

                if (empresa.adicionarGestor(gestora) == true) {
                    Dialogo inf = new Dialogo(Alert.AlertType.INFORMATION);
                    //mostra o dialogo
                    inf.mostrarDialogo("INFORMAÇÃO", "Conta de Gestor(a) foi criado com sucesso");
                } else {
                    //cria o dialogo de erro
                    Dialogo erro = new Dialogo(Alert.AlertType.ERROR);
                    //mostra o dialogo
                    erro.mostrarDialogo("ERRO", "Conta de Gestor(a) não foi criado");
                }
                painelLogin(root, empresa);
            }

        });

        /**
         * Evento do botão para Retroceder ao painel login
         */
        btnRetroceder.setOnAction((ActionEvent e) -> {
            painelLogin(root, empresa);

        });
    }

    private StackPane painelLogin(BorderPane root, Empresa empresa) {
        return new PainelLogin(root, empresa);
    }
}
