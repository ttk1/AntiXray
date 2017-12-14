package net.ttk1;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiXray extends JavaPlugin {
    Map<Location, Material> map = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockHider(map), this);
        getLogger().info("AntiXray enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Retrieving all hidden blocks");
        for (Location loc: map.keySet()) {
            Block block = loc.getBlock();
            block.setType(map.get(loc));
        }
        getLogger().info("AntiXray disabled");
    }

    class BlockHider implements Listener {
        Map<Location, Material> map;

        BlockHider(Map<Location, Material> map){
            this.map = map;
        }

        @EventHandler
        public synchronized void onChunkLoadEvent(ChunkLoadEvent event) {
            Chunk chunk = event.getChunk();
            // chunk内のブロックを列挙
            for (int x = 0; x <= 15; x++) {
                for (int y = 0; y <= 255; y++) {
                    for (int z = 0; z <= 15; z++) {
                        Block block = chunk.getBlock(x, y, z);
                        // 鉱石ブロックか確認
                        if (isOre(block)) {
                            // ブロックの明るさが0 == 視認不可能
                            if (!isSurface(block)) {
                                getLogger().info("Block hided");
                                Material type = block.getType();
                                Location loc = block.getLocation();
                                map.put(loc, type);
                                // blockを隠ぺい（石に偽装）
                                block.setType(Material.STONE);
                            }
                        }
                    }
                }
            }
        }

        @EventHandler
        public synchronized void onBlockPhysicsEvent(BlockPhysicsEvent event) {
            Block block = event.getBlock();
            Location loc = block.getLocation();

            if(map.containsKey(loc)) {
                getLogger().info("Block retrieved");
                block.setType(map.get(loc));
                map.remove(loc);
            }
        }

        // 隠ぺいしたいブロックの場合true, それ以外false
        private boolean isOre(Block block) {
            if (block.getType().equals(Material.DIAMOND_ORE) || block.getType().equals(Material.COAL_ORE)) {
                return true;
            }
            return false;
        }

        private boolean isSurface(Block block) {
            if (block.getRelative(BlockFace.DOWN).getType().equals(Material.AIR) ||
                    block.getRelative(BlockFace.UP).getType().equals(Material.AIR) ||
                    block.getRelative(BlockFace.WEST).getType().equals(Material.AIR) ||
                    block.getRelative(BlockFace.SOUTH).getType().equals(Material.AIR) ||
                    block.getRelative(BlockFace.EAST).getType().equals(Material.AIR) ||
                    block.getRelative(BlockFace.NORTH).getType().equals(Material.AIR)) {
                return true;
            }
            return false;
        }
    }
}
