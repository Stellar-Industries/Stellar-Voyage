package lol.koblizek.stellarvoyage.util;

import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

import java.util.*;

public class MasterProperty extends Property<Vec3i> {

    private final List<Vec3i> collect;

    public MasterProperty(String name, BlockPos masterPos) {
        super(name, Vec3i.class);
        collect = new ArrayList<>();
        if (masterPos != null)
            collect.add(masterPos);
    }

    public static MasterProperty of(String name) {
        return new MasterProperty(name, null);
    }

    public boolean hasMaster() {
        return collect.isEmpty();
    }

    @Override
    public Collection<Vec3i> getValues() {
        return collect;
    }

    @Override
    public String name(Vec3i value) {
        return value.toShortString();
    }

    @Override
    public Optional<Vec3i> parse(String name) {
        var x = Arrays.stream(name.split(", ")).map(Integer::parseInt)
                .toArray(Integer[]::new);
        return Optional.of(new Vec3i(x[0], x[1], x[2]));
    }
}
