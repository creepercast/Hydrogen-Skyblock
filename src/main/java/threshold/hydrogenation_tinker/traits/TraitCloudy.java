package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitCloudy extends ModifierTrait {
    public TraitCloudy() {
        super("cloudy", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase player = event.getEntityLiving();
        if (Utils.getTotalLevel(player,this) > 0) {
            player.fallDistance = 0;
            if(!player.isSneaking()) {
                player.motionY = Math.max(-0.5, player.motionY);
            }
        }
    }
}
