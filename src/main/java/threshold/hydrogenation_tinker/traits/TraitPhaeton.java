package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import threshold.hydrogenation_tinker.HydrogenationConfig;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitPhaeton extends AbstractArmorTrait {
    public TraitPhaeton() {
        super("phaeton", 6536564);
    }
    @Override
    public float onDamaged(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingDamageEvent evt) {
        if (Utils.getTotalLevel(player,this) > 0 && 0.1f > Math.random()) {
            evt.setCanceled(true);
            return 0;
        }
        return newDamage;
    }
    @Override
    public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
        if(HydrogenationConfig.joking){
            if(player.hurtResistantTime == 0){
                player.attackEntityFrom(DamageSource.DROWN,1.0f);
            }
        }
    }
}
