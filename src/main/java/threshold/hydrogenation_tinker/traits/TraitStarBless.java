package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;

public class TraitStarBless extends ModifierTrait {
    public TraitStarBless() {
        super("star_bless", 0xff000000);
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof EntityLivingBase) {
            if(!world.isDaytime() && random.nextInt(100) < 20){
                ToolHelper.healTool(tool,1, (EntityLivingBase) entity);
            }
        }
    }
}
