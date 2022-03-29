package fr.blueberry.studio.hermes;

import fr.blueberry.studio.hermes.api.plugins.Plugin;
import fr.blueberry.studio.hermes.listeners.discord.MessageReceivedListener;
import org.simpleyaml.configuration.file.YamlFile;
import fr.blueberry.studio.hermes.api.bots.BotManager;
import fr.blueberry.studio.hermes.api.utils.MessageEmbedHelper;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import java.util.HashMap;
import java.util.Map;

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
        final Map<String, String> replacements =  new HashMap<String, String>();

        replacements.put("%user%", winner.getAsMention());
        replacements.put("%reward%", INSTANCE.getConfiguration().getString("prize"));

        MessageEmbed victoryMessageEmbed = MessageEmbedHelper.craftEmbedFromConfig("victoryMessage", INSTANCE.getConfiguration(), replacements);
        return victoryMessageEmbed;
    }
}
