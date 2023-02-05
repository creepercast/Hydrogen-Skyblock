package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitMagicNemesis extends ModifierTrait {
    public TraitMagicNemesis() {
        super("magic_nemesis", 0xffaaaaff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        if(!event.getSource().isMagicDamage()) return;
        if(Utils.getTotalLevel(event.getEntityLiving(),this) > 0 && event.getEntityLiving().isActiveItemStackBlocking()){
            Utils.Joke_HatedTrait(event.getEntityLiving());
            event.setCanceled(true);
        }
        if(event.getSource().getTrueSource() instanceof EntityLivingBase){
            EntityLivingBase user = (EntityLivingBase) event.getSource().getTrueSource();
            if(Utils.getLevel(user.getHeldItemMainhand(),this) + Utils.getLevel(user.getHeldItemOffhand(),this) > 0){
                event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getAmount());
                if(event.getEntityLiving().getHealth() < 0){
                    event.getEntityLiving().onDeath(event.getSource());
                }
                event.setCanceled(true);
            }
        }
    }
}
