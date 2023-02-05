package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.List;

public class TraitMinor extends AbstractTrait {
    public TraitMinor() {
        super("minor", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase user = event.getEntityLiving();
        if(Utils.getTotalLevel(user,this) == 0) return;
        List<Entity> entities= user.getEntityWorld().getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(
            user.posX - 5,user.posY - 5,user.posZ - 5,
            user.posX + 5,user.posY + 5,user.posZ + 5
        ), input -> {
            if(input != null) {
                return input.getDistance(user) < 5;
            }else{
                return false;
            }
        });
        for(Entity target:entities){
            if(target instanceof EntityLivingBase) continue;//所以空投吊舱什么的有生命的弹射物没法免。。。
            double distance1 = target.getPositionVector().distanceTo(user.getPositionVector());
            Vec3d position2 = target.getPositionVector().add(target.motionX,target.motionY,target.motionZ);
            double distance2 = position2.distanceTo(user.getPositionVector());
            if(distance2 + 0.05 > distance1){
                continue;
            }
            Vec3d looking = user.getLookVec();
            target.setPosition(
                    user.posX + looking.x,
                    user.posY + user.getEyeHeight() + looking.y,
                    user.posZ + looking.z
            );
            target.setVelocity(
                    looking.x * 5,
                    looking.y * 5,
                    looking.z * 5
            );
        }
    }

}
