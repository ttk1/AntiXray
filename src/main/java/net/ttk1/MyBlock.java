package net.ttk1;

import java.util.Collection;
import java.util.List;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.PistonMoveReaction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class MyBlock implements Block {
    private Block block;
    private Chunk chunk;

    public MyBlock(Block block, Chunk chunk) {
        this.block = block;
        this.chunk = chunk;
    }

    public List<MetadataValue> getMetadata(String metadataKey) {
        return block.getMetadata(metadataKey);
    }

    public boolean hasMetadata(String metadataKey) {
        return block.hasMetadata(metadataKey);
    }

    public void removeMetadata(String metadataKey, Plugin owningPlugin) {
        block.removeMetadata(metadataKey, owningPlugin);
    }

    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
        block.setMetadata(metadataKey, newMetadataValue);
    }

    public boolean breakNaturally() {
        return block.breakNaturally();
    }

    public boolean breakNaturally(ItemStack tool) {
        return block.breakNaturally(tool);
    }

    public Biome getBiome() {
        return block.getBiome();
    }

    public int getBlockPower() {
        return block.getBlockPower();
    }

    public int getBlockPower(BlockFace face) {
        return block.getBlockPower(face);
    }

    public Chunk getChunk() {
        //return block.getChunk();
        return chunk;
    }

    @SuppressWarnings("deprecation")
    public byte getData() {
        return block.getData();
    }

    public Collection<ItemStack> getDrops() {
        return block.getDrops();
    }

    public Collection<ItemStack> getDrops(ItemStack tool) {
        return block.getDrops(tool);
    }

    public BlockFace getFace(Block block) {
        return block.getFace(block);
    }

    public double getHumidity() {
        return block.getHumidity();
    }

    public byte getLightFromBlocks() {
        return block.getLightFromBlocks();
    }

    public byte getLightFromSky() {
        return block.getLightFromSky();
    }

    public byte getLightLevel() {
        return block.getLightLevel();
    }

    public Location getLocation() {
        return block.getLocation();
    }

    public Location getLocation(Location loc) {
        return block.getLocation(loc);
    }

    public PistonMoveReaction getPistonMoveReaction() {
        return block.getPistonMoveReaction();
    }

    public Block getRelative(BlockFace face) {
        return block.getRelative(face);
    }

    public Block getRelative(BlockFace face, int distance) {
        return block.getRelative(face, distance);
    }

    public Block getRelative(int modX, int modY, int modZ) {
        return block.getRelative(modX, modY, modZ);
    }

    public BlockState getState() {
        return block.getState();
    }

    public double getTemperature() {
        return block.getTemperature();
    }

    // とりあえず石ブロックに置換する
    public Material getType() {
        return Material.STONE;
    }

    // とりあえず石ブロックに置換する
    public int getTypeId() {
        return 1;
    }

    public World getWorld() {
        return block.getWorld();
    }

    public int getX() {
        return block.getX();
    }

    public int getY() {
        return block.getY();
    }

    public int getZ() {
        return block.getZ();
    }

    public boolean isBlockFaceIndirectlyPowered(BlockFace face) {
        return block.isBlockFaceIndirectlyPowered(face);
    }

    public boolean isBlockFacePowered(BlockFace face) {
        return block.isBlockFacePowered(face);
    }

    public boolean isBlockIndirectlyPowered() {
        return block.isBlockIndirectlyPowered();
    }

    public boolean isBlockPowered() {
        return block.isBlockPowered();
    }

    public boolean isEmpty() {
        return block.isEmpty();
    }

    public boolean isLiquid() {
        return block.isLiquid();
    }

    public void setBiome(Biome bio) {
        block.setBiome(bio);
    }

    @SuppressWarnings("deprecation")
    public void setData(byte data) {
        block.setData(data);
    }

    @SuppressWarnings("deprecation")
    public void setData(byte data, boolean applyPhysics) {
        block.setData(data, applyPhysics);
    }

    public void setType(Material type) {
        block.setType(type);
    }

    public void setType(Material type, boolean applyPhysics) {
        block.setType(type, applyPhysics);
    }

    @SuppressWarnings("deprecation")
    public boolean setTypeId(int type) {
        return block.setTypeId(type);
    }

    @SuppressWarnings("deprecation")
    public boolean setTypeId(int type, boolean applyPhysics) {
        return block.setTypeId(type, applyPhysics);
    }

    @SuppressWarnings("deprecation")
    public boolean setTypeIdAndData(int type, byte data, boolean applyPhysics) {
        return block.setTypeIdAndData(type, data, applyPhysics);
    }

}
