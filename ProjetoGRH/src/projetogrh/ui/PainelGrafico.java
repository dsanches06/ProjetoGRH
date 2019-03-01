/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import projetogrh.model.*;

/**
 *
 * @author
 */
public class PainelGrafico extends StackPane {

    public PainelGrafico(BorderPane root, Empresa empresa, Gestores gestora, Funcionario funcionario) {

        setAlignment(Pos.CENTER);

        VBox vbox3 = new VBox(20);
        vbox3.setAlignment(Pos.TOP_CENTER);
        mostrarGrafico(root, vbox3, empresa, gestora, funcionario);
        root.setCenter(vbox3);
    }

    private void mostrarGrafico(BorderPane root, VBox vbox3, Empresa empresa, Gestores gestora, Funcionario funcionario) {

        GridPane gridTitulo = new GridPane();
        gridTitulo.setAlignment(Pos.TOP_CENTER);
        gridTitulo.setHgap(10);
        gridTitulo.setVgap(10);

        Text titulo = new Text("Gráficos de Avaliação e Desempenho");
        titulo.setId("titulo-text");
        gridTitulo.add(titulo, 0, 0, 3, 1);

        GridPane gridGrafico = new GridPane();
        gridGrafico.setAlignment(Pos.TOP_CENTER);
        gridGrafico.setHgap(10);
        gridGrafico.setVgap(10);

        //obter a imagem do perfil
        ImageView fotoFuncionario = null;

        switch (funcionario.getGenero()) {
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

        Text funcionarioText = new Text(funcionario.mostrarInfDashBoard());

        HBox hboxFuncionario = new HBox(5);
        hboxFuncionario.getChildren().addAll(fotoFuncionario, funcionarioText);

        Button btnRetroceder = new Button("Retroceder");
        btnRetroceder.setPrefSize(200, 30);
        btnRetroceder.setOnAction((ActionEvent e) -> {
            dashBoardGestora(root, empresa, gestora);
        });

        HBox hboxBtn = new HBox();
        hboxBtn.setAlignment(Pos.TOP_CENTER);
        hboxBtn.getChildren().add(btnRetroceder);

        //criar um painel
        Pane painelFuncionario = new BorderComTitulo("Funcionário Avaliado", hboxFuncionario);
        painelFuncionario.getStyleClass().add("titled-address");
        painelFuncionario.setPrefSize(300, 30);

        Separator separador = new Separator();
        separador.setOrientation(Orientation.HORIZONTAL);
        separador.setHalignment(HPos.CENTER);

        //criar um painel
        Pane painelBtn = new BorderComTitulo("Operações", hboxBtn);
        painelBtn.getStyleClass().add("titled-address");

        VBox vboxFuncionario = new VBox(25);
        vboxFuncionario.setAlignment(Pos.TOP_CENTER);
        vboxFuncionario.getChildren().addAll(painelBtn, separador, painelFuncionario);

        //Horizontal separator
        Separator separador1 = new Separator();
        separador1.setOrientation(Orientation.VERTICAL);
        separador1.setHalignment(HPos.CENTER);

        Separator separador2 = new Separator();
        separador2.setOrientation(Orientation.VERTICAL);
        separador2.setHalignment(HPos.CENTER);

        Separator separador3 = new Separator();
        separador3.setOrientation(Orientation.HORIZONTAL);
        separador3.setHalignment(HPos.CENTER);

        PieChart pieChart = graficoPieChart(funcionario);
        LineChart lineChart = graficoLineChart(funcionario);
        BarChart barChart = graficoBarChart(funcionario);

        HBox hboxLineChart = new HBox(10);
        hboxLineChart.getChildren().add(lineChart);

        Pane painelLineChart = new BorderComTitulo("Gráfico LineChart", hboxLineChart);
        painelLineChart.getStyleClass().add("titled-address");

        HBox hboxPieChart = new HBox(10);
        hboxPieChart.getChildren().add(pieChart);

        Pane painelPieChart = new BorderComTitulo("Gráfico PieChart", hboxPieChart);
        painelPieChart.getStyleClass().add("titled-address");

        HBox hbox1 = new HBox(15);
        hbox1.getChildren().addAll(
                vboxFuncionario,
                separador1,
                painelLineChart,
                separador2,
                painelPieChart);

        HBox hboxBarChart = new HBox(10);
        hboxBarChart.getChildren().add(barChart);

        Pane painelBarChart = new BorderComTitulo("Gráfico BarChart", hboxBarChart);
        painelBarChart.getStyleClass().add("titled-address");
        painelBarChart.setPrefHeight(564);

        VBox vbox2 = new VBox(10);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox2.getChildren().addAll(hbox1, separador3, painelBarChart);

        vbox3.getChildren().addAll(gridTitulo, vbox2);
    }

    private PieChart graficoPieChart(Funcionario funcionario) {

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Assiduidade", funcionario.getValorAvaliacaoAssiduidade()),
                new PieChart.Data("Autonomia", funcionario.getValorAvaliacaoAutonomia()),
                new PieChart.Data("Competencias", funcionario.getValorAvaliacaoCompetencias()),
                new PieChart.Data("Comunicacao", funcionario.getValorAvaliacaoComunicacao()),
                new PieChart.Data("CumprimentoPrazos", funcionario.getValorAvaliacaoCumprimentoPrazos()),
                new PieChart.Data("Lideranca", funcionario.getValorAvaliacaoLideranca()),
                new PieChart.Data("Pontualidade", funcionario.getValorAvaliacaoPontualidade()),
                new PieChart.Data("Qualidade Trabalho", funcionario.getValorAvaliacaoQualidadeTrabalho()),
                new PieChart.Data("Quantidade Trabalho", funcionario.getValorAvaliacaoQuantidadeTrabalho()));

        PieChart piechart = new PieChart();
        piechart.setData(pieChartData);
        piechart.setLegendSide(Side.BOTTOM);
        piechart.setPrefSize(550, 550);
        piechart.setClockwise(false);
        piechart.setLabelsVisible(true);

        return piechart;
    }

