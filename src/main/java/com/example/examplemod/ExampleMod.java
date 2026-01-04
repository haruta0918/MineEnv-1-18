package com.example.examplemod;

import com.example.examplemod.BlockCopier.BlockCopier;
import com.example.examplemod.BlockCopier.LandMineStick;
import com.example.examplemod.mc_01_myblock.BlockMyBlock;
import com.example.examplemod.mc_01_myblock.GenerateBlock;
import com.example.examplemod.mc_01_myblock.NewBlockMyBlock;
import com.example.examplemod.mc_03_magicstick.ItemMagicStick;
import com.example.examplemod.mc_03_magicstick.NewItemMagicStick;
import com.example.examplemod.mc_04_hipotion.ItemHiPotion;
import com.example.examplemod.mc_05_mysword.ItemMySword;
import com.example.examplemod.mc_05_mysword.NewItemMySword;
import com.example.examplemod.mc_06_rainbowblock.BlockRainbow;
import com.example.examplemod.mc_08_woodcut.BlockBreakEventHandler;
import com.example.examplemod.mc_09_redstone.*;
import com.example.examplemod.mc_10_snowball_fight.EntityMySnowball;
import com.example.examplemod.mc_10_snowball_fight.ItemMySnowball;
import com.example.examplemod.mc_11_footprints_sand.BlockFootprintsSand;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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

import static java.rmi.registry.LocateRegistry.getRegistry;

@Mod(ExampleMod.MODID)
public class ExampleMod {

    //MODID
    public static final String MODID = "examplemod";
    public static final Block TRAP =new Trap().setRegistryName(MODID,"trap");
    public static final Block TRAP2 =new Trap2().setRegistryName(MODID,"trap2");
    public static final Block TRAP3 =new Trap3().setRegistryName(MODID,"trap3");
    public static final Block TRAP4 =new Trap4().setRegistryName(MODID,"trap4");
    public static final Block TRAP5 =new Trap5().setRegistryName(MODID,"trap5");
    public static final Block BLOCK_SENSOR =new BlockSensor().setRegistryName(MODID,"block_sensor");
    public static final Item BLOCK_COPIER = new BlockCopier().setRegistryName(MODID,"block_copier");
    public static final Item LANDMINE_STICK = new LandMineStick().setRegistryName(MODID,"landmine_stick");

    public static final Block BLOCK_RAINBOW =
            new BlockRainbow().setRegistryName(MODID, "block_rainbow");
    public static final Block BLOCK_FOOTPRINTS_SAND =
            new BlockFootprintsSand().setRegistryName(MODID, "block_footprints_sand");
    //Entity
    public static final EntityType<EntityMySnowball> ENTITY_MY_SNOWBALL =
            EntityType.Builder.<EntityMySnowball>of(EntityMySnowball::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .setShouldReceiveVelocityUpdates(true)
                    .build("my_snowball");
    public static final EntityType<EntityMySnowball> TNT_BALL =
            EntityType.Builder.<EntityMySnowball>of(EntityMySnowball::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f)
                    .setShouldReceiveVelocityUpdates(true)
                    .build("tnt_snowball");
    //Item
    public static final Item ITEM_HI_POTION =
            new ItemHiPotion().setRegistryName(MODID,"hi_potion");
    public static final Item ITEM_TNT_BALL =
            new ItemHiPotion().setRegistryName(MODID,"tnt_ball");
    public static final Item ITEM_MY_SNOWBALL =
            new ItemMySnowball().setRegistryName(MODID,"my_snowball");
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
    public static final Block GENERATEBLOCK =
            new GenerateBlock().setRegistryName(MODID, "generate_block");



    public ExampleMod() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BlockBreakEventHandler());
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        EntityRenderers.register(ENTITY_MY_SNOWBALL, ThrownItemRenderer::new);
        EntityRenderers.register(TNT_BALL, ThrownItemRenderer::new);

    }


    public static final Item ITEM_MY_SWORD =
            new ItemMySword().setRegistryName(MODID,"my_sword");
    public static final Item NEW_ITEM_MY_SWORD =
            new NewItemMySword().setRegistryName(MODID,"new_my_sword");
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        private static final RegisterBlockData[] registerBlocks = {
                // ここにBlockを書いてね！
                new RegisterBlockData(BLOCK_RAINBOW),
                new RegisterBlockData(BLOCK_MYBLOCK),
                new RegisterBlockData(NEWBLOCK_MYBLOCK),
                new RegisterBlockData(BLOCK_FOOTPRINTS_SAND),
                new RegisterBlockData(GENERATEBLOCK),
                new RegisterBlockData(BLOCK_SENSOR),
                new RegisterBlockData(TRAP),
                new RegisterBlockData(TRAP2),
                new RegisterBlockData(TRAP3),
                new RegisterBlockData(TRAP4),
                new RegisterBlockData(TRAP5)
        };

        private static final Item[] registerItems = {
                // ここにItemを書いてね！
                ITEM_MY_SNOWBALL,
                ITEM_TNT_BALL,
                ITEM_HI_POTION,
                ITEM_MY_SWORD,
                NEW_ITEM_MY_SWORD,
                ITEM_MAGIC_STICK,
                NEWITEM_MAGIC_STICK,
                BLOCK_COPIER,
                LANDMINE_STICK,

        };

        @SubscribeEvent
        public static void onBiomeRegistry(final RegistryEvent.Register<Biome> event) {

        }

        @SubscribeEvent
        public static void onAttributeCreation(final EntityAttributeCreationEvent event) {

        }

        @SubscribeEvent
        public static void onEntitiesRegistry(final RegistryEvent.Register<EntityType<?>> event) {
            event.getRegistry().register(ENTITY_MY_SNOWBALL.setRegistryName(MODID,"my_snowball"));

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
