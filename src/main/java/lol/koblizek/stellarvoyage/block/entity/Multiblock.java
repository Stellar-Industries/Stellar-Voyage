package lol.koblizek.stellarvoyage.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class Multiblock {
    public static final BooleanProperty ISMASTER = BooleanProperty.of("ismaster");
    public static final BooleanProperty INVISIBLE = BooleanProperty.of("invisible");
    public static void setBlocks(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state.with(INVISIBLE, true).with(ISMASTER, false));

    }
    public static void multiblock2x2(Direction direction, World world, BlockPos pos, BlockState state) {

        switch (direction) {
            case NORTH:
                setBlocks(world, pos.up(1), state);
                setBlocks(world, pos.south(1), state);
                setBlocks(world, pos.west(1),  state);
                setBlocks(world, pos.west(1).south(1), state);
                setBlocks(world, pos.south(1).up(1), state);
                setBlocks(world, pos.west(1).up(1),  state);
                setBlocks(world, pos.west(1).south(1).up(1), state);
                break;
            case SOUTH:
                setBlocks(world, pos.up(1), state);
                setBlocks(world, pos.north(1), state);
                setBlocks(world, pos.east(1),  state);
                setBlocks(world, pos.east(1).north(1), state);
                setBlocks(world, pos.north(1).up(1),  state);
                setBlocks(world, pos.east(1).up(1),  state);
                setBlocks(world, pos.east(1).north(1).up(1), state);
                break;
            case WEST:
                setBlocks(world, pos.up(1), state);
                setBlocks(world, pos.south(1),  state);
                setBlocks(world, pos.east(1),  state);
                setBlocks(world, pos.east(1).south(1),  state);
                setBlocks(world, pos.south(1).up(1),  state);
                setBlocks(world, pos.east(1).up(1),  state);
                setBlocks(world, pos.east(1).south(1).up(1),  state);
                break;
            case EAST:
                setBlocks(world, pos.up(1),  state);
                setBlocks(world, pos.north(1),  state);
                setBlocks(world, pos.west(1), state);
                setBlocks(world, pos.west(1).north(1),  state);
                setBlocks(world, pos.north(1).up(1),  state);
                setBlocks(world, pos.west(1).up(1),  state);
                setBlocks(world, pos.west(1).north(1).up(1),   state);
                break;
            default:
                break;

        }

    }
}
