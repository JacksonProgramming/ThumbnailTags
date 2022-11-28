package com.example.thumbnailtags;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.security.auth.login.LoginContext;
import java.io.IOException;

public class HelloApplication extends Application {
    private Genre genreTest = new Genre("testGenre.txt");

    @Override
    public void start(Stage window) throws IOException {
        //SET FILEPATH
        boolean isSuccess = genreTest.load();
        if (isSuccess) {
            System.out.println("Genres loaded!");
        }

        //UI
        BorderPane borderPane = new BorderPane();
        HBox buttons = new HBox();
        Button proRev = new Button("Pro Revenge");
        Button IDWHL = new Button("IDWHL");
        buttons.getChildren().addAll(proRev,IDWHL);
        buttons.setSpacing(5);
        borderPane.setTop(buttons);
        TextField text = new TextField("Press a button");
        text.setPrefHeight(100);
        borderPane.setCenter(text);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.setHeight(200);
        window.setWidth(200);
        window.show();

        //LOGIC
        proRev.setOnMouseClicked((event) -> {
            text.setText(genreTest.getGenre("ProRevenge"));
            copyToClipboard("ProRevenge");
        });

        IDWHL.setOnMouseClicked((event) -> {
            text.setText(genreTest.getGenre("IDWHL"));
            copyToClipboard("IDWHL");
        });


    }

    public boolean makeButtons() {


        return true;
    }

    public String copyToClipboard(String text) {
       String toBeCopied = genreTest.getGenre(text);
       ClipboardContent clipboard = new ClipboardContent();
       clipboard.putString(toBeCopied);
       Clipboard.getSystemClipboard().setContent(clipboard);
       return "Success!";
    }

    public static void main(String[] args) {
        launch();
    }
}