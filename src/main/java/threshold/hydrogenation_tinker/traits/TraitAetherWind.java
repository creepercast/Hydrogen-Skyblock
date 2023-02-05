package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class TraitAetherWind extends ModifierTrait {
    public TraitAetherWind() {
        super("aether_wind", 0xffaaaaff);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);
        target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION,5,40));
    }
}
