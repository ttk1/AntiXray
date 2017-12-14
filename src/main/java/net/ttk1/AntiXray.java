package net.ttk1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Protocol;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.ChunkPosition;

public class AntiXray extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockHider(), this);
        getLogger().info("AntiXray enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("AntiXray disabled");
    }

    class BlockHider implements Listener {
        Map<String, List<MyPosition>> map = new HashMap<>();
        ProtocolManager  protocolManager = ProtocolLibrary.getProtocolManager();

        @EventHandler
        public void onChunkLoadEvent(PacketEvent event) {
            if (event.getPacketType() == PacketType.Play.Server.MAP_CHUNK) {
                ChunkPosition cp = event.getPacket().getPositionModifier().readSafely(0);
                Player player = event.getPlayer();
                World currentWorld = player.getWorld();
                MyPosition mp = new MyPosition(cp.getX(), cp.getY(), cp.getZ(), currentWorld.getName());

                // すでに送信済みのパケットは再送しない
                String uuid = player.getUniqueId().toString();
                if (map.containsKey(uuid)) {
                    List<MyPosition> positions = map.get(uuid);
                    if (positions.contains(mp)) {
                        // listからmpを削除し送信済みとして扱う
                        positions.remove(mp);
                        // eventのキャンセルはせずそのままreturn
                        return;
                    } else {
                        // 未送信としてmpをlistに追加
                        positions.add(mp);
                    }
                } else {
                    List<MyPosition> positions = new ArrayList<>();
                    map.put(uuid, positions);
                }

                // ラッパークラスのMyChunkのインスタンスを生成しプレーヤーに送信
                event.setCancelled(true);
                PacketContainer sendChunk = protocolManager.createPacket(PacketType.Play.Server.MAP_CHUNK);

                //Chunk chunk = currentWorld.getChunkAt(cp.getX(), cp.getZ());
                //event.getPlayer().getUniqueId().toString();
            }
/*            Chunk chunk = event.getChunk();
            if (!(chunk instanceof MyChunk)) {
                chunk.unload();
                MyChunk myChunk = new MyChunk(event.getChunk(), getLogger());
                //ChunkLoadEvent event2 = new ChunkLoadEvent(myChunk, event.isNewChunk());
                //Bukkit.getPluginManager().callEvent(event2);
                getLogger().info("AntiXray :chunk loaded");
            } else {
                chunk.unload();
            }*/
        }
    }
}
