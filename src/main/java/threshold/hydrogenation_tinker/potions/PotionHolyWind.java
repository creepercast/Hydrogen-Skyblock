package threshold.hydrogenation_tinker.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PotionHolyWind extends Potion {
    public PotionHolyWind(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        if(!event.getEntityLiving().isPotionActive(this)) return;
        EntityLivingBase owner = event.getEntityLiving();
        int amplifier = Objects.requireNonNull(event.getEntityLiving().getActivePotionEffect(this)).getAmplifier();
        Utils.clean_debuff(owner);
        if(amplifier <= 0){
            return;
        }
        World world = owner.getEntityWorld();
        for (int i = 0; i < 40; i++) {
            double x = owner.posX + amplifier * Math.cos(Math.toRadians(i*9));
            double y = owner.posY + Math.random() * 3;
            double z = owner.posZ + amplifier * Math.sin(Math.toRadians(i*9));
            double angle = i*9 + 115 + Math.random() * 10;
            double dx = Math.cos(Math.toRadians(angle));
            double dy = Math.random() * 0.5 - 0.25;
            double dz = Math.sin(Math.toRadians(angle));
            world.spawnParticle(
                    EnumParticleTypes.SNOW_SHOVEL,
                    x,y,z,
                    dx,dy,dz
            );
        }
        if(owner.getEntityWorld().isRemote){
            return;
        }
        List<EntityLivingBase> targets = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(
                owner.posX - amplifier, owner.posY - amplifier, owner.posZ - amplifier,
                owner.posX + amplifier, owner.posY + amplifier, owner.posZ + amplifier
        ), input -> input.getDistance(owner) < amplifier && input != owner);
        for (EntityLivingBase target:targets) {
            if(target instanceof EntityPlayer && owner instanceof EntityPlayer){
                Utils.clean_debuff(target);
            }else if(target != null){
                List<Potion> removes = new ArrayList<>();
                for (PotionEffect effect:target.getActivePotionEffects()) {
                    if(effect.getPotion().isBeneficial()) removes.add(effect.getPotion());
                }
                for (Potion remove:removes) {
                    target.removePotionEffect(remove);
                }
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,20,1));
                if(target.hurtResistantTime == 0) {
                    target.attackEntityFrom(DamageSource.causeMobDamage(owner).setMagicDamage(), 1.0f);
                }
            }
        }
    }
}
