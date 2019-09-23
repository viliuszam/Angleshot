package me.vilius.angleshot.feature.include;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import me.vilius.angleshot.Angleshot;
import me.vilius.angleshot.feature.Feature;
import javafx.scene.control.*;
import net.dv8tion.jda.api.entities.User;

import static me.vilius.angleshot.utility.Wrapper.sendDirectMessage;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-23
 *   Inspired by Flex Seal™
 */

public class TestFeature extends Feature
{
    public TestFeature() {
        super("Test", 400, 230);
    }

    @Override
    public void create() {
        //Set the grids' properties
        getContent().setAlignment(Pos.TOP_LEFT);
        getContent().setVgap(15);
        getContent().setHgap(15);

        Button sendMessage = new Button("Send");

        sendMessage.setOnAction(event -> {
            sendDirectMessage(Angleshot.getInstance().getJDA().getUserByTag("Titas", "9501"), "test");
        });

        getContent().add(sendMessage, 1, 1);
    }
}
