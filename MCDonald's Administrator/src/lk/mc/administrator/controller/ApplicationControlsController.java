/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.mc.administrator.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author lakitha
 */
public class ApplicationControlsController {

    public static void showSmartComfirmation(StackPane stackPane, Node nodeToBeBlur, String message, List<JFXButton> controls) {
        BoxBlur bb = new BoxBlur(3, 3, 3);

        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach((controlButton) -> {
            controlButton.getStyleClass().add("button_dialog");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(message));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {

            nodeToBeBlur.setEffect(null);
        });

        nodeToBeBlur.setEffect(bb);
    }

    public static void showSmartDialog(String message, StackPane stackPane, Node needToBeBlur) {
        BoxBlur bb = new BoxBlur(3, 3, 3);
        JFXButton dialogButton = new JFXButton("OK");

        dialogButton.getStyleClass().add("button_dialog");
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(stackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        dialogButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

            dialog.close();
        });

        dialogLayout.setHeading(new Label(message));
        dialogLayout.setActions(dialogButton);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {

            needToBeBlur.setEffect(null);
        });

        needToBeBlur.setEffect(bb);
    }
    
    public static void setAnimationFade(int miliSec, Pane pane){
        FadeTransition ft = new FadeTransition(Duration.millis(miliSec), pane);
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
            ft.play();
    }
    
}
