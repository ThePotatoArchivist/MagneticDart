package archives.tater.magneticdart.mixin;

import archives.tater.magneticdart.entity.MagneticDartEntity;
import archives.tater.magneticdart.registry.MagneticDartEntityTags;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import static archives.tater.magneticdart.entity.MagneticDartEntity.MAX_RANGE;
import static net.minecraft.util.Mth.square;

@Mixin(Projectile.class)
public abstract class ProjectileMixin extends Entity {

    @Unique
    private static final double DISTANCE = 2 * MAX_RANGE;

    @Unique
    private boolean captured = false;

    public ProjectileMixin(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void attract(CallbackInfo ci) {
        if (!is(MagneticDartEntityTags.MAGNETIC_PROJECTILE)) return;
        captured = false;
        for (var dart : level().getEntitiesOfClass(MagneticDartEntity.class, AABB.ofSize(position(), DISTANCE, DISTANCE, DISTANCE))) {
            if (distanceToSqr(dart) > MAX_RANGE * MAX_RANGE) return;
            var diff = dart.position().subtract(position());
            var length = diff.length();
            addDeltaMovement(diff.normalize().scale(square(MagneticDartEntity.MAX_ACCELERATION) / length));
//            if (length > MagneticDartEntity.DRAG_RANGE)
//                setDeltaMovement(getDeltaMovement().scale(MagneticDartEntity.DRAG));
            captured = true;
        }
    }

    @Override
    protected void applyGravity() {
        if (captured) return;
        super.applyGravity();
    }
}
