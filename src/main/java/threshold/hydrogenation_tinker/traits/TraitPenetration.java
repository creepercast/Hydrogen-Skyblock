package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tools.ranged.ProjectileCore;


public class TraitPenetration extends ModifierTrait {
    public TraitPenetration() {
        super("penetration", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public void onAttack(LivingHurtEvent event) {
        if (event.getEntity().world.isRemote) return;
        if (event.getSource() instanceof EntityDamageSourceIndirect
                || event.getSource() instanceof ProjectileCore.DamageSourceProjectileForEndermen) {
            // handle projectiles first
            Entity projectile = event.getSource().getImmediateSource();
            if (projectile instanceof EntityProjectileBase) {
                if (
                        isToolWithTrait(((EntityProjectileBase) projectile).tinkerProjectile.getItemStack())
                        && !event.getSource().equals(DamageSource.OUT_OF_WORLD)
                ) {
                    event.setAmount(0.5f*event.getAmount());
                    event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getAmount());
                }
            }
        }else if (event.getSource() instanceof EntityDamageSource
                && !event.getSource().equals(DamageSource.OUT_OF_WORLD)
                && event.getSource().getTrueSource() instanceof EntityLivingBase
        ) {
            ItemStack stack = ((EntityLivingBase)event.getSource().getTrueSource()).getHeldItemMainhand();
            if (this.isToolWithTrait(stack)) {
                event.setAmount(0.5f*event.getAmount());
                event.getEntityLiving().setHealth(event.getEntityLiving().getHealth() - event.getAmount());
            }
        }
    }
}
