package com.example.examplemod.mc_09_redstone;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
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
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;


public class Trap2 extends Block {
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final double RADIUS = 5.0D;
    public Trap2() {
        super(Properties.of(Material.STONE).strength(30f));
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

        Player targetPlayer = null;

        for (Entity entity : level.getEntities(null, aabb)) {
            if (entity instanceof Player player) {
                targetPlayer = player;
                break;
            }
        }

        boolean isFound = targetPlayer != null;
        level.setBlockAndUpdate(pos, state.setValue(POWERED, isFound));

        // プレイヤーがいたら矢を撃つ
        if (isFound && !level.isClientSide) {

            // 矢を生成
            Arrow arrow = new Arrow(level,
                    pos.getX() + 0.5,
                    pos.getY() + 1.0,
                    pos.getZ() + 0.5
            );

            // ブロック → プレイヤーへの方向ベクトル
            Vec3 dir = targetPlayer.position()
                    .add(0, targetPlayer.getEyeHeight(), 0)
                    .subtract(arrow.position())
                    .normalize();

            // 矢の速度設定
            double speed = 5.D;
            arrow.setDeltaMovement(
                    dir.x * speed,
                    dir.y * speed,
                    dir.z * speed
            );

            arrow.setBaseDamage(0.5D); // ダメージ調整
            arrow.setOwner(null);      // 発射者なし（罠）

            level.addFreshEntity(arrow);
        }

        // 次のチェック用に再スケジュール
        level.scheduleTick(pos, this, 10); // 1秒ごと
    }



}


