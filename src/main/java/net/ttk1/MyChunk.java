package net.ttk1;

import java.util.logging.Logger;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Entity;

public class MyChunk implements Chunk {
    private Chunk chunk;
    private Logger logger;

    public MyChunk(Chunk chunk, Logger logger) {
        this.chunk = chunk;
        this.logger = logger;
    }

    // 周りがすべてブロックに覆われている場合鉱石を隠ぺいする
    public Block getBlock(int x, int y, int z) {
        logger.info("AntiXray :getBlock");
        Block block = chunk.getBlock(x, y, z);
        if (isOre(block)) {
            if (isSurface(block)) {
                return block;
            } else {
                return new MyBlock(block, this);
            }
        } else {
            return block;
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
        if (block.getLightFromBlocks() == 0 && block.getLightFromSky() == 0) {
            return true;
        }
        return false;
    }

    public ChunkSnapshot getChunkSnapshot() {
        ChunkSnapshot chunkSnapshot = chunk.getChunkSnapshot();
        return new MyChunkSnapshot(this, chunkSnapshot);
    }

    public ChunkSnapshot getChunkSnapshot(boolean includeMaxblocky, boolean includeBiome, boolean includeBiomeTempRain) {
        ChunkSnapshot chunkSnapshot = chunk.getChunkSnapshot(includeMaxblocky, includeBiome, includeBiomeTempRain);
        return new MyChunkSnapshot(this, chunkSnapshot);
    }

    public Entity[] getEntities() {
        return chunk.getEntities();
    }

    public BlockState[] getTileEntities() {
        return chunk.getTileEntities();
    }

    public World getWorld() {
        return chunk.getWorld();
    }

    public int getX() {
        return chunk.getX();
    }

    public int getZ() {
        return chunk.getZ();
    }

    public boolean isLoaded() {
        return chunk.isLoaded();
    }

    public boolean isSlimeChunk() {
        return chunk.isSlimeChunk();
    }

    public boolean load() {
        return chunk.load();
    }

    public boolean load(boolean generate) {
        return chunk.load(generate);
    }

    public boolean unload() {
        return chunk.unload();
    }

    public boolean unload(boolean save) {
        return chunk.unload(save);
    }

    @SuppressWarnings("deprecation")
    public boolean unload(boolean save, boolean safe) {
        return chunk.unload(save, safe);
    }


}
