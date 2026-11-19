package archives.tater.magneticdart.mixin;

import archives.tater.magneticdart.MagneticDart;
import archives.tater.magneticdart.registry.MagneticDartAttachments;
import archives.tater.magneticdart.registry.MagneticDartEntityTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import java.util.List;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends Entity {

    public ProjectileMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void attract(CallbackInfo ci) {
        if (!is(MagneticDartEntityTags.MAGNETIC_PROJECTILE)) return;
        var attractors = getAttractors();
        for (var ref : attractors) {
            var entity = ref.getEntity(level(), Entity.class);
            if (entity == null) continue;
            addDeltaMovement(MagneticDart.getPull(entity, this));
        }
    }

    @Override
    protected void applyGravity() {
        if (!getAttractors().isEmpty()) return;
        super.applyGravity();
    }

    @Unique
    private List<EntityReference<Entity>> getAttractors() {
        return getAttachedOrElse(MagneticDartAttachments.ATTRACTORS, List.of());
    }
}
