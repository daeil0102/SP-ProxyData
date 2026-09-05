package net.teujaem.spProxyData.broadcast.command;

import net.teujaem.spProxyData.util.TextColorFormatter;
import net.teujaem.spProxyData.broadcast.api.Broadcast;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class BroadcastCmd implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(TextColorFormatter.toColored("&c[!]&f 플레이어만 실행할 수 있는 명령어입니다."));
            return true;
        }

        if (!player.hasPermission("spporxydata.broadcast")) {
            player.sendMessage(TextColorFormatter.toColored("&c[!]&f 당신은 권한이 없으므로 해당 명령어를 실행하실 수 없습니다."));
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(TextColorFormatter.toColored("&c[!]&f 사용법: /broadcast <message>"));
            return true;
        }

        Broadcast.toServer(String.join(" ", args));

        return true;
    }
}
