package archives.tater.magneticdart.registry;

import archives.tater.magneticdart.MagneticDart;
import archives.tater.magneticdart.item.MagneticDartItem;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public class MagneticDartItems {
    private static Item register(String path, Function<Item.Properties, Item> item, Item.Properties properties) {
        var key = ResourceKey.create(Registries.ITEM, MagneticDart.id(path));
        return Registry.register(BuiltInRegistries.ITEM, key, item.apply(properties.setId(key)));
    }

    public static final Item MAGNETIC_DART = register("magnetic_dart", MagneticDartItem::new, new Item.Properties()
            .stacksTo(16)
    );

    public static void init() {}
}
