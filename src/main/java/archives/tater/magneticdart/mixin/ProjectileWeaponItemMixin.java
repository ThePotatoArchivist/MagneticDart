package archives.tater.magneticdart.mixin;

import archives.tater.magneticdart.registry.MagneticDartItems;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;

import java.util.function.Predicate;

@Mixin(ProjectileWeaponItem.class)
public class ProjectileWeaponItemMixin {
	@Definition(id = "ARROW_OR_FIREWORK", field = "Lnet/minecraft/world/item/ProjectileWeaponItem;ARROW_OR_FIREWORK:Ljava/util/function/Predicate;")
	@Expression("ARROW_OR_FIREWORK = @(?)")
	@ModifyExpressionValue(
			method = "<clinit>",
			at = @At("MIXINEXTRAS:EXPRESSION")
	)
	private static Predicate<ItemStack> allowDart(Predicate<ItemStack> original) {
		return original.or(stack -> stack.is(MagneticDartItems.MAGNETIC_DART));
	}
}