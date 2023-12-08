package lol.koblizek.stellarvoyage.util;

import com.google.common.reflect.ClassPath;
import lol.koblizek.stellarvoyage.References;
import lol.koblizek.stellarvoyage.blocks.TestBlock;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class IDataLookupRegistration<T> implements References {

    private static IDataLookupRegistration inst;

    public static IDataLookupRegistration get() {
        return inst;
    }

    private Map<String, T> stuff = new HashMap<>();

    protected abstract Registry<T> getRegistry();
    protected abstract String getPackage();

    public void registerAll() {
        inst = this;

        try {
            List<? extends Class<?>> list = ClassPath.from(TestBlock.class.getClassLoader())
                    .getAllClasses().stream()
                    .filter(c -> c.getPackageName().equals(getPackage()))
                    .map(c -> {
                        try {
                            return Class.forName(c.getName(), false, TestBlock.class.getClassLoader());
                        } catch (ClassNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .filter(c -> c.isAnnotationPresent(Autoregister.class)).toList();
            for (Class<?> c : list) {
                LOGGER.debug("Adding: {}", c.getSimpleName());
                String id = c.getAnnotation(Autoregister.class).value();
                T t = (T) c.newInstance();
                Registry.register(getRegistry(), new Identifier(MOD_ID, id),
                        t);
                stuff.put(id, t);
            }
        } catch (IOException e) {
            LOGGER.error("Unknown IO Exception occurred: ", e);
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("Unknown error occurred and no more will be loaded...");
        }
    }

    public T get(String id) {
        return stuff.get(id);
    }
}
