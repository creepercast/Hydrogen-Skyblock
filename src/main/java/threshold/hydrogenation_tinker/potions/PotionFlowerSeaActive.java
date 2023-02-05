package threshold.hydrogenation_tinker.potions;

import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionFlowerSeaActive extends Potion {
    public PotionFlowerSeaActive(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onHeal(LivingHealEvent event){
        if(event.getEntityLiving().isPotionActive(this)){
            event.setCanceled(true);
            event.setAmount(0);
        }
    }
}
