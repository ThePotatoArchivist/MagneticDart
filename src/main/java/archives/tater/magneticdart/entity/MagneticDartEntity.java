package archives.tater.magneticdart.entity;

import archives.tater.magneticdart.registry.MagneticDartEntities;
import archives.tater.magneticdart.registry.MagneticDartItems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import org.jspecify.annotations.Nullable;

public class MagneticDartEntity extends AbstractArrow {
    public static final double MAX_RANGE = 32;
    public static final double MAX_ACCELERATION = 0.5;
    public static final double DRAG = 0.9;
    public static final double DRAG_RANGE = 2;

    public MagneticDartEntity(EntityType<? extends MagneticDartEntity> type, Level level) {
        super(type, level);
    }

    public MagneticDartEntity(Level level, double x, double y, double z, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(MagneticDartEntities.MAGNETIC_DART, x, y, z, level, pickupItemStack, firedFromWeapon);
    }

    public MagneticDartEntity(Level level, LivingEntity mob, ItemStack pickupItemStack, @Nullable ItemStack firedFromWeapon) {
        super(MagneticDartEntities.MAGNETIC_DART, mob, level, pickupItemStack, firedFromWeapon);
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return MagneticDartItems.MAGNETIC_DART.getDefaultInstance();
    }
}
