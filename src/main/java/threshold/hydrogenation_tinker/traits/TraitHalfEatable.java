package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.traits.AbstractTrait;

public class TraitHalfEatable extends AbstractTrait {
    public TraitHalfEatable() {
        super("half_eatable", TextFormatting.AQUA);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        if(!(entity instanceof EntityLivingBase && isSelected))return;
        EntityLivingBase user = (EntityLivingBase) entity;
        user.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,20,0));
        user.addPotionEffect(new PotionEffect(MobEffects.SPEED,20,0));
        user.addPotionEffect(new PotionEffect(MobEffects.SATURATION,20,0));
    }
}
