package threshold.hydrogenation_tinker.potions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionCollideAttack extends Potion {
    public PotionCollideAttack(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void livingAttack(LivingAttackEvent event) {
        EntityLivingBase living = (EntityLivingBase)event.getEntity();
        if (living.isPotionActive(this)) {
            Entity source = event.getSource().getTrueSource();
            if (!(source instanceof EntityLivingBase)) {
                return;
            }
            event.setCanceled(true);
            float distance = source.getDistance(living);
            if (distance > 3.0F) {
                return;
            }
            source.attackEntityFrom(DamageSource.causeMobDamage(living), 8.0F);
            double dx = source.posX - living.posX;
            double dy = source.posY + 1.0D - living.posY;
            double dz = source.posZ - living.posZ;
            double velocityFactor = 2.5D / Math.sqrt(dx * dx + dy * dy + dz * dz);
            source.motionX = velocityFactor * dx;
            source.motionY = velocityFactor * dy;
            source.motionZ = velocityFactor * dz;
        }
    }
}
