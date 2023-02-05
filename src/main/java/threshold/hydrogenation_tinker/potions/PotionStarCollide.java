package threshold.hydrogenation_tinker.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.Entity.EntityMarbleBlock;

public class PotionStarCollide extends Potion {
    public PotionStarCollide(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(
            priority = EventPriority.HIGHEST
    )
    public void livingFall(LivingFallEvent event) {
        EntityLivingBase attacker = event.getEntityLiving();
        World world = attacker.getEntityWorld();
        if (attacker.isPotionActive(this)) {
            for(int i = 0; i < 10; ++i) {
                Vec3d vec3d = attacker.getLook(1.0F);
                EntityMarbleBlock block = new EntityMarbleBlock(world,
                        attacker.posX + vec3d.x * 2.0D * (double)(i + 1),
                        attacker.posY + (double)attacker.getEyeHeight(),
                        attacker.posZ + vec3d.z * 2.0D * (double)(i + 1), 10.0F, attacker);
                world.spawnEntity(block);
            }

            event.setCanceled(true);
            attacker.removePotionEffect(this);
        }

    }
}
