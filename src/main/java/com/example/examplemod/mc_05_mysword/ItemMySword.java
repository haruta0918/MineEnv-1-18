package com.example.examplemod.mc_05_mysword;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;


public class ItemMySword extends SwordItem {
    public ItemMySword(){
        super(Tiers.NETHERITE,
                3,
                -2.04F,
                (new Item.Properties()).tab(CreativeModeTab.TAB_COMBAT));
        }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker){
        MobEffect effect = MobEffects.HEALTH_BOOST;
        int seconds = 10000;
        int potionLevel = 500;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));
        effect = MobEffects.DAMAGE_BOOST;
        seconds = 1000;
        potionLevel = 1000;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));
        Level level = pAttacker.level;
        BlockPos spawnPos =pAttacker.blockPosition();
        LivingEntity entityMob =new IronGolem(EntityType.IRON_GOLEM, level);

        entityMob.setPos(spawnPos.getX() - 5,spawnPos.getY() ,spawnPos.getZ() - 5);
        if(!level.isClientSide) {
            ((ServerLevel) level).tryAddFreshEntityWithPassengers(entityMob);
            //entityMob = new EXPERIENCE_ORB(EXPERIENCE_ORB, level);

            entityMob.setPos(spawnPos.getX() - 5, spawnPos.getY(), spawnPos.getZ() - 5);
            if (!level.isClientSide) {
                ((ServerLevel) level).tryAddFreshEntityWithPassengers(entityMob);



            }}




            return super.hurtEnemy(pStack, pTarget, pAttacker);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(
            Level level, Player playerIn, InteractionHand handIn){

    ArrowItem itemArrow = new ArrowItem(new Item.Properties());
        if (!level.isClientSide) {
            Arrow arrow = (Arrow) itemArrow.createArrow(level,
                    playerIn.getMainHandItem(), playerIn);
            arrow.shootFromRotation(playerIn, playerIn.xRotO, playerIn.yRotO, 0.0f, 20000f, 10.0f);
            level.addFreshEntity(arrow);
        }


        return super.use(level,playerIn,handIn);

    }
    @Override
    public boolean onEntitySwing(ItemStack pStack,LivingEntity pAttacker){
        LightningBolt lightningbolt = EntityType.LIGHTNING_BOLT.create(pAttacker.level);
        lightningbolt.moveTo(pAttacker.position().x-5,pAttacker.position().y,pAttacker.position().z-5);
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
        effect = MobEffects.HEAL;
        seconds = 1;
        potionLevel = 1;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));
        effect = MobEffects.SATURATION;
        seconds = 40;
        potionLevel = 300;
        pAttacker.addEffect(new MobEffectInstance(effect,seconds,potionLevel));
        Enchantment enchantment = Enchantments.UNBREAKING;
        pAttacker.getMainHandItem().enchant(enchantment,enchantment.getMaxLevel());
        enchantment = Enchantments.MENDING;
        pAttacker.getMainHandItem().enchant(enchantment,enchantment.getMaxLevel());
        enchantment = Enchantments.SWEEPING_EDGE;
        pAttacker.getMainHandItem().enchant(enchantment,enchantment.getMaxLevel());

        Level level = pAttacker.level;
        BlockPos spawnPos =pAttacker.blockPosition();
        LivingEntity entityMob =new IronGolem(EntityType.IRON_GOLEM, level);
        entityMob.setPos(spawnPos.getX()+5 ,spawnPos.getY() ,spawnPos.getZ()+5 );
        if(!level.isClientSide) {
            ((ServerLevel) level).tryAddFreshEntityWithPassengers(entityMob);
            }
        return super.onEntitySwing(pStack,pAttacker);
    }
}

