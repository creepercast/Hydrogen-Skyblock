package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

public class TraitBitingWind extends ModifierTrait {
    public TraitBitingWind() {
        super("biting_wind", 0xffaaaaff);
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);
        int frozen = 0;
        for (ItemStack stack:target.getArmorInventoryList()) {
            if(stack.isEmpty()){
                frozen += 1;
            }
        }
        target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,frozen));
        if(frozen > 1){
            target.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,60,150));
            target.hurtResistantTime = 0;
            target.attackEntityFrom(DamageSource.causeMobDamage(player).setMagicDamage(),2.0f);
        }
    }
}
