package com.example.examplemod.mc_10_snowball_fight;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class EntityMySnowball extends Snowball {


    //Entityに当たったダメージ量
    private static final float DAMAGE = 10000f;

    public EntityMySnowball(EntityType<? extends Snowball> entityTypeIn, Level level) {
        super(entityTypeIn, level);
    }

    public EntityMySnowball(Level level, LivingEntity throwerIn) {
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
                    if (block == Blocks.SNOW_BLOCK) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    } else if (result.getType() == HitResult.Type.ENTITY) {
                        System.err.println("EntityMySnowball.onHit: Entityに当たった");
                        EntityHitResult entityRayTraceResult = (EntityHitResult) result;
                        entityRayTraceResult.getEntity().hurt(DamageSource.thrown(this, getOwner()), DAMAGE);
                    }
                    else if (block == Blocks.GRANITE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                        }
                    else if (block == Blocks.STONE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.DIORITE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.DEEPSLATE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.ANDESITE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.COBBLESTONE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.STONE_BRICKS) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.MOSSY_STONE_BRICKS) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.DIRT) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.GLASS) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.COBBLESTONE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.SAND) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.GRAVEL) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.SANDSTONE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.CLAY) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.CALCITE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.TUFF) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.POINTED_DRIPSTONE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.DRIPSTONE_BLOCK) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.WATER) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.LAVA) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.ICE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.BLUE_ICE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.FROSTED_ICE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }
                    else if (block == Blocks.PACKED_ICE) {
                        level.setBlockAndUpdate(blockHitResult.getBlockPos(), Blocks.AIR.defaultBlockState());
                    }

                }
            }
        }
    }
}

