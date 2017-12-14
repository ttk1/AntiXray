package net.ttk1;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiXray extends JavaPlugin {
    Map<MyPosition, Material> map = new HashMap<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BlockHider(map), this);
        getLogger().info("AntiXray enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Retrieving all hidden blocks");
        for (MyPosition p: map.keySet()) {
            World world = getServer().getWorld(p.getWorldName());
            Block block = world.getBlockAt(p.getX(), p.getY(), p.getZ());
            block.setType(map.get(p));
        }
        getLogger().info("AntiXray disabled");
    }

    class BlockHider implements Listener {
        Map<MyPosition, Material> map;

        BlockHider(Map<MyPosition, Material> map){
            this.map = map;
        }

        @EventHandler
        public void onChunkLoadEvent(ChunkLoadEvent event) {
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
                                MyPosition p = new MyPosition(x, y, z, chunk.getWorld().toString());
                                map.put(p, type);
                                // blockを隠ぺい（石に偽装）
                                block.setType(Material.STONE);
                            }
                        }
                    }
                }
            }
        }

        @EventHandler
        public void onBlockPhysicsEvent(BlockPhysicsEvent event) {
            Block block = event.getBlock();

            int x = block.getX();
            int y = block.getY();
            int z = block.getZ();
            String worldname = block.getWorld().toString();
            MyPosition p = new MyPosition(x, y, z, worldname);

            if(map.containsKey(p)) {
                getLogger().info("Block retrieved");
                block.setType(map.get(p));
                map.remove(p);
            }
        }

        // 隠ぺいしたいブロックの場合true, それ以外false
        private boolean isOre(Block block) {
            if (block.getType().equals(Material.DIAMOND_ORE) || block.getType().equals(Material.COAL_ORE)) {
                return true;
            }
            return false;
        }

        // blockの周囲の明るさが0でなければtrue
        private boolean isSurface(Block block) {
            if (block.getLightFromBlocks() == 0x00 && block.getLightFromSky() == 0x00) {
                return true;
            }
            return false;
        }
    }
}
