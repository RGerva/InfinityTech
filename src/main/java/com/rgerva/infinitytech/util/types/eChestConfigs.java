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
    IRON(54, 9, 184, 222);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;

    eChestConfigs(int size, int rowLength, int xSize, int ySize){
        this(null, size, rowLength, xSize, ySize);
    }

    eChestConfigs(@Nullable String name, int size, int rowLength, int xSize, int ySize) {
        this.name = name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    @Override
    public @NotNull String getSerializedName() {
        assert this.name != null;
        return this.name;
    }

    public static List<Block> get(eChestConfigs type) {
        return switch (type) {
            case IRON -> Arrays.asList(ModBlocks.IRON_CHEST.get());
            default -> List.of(Blocks.CHEST);
        };
    }
}
