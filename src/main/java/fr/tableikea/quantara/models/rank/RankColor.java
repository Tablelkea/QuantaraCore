package fr.tableikea.quantara.models.rank;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

public class RankColor {

    public static Component getChatRankColor(String rank, String string){
        switch (rank){
            case "ADMIN" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#c90000>Administrateur</color><color:#262626>]</color> <color:#c90000><player></color>", Placeholder.component("player", Component.text(string)));
            }
            case "MODERATEUR" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#29912c>Moderateur</color><color:#262626>] </color><color:#29912c><player></color>", Placeholder.component("player", Component.text(string)));
            }
            case "HELPER" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#3b7fff>Helper</color><color:#262626>] </color><color:#3b7fff><player></color>", Placeholder.component("player", Component.text(string)));
            }
            case "VIP" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#bf6bc7>VIP</color><color:#262626>] </color><color:#bf6bc7><player></color>", Placeholder.component("player", Component.text(string)));
            }
            case "JOUEUR" -> {
            return MiniMessage.miniMessage().deserialize("<color:#262626>[</color>Joueur<color:#262626>] </color><player>", Placeholder.component("player", Component.text(string)));
        }
            case null, default ->  {
                return MiniMessage.miniMessage().deserialize("<color:#4f1a1a><b><i>Erreur</i></b></color>");

            }
        }
    }

    public static Component getDefaultRankColor(String rank){
        switch (rank){
            case "ADMIN" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#a80000>Administrateur</color><color:#262626>]</color>");
            }
            case "MODERATEUR" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#29912c>Moderateur</color><color:#262626>]");

            }
            case "HELPER" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#3b7fff>Helper</color><color:#262626>] </color>");
            }
            case "VIP" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color><color:#bf6bc7>VIP</color><color:#262626>] </color>");
            }
            case "JOUEUR" -> {
                return MiniMessage.miniMessage().deserialize("<color:#262626>[</color>Joueur<color:#262626>] </color>");
            }
            case null, default ->  {
                return MiniMessage.miniMessage().deserialize("<color:#4f1a1a><b><i>Erreur</i></b></color>");
            }
        }
    }

}
