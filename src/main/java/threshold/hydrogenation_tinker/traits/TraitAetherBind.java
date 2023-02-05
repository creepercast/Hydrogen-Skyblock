package threshold.hydrogenation_tinker.traits;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.HydrogenationConfig;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.List;

public class TraitAetherBind extends ModifierTrait {
    public static final int EFFECT_DIMENSION = 4;
    public TraitAetherBind() {
        super("aether_bind", 0xffaaaaff);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {
        if(entity.dimension == EFFECT_DIMENSION) {
            return 0;
        }
        return super.onToolDamage(tool, damage, newDamage, entity);
    }

    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        if(player.dimension == EFFECT_DIMENSION) {
            return super.damage(tool, player, target, damage, newDamage * 1.8f, isCritical);
        }else{
            return super.damage(tool, player, target, damage, newDamage * 0.9f, isCritical);
        }
    }

    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        if(player.dimension == EFFECT_DIMENSION) {
            super.afterHit(tool, player, target, damageDealt, wasCritical, wasHit);
            target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 5, 40));
        }
    }
    @SubscribeEvent
    public void onToolTipUpdate(ItemTooltipEvent event){
        if(HydrogenationConfig.joking && isToolWithTrait(event.getItemStack())) {
            List<String> list = event.getToolTip();
            list.add(Utils.Colored(I18n.format("hydrogenation_tinker.tips.bound1")));
            if(!(event.getEntityPlayer() != null && event.getEntityPlayer().getName().equals("Aether"))) {
                list.add(Utils.Colored(I18n.format("hydrogenation_tinker.tips.bound2")));
            }
        }
    }

    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        if(HydrogenationConfig.joking){
            if(entity instanceof EntityLivingBase)
            if(entity.hurtResistantTime == 0 && !entity.getName().equals("Aether")){
                entity.attackEntityFrom(new DamageSource("aether-relic"),2.0f);
            }
        }

    }
}
