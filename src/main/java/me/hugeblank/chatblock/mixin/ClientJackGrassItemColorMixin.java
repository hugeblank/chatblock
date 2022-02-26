package me.hugeblank.chatblock.mixin;

import me.hugeblank.chatblock.Init;
import net.minecraft.block.BlockState;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.item.BlockItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(ItemColors.class)
public class ClientJackGrassItemColorMixin {

        @Inject(at = @At("RETURN"), method = "create(Lnet/minecraft/client/color/block/BlockColors;)Lnet/minecraft/client/color/item/ItemColors;", locals = LocalCapture.CAPTURE_FAILHARD)
        private static void addJackgrass(BlockColors blockColors, CallbackInfoReturnable<ItemColors> cir, ItemColors itemColors) {
            itemColors.register((stack, tintIndex) -> {
                BlockState blockState = ((BlockItem)stack.getItem()).getBlock().getDefaultState();
                return blockColors.getColor(blockState, null, null, tintIndex);
            }, Init.JACKGRASS);
        }
}
