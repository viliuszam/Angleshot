package me.vilius.angleshot.feature;

import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-22
 *   Inspired by Flex Seal™
 */
public abstract class Feature
{
    private String name;
    private GridPane content;

    public Feature(String name, float width, float height)
    {
        //Constructor-y stuff
        this.name = name;

        //Initialize pane
        this.content = new GridPane();

        //Set the panes size
        this.content.setPrefSize(width, height);

        //Call the feature creation method (pane design, functionality etc)
        this.create();
    }

    public abstract void create();

    public GridPane getContent() {
        return content;
    }

    public String getName() {
        return name;
    }
}

