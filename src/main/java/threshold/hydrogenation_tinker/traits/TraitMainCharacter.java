package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHealEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitMainCharacter extends AbstractArmorTrait {
    public TraitMainCharacter() {
        super("main_character", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        if(!(entity instanceof EntityLivingBase))return;
        EntityLivingBase user = (EntityLivingBase) entity;
        user.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,10,0));
        user.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING,10,0));
        user.addPotionEffect(new PotionEffect(MobEffects.LUCK,10,4));
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0){
            if(Math.random() < 0.1f){
                event.setAmount(0f);
                event.setCanceled(true);
            }
            if(event.getEntityLiving().getHealth() - event.getAmount() < 0 && Math.random() < 0.2f){
                    EntityLivingBase target = event.getEntityLiving();
                    target.heal(5f);
                    if(target instanceof EntityPlayer) {
                        ((EntityPlayer) target).inventory.addItemStackToInventory(
                                new ItemStack(Items.GOLDEN_APPLE, 1, 1)
                        );
                    }
                    target.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,400,1));
                    target.addPotionEffect(new PotionEffect(MobEffects.ABSORPTION,2400,3));
                    target.addPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE,6000,0));
                    target.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,6000,0));

                    event.setCanceled(true);
            }
        }
    }
    @SubscribeEvent
    public void onHeal(LivingHealEvent event){
        int level = Utils.getTotalArmorLevel(event.getEntityLiving(),this);
        if(level > 0) {
            if (Math.random() < 0.1f) {
                event.setAmount(event.getAmount() + 10);
            }
        }
    }

}
