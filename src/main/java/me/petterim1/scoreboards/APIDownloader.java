package me.petterim1.scoreboards;

import cn.nukkit.Server;
import cn.nukkit.plugin.Plugin;

import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;

public class APIDownloader {

    static void checkAndRun(Plugin plugin) {
        Server server = plugin.getServer();

        if (server.getPluginManager().getPlugin("ScoreboardPlugin") != null) return;

        plugin.getLogger().info("Downloading ScoreboardAPI...");

        String scoreboardApi = server.getFilePath() + "/plugins/ScoreboardAPI.jar";

        try {
            FileOutputStream fos = new FileOutputStream(scoreboardApi);
            fos.getChannel().transferFrom(Channels.newChannel(new URL("https://sites.google.com/site/downloadcenterpetterim1/ScoreboardAPI.jar").openStream()), 0, Long.MAX_VALUE);
            fos.close();
        } catch (Exception e) {
            server.getLogger().logException(e);
            server.getPluginManager().disablePlugin(plugin);
            return;
        }

        plugin.getLogger().info("ScoreboardAPI downloaded successfully");
        server.getPluginManager().loadPlugin(scoreboardApi);

        if (server.getPluginManager().getPlugin("PlaceholderAPI") != null) return;

        plugin.getLogger().info("Downloading PlaceholderAPI...");

        String placeholderApi = server.getFilePath() + "/plugins/PlaceholderAPI.jar";

        try {
            FileOutputStream fos = new FileOutputStream(placeholderApi);
            fos.getChannel().transferFrom(Channels.newChannel(new URL("https://sites.google.com/site/downloadcenterpetterim1/PlaceholderAPI.jar").openStream()), 0, Long.MAX_VALUE);
            fos.close();
        } catch (Exception e) {
            server.getLogger().logException(e);
            server.getPluginManager().disablePlugin(plugin);
            return;
        }

        plugin.getLogger().info("PlaceholderAPI downloaded successfully");
        server.getPluginManager().loadPlugin(placeholderApi);
    }
}