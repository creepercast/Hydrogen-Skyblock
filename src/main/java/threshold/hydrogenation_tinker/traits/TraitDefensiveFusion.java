package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitDefensiveFusion extends ModifierTrait {
    public TraitDefensiveFusion() {
        super("defensive_fusion", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onDamage(LivingHurtEvent event){
        Entity target = event.getSource().getTrueSource();
        EntityLivingBase player = event.getEntityLiving();
        if (target != null && Utils.getTotalLevel(player,this) > 0 && target.getDistance(player) < 5f) {
            player.world.newExplosion(player,player.posX,player.posY,player.posZ,10f,false,false);
        }
    }
}
