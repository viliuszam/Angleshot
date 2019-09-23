package me.vilius.angleshot.utility;

import me.vilius.angleshot.Angleshot;
import net.dv8tion.jda.api.entities.SelfUser;
import net.dv8tion.jda.api.entities.User;

/*
 *   Written by yours truly - Nya (Vilius)
 *   Created 2019-09-23
 *   Inspired by Flex Seal™
 */
public class Wrapper
{
    //Direct message somebody
    public static void sendDirectMessage(User user, String content) {
        user.openPrivateChannel().queue(channel -> channel.sendMessage(content).queue());
    }
}
