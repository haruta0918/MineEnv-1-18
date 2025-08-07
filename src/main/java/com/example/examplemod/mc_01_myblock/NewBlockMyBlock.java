package com.example.examplemod.mc_01_myblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class NewBlockMyBlock extends Block {
    public NewBlockMyBlock() {
        super(Properties.of(Material.DIRT));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        super.stepOn(pLevel, pPos, pState, pEntity);

        LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(pEntity.level);
        lightningbolt.moveTo(pEntity.position().x, pEntity.position().y, pEntity.position().z);
        lightningbolt.setVisualOnly(false);
        pEntity.level.addFreshEntity(lightningbolt);
        if (!pLevel.isClientSide) {
            float explosionRadius = 20f;
            pLevel.explode(null, pPos.getX(), pPos.getY(), pPos.getZ(),
                    explosionRadius, Explosion.BlockInteraction.BREAK);
        }


    }
}



