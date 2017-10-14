package net.ttk1;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class InfiniteLava extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new LavaFlow(), this);
        getLogger().info("InfiniteLava enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("InfiniteLava disabled");
    }

    class LavaFlow implements Listener {
        @EventHandler
        public void onBlockFromTo(BlockFromToEvent event) {
            Block block = event.getToBlock();
            if(count(block) >= 2) {
                event.setCancelled(true);
                replace(block);
            }
        }
        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) {
            Block placed = event.getBlockPlaced();
            if (isSource(placed)) {
                for(Block adj: getAround(placed)) {
                    if (count(adj) >= 2) {
                        replace(adj);
                    }
                }
            }
        }

        private List<Block> getAround(Block block) {
            List<Block> blocks = new ArrayList<Block>();
            blocks.add(block.getRelative(BlockFace.EAST));
            blocks.add(block.getRelative(BlockFace.WEST));
            blocks.add(block.getRelative(BlockFace.NORTH));
            blocks.add(block.getRelative(BlockFace.SOUTH));
            return blocks;
        }

        private void replace(Block block) {
            block.setType(Material.LAVA);
            block.setData((byte)0x0,true);
        }

        private int count(Block block) {
            if (block.getType() == Material.LAVA) {
                int counter = 0;
                for (Block adj: getAround(block)) {
                    if (isSource(adj)) {
                        counter++;
                    }
                }
                return counter;
            }
            return 0;
        }

        private boolean isSource(Block block) {
            if (block.getType() == Material.LAVA || block.getType() == Material.STATIONARY_LAVA) {
                if (block.getData() == (byte) 0x0) {
                    return true;
                }
            }
            return false;
        }
    }
}
