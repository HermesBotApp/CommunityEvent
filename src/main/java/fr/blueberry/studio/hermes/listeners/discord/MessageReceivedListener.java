package fr.blueberry.studio.hermes.listeners.discord;

import org.simpleyaml.configuration.file.YamlFile;
import java.awt.Color;
import fr.blueberry.studio.hermes.api.bots.BotManager;
import fr.blueberry.studio.hermes.api.utils.ColorHelper;
import fr.blueberry.studio.hermes.api.utils.MessageEmbedHelper;
import fr.blueberry.studio.hermes.api.bots.Bot;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class MessageReceivedListener extends ListenerAdapter {
    private final BotManager botManager;
    private final YamlFile config;

    public MessageReceivedListener(BotManager botManager, YamlFile config) {
        this.botManager = botManager;
        this.config = config;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        final Bot bot = this.botManager.getBot(config.getString("botName"));

        if (bot == null) return;

        final TextChannel eventTextChannel = event.getGuild().getTextChannelById(config.getLong("eventChannel"));
        final Color color = ColorHelper.toRGB(config.getString("color"));

        if (eventTextChannel != event.getTextChannel()) return;
        if (event.getAuthor().isBot()) return;
        if (event.getMessage().getContentRaw().contains("http")) return;
        if (event.getMessage().getAttachments().size() > 0) return;

        final MessageEmbed embed = MessageEmbedHelper.getBuilder()
            .setTitle("La blague de " + event.getMember().getEffectiveName())
            .setDescription(event.getMessage().getContentRaw())
            .setColor(color)
            .setThumbnail(event.getMember().getUser().getAvatarUrl())
            .build();

        event.getChannel().sendMessage(embed).queue(message -> {
            event.getMessage().delete().queue();
        });
    }
}
