package archives.tater.magneticdart.registry;

import archives.tater.magneticdart.MagneticDart;
import archives.tater.magneticdart.entity.MagneticDartEntity;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.Consumer;

public class MagneticDartEntities {
    private static <T extends Entity> EntityType<T> register(String path, EntityType.Builder<T> type) {
        var key = ResourceKey.create(Registries.ENTITY_TYPE, MagneticDart.id(path));
        return Registry.register(
                BuiltInRegistries.ENTITY_TYPE,
                key,
                type.build(key)
        );
    }

    private static <T extends Entity> EntityType<T> register(String path, EntityType.EntityFactory<T> factory, MobCategory category, Consumer<EntityType.Builder<T>> init) {
        var type = EntityType.Builder.of(factory, category);
        init.accept(type);
        return register(path, type);
    }

    private static <T extends Entity> EntityType<T> register(String path, EntityType.EntityFactory<T> factory, Consumer<EntityType.Builder<T>> init) {
        return register(path, factory, MobCategory.MISC, init);
    }

    public static final EntityType<MagneticDartEntity> MAGNETIC_DART = register("magnetic_dart", MagneticDartEntity::new, builder -> builder
            .noLootTable()
            .sized(0.5F, 0.5F)
            .eyeHeight(0.13F)
            .clientTrackingRange(4)
            .updateInterval(20)
    );

    public static void init() {

    }
}
