package me.vilius.angleshot.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import me.vilius.angleshot.Angleshot;
import me.vilius.angleshot.feature.Feature;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-22
 *   Inspired by Flex Seal™
 */
public class ControlScreen extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        //Set the title to the name of the application
        primaryStage.setTitle(Angleshot.APP_NAME + " " + Angleshot.APP_VERSION);

        //Tabpane for tabs of our features
        TabPane tabs = new TabPane();

        //Create tabs for every feature
        for (Feature feature : Angleshot.getInstance().getFeatureManager().getFeatureList()) {
            Tab featureTab = new Tab(feature.getName());
            //Attach our content to the tab
            featureTab.setContent(feature.getContent());
            //Make sure the users can't close the feature tabs (:P)
            featureTab.setClosable(false);
            tabs.getTabs().add(featureTab);
        }

        //If we close the control window, the application should shut down
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        //Create a scene with our features' tabs
        Scene scene = new Scene(tabs, 430, 280);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
