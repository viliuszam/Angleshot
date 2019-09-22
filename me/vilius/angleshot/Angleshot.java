package me.vilius.angleshot;

import javafx.application.Application;
import me.vilius.angleshot.ui.LoginScreen;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-19
 *   Inspired by Flex Seal™
 */
public class Angleshot
{
    //Some information about the app, should it ever change
    public final static String APP_NAME = "Angleshot", APP_VERSION = "v0.0.1";

    public static void main(String[] args) throws Exception
    {
        //Start the program by opening the login screen (have option to save the discord token)
        Application.launch(LoginScreen.class, args);
    }
}
