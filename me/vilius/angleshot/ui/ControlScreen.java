package me.vilius.angleshot.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import me.vilius.angleshot.Angleshot;
import me.vilius.angleshot.feature.Feature;

import java.text.SimpleDateFormat;

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

        //Root of all the content in the control screen
        VBox root = new VBox(2);
        root.setAlignment(Pos.CENTER);

        //Tabpane for tabs of our features
        TabPane tabs = new TabPane();

        //Information
        Label userInfo = new Label("Logged in as: " + Angleshot.getInstance().getJDA().getSelfUser().getName() + "#" + Angleshot.getInstance().getJDA().getSelfUser().getDiscriminator());
        Label channelInfo = new Label(channelStatus());

        //Create tabs for every feature
        for (Feature feature : Angleshot.getInstance().getFeatureManager().getFeatureList()) {
            Tab featureTab = new Tab(feature.getName());
            //Attach our content to the tab
            featureTab.setContent(feature.getContent());
            //Make sure the users can't close the feature tabs (:P)
            featureTab.setClosable(false);
            tabs.getTabs().add(featureTab);
        }

        //Make sure the channel info label is constantly being updated (stackoverflow'd gg)
        //TODO: simplify and de-uglify this
        Task updateInfoTask = new Task<Void>() {
            @Override
            protected Void call(){
                while (true) {
                    updateMessage(channelStatus());
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        break;
                    }
                }
                return null;
            }
        };
        channelInfo.textProperty().bind(updateInfoTask.messageProperty());
        new Thread(updateInfoTask).start();

        //If we close the control window, the application should shut down
        primaryStage.setOnCloseRequest(event -> System.exit(0));

        //Add everything to the content root
        root.getChildren().addAll(userInfo, channelInfo, tabs);

        //Create a scene with our features' tabs
        Scene scene = new Scene(root, 450, 315);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private String channelStatus(){
        return "Selected channel: " + (Angleshot.getInstance().getTextChannel() == null ? "none." : Angleshot.getInstance().getTextChannel().getGuild().getName() + " -> " + Angleshot.getInstance().getTextChannel().getName());
    }
}
