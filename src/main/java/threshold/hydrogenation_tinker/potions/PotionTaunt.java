package threshold.hydrogenation_tinker.potions;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;
import java.util.Objects;

public class PotionTaunt extends Potion {
    public PotionTaunt(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        if(!event.getEntityLiving().isPotionActive(this)) return;
        EntityLivingBase owner = event.getEntityLiving();
        int amplifier = Objects.requireNonNull(event.getEntityLiving().getActivePotionEffect(this)).getAmplifier();
        World world = owner.getEntityWorld();
        int range = 20+amplifier * 10;
        List<EntityLiving> targets = world.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB(
                owner.posX - range, owner.posY - range, owner.posZ - range,
                owner.posX + range, owner.posY + range, owner.posZ + range
        ), input -> true);
        for (EntityLiving target:targets) {
            target.setAttackTarget(owner);
        }
    }
}
