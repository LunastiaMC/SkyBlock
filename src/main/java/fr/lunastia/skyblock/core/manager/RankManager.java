package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.session.Rank;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;

public class RankManager {

    private ConfigurationSection file;
    private HashMap<Integer, Rank> ranks;

    public RankManager() {
        final YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Core.getInstance().getDataFolder(), "ranks.yml"));
        file = config.getConfigurationSection("ranks");

        // Load all ranks
        assert file != null;
        for (String rankName : file.getKeys(false)) {
            ConfigurationSection rank = file.getConfigurationSection(rankName);
            System.out.println(rankName);
            System.out.println(rank.getString("name"));
        }
    }
}
