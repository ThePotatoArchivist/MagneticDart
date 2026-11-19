package archives.tater.magneticdart;

import archives.tater.magneticdart.entity.MagneticDartEntity;
import archives.tater.magneticdart.registry.MagneticDartAttachments;
import archives.tater.magneticdart.registry.MagneticDartEntities;
import archives.tater.magneticdart.registry.MagneticDartItems;

import net.fabricmc.api.ModInitializer;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.phys.Vec3;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static net.minecraft.util.Mth.square;

public class MagneticDart implements ModInitializer {
	public static final String MOD_ID = "magneticdart";

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static <T> List<T> withAppended(List<T> list, T value) {
		var builder = ImmutableList.<T>builderWithExpectedSize(list.size() + 1);
		builder.addAll(list);
		builder.add(value);
		return builder.build();
	}

	public static Vec3 getPull(Entity attractor, Entity attracted) {
		var diff = attractor.position().subtract(attracted.position());
		var length = diff.length();
        return diff.normalize().scale(square(MagneticDartEntity.MAX_ACCELERATION) / length);
	}

	@Unique
	public static List<EntityReference<Entity>> getAttractors(Entity entity) {
		return entity.getAttachedOrElse(MagneticDartAttachments.ATTRACTORS, List.of());
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		MagneticDartItems.init();
		MagneticDartEntities.init();
		MagneticDartAttachments.init();
	}
}