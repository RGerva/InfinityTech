package com.rgerva.infinitytech.util.types;

import com.rgerva.infinitytech.block.ModBlocks;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

public enum eChestConfigs implements StringRepresentable {
    IRON(54, 9, 184, 222,256, 256),
    COPPER(45, 9, 184, 204,256, 256),
    GOLD(81, 9, 184, 276, 256, 276),
    DIAMOND(108, 12, 238, 276, 256, 276),
    OBSIDIAN(108, 12, 238, 276,256, 276),
    NETHERITE(108, 12, 238, 276, 256, 276);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final int textureXSize;
    public final int textureYSize;

    eChestConfigs(int size, int rowLength, int xSize, int ySize, int textureXSize, int textureYSize){
        this(null, size, rowLength, xSize, ySize, textureXSize, textureYSize);
    }

    eChestConfigs(@Nullable String name, int size, int rowLength, int xSize, int ySize, int textureXSize, int textureYSize) {
        this.name = name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    @Override
    public @NotNull String getSerializedName() {
        assert this.name != null;
        return this.name;
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public static List<Block> get(eChestConfigs type) {
        return switch (type) {
            case IRON -> Arrays.asList(ModBlocks.CHEST_IRON.get());
            case COPPER -> Arrays.asList(ModBlocks.CHEST_COPPER.get());
            default -> List.of(Blocks.CHEST);
        };
    }

    public String getEnumName() {
        return this.name();
    }
}
