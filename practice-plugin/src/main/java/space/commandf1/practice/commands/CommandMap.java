package space.commandf1.practice.commands;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import space.commandf1.capi.command.CommandInfo;
import space.commandf1.capi.command.ICommandMap;
import space.commandf1.practice.queue.Queue;
import space.commandf1.practice.queue.QueueManager;
import space.commandf1.practice.queue.QueueType;
import space.commandf1.practice.settings.Setting;
import space.commandf1.practice.settings.Settings;

public class CommandMap implements ICommandMap {

    @CommandInfo(
            name = "practice",
            permission = "practice.commands.admin",
            needPermission = true,
            description = "cPractice Main Command",
            aliases = {"cp", "cPractice"}
    )
    public void practiceCommand(CommandSender sender, String s, String[] args) {
        // sender.sendMessage("§ecPractice §bBy commandf1");
        final String notPlayerMessage = "§cYou are not a player";
        if (args.length <= 0) {
            sender.sendMessage("§c/practice <setLobby>");
            return;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("setLobby")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(notPlayerMessage);
                return;
            }
            Player player = (Player) sender;
            Location location = player.getLocation();
            Setting.getData().setWithoutException(Settings.LOBBY.getPath(), location);
            player.sendMessage("§aSuccessfully set the lobby location.");
        } else {
            sender.sendMessage("No such method.");
        }
    }

    @CommandInfo(
            name = "debug",
            description = "debug command",
            needPermission = true,
            permission = "practice.commands.admin"
    )
    public void debugCommand(CommandSender sender, String s, String[] args) {
        final String notPlayerMessage = "§cYou are not a player";
        if (args.length <= 0) {
            sender.sendMessage("§c/debug <queue/createQueue>");
            return;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("queue")) {
            for (Queue queue : QueueManager.getQueues()) {
                sender.sendMessage(queue.toString());
            }
        } else if (args.length == 1 && args[0].equalsIgnoreCase("createQueue")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(notPlayerMessage);
                return;
            }
            Player player = (Player) sender;
            Queue queue = new Queue(player, QueueType.UNRANKED);
            QueueManager.getQueues().add(queue);
        } else {
            sender.sendMessage("No such method.");
        }
    }
}
