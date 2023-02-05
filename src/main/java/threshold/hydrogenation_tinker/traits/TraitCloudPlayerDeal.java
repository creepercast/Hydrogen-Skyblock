package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitCloudPlayerDeal extends AbstractArmorTrait {
    public TraitCloudPlayerDeal() {
        super("cloud_player_deal", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0){
            double random = Math.random();
            //noinspection ConstantConditions
            if(random < 0){
                event.setAmount(0);
                event.setCanceled(true);
            }
            //noinspection ConstantConditions
            if(random < 1){
                event.getEntityLiving().setHealth(-100.0f);
                event.getEntityLiving().onDeath(event.getSource());
            }
        }
    }
}
