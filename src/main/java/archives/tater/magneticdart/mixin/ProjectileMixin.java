package archives.tater.magneticdart.mixin;

import archives.tater.magneticdart.MagneticDart;
import archives.tater.magneticdart.registry.MagneticDartEntityTags;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;

import static archives.tater.magneticdart.MagneticDart.getAttractors;

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
        for (var ref : getAttractors(this)) {
            var entity = ref.getEntity(level(), Entity.class);
            if (entity == null) continue;
            addDeltaMovement(MagneticDart.getPull(entity, this));
        }
    }

    @Definition(id = "BLOCK", field = "Lnet/minecraft/world/phys/HitResult$Type;BLOCK:Lnet/minecraft/world/phys/HitResult$Type;")
    @Expression("? == BLOCK")
    @ModifyExpressionValue(
            method = "onHit",
            at = @At("MIXINEXTRAS:EXPRESSION")
    )
    private boolean noHitBlock(boolean original) {
        return original && getAttractors(this).isEmpty();
    }

    @Override
    protected void applyGravity() {
        if (!getAttractors(this).isEmpty()) return;
        super.applyGravity();
    }
}