    private BarChart graficoBarChart(Funcionario funcionario) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        BarChart<String, Number> barchart = new BarChart<>(xAxis, yAxis);
        barchart.setPrefSize(1500, 500);
        barchart.setBarGap(0);
        barchart.setCategoryGap(0);

        XYChart.Series serieAssiduidade = new XYChart.Series();
        serieAssiduidade.setName("Assiduidade");
        serieAssiduidade.getData().add(new XYChart.Data("Assiduidade", funcionario.getValorAvaliacaoAssiduidade()));

        XYChart.Series serieAutonomia = new XYChart.Series();
        serieAutonomia.setName("Autonomia");
        serieAutonomia.getData().add(new XYChart.Data("Autonomia", funcionario.getValorAvaliacaoAutonomia()));

        XYChart.Series serieCompetencias = new XYChart.Series();
        serieCompetencias.setName("Competencias");
        serieCompetencias.getData().add(new XYChart.Data("Competencias", funcionario.getValorAvaliacaoCompetencias()));

        XYChart.Series serieComunicacao = new XYChart.Series();
        serieComunicacao.setName("Comunicação");
        serieComunicacao.getData().add(new XYChart.Data("Comunicação", funcionario.getValorAvaliacaoComunicacao()));

        XYChart.Series serieCumprimentoPrazos = new XYChart.Series();
        serieCumprimentoPrazos.setName("Cumprimento Prazos");
        serieCumprimentoPrazos.getData().add(new XYChart.Data("CumprimentoPrazos", funcionario.getValorAvaliacaoCumprimentoPrazos()));

        XYChart.Series serieLideranca = new XYChart.Series();
        serieLideranca.setName("Liderança");
        serieLideranca.getData().add(new XYChart.Data("Liderança", funcionario.getValorAvaliacaoLideranca()));

        XYChart.Series seriePontualidade = new XYChart.Series();
        seriePontualidade.setName("Pontualidade");
        seriePontualidade.getData().add(new XYChart.Data("Pontualidade", funcionario.getValorAvaliacaoPontualidade()));

        XYChart.Series serieQualidadeTrabalho = new XYChart.Series();
        serieQualidadeTrabalho.setName("Qualidade Trabalho");
        serieQualidadeTrabalho.getData().add(new XYChart.Data("Qualidade Trabalho", funcionario.getValorAvaliacaoQualidadeTrabalho()));

        XYChart.Series serieQuantidadeTrabalho = new XYChart.Series();
        serieQuantidadeTrabalho.setName("Quantidade Trabalho");
        serieQuantidadeTrabalho.getData().add(new XYChart.Data("Quantidade Trabalho", funcionario.getValorAvaliacaoQuantidadeTrabalho()));

        barchart.getData().addAll(
                serieAssiduidade,
                serieAutonomia,
                serieCompetencias,
                serieComunicacao,
                serieCumprimentoPrazos,
                seriePontualidade,
                serieLideranca,
                serieQualidadeTrabalho,
                serieQuantidadeTrabalho);

        return barchart;
    }

    private LineChart graficoLineChart(Funcionario funcionario) {

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        LineChart<String, Number> linechart = new LineChart<>(xAxis, yAxis);
        linechart.setPrefSize(450, 400);

        XYChart.Series totalAvaliacao = new XYChart.Series();
        totalAvaliacao.setName("Avaliação de Desempenho");

        totalAvaliacao.getData().addAll(
                new XYChart.Data("Assiduidade", funcionario.getValorAvaliacaoAssiduidade()),
                new XYChart.Data("Autonomia", funcionario.getValorAvaliacaoAutonomia()),
                new XYChart.Data("Competencias", funcionario.getValorAvaliacaoCompetencias()),
                new XYChart.Data("Comunicacao", funcionario.getValorAvaliacaoComunicacao()),
                new XYChart.Data("CumprimentoPrazos", funcionario.getValorAvaliacaoCumprimentoPrazos()),
                new XYChart.Data("Lideranca", funcionario.getValorAvaliacaoLideranca()),
                new XYChart.Data("Pontualidade", funcionario.getValorAvaliacaoPontualidade()),
                new XYChart.Data("Qualidade Trabalho", funcionario.getValorAvaliacaoQualidadeTrabalho()),
                new XYChart.Data("Quantidade Trabalho", funcionario.getValorAvaliacaoQuantidadeTrabalho()));

        linechart.getData().addAll(totalAvaliacao);

        return linechart;
    }

    private StackPane dashBoardGestora(BorderPane root, Empresa empresa, Gestores gestora) {
        return new PainelDashBoard(root, empresa, gestora);
    }

}
