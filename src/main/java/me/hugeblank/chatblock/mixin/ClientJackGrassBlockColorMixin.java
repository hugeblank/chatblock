package me.hugeblank.chatblock.mixin;

import me.hugeblank.chatblock.Init;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.world.BiomeColors;
import net.minecraft.client.color.world.GrassColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockColors.class)
public class ClientJackGrassBlockColorMixin {
    @Inject(at = @At("RETURN"), method = "create()Lnet/minecraft/client/color/block/BlockColors;", locals = LocalCapture.CAPTURE_FAILHARD)
    private static void addJackgrass(CallbackInfoReturnable<BlockColors> cir, BlockColors blockColors) {
        blockColors.registerColorProvider(
                (state, world, pos, tintIndex) ->
                        world != null && pos != null ?
                                BiomeColors.getGrassColor(world, pos) :
                                GrassColors.getColor(0.5D, 1.0D)
                , Init.JACKGRASS);
    }
}
