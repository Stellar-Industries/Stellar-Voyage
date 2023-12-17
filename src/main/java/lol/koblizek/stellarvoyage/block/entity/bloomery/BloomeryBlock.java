package lol.koblizek.stellarvoyage.block.entity.bloomery;

import com.mojang.serialization.MapCodec;
import lol.koblizek.stellarvoyage.block.ModBlockEntities;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.*;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BloomeryBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final Property<Boolean> INVISIBLE = BooleanProperty.of("invisible");
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;


    public BloomeryBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(INVISIBLE, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(FACING,
                context.getHorizontalPlayerFacing().rotateYClockwise().rotateYClockwise());
    }
    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        if(state.get(INVISIBLE).equals(false)) {
            return new BloomeryBlockEntity(pos, state);
        } else
        {
            return null;
        }
    }


    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(INVISIBLE);
    }


    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity block = world.getBlockEntity(pos);
            if (block instanceof BloomeryBlockEntity ent) {
                ItemScatterer.spawn(world, pos, ent);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = ((BloomeryBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.BLOOMERY, (world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1));
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return super.canPlaceAt(state, world, pos);
    }

    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        if(state.get(INVISIBLE).equals(false)) {
            Direction direction = state.get(FACING);
            System.out.println(direction);
            switch (direction) {
                case NORTH:
                    world.setBlockState(pos.up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.south(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).south(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.south(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).south(1).up(1), state.with(INVISIBLE, true));
                    break;
                case SOUTH:
                    world.setBlockState(pos.up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.north(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).north(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.north(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).north(1).up(1), state.with(INVISIBLE, true));
                    break;
                case WEST:
                    world.setBlockState(pos.up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.south(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).south(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.south(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.east(1).south(1).up(1), state.with(INVISIBLE, true));
                    break;
                case EAST:
                    world.setBlockState(pos.up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.north(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).north(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.north(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).up(1), state.with(INVISIBLE, true));
                    world.setBlockState(pos.west(1).north(1).up(1), state.with(INVISIBLE, true));
                    break;
                default:
                    break;

            }
        }


    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }
}