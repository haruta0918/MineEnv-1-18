package com.example.examplemod.mc_01_myblock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

import javax.annotation.Nullable;

public class GenerateBlock extends Block {
    public GenerateBlock () {super(Properties.of(Material.DIRT));}

    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack){
    for(int i = 0; i < 5; i++) {
        ItemStack stack = new ItemStack(Items.DIAMOND);
        ItemEntity itemEntity = new ItemEntity(pLevel, pPos.getX(), pPos.getY(), pPos.getZ(), stack);
        itemEntity.setDefaultPickUpDelay();
        pLevel.addFreshEntity(itemEntity);
    }
    super.setPlacedBy(pLevel,pPos,pState,pPlacer,pStack);
    }
}