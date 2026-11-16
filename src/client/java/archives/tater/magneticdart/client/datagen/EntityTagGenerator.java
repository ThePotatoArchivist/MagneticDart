package archives.tater.magneticdart.client.datagen;

import archives.tater.magneticdart.registry.MagneticDartEntityTags;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class EntityTagGenerator extends FabricTagsProvider.EntityTypeTagsProvider {
    public EntityTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(MagneticDartEntityTags.MAGNETIC_PROJECTILE)
                .add(EntityType.ARROW)
                .add(EntityType.SPECTRAL_ARROW)
                .add(EntityType.FIREWORK_ROCKET)
                .add(EntityType.TRIDENT);
    }
}
