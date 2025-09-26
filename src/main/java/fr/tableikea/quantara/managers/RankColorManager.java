package fr.tableikea.quantara.managers;

import fr.tableikea.quantara.Main;
import fr.tableikea.quantara.models.Rank;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.UUID;

public class RankColorManager {

    static String adminColor = "c90000";
    static String moderateurColor = "29912c";
    static String helperColor = "3b7fff";
    static String vipColor = "bf6bc7";
    static String errorColor = "4f1a1a";

    public static Component getPlayerPrefixInChat(UUID uuid){
        FileConfiguration config = Main.getInstance().getConfig();
        String path = "qprofils."+uuid+".";

        if(config.get(path+"permission") != null){
            Rank playerRank = Rank.valueOf(config.getString(path+"permission"));
            String player = config.getString(path+"player");

            switch (playerRank){
                case ADMIN -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+adminColor+">Administrateur</color><color:#262626>]</color> <color:#"+adminColor+"><player></color> <color:#616161>»</color> ", Placeholder.component("player", Component.text(player)));}
                case MODERATEUR -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+moderateurColor+">Moderateur</color><color:#262626>] </color><color:#"+moderateurColor+"><player></color> <color:#616161>»</color> ", Placeholder.component("player", Component.text(player)));}
                case HELPER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+helperColor+">Helper</color><color:#262626>] </color><color:#"+helperColor+"><player></color> <color:#616161>»</color> ", Placeholder.component("player", Component.text(player)));}
                case VIP -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+vipColor+">VIP</color><color:#262626>] </color><color:#"+vipColor+"><player></color> <color:#616161>»</color> ", Placeholder.component("player", Component.text(player)));}
                case PLAYER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color>Joueur<color:#262626>] </color><player> <color:#616161>»</color> ", Placeholder.component("player", Component.text(player)));}
                case null, default -> {return MiniMessage.miniMessage().deserialize("<color:#"+errorColor+"><b><i>Erreur</i></b></color>");}
            }
        }
        return null;
    }

    public static Component getPlayerPrefixInTab(UUID uuid){
        FileConfiguration config = Main.getInstance().getConfig();
        String path = "qprofils."+uuid+".";

        if(config.get(path+"permission") != null){
            Rank playerRank = Rank.valueOf(config.getString(path+"permission"));
            String player = config.getString(path+"player");

            switch (playerRank){
                case ADMIN -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+adminColor+">Admin</color><color:#262626>]</color> <color:#"+adminColor+"><player></color>", Placeholder.component("player", Component.text(player)));}
                case MODERATEUR -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+moderateurColor+">Modo</color><color:#262626>] </color><color:#"+moderateurColor+"><player></color>", Placeholder.component("player", Component.text(player)));}
                case HELPER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+helperColor+">Helper</color><color:#262626>] </color><color:#"+helperColor+"><player></color>", Placeholder.component("player", Component.text(player)));}
                case VIP -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+vipColor+">VIP</color><color:#262626>] </color><color:#"+vipColor+"><player></color>", Placeholder.component("player", Component.text(player)));}
                case PLAYER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color>Joueur<color:#262626>] </color><player>", Placeholder.component("player", Component.text(player)));}
                default -> {return MiniMessage.miniMessage().deserialize("<color:#"+errorColor+"><b><i>Erreur</i></b></color>");}
            }
        }else{

        }
        return MiniMessage.miniMessage().deserialize("<color:#"+errorColor+"><b><i>Erreur</i></b></color>");
    }

    public static Component getPlayerPrefixInScoreboard(UUID uuid){
        FileConfiguration config = Main.getInstance().getConfig();
        String path = "qprofils."+uuid+".";

        if(config.get(path+"permission") != null){
            Rank playerRank = Rank.valueOf(config.getString(path+"permission"));
            String player = config.getString(path+"player");

            switch (playerRank){
                case ADMIN -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+adminColor+">Administrateur</color><color:#262626>]</color>");}
                case MODERATEUR -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+moderateurColor+">Moderateur</color><color:#262626>]</color>");}
                case HELPER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+helperColor+">Helper</color><color:#262626>]</color>");}
                case VIP -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#"+vipColor+">VIP</color><color:#262626>] </color>");}
                case PLAYER -> {return MiniMessage.miniMessage().deserialize("<color:#262626>[</color>Joueur<color:#262626>]</color>");}
                case null, default -> {return MiniMessage.miniMessage().deserialize("<color:#"+errorColor+"><b><i>Erreur</i></b></color>");}
            }
        }
        return null;
    }

}
