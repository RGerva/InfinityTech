package com.rgerva.infinitytech.tests.test_case;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.block.ModBlocks;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;

import java.util.ArrayList;
import java.util.Collection;

public class LootTableTests {
    private static final String BATCH_ID = String.format("%s:LootTableBatch", InfinityTech.MOD_ID);

    public static final Collection<TestFunction> TEST_FUNCTIONS = new ArrayList<>() {
        {
            add(LootTableTests.createTest("test", ModBlocks.CHEST_COPPER.get(), Items.WOODEN_AXE, true));
        }
    };

    private static TestFunction createTest(String name, Block chestBlock, Item tool, boolean shouldDrop) {
        String testName = String.format("%s %s %s",
                        InfinityTech.MOD_ID,
                        LootTableTests.class.getSimpleName(),
                        name)
                .replace(" ", "_");

        return new TestFunction(
                LootTableTests.BATCH_ID,
                testName,
                null,
                Rotation.CLOCKWISE_90,
                1000,
                0L,
                true,
                false,
                1,
                1,
                false,
                (context) -> {
                    context.succeed();
                });
    }

}
