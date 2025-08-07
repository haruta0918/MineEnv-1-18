package com.example.examplemod.mc_04_hipotion;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ItemHiPotion extends Item{
    public ItemHiPotion() {
        super(new Item.Properties().food(new FoodProperties.Builder()
                .nutrition(1)
                .saturationMod(0.5f)
                .alwaysEat()
                .build()
        ).tab(CreativeModeTab.TAB_FOOD));
    }




    }


