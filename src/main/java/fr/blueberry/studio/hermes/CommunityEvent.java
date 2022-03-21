package fr.blueberry.studio.hermes;

import fr.blueberry.studio.hermes.api.plugins.Plugin;

public class CommunityEvent extends Plugin {
    public static CommunityEvent INSTANCE;

    @Override
    public void onLoad() {
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
    }
}
