package me.vilius.angleshot.ui;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.vilius.angleshot.Angleshot;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-19
 *   Inspired by Flex Seal™
 */
public class LoginScreen extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set the title to the name of the application
        primaryStage.setTitle(Angleshot.APP_NAME + " " + Angleshot.APP_VERSION);

        //Create a new grid, add all the required elements for authentication
        GridPane grid = new GridPane();

        //Set the grids' properties
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWidth(270);
        grid.setPrefHeight(90);
        grid.setVgap(5);

        //Add a label
        Text title = new Text("Input your Discord token:");
        title.setFont(Font.loadFont(LoginScreen.class.getResourceAsStream("Roboto-Regular.ttf"), 16));
        grid.add(title, 1, 1);

        //Add token field
        PasswordField tokenField = new PasswordField();
        grid.add(tokenField, 1, 2);

        //Add authentication button
        Button authenticateButton = new Button("Authenticate");
        grid.add(authenticateButton, 1, 3);

        //Add an option for the users to save their token
        CheckBox saveToken = new CheckBox("Save token?");
        saveToken.setTranslateX(95); //Move it to the side :^)
        grid.add(saveToken, 1, 3);

        //Set the scene
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeBot(String discordToken)
    {

    }
}
