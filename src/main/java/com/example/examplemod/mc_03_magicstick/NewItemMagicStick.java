package com.example.examplemod.mc_03_magicstick;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

public class NewItemMagicStick extends Item{
    public NewItemMagicStick() {
        super(new Properties().tab(CreativeModeTab.TAB_COMBAT));
    }


    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        Level level = pTarget.level;
        BlockPos spawnPos =pTarget.blockPosition();

if(!level.isClientSide){
    float explosionRadius = 50f;
    level.explode(null,pAttacker.getX(),pAttacker.getY(),pAttacker.getZ(),
            explosionRadius, Explosion.BlockInteraction.BREAK);

        }
        return super.hurtEnemy(pStack,pTarget,pAttacker);






    }

}




