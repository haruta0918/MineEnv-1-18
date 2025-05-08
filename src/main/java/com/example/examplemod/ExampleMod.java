package com.example.examplemod;

import com.example.examplemod.mc_01_myblock.BlockMyBlock;
import com.example.examplemod.mc_01_myblock.NewBlockMyBlock;
import com.example.examplemod.mc_03_magicstick.ItemMagicStick;
import com.example.examplemod.mc_03_magicstick.NewItemMagicStick;
import com.example.examplemod.mc_05_mysword.ItemMySword;
import com.example.examplemod.mc_05_mysword.NewItemMySword;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ExampleMod.MODID)
public class ExampleMod {

    //MODID
    public static final String MODID = "examplemod";

    //Item
    public static final Item ITEM_MAGIC_STICK =
            new ItemMagicStick().setRegistryName(MODID,"magic_stick");
    public static final Item NEWITEM_MAGIC_STICK =
            new NewItemMagicStick().setRegistryName(MODID,"new_magic_stick");

    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public static final Block BLOCK_MYBLOCK =
            new BlockMyBlock().setRegistryName(MODID, "block_myblock");
    public static final Block NEWBLOCK_MYBLOCK =
            new NewBlockMyBlock().setRegistryName(MODID, "new_block_myblock");



    public ExampleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

    public static final Item ITEM_MY_SWORD =
            new ItemMySword().setRegistryName(MODID,"my_sword");
    public static final Item NEW_ITEM_MY_SWORD =
            new NewItemMySword().setRegistryName(MODID,"new_my_sword");
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        private static final RegisterBlockData[] registerBlocks = {
                // ここにBlockを書いてね！
                new RegisterBlockData(BLOCK_MYBLOCK),
                new RegisterBlockData(NEWBLOCK_MYBLOCK),

        };

        private static final Item[] registerItems = {
                // ここにItemを書いてね！
                ITEM_MY_SWORD,
                NEW_ITEM_MY_SWORD,
                ITEM_MAGIC_STICK,
                NEWITEM_MAGIC_STICK,
        };

        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> event) {

        }

        @SubscribeEvent
        public static void onAttributeCreation(final EntityAttributeCreationEvent event) {

        }

        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event) {

        }

        // ======================================================================================================
        // ここから下はいじらないよ！

        private static void setupBiome(Biome biome, int weight, BiomeManager.BiomeType biomeType, BiomeDictionary.Type... types) {
            ResourceKey<Biome> key = ResourceKey.create(ForgeRegistries.Keys.BIOMES, ForgeRegistries.BIOMES.getKey(biome));

            BiomeDictionary.addTypes(key, types);
            BiomeManager.addBiome(biomeType, new BiomeManager.BiomeEntry(key, weight));
        }

        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> event) {
            LOGGER.info("HELLO from Register Block");
            for (RegisterBlockData data : registerBlocks) {
                event.getRegistry().register(data.block);
            }
        }

        @SubscribeEvent
        public static void onItemsRegistry(final RegistryEvent.Register<Item> event) {
            for (RegisterBlockData data : registerBlocks) {
                event.getRegistry().register(new BlockItem(data.block, new Item.Properties().tab(data.creativeModeTab)).setRegistryName(data.block.getRegistryName()));
            }

            for (Item item : registerItems) {
                event.getRegistry().register(item);
            }
        }

        static class RegisterBlockData {
            Block block;
            CreativeModeTab creativeModeTab;

            public RegisterBlockData(Block block) {
                this.block = block;
                creativeModeTab = CreativeModeTab.TAB_BUILDING_BLOCKS;
            }

            public RegisterBlockData(Block block, CreativeModeTab creativeModeTab) {
                this.block = block;
                this.creativeModeTab = creativeModeTab;
            }
        }
    }
}
