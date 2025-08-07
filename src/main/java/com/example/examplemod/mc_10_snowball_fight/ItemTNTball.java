package com.example.examplemod.mc_10_snowball_fight;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ItemTNTball extends SnowballItem {

    public ItemTNTball() {
        super(new Properties().stacksTo(16).tab(CreativeModeTab.TAB_COMBAT));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand handIn) {

        ItemStack itemStack = playerIn.getItemInHand(handIn);

        if (!playerIn.isCreative()) {
            itemStack.setCount(itemStack.getCount() - 1);
        }

        Random random = new Random();

        level.playSound(
                null,
                playerIn.getX(),
                playerIn.getY(),
                playerIn.getZ(),
                SoundEvents.SNOWBALL_THROW,
                SoundSource.NEUTRAL,
                0.5f,
                0.4f / (random.nextFloat() * 0.4f + 0.8f)
        );
        if (!level.isClientSide) {
            EntityMySnowball entity = new EntityMySnowball(level, playerIn);
            entity.shootFromRotation(playerIn, playerIn.xRotO, playerIn.yRotO, 0.0f, 1.5f, 1.0f);
            level.addFreshEntity(entity);
        }
        if (!level.isClientSide) {
            try {
                Thread.sleep(100); // 0.1秒(百ミリ秒)間だけ処理を止める
            } catch (InterruptedException e) {
            }
            MobEffect effect = MobEffects.DAMAGE_RESISTANCE;
            int secounds = 1;
            int potionLevel = 5;
            playerIn.addEffect(new MobEffectInstance(effect,secounds,potionLevel));
            effect = MobEffects.SLOW_FALLING;
            secounds = 100;
            potionLevel = 3000;
            playerIn.addEffect(new MobEffectInstance(effect,secounds,potionLevel));

            float explosionRadius = 2f;
            level.explode(null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), explosionRadius, Explosion.BlockInteraction.BREAK);

        }
        return InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide());
    }
}

