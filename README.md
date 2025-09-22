<p align="center">
  <img src="src/main/assets/preview.png" alt="QuantaraCore banner" width="100%" />
</p>


# 🚀 QuantaraCore

**QuantaraCore** est un plugin Minecraft *core* pensé pour les serveurs professionnels.  
Il fournit un système complet de gestion des profils joueurs, des grades, des annonces, des statistiques, un scoreboard dynamique, une tablist personnalisée ainsi que des utilitaires sociaux (homes, messages privés, freeze, etc.).

---

## ✨ Fonctionnalités principales

- 🎮 **Profils joueurs** : création, suppression, vérification
- 🎖️ **Système de grades & permissions** : gestion par commande intégrée
- 📢 **Annonces en jeu** : messages globaux & modération
- 📊 **Statistiques joueur** : suivi et réinitialisation
- ⏱️ **Scoreboard dynamique** : mise à jour automatique ou manuelle
- 📋 **Tablist améliorée** : affichage trié par grade
- 💬 **Messagerie privée** : système de MP avec réponse rapide
- 🏠 **Homes multiples** : définir, supprimer, lister et se téléporter
- ❄️ **Outils de modération** : freeze / unfreeze des joueurs
- 🔄 **Mises à jour serveur** : scoreboard, tab et global refresh

---

## ⚙️ Commandes & Permissions

| Commande             | Aliases                                | Description                                                   | Permission        |
|----------------------|-----------------------------------------|---------------------------------------------------------------|-------------------|
| `/createprofile`     | `forceprofil`, `fcp`                   | Force la création d’un profil joueur                          | `admin.use`       |
| `/checkprofile`      | `checkplayer`, `check`, `lookup`       | Vérifie les informations d’un joueur                          | `moderateur.use`  |
| `/removeprofile`     | `frp`                                  | Supprime un profil joueur                                     | `admin.use`       |
| `/reloadconfig`      | `rlc`                                  | Recharge la configuration                                     | `moderateur.use`  |
| `/mod`               | `annonce`, `broadcast`                 | Envoie un message global au serveur                           | `moderateur.use`  |
| `/color`             | `showcolor`                            | Affiche toutes les couleurs disponibles                       | `vip.use`         |
| `/perm`              | `changeperm`, `permission`, `rank`…    | Change le grade d’un joueur                                   | `admin.use`       |
| `/restartserver`     | `rs`                                   | Redémarre le serveur Minecraft                                | `admin.use`       |
| `/resetstatistic`    | `statreset`                            | Réinitialise les statistiques d’un joueur                     | `admin.use`       |
| `/updatescoreboard`  | `updatesb`                             | Met à jour le scoreboard principal                           | `admin.use`       |
| `/freeze`            | -                                      | Bloque tous les mouvements d’un joueur                        | `moderateur.use`  |
| `/unfreeze`          | -                                      | Débloque tous les mouvements d’un joueur                      | `moderateur.use`  |
| `/msg`               | -                                      | Envoie un message privé                                       | `player.use`      |
| `/reply`             | -                                      | Répond au dernier message privé                               | `player.use`      |
| `/togglemp`          | -                                      | Active/désactive la réception des messages privés             | `player.use`      |
| `/sethome`           | -                                      | Définit une nouvelle home                                     | `player.use`      |
| `/home`              | -                                      | Téléporte à une home                                          | `player.use`      |
| `/delhome`           | -                                      | Supprime une home                                             | `player.use`      |
| `/listhomes`         | -                                      | Liste toutes vos homes                                        | `player.use`      |
| `/updatetab`         | -                                      | Force la mise à jour de la tablist                            | `moderateur.use`  |
| `/updateglobal`      | -                                      | Met à jour toutes les données globales du serveur             | `moderateur.use`  |

---

## 🔑 Permissions

- `admin.use` → accès complet (hérite de tous les rôles)  
- `moderateur.use` → outils de modération + joueur & VIP  
- `helper.use` → assistance & support joueur  
- `vip.use` → avantages esthétiques et utilitaires  
- `player.use` → commandes de base accessibles à tous  

---

## 🚀 Installation

1. Télécharge le fichier `.jar` depuis [Releases](https://github.com/Tablelkea/QuantaraCore/releases).
2. Place-le dans le dossier `plugins` de ton serveur.
3. Redémarre le serveur.
4. Configure le plugin via `config.yml`.

---

## 🛠️ Utilisation

- Les profils se créent automatiquement à la première connexion.  
- Le scoreboard & la tablist se mettent à jour toutes les **5 minutes**.  
- Tu peux forcer les mises à jour avec :  
  - `/updatescoreboard`  
  - `/updatetab`  
  - `/updateglobal`  

---

## 📋 Prérequis

- **Paper 1.21.x** (ou version compatible)  
- **Java 17+**

---

## 🤝 Contribuer

Tu peux :  
- Créer une *issue* pour signaler un bug ou proposer une idée 💡  
- Ouvrir une *pull request* pour améliorer le code ✨  

---

## 📄 Licence

Ce projet est sous licence **MIT**.

---

*Développé par [Tablelkea](https://github.com/Tablelkea) & [Lorenzo](https://github.com/Lorenzo605)*

