package threshold.hydrogenation_tinker.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionForeverFire extends Potion {
    public PotionForeverFire(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        if(event.getEntityLiving().getEntityWorld().isRemote) return;
        if(!event.getEntityLiving().isPotionActive(this)) return;
        EntityLivingBase owner = event.getEntityLiving();
        World  world = owner.getEntityWorld();
        owner.setFire(1);
        if(owner.isPotionActive(MobEffects.FIRE_RESISTANCE)) owner.removePotionEffect(MobEffects.FIRE_RESISTANCE);
        if(owner.isInWater()){
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        if(world.getBlockState(owner.getPosition().add(x, y, z)).getBlock() == Blocks.WATER) {
                            world.setBlockToAir(owner.getPosition().add(x, y, z));
                        }
                    }
                }
            }
        }
        if(owner.getHeldItemMainhand().getItem() == Items.MILK_BUCKET){
            owner.setHeldItem(EnumHand.MAIN_HAND,new ItemStack(Items.BUCKET));
        }
        if(owner.getHeldItemOffhand().getItem() == Items.MILK_BUCKET){
            owner.setHeldItem(EnumHand.OFF_HAND,new ItemStack(Items.BUCKET));
        }
    }
}
