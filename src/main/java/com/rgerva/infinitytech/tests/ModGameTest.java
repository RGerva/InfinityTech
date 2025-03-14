package com.rgerva.infinitytech.tests;

import com.rgerva.infinitytech.InfinityTech;
import com.rgerva.infinitytech.tests.test_case.LootTableTests;
import net.minecraft.core.BlockPos;
import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.gametest.framework.TestFunction;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.gametest.GameTestHolder;

import java.util.ArrayList;
import java.util.Collection;

@GameTestHolder(InfinityTech.MOD_ID)
public class ModGameTest {

    @GameTest
    public Collection<TestFunction> registerTests(GameTestHelper holder) {
        Collection<TestFunction> testFunctions = new ArrayList<>();

        if (System.getProperty(this.getClass().getPackageName()) == null) {
            holder.assertTrue(true, "fail");
        }

        testFunctions.addAll(LootTableTests.TEST_FUNCTIONS);

        return testFunctions;
    }

    @GameTest(templateNamespace = InfinityTech.MOD_ID)
    public static void testWood(GameTestHelper helper) {
        // The woodPos is in the bottom center of the 3x3x3 structure
        BlockPos woodPos = new BlockPos(1, 1, 1);

        // assertBlockState will convert the relative woodPos into a real world block position and check it with the predicate.
        // Relative positions are made absolute by adding their value to the block position of the structure tile entity,
        // which is always the lowest northwest corner inside the structure.
        // If the predicate fails, the String supplier will be used to construct an exception message, which is thrown
        helper.assertBlockState(woodPos, b -> b.is(Blocks.OAK_LOG), () -> "Block was not an oak log");

        helper.succeed();
    }
}
