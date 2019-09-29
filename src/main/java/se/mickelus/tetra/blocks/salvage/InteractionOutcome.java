package se.mickelus.tetra.blocks.salvage;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface InteractionOutcome {
    public boolean apply(World world, BlockPos pos, IBlockState blockState, PlayerEntity player, EnumHand hand, EnumFacing hitFace);
}
