package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitHolyWind extends AbstractArmorTrait {
    public TraitHolyWind() {
        super("holy_wind", 6536564);
    }
    @Override
    public float onHurt(ItemStack armor, EntityPlayer player, DamageSource source, float damage, float newDamage, LivingHurtEvent evt) {
        if (Utils.getTotalLevel(player,this) > 0 && 0.25f > Math.random()) {
            player.addPotionEffect(new PotionEffect(
                    Hydrogenation_tinker.Potions.HOLY_WIND,
                    160,
                    Utils.getTotalLevel(player,this) + 5
            ));
        }
        return newDamage;
    }
}
