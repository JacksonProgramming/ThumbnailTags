package com.example.thumbnailtags;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import javax.security.auth.login.LoginContext;
import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application implements EventHandler<ActionEvent> {
    private Genre genreTest = new Genre("testGenre.txt");

    TextField text = new TextField("Press a button");
    Label bottomText = new Label("");

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
        buttons.setSpacing(5);
        borderPane.setTop(buttons);
        text.setPrefHeight(100);
        borderPane.setCenter(text);
        borderPane.setBottom(bottomText);

        Scene scene = new Scene(borderPane);

        window.setScene(scene);
        window.setHeight(200);
        window.setWidth(200);
        window.show();

        //BUTTON CREATION & LOGIC
        for (String i: genreTest.returnKeySet()) {
            Button button = new Button(i);
            buttons.getChildren().add(button);
            button.setOnAction(this::handle);
            System.out.println(i);
        }

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

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        text.setText(genreTest.getGenre(button.getText().replaceAll(" ","")));
        copyToClipboard(button.getText().replaceAll(" ",""));
        bottomText.setText(button.getText() + " tags copied to clipboard!");
    }
}