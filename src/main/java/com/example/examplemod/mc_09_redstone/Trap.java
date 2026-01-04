package com.example.examplemod.mc_09_redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.AABB;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class Trap extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final double RADIUS = 5.0D;
    public Trap() {
        super(Block.Properties.of(Material.STONE).strength(30f));
        this.registerDefaultState(this.getStateDefinition().any().setValue(POWERED,false));
    }
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState > pBuilder){
        pBuilder.add(POWERED);
    }
    @Override
    public boolean isSignalSource(BlockState pBlockState) {
        return true;
    }

    @Override
    public int getSignal(BlockState pBlockState, BlockGetter pBlockAccess, BlockPos pPos, Direction pSide) {
        if (pBlockState.getValue(POWERED) == true) {
            return 15;
        } else {
            return 0;
        }
    }
    @Override
    public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack
    pStack) {
        super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
        pLevel.scheduleTick(pPos, this, 5);
    }
    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random rand) {
        super.tick(state, level, pos, rand);

        AABB aabb = new AABB(
                pos.getX() - RADIUS,
                pos.getY() - RADIUS,
                pos.getZ() - RADIUS,
                pos.getX() + RADIUS,
                pos.getY() + RADIUS,
                pos.getZ() + RADIUS
        );
        boolean isFound = false;
        List<Entity> entityList = level.getEntities(null, aabb);

        for (Entity entity : entityList) {
            if (entity.getType() == EntityType.PLAYER) {
                isFound = true;
                break;
            }
        }

        level.setBlockAndUpdate(pos, state.setValue(POWERED, isFound));
        int num = 2;
        while(num < 101) {
            BlockPos setPos = new BlockPos(
                    pos.getX()+1 ,
                    pos.getY() -num,
                    pos.getZ()
            );
            level.setBlock(setPos, Blocks.SPONGE.defaultBlockState(), 3);
            level.scheduleTick(pos, this, 20);
            num++;
        }
        num = 2;
        while(num < 101) {
            BlockPos setPos = new BlockPos(
                    pos.getX()-1 ,
                    pos.getY() -num,
                    pos.getZ()
            );
            level.setBlock(setPos, Blocks.SPONGE.defaultBlockState(), 3);
            level.scheduleTick(pos, this, 20);
            num++;
        }
        num = 2;
        while(num < 101) {
            BlockPos setPos = new BlockPos(
                    pos.getX() ,
                    pos.getY() -num,
                    pos.getZ()+1
            );
            level.setBlock(setPos, Blocks.SPONGE.defaultBlockState(), 3);
            level.scheduleTick(pos, this, 20);
            num++;
        }
        num = 2;
        while(num < 101) {
            BlockPos setPos = new BlockPos(
                    pos.getX() ,
                    pos.getY() -num,
                    pos.getZ()-1
            );
            level.setBlock(setPos, Blocks.SPONGE.defaultBlockState(), 3);
            level.scheduleTick(pos, this, 20);
            num++;
        }
        num = 1;
        while(num < 100) {
            BlockPos setPos = new BlockPos(
                    pos.getX() ,
                    pos.getY() -num,
                    pos.getZ()
            );
            level.setBlock(setPos, Blocks.OAK_BUTTON.defaultBlockState(), 3);
            level.scheduleTick(pos, this, 20);
            num++;
        }
        BlockPos setPos = new BlockPos(
                pos.getX() ,
                pos.getY() -101,
                pos.getZ()
        );
        level.setBlock(setPos, Blocks.DRIPSTONE_BLOCK.defaultBlockState(), 3);
        level.scheduleTick(pos, this, 20);
        setPos = new BlockPos(
                pos.getX() ,
                pos.getY() -100,
                pos.getZ()
        );
        level.setBlock(setPos, Blocks.POINTED_DRIPSTONE.defaultBlockState(), 3);
        level.scheduleTick(pos, this, 20);
        level.scheduleTick(pos, this, 20);
        setPos = new BlockPos(
                pos.getX() ,
                pos.getY() -102,
                pos.getZ()
        );
        level.setBlock(setPos, Blocks.DIAMOND_BLOCK.defaultBlockState(), 3);
        level.scheduleTick(pos, this, 20);

    }
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);

        if (!level.isClientSide && entity instanceof net.minecraft.server.level.ServerPlayer player) {

            double x = pos.getX();
            double y = pos.getY() - 2;   // ← 下が空気ブロックであること！
            double z = pos.getZ();

            player.teleportTo(x, y, z);
        }
    }


}


