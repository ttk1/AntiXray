package net.ttk1;

import org.bukkit.Chunk;
import org.bukkit.ChunkSnapshot;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class MyChunkSnapshot implements ChunkSnapshot {
    private Chunk chunk;
    private ChunkSnapshot chunkSnapshot;

    public MyChunkSnapshot(Chunk chunk, ChunkSnapshot chunkSnapshot) {
        this.chunk = chunk;
        this.chunkSnapshot = chunkSnapshot;
    }

    public Biome getBiome(int x, int z) {
        return chunkSnapshot.getBiome(x, z);
    }

    @SuppressWarnings("deprecation")
    public int getBlockData(int x, int y, int z) {
        return chunkSnapshot.getBlockData(x, y, z);
    }

    public int getBlockEmittedLight(int x, int y, int z) {
        return chunkSnapshot.getBlockEmittedLight(x, y, z);
    }

    public int getBlockSkyLight(int x, int y, int z) {
        return chunkSnapshot.getBlockSkyLight(x, y, z);
    }

    public Material getBlockType(int x, int y, int z) {
        Block block = chunk.getBlock(x, y, z);
        return block.getType();
    }

    @SuppressWarnings("deprecation")
    public int getBlockTypeId(int x, int y, int z) {
        Block block = chunk.getBlock(x, y, z);
        return block.getTypeId();
    }

    public long getCaptureFullTime() {
        return chunkSnapshot.getCaptureFullTime();
    }

    public int getHighestBlockYAt(int x, int z) {
        return chunkSnapshot.getHighestBlockYAt(x, z);
    }

    @SuppressWarnings("deprecation")
    public double getRawBiomeRainfall(int x, int z) {
        return chunkSnapshot.getRawBiomeRainfall(x, z);
    }

    public double getRawBiomeTemperature(int x, int z) {
        return chunkSnapshot.getRawBiomeTemperature(x, z);
    }

    public String getWorldName() {
        return chunkSnapshot.getWorldName();
    }

    public int getX() {
        return chunkSnapshot.getX();
    }

    public int getZ() {
        return chunkSnapshot.getZ();
    }

    public boolean isSectionEmpty(int sy) {
        return chunkSnapshot.isSectionEmpty(sy);
    }

}
