package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.HydrogenationConfig;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitEvilDeal extends AbstractArmorTrait {
    public TraitEvilDeal() {
        super("evil_deal", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0){
            if(HydrogenationConfig.joking){
                event.setAmount(Float.MAX_VALUE);
                event.getEntityLiving().setHealth(-100.0f);
                event.getEntityLiving().onDeath(event.getSource());
                event.getEntityLiving().sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.evil_deal")));
                return;
            }
            double random = Math.random();
            if(random < 0.05){
                event.setAmount(0);
                event.setCanceled(true);
            }else if(random<0.25){
                event.setAmount(event.getAmount() * 2);
            }else if(random < 0.275){
                event.setAmount(Float.MAX_VALUE);
                event.getEntityLiving().setHealth(-100.0f);
                event.getEntityLiving().onDeath(event.getSource());
            }
        }
    }
}
