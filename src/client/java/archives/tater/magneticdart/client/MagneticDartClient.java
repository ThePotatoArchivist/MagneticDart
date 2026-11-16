package archives.tater.magneticdart.client;

import archives.tater.magneticdart.client.render.MagneticDartRenderer;
import archives.tater.magneticdart.registry.MagneticDartEntities;

import net.fabricmc.api.ClientModInitializer;

import net.minecraft.client.renderer.entity.EntityRenderers;

public class MagneticDartClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		EntityRenderers.register(MagneticDartEntities.MAGNETIC_DART, MagneticDartRenderer::new);
	}
}