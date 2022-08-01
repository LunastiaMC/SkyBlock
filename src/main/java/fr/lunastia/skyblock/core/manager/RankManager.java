package fr.lunastia.skyblock.core.manager;

import fr.lunastia.skyblock.core.Core;
import fr.lunastia.skyblock.core.session.Rank;
import fr.lunastia.skyblock.core.utils.ColorUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class RankManager {

    private ConfigurationSection file;
    private HashMap<Integer, Rank> ranks;

    public RankManager() {
        ranks = new HashMap<>();

        final YamlConfiguration config = YamlConfiguration.loadConfiguration(new File(Core.getInstance().getDataFolder(), "ranks.yml"));
        file = config.getConfigurationSection("ranks");

        assert file != null;
        for (String rankId : file.getKeys(false)) {
            ConfigurationSection rank = file.getConfigurationSection(rankId);
            assert rank != null;

            ArrayList<String> permissions = new ArrayList<>(rank.getStringList("permissions"));
            ranks.put(Integer.parseInt(rankId), new Rank(Integer.parseInt(rankId), rank.getString("name"), ColorUtil.colorize(rank.getString("coloredName")), ColorUtil.colorize(rank.getString("nametagName")), ColorUtil.colorize(rank.getString("arrow")), permissions));
        }
    }

    public Rank getRank(int id) {
        return ranks.getOrDefault(id, getDefaultRank());
    }

    public Rank getDefaultRank() {
        return ranks.get(0);
    }
}
