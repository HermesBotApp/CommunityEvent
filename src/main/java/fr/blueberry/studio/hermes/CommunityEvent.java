package fr.blueberry.studio.hermes;

import fr.blueberry.studio.hermes.api.plugins.Plugin;
import fr.blueberry.studio.hermes.listeners.discord.MessageReceivedListener;

import org.simpleyaml.configuration.file.YamlFile;

import fr.blueberry.studio.hermes.api.bots.BotManager;

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
}
