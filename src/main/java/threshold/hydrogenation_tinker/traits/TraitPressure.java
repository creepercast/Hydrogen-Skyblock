package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitPressure extends AbstractTrait {
    public TraitPressure() {
        super("pressure", 6536564);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        return super.damage(tool, player, target, damage, newDamage, isCritical)  + (float)Math.min(20.0,Math.max(player.posY- target.posY,0.0f) * 2.0f);
    }
}
