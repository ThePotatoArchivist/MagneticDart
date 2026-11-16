package archives.tater.magneticdart.registry;

import archives.tater.magneticdart.MagneticDart;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;

public class MagneticDartEntityTags {
    private static TagKey<EntityType<?>> create(String path) {
        return TagKey.create(Registries.ENTITY_TYPE, MagneticDart.id(path));
    }

    public static final TagKey<EntityType<?>> MAGNETIC_PROJECTILE = create("magnetic_projectile");
}
