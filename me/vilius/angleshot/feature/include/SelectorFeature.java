package me.vilius.angleshot.feature.include;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import me.vilius.angleshot.Angleshot;
import me.vilius.angleshot.feature.Feature;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.utils.WidgetUtil;
import net.dv8tion.jda.internal.utils.PermissionUtil;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-23
 *   Inspired by Flex Seal™
 */
public class SelectorFeature extends Feature
{
    public SelectorFeature() {
        super("Server Selector",600, 600);
    }

    @Override
    public void create() {
        //Set the grids' properties
        getContent().setAlignment(Pos.TOP_LEFT);
        getContent().setVgap(15);
        getContent().setHgap(15);

        ListView<Guild> guildList = new ListView(FXCollections.observableArrayList(Angleshot.getInstance().getJDA().getSelfUser().getMutualGuilds()));
        ListView<TextChannel> channelList = new ListView();
        guildList.prefHeight(170);
        channelList.prefHeight(170);

        //Make sure the listview knows what to do with the guilds, same with channels
        guildList.setCellFactory(param -> new ListCell<Guild>() {
            @Override
            protected void updateItem(Guild guild, boolean empty) {
                super.updateItem(guild, empty);
                setText((empty || guild == null || guild.getName() == null) ? null : guild.getName());
            }
        });
        channelList.setCellFactory(param -> new ListCell<TextChannel>() {
            @Override
            protected void updateItem(TextChannel channel, boolean empty) {
                super.updateItem(channel, empty);
                setText((empty || channel == null || channel.getName() == null) ? null : channel.getName());
            }
        });

        //Update the accessible and chattable channels for selected guild
        guildList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> channelList.setItems(FXCollections.observableArrayList((Collection<? extends TextChannel>) newValue.getTextChannels().stream().filter(channel -> PermissionUtil.checkPermission(channel, channel.getGuild().getSelfMember(), Permission.MESSAGE_WRITE)).collect(Collectors.toCollection(ArrayList::new)))));

        //Update our selected text channel for action
        channelList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> Angleshot.getInstance().setTextChannel(newValue));

        getContent().add(guildList, 1, 1);
        getContent().add(channelList, 2, 1);
    }

}
