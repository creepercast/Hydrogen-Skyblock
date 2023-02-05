package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.network.PacketMotions;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.List;

public class TraitSilence extends ModifierTrait {
    public TraitSilence() {
        super("silence", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        World world = event.getEntityLiving().getEntityWorld();
        EntityLivingBase entity = event.getEntityLiving();
        if (!world.isRemote) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) entity;
                if (player.isHandActive() && Utils.getLevel(player.getHeldItemMainhand(),this) > 0) {
                    player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,20,3));
                }
                if(player.isPotionActive(Hydrogenation_tinker.Potions.SILENCE_NOTE)){
                    PotionEffect effect = player.getActivePotionEffect(Hydrogenation_tinker.Potions.SILENCE_NOTE);
                    assert effect != null;
                    int level = effect.getAmplifier();
                    List<Entity> targets = player.world.getEntitiesWithinAABB(Entity.class,new AxisAlignedBB(
                            player.posX - 5,player.posY - 5,player.posZ - 5,
                            player.posX + 5,player.posY + 5,player.posZ + 5
                    ));
                    for (Entity target:targets) {
                        if(target instanceof IProjectile){
                            player.sendMessage(new TextComponentString("Projectile"));
                            target.motionX = target.motionX * (1+0.1*level);
                            target.motionY = player.getLookVec().y * Math.abs(target.motionY) * (1+0.1*level);
                            target.motionZ = target.motionZ * (1+0.1*level);
                            WorldServer server = ((WorldServer) entity.world);
                            PacketMotions motion = new PacketMotions(
                                    target.getEntityId(),
                                    target.posX,target.posY,target.posZ,
                                    target.motionX,
                                    target.motionY,
                                    target.motionZ
                            );
                            for (EntityPlayer viewer : server.getEntityTracker().getTrackingPlayers(target)) {
                                if (viewer instanceof EntityPlayerMP)
                                    Hydrogenation_tinker.network.sendTo(motion, (EntityPlayerMP) viewer);
                            }
                        }
                    }
                    player.removePotionEffect(Hydrogenation_tinker.Potions.SILENCE_NOTE);
                }
            }
        }
    }
    @SubscribeEvent
    public void onStoppedUsing(LivingEntityUseItemEvent.Stop event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0){
            EntityLivingBase user = event.getEntityLiving();
            user.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.SILENCE_NOTE,10,level));
        }
    }
}
