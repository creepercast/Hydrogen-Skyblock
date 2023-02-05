package threshold.hydrogenation_tinker.Entity.renders;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import threshold.hydrogenation_tinker.Entity.EntityMarbleBlock;
import threshold.hydrogenation_tinker.Entity.models.ModelMarbleBlock;

@SuppressWarnings("NullableProblems")
public class RenderMarbleBlock extends Render<EntityMarbleBlock> {
    private final ModelBase model = new ModelMarbleBlock();
    private static final ResourceLocation MY_ENTITY_TEXTURE = new ResourceLocation("hydrogenation_tinker", "textures/entity/marble_block.png");

    public RenderMarbleBlock(RenderManager manager) {
        super(manager);
    }

    public void doRender(EntityMarbleBlock entity, double x, double y, double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x, (float) y, (float) z);

        this.bindEntityTexture(entity);

        GlStateManager.scale(-1.0F, -1.0F, 1.0F);

        GlStateManager.rotate(MathHelper.wrapDegrees(((float) x + (float) z + entity.ticksExisted + partialTicks) * 11F), 0, 1, 0);


        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GlStateManager.translate(0F, -0.5F, 0F);
        this.model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, partialTicks, 0.0625F / 2F);
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();


        GlStateManager.popMatrix();
    }

    protected ResourceLocation getEntityTexture(EntityMarbleBlock entity) {
        return MY_ENTITY_TEXTURE;
    }
}