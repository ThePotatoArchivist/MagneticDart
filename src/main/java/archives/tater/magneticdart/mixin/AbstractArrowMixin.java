package archives.tater.magneticdart.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import static archives.tater.magneticdart.MagneticDart.getAttractors;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    public AbstractArrowMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    @ModifyExpressionValue(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;isAir()Z")
    )
    private boolean noPhysics(boolean original) {
        return original || !getAttractors(this).isEmpty();
    }

    @WrapOperation(
            method = "tick",
            at = @At(value = "NEW", target = "(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/ClipContext$Block;Lnet/minecraft/world/level/ClipContext$Fluid;Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/level/ClipContext;")
    )
    private ClipContext noPhysics(Vec3 from, Vec3 _to, ClipContext.Block block, ClipContext.Fluid fluid, Entity entity, Operation<ClipContext> original) {
        if (getAttractors(this).isEmpty()) return original.call(from, _to, block, fluid, entity);
        return original.call(from, _to, ClipContext.Block.MAGNETICDART_NONE, fluid, entity);
    }

    @WrapWithCondition(
            method = "tick",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/arrow/AbstractArrow;applyInertia(F)V")
    )
    private boolean noDrag(AbstractArrow instance, float inertia) {
        return getAttractors(this).isEmpty();
    }
}
