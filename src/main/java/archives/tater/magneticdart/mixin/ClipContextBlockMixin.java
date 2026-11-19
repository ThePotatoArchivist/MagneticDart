package archives.tater.magneticdart.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.shapes.Shapes;

@Mixin(ClipContext.Block.class)
public enum ClipContextBlockMixin {
    MAGNETICDART_NONE((_, _, _, _) -> Shapes.empty());

    @Shadow
    ClipContextBlockMixin(ClipContext.ShapeGetter getShape) {
    }
}
