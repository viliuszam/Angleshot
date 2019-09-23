package me.vilius.angleshot;

import javafx.application.Application;
import javafx.application.Platform;
import me.vilius.angleshot.feature.FeatureManager;
import me.vilius.angleshot.ui.LoginScreen;
import net.dv8tion.jda.api.JDA;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-19
 *   Inspired by Flex Seal™
 */
public class Angleshot
{
    //Some information about the app
    public final static String APP_NAME = "Angleshot", APP_VERSION = "v0.0.1";

    //Our JDA instance
    private JDA jda;

    //Instance of the manager for all our feature objects
    private FeatureManager featureManager;

    private static Angleshot instance = new Angleshot();

    public static void main(String[] args) throws Exception
    {
        //Start the program by opening the login screen (have option to save the discord token)
        Platform.setImplicitExit(false);
        Application.launch(LoginScreen.class);
    }

    public JDA getJDA(){
        return this.jda;
    }

    public void setJDA(JDA jda) {
        this.jda = jda;
    }

    public static Angleshot getInstance() {
        return instance;
    }

    public FeatureManager getFeatureManager() {
        return featureManager;
    }

    public void setFeatureManager(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }
}
