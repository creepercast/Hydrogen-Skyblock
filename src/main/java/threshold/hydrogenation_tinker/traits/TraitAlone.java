package threshold.hydrogenation_tinker.traits;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovementInput;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.UUID;

public class TraitAlone extends AbstractTrait {
    public static final UUID target = UUID.fromString("4dd40d86-656f-4cb6-ac94-10509e7156b3");
    public TraitAlone() {
        super("alone", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getLevel(event.getEntityLiving().getHeldItemMainhand(),this);
        if(level > 0){
            int armor = 0;
            for (ItemStack stack:event.getEntityLiving().getArmorInventoryList()) {
                if(!stack.isEmpty()) armor += 1;
            }
            if(event.getSource().isExplosion()){
                event.setAmount(event.getAmount() * (0.4f+0.15f*armor));
            }
            if(event.getSource().isFireDamage()){
                event.setAmount(event.getAmount() * (0.4f+0.15f*armor));
            }
        }
        if(!(event.getSource().getTrueSource() instanceof EntityLivingBase)) return;
        EntityLivingBase attacker = (EntityLivingBase) event.getSource().getTrueSource();
        level = Utils.getLevel(
                attacker.getHeldItemMainhand(),
                this
        );
        if(level > 0){
            int armor = 0;
            for (ItemStack stack:attacker.getArmorInventoryList()) {
                if(!stack.isEmpty()) armor += 1;
            }
            event.setAmount(event.getAmount() - 0.1f*armor);
        }
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(!(entity instanceof EntityLivingBase)) return;
        EntityLivingBase user = (EntityLivingBase) entity;
        int armor = 0;
        for (ItemStack stack:user.getArmorInventoryList()) {
            if(!stack.isEmpty()) armor += 1;
        }
        AttributeModifier modifier = new AttributeModifier(
                target,
                "knock_back",
                1 - 0.25*armor, 0
        );
        if(isSelected) {
            if(user.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getModifier(target) == null) {
                user.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).applyModifier(modifier);
            }
        }else {
            if(user.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).getModifier(target) != null) {
                user.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).removeModifier(target);
            }
        }
        if (user instanceof EntityPlayerSP) {
            EntityPlayerSP playerSP = (EntityPlayerSP) user;
            ItemStack usingItem;
            usingItem = playerSP.getHeldItemMainhand();
            if (isToolWithTrait(usingItem)) {
                MovementInput var10000 = playerSP.movementInput;
                var10000.moveForward *= 1.0f-0.1f*armor;
                var10000.moveStrafe *= 1.0f-0.1f*armor;
            }
        }

    }
}
