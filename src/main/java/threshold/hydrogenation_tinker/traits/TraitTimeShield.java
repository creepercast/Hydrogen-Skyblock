package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.HydrogenationConfig;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitTimeShield extends AbstractArmorTrait {
    public TraitTimeShield() {
        super("time_shield", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0){
            event.setAmount(event.getAmount() / 2);
        }
    }

    @Override
    public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
        if(HydrogenationConfig.joking){
            player.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,10,2));
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,10,2));
            player.addPotionEffect(new PotionEffect(MobEffects.MINING_FATIGUE,10,2));
        }
    }
}
