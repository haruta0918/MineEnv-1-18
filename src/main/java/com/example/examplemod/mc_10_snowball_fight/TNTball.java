package com.example.examplemod.mc_10_snowball_fight;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class TNTball extends Snowball {


    //Entityに当たったダメージ量
    private static final float DAMAGE = 10000f;

    public TNTball(EntityType<? extends Snowball> entityTypeIn, Level level) {
        super(entityTypeIn, level);
    }

    public TNTball(Level level, LivingEntity throwerIn) {
        super(level, throwerIn);
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        System.err.println("EntityMySnowball.onHitEntity: " + pResult.getType());
        super.onHitEntity(pResult);
    }

    @Override
    protected void onHit(HitResult result) {
        super.onHit(result);
        System.err.println("EntityMySnowball.onHit: " + result.getType());
        if (result.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) result;
            switch (blockHitResult.getDirection()) {
                case EAST, WEST, NORTH, SOUTH,UP,DOWN -> {
                    Block block = level.getBlockState(blockHitResult.getBlockPos()).getBlock();
                    if (block == ExampleMod.BLOCK_FOOTPRINTS_SAND) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                        if(!level.isClientSide){
                            float explosionRadius = 5f;
                            level.explode(null, blockHitResult.getBlockPos().getX(), blockHitResult.getBlockPos().getY(), blockHitResult.getBlockPos().getZ(), explosionRadius, Explosion.BlockInteraction.BREAK);
                        }

                    }

                }
            }
        }
    }
}

