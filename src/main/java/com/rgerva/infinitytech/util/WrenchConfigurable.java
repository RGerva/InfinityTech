/**
 * Class: WrenchConfigurable
 * Created by: D56V1OK
 * On: 2025/abr.
 * GitHub: https://github.com/RGerva
 * Copyright (c) 2025 @RGerva. All Rights Reserved.
 */

package com.rgerva.infinitytech.util;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import org.jetbrains.annotations.NotNull;

public interface WrenchConfigurable {
    @NotNull InteractionResult onUseWrench(UseOnContext context, Direction selectedFace, boolean nextPreviousValue);
}
