package me.hugeblank.chatblock.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.GrassBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.SilverfishEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;

public class JackGrass extends GrassBlock {

    public JackGrass(Settings settings) {
        super(settings);
    }

    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        super.onSteppedOn(world, pos, state, entity);
        if (entity.isPlayer() && !world.isClient) {
            ServerPlayerEntity player = (ServerPlayerEntity) entity;
            world.breakBlock(pos, true, entity);
            SilverfishEntity silverfish = Objects.requireNonNull(EntityType.SILVERFISH.create(world));
            world.spawnEntity(silverfish);
            silverfish.setPersistent();
            silverfish.setPosition(Vec3d.of(pos).add(0.5f, 0.0f, 0.5f));
            player.networkHandler.disconnect(new LiteralText("Touched jackgrass block at position [" + pos.getX() + " " + pos.getY() + " " + pos.getZ() + "], nerd!"));
        }
    }
}
