package archives.tater.magneticdart.registry;

import archives.tater.magneticdart.MagneticDart;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;

import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityReference;

import java.util.List;

public interface MagneticDartAttachments {
    AttachmentType<List<EntityReference<Entity>>> ATTRACTORS = AttachmentRegistry.create(
            MagneticDart.id("attractors"), builder -> builder
            .syncWith(EntityReference.<Entity>streamCodec().apply(ByteBufCodecs.list()), AttachmentSyncPredicate.all())
    );

    static void init() {

    }
}
