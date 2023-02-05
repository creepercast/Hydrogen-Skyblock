package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.Objects;

public class TraitCureWind extends ModifierTrait {
    public TraitCureWind() {
        super("cure_wind", 65365645,5,0);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        entity.heal(1f);
        return newDamage;
    }
    @SubscribeEvent
    public void onInteractBlock(PlayerInteractEvent.RightClickBlock event) {
        if(Utils.getLevel(event.getEntityPlayer().getHeldItemMainhand(),this) > 0){
            EntityLivingBase player = event.getEntityPlayer();
            if(!player.isSneaking()) return;
            player.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,160,5));
            player.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,160,150));
            player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,160,5));
        }
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase player = event.getEntityLiving();
        if (Utils.getTotalArmorLevel(player,this) > 0) {
            int level = 1;
            if(player.isPotionActive(MobEffects.SLOWNESS)){
                level += Objects.requireNonNull(player.getActivePotionEffect(MobEffects.SLOWNESS)).getAmplifier() + 1;
            }
            level *= Utils.getTotalArmorLevel(player,this);
            player.heal(level/40f);
        }
    }
}
