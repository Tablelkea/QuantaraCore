package fr.tableikea.quantara.models.rank;

public class RankColor {

    public static String getRankColor(String rank){
        switch (rank){
            case "ADMIN" -> {
                return "§8[§4Admin§8] §4";
            }
            case "MODERATEUR" -> {
                return "§8[§aModerateur§8] §a";
            }
            case "HELPER" -> {
                return "§8[§3Helper§8] §3";
            }
            case "VIP" -> {
                return "§8[§6VIP§8] §6";
            }
            case "JOUEUR" -> {
                return "§8[§7Joueur§8] §7";
            }
            case null, default ->  {
                return "§cErreur";
            }
        }
    }

}
