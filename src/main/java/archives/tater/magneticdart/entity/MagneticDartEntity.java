package archives.tater.magneticdart.entity;

import archives.tater.magneticdart.registry.MagneticDartAttachments;
import archives.tater.magneticdart.registry.MagneticDartEntities;
import archives.tater.magneticdart.registry.MagneticDartEntityTags;
import archives.tater.magneticdart.registry.MagneticDartItems;

import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import org.jspecify.annotations.Nullable;

import static archives.tater.magneticdart.MagneticDart.withAppended;
import static net.minecraft.util.Mth.square;

public class MagneticDartEntity extends AbstractArrow {
    public static final double MAX_RANGE = 32;
    public static final double MAX_ACCELERATION = 0.5;

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
    public void tick() {
        if (!level().isClientSide()) {
            var maxRange = MAX_RANGE;

            for (var entity : level().getEntities(
                    this,
                    AABB.ofSize(position(), 2 * maxRange, 2 * maxRange, 2 * maxRange),
                    entity -> entity.is(MagneticDartEntityTags.MAGNETIC_PROJECTILE) && distanceToSqr(entity) < square(maxRange)
            ))
                entity.modifyAttached(MagneticDartAttachments.ATTRACTORS, attractors ->
                        withAppended(attractors, EntityReference.of(this))
                );
        }

        super.tick();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return MagneticDartItems.MAGNETIC_DART.getDefaultInstance();
    }
}
