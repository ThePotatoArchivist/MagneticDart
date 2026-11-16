package archives.tater.magneticdart.mixin;

import archives.tater.magneticdart.registry.MagneticDartItems;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ChargedProjectiles;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {
    @WrapOperation(
            method = "getShootingPower",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/ChargedProjectiles;contains(Lnet/minecraft/world/item/Item;)Z")
    )
    private static boolean reducePowerForDart(ChargedProjectiles instance, Item item, Operation<Boolean> original) {
        return original.call(instance, item) || instance.contains(MagneticDartItems.MAGNETIC_DART);
    }
}
