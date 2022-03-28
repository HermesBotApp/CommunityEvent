package fr.blueberry.studio.hermes;

import fr.blueberry.studio.hermes.api.plugins.Plugin;
import fr.blueberry.studio.hermes.listeners.discord.MessageReceivedListener;
import org.simpleyaml.configuration.file.YamlFile;
import java.awt.Color;
import fr.blueberry.studio.hermes.api.bots.BotManager;
import fr.blueberry.studio.hermes.api.utils.ColorHelper;
import fr.blueberry.studio.hermes.api.utils.MessageEmbedHelper;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;

public class CommunityEvent extends Plugin {
    public static CommunityEvent INSTANCE;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        final BotManager botManager = getHermes().getBotManager();
        final YamlFile config = getConfiguration();

        botManager.getJDAListenerManager().registerJDAListener(new MessageReceivedListener(botManager, config));
    }

    public static MessageEmbed getVictoryMessage (Member winner) {
        final Color victoryColor = ColorHelper.toRGB(INSTANCE.getConfiguration().getString("victoryColor"));
        final String prize = INSTANCE.getConfiguration().getString("prize");
        final String iconUrl = INSTANCE.getConfiguration().getString("iconUrl");

        final MessageEmbed victoryMessage = MessageEmbedHelper
            .getBuilder()
            .setTitle("Victoire !")
            .setColor(victoryColor)
            .setThumbnail(iconUrl)
            .setDescription(winner.getAsMention() + " a gagné ! Il obtient le lot: " + prize)
            .build();
        return victoryMessage;
    }
}
