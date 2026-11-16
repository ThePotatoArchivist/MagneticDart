package archives.tater.magneticdart.client.render;

import archives.tater.magneticdart.MagneticDart;
import archives.tater.magneticdart.entity.MagneticDartEntity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.ArrowRenderState;
import net.minecraft.resources.Identifier;

public class MagneticDartRenderer extends ArrowRenderer<MagneticDartEntity, ArrowRenderState> {

    public static final Identifier LOCATION = MagneticDart.id("textures/entity/magnetic_dart.png");

    public MagneticDartRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected Identifier getTextureLocation(ArrowRenderState state) {
        return LOCATION;
    }

    @Override
    public ArrowRenderState createRenderState() {
        return new ArrowRenderState();
    }
}
