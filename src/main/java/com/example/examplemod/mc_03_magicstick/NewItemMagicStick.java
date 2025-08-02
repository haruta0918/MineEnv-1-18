package com.example.examplemod.mc_03_magicstick;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class NewItemMagicStick extends Item {
    public NewItemMagicStick() {
        super(new Properties().tab(CreativeModeTab.TAB_COMBAT));
    }


    @Override
    public boolean hurtEnemy(ItemStack pStack,LivingEntity pTarget, LivingEntity pAttacker) {

        LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(pTarget.level);
        lightningbolt.moveTo(pTarget.position().x,pTarget     .position().y,pTarget.position().z);
        lightningbolt.setVisualOnly(false);
        pAttacker.level.addFreshEntity(lightningbolt);
        MobEffect effect = MobEffects.FIRE_RESISTANCE;
        int seconds = 100;
        int potionLevel = 5;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));
        effect = MobEffects.DAMAGE_RESISTANCE;
        seconds = 100;
        potionLevel = 3000;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));

        return super.onEntitySwing(pStack,pAttacker);
}






    }






