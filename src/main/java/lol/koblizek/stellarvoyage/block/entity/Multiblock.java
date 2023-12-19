package lol.koblizek.stellarvoyage.block.entity;

import lol.koblizek.stellarvoyage.util.MasterProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Multiblock {
    public static final BooleanProperty ISMASTER = BooleanProperty.of("ismaster");
    public static final BooleanProperty INVISIBLE = BooleanProperty.of("invisible");
    public static final MasterProperty MASTER_BLOCK = MasterProperty.of("master_block");

    public static void setBlocks(World world, BlockPos pos, BlockState state, BlockPos original) {
        world.setBlockState(pos, state.with(INVISIBLE, true).with(ISMASTER, false)
                .with(MASTER_BLOCK, original));

    }
    public static void destroyBlocks(World world, BlockPos pos, BlockState state, BlockPos original) {
        world.setBlockState(pos, state);

    }
    public static void multiblock2x2(Direction direction, World world, BlockPos pos, BlockState state) {

        switch (direction) {
            case NORTH:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.south(1), state, pos);
                setBlocks(world, pos.west(1),  state, pos);
                setBlocks(world, pos.west(1).south(1), state, pos);
                setBlocks(world, pos.south(1).up(1), state, pos);
                setBlocks(world, pos.west(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).south(1).up(1), state, pos);
                break;
            case SOUTH:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.north(1), state, pos);
                setBlocks(world, pos.east(1),  state, pos);
                setBlocks(world, pos.east(1).north(1), state, pos);
                setBlocks(world, pos.north(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).north(1).up(1), state, pos);
                break;
            case WEST:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.south(1),  state, pos);
                setBlocks(world, pos.east(1),  state, pos);
                setBlocks(world, pos.east(1).south(1),  state, pos);
                setBlocks(world, pos.south(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).south(1).up(1),  state, pos);
                break;
            case EAST:
                setBlocks(world, pos.up(1),  state, pos);
                setBlocks(world, pos.north(1),  state, pos);
                setBlocks(world, pos.west(1), state, pos);
                setBlocks(world, pos.west(1).north(1),  state, pos);
                setBlocks(world, pos.north(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).north(1).up(1),   state, pos);
                break;
            default:
                break;

        }

    }
    public static void multiblock2x2delete(Direction direction, World world, BlockPos pos, BlockState state1) {
        BlockState state = Blocks.AIR.getDefaultState();
        switch (direction) {
            case NORTH:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.south(1), state, pos);
                setBlocks(world, pos.west(1),  state, pos);
                setBlocks(world, pos.west(1).south(1), state, pos);
                setBlocks(world, pos.south(1).up(1), state, pos);
                setBlocks(world, pos.west(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).south(1).up(1), state, pos);
                break;
            case SOUTH:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.north(1), state, pos);
                setBlocks(world, pos.east(1),  state, pos);
                setBlocks(world, pos.east(1).north(1), state, pos);
                setBlocks(world, pos.north(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).north(1).up(1), state, pos);
                break;
            case WEST:
                setBlocks(world, pos.up(1), state, pos);
                setBlocks(world, pos.south(1),  state, pos);
                setBlocks(world, pos.east(1),  state, pos);
                setBlocks(world, pos.east(1).south(1),  state, pos);
                setBlocks(world, pos.south(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).up(1),  state, pos);
                setBlocks(world, pos.east(1).south(1).up(1),  state, pos);
                break;
            case EAST:
                setBlocks(world, pos.up(1),  state, pos);
                setBlocks(world, pos.north(1),  state, pos);
                setBlocks(world, pos.west(1), state, pos);
                setBlocks(world, pos.west(1).north(1),  state, pos);
                setBlocks(world, pos.north(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).up(1),  state, pos);
                setBlocks(world, pos.west(1).north(1).up(1),   state, pos);
                break;
            default:
                break;

        }

    }
}
