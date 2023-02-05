package threshold.hydrogenation_tinker.Entity.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SuppressWarnings("NullableProblems")
public class ModelMarbleBlock extends ModelBase {
    public ModelRenderer box;

    public ModelMarbleBlock() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.box = new ModelRenderer(this, 0, 0);
        this.box.addBox(-32.0F, -160.0F, -32.0F, 64, 320, 64, 0.0F);
        this.box.setRotationPoint(0.0F, 0.0F, 0.0F);
    }
    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.box.render(scale);
    }
}
