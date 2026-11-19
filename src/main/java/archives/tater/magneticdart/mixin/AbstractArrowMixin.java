package archives.tater.magneticdart.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;

import static archives.tater.magneticdart.MagneticDart.getAttractors;

@Mixin(AbstractArrow.class)
public abstract class AbstractArrowMixin extends Projectile {
    public AbstractArrowMixin(EntityType<? extends Projectile> type, Level level) {
        super(type, level);
    }

    @ModifyReturnValue(
            method = "isNoPhysics",
            at = @At("TAIL")
    )
    private boolean noPhysics(boolean original) {
        return original || !getAttractors(this).isEmpty();
    }
}
