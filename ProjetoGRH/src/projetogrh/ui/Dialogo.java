/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetogrh.ui;

import javafx.scene.control.*;
import javafx.scene.control.Alert.*;

/**
 *
 * @author
 */
public class Dialogo {

    private final Alert dialogo;

    public Dialogo(AlertType tipo) {
        this.dialogo = new Alert(tipo);
    }

    public void mostrarDialogo(String titulo, String mensagem) {
        this.dialogo.setTitle(titulo);
        this.dialogo.setHeaderText(null);
       
        this.dialogo.setContentText(mensagem);
        this.dialogo.showAndWait();
    }
}
