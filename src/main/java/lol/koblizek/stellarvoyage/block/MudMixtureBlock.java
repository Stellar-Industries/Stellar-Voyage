package lol.koblizek.stellarvoyage.block;

import lol.koblizek.stellarvoyage.item.ModItems;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MudMixtureBlock extends Block {
    public MudMixtureBlock() {
        super(FabricBlockSettings.copyOf(Blocks.DIRT));
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        for (BlockState blockState : List.of(
                pos.add(-1, 0, 0),
                pos.add(0, 0, 1),
                pos.add(1, 0, 0),
                pos.add(0, 0, -1),
                pos.add(0, 1, 0),
                pos.add(0, -1, 0)
        ).stream().map(world::getBlockState).toList()) {
            if (blockState.getBlock() == Blocks.WATER) {
                world.scheduleBlockTick(pos, this, 40);
                break;
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
//        world.breakBlock(pos, true);
//        world.spawnEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(),
//                new ItemStack(Items.BRICK, 1)));
        world.setBlockState(pos, Blocks.DIRT.getDefaultState()); // TODO: set proper replacement
    }
}
