package me.vilius.angleshot.ui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import me.vilius.angleshot.Angleshot;
import me.vilius.angleshot.AngleshotListener;
import me.vilius.angleshot.feature.FeatureManager;
import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-19
 *   Inspired by Flex Seal™
 */
public class LoginScreen extends Application
{
    private final Path tokenFilePath = Paths.get(Angleshot.APP_NAME + ".token");

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Set the title to the name of the application
        primaryStage.setTitle(Angleshot.APP_NAME + " authentication");

        //Create a new grid, add all the required elements for authentication
        GridPane grid = new GridPane();

        //Set the grids' properties
        grid.setAlignment(Pos.CENTER);
        grid.setPrefWidth(290);
        grid.setPrefHeight(90);
        grid.setVgap(5);

        //Add a label
        Text title = new Text("Input your Discord token:");
        title.setFont(Font.loadFont(LoginScreen.class.getResourceAsStream("Roboto-Regular.ttf"), 16));
        grid.add(title, 1, 1);

        //Add token field
        PasswordField tokenField = new PasswordField();
        grid.add(tokenField, 1, 2);
        //See if we have a saved token to put in the field (first make sure the token file exists)
        tokenFilePath.toFile().createNewFile();
        String loadedToken = Files.lines(tokenFilePath).findFirst().orElse("");
        tokenField.setText(loadedToken);

        //Add authentication button
        Button authenticateButton = new Button("Authenticate");
        grid.add(authenticateButton, 1, 3);

        //Add an option for the users to save their token
        CheckBox saveToken = new CheckBox("Save token?");
        saveToken.setTranslateX(95); //Move it to the side :^)
        grid.add(saveToken, 1, 3);

        //Label that let's the user know the bots progress
        Label authState = new Label("Idling...");
        authState.setFont(Font.loadFont(LoginScreen.class.getResourceAsStream("Roboto-Regular.ttf"), 13));
        authState.setTextFill(Color.CORNFLOWERBLUE);
        GridPane.setHalignment(authState, HPos.CENTER);
        grid.add(authState, 1, 4);

        //Add an event listener to the authentication button
        authenticateButton.setOnAction(event -> {
            //Check if the user wanted to save their discord token, if so, handle it
            if(saveToken.isSelected()) //TODO: don't keep the damn token in plain text
            {
                try {
                    Files.write(tokenFilePath, tokenField.getText().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else //If the users don't want their tokens saved, wipe the token files
            {
                tokenFilePath.toFile().delete();
            }

            //Initialize our JDA instance with our given Discord token
            try {
                Angleshot.getInstance().setJDA(new JDABuilder(AccountType.CLIENT).setToken(tokenField.getText()).build());
                authState.setTextFill(Color.LAWNGREEN);
                authState.setText("Successfully authenticated, opening controls.");

                //Add our custom event listener to the JDA we built
                Angleshot.getInstance().getJDA().addEventListener(new AngleshotListener());

                //Wait for the JDA to be ready before we present the user with the features
                Angleshot.getInstance().getJDA().awaitStatus(JDA.Status.CONNECTED);

                //Initialize our feature manager
                Angleshot.getInstance().setFeatureManager(new FeatureManager());

                //Open the control window
                new ControlScreen().start(new Stage());

                //Bye login window
                primaryStage.close();
            } catch (LoginException e) {
                e.printStackTrace();
                authState.setTextFill(Color.FIREBRICK);
                authState.setText("Uh oh, are you sure your token is right?");
            } catch (Exception e) {
                e.printStackTrace();
                authState.setTextFill(Color.FIREBRICK);
                authState.setText("Uh oh, something went wrong.");
            }
        });

        //Set the scene
        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }
}
