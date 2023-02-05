package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.List;


public class TraitFireOfSacrifice extends ModifierTrait {
    public TraitFireOfSacrifice() {
        super("fire_of_sacrifice", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event){
        int level = Utils.getTotalLevel(event.getEntityLiving(),this);
        if(level > 0){
            if(event.getEntityLiving().getHealth() < event.getEntityLiving().getMaxHealth() * 0.1) {
                event.setAmount(event.getAmount() / 2);
            }
            if(event.getAmount() > event.getEntityLiving().getHealth() * 0.5){
                event.getEntityLiving().setHealth(-100f);
                event.getEntityLiving().onDeath(DamageSource.ANVIL);
            }
        }
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(tool, world, entity, itemSlot, isSelected);
        EntityLivingBase player = (EntityLivingBase) entity;
        if(player.getHealth() > player.getMaxHealth() * 0.1) return;
        Vec3d position = player.getPositionVector();
        AxisAlignedBB axis = new AxisAlignedBB(
                position.x - 10,
                position.y - 10,
                position.z - 10,
                position.x + 10,
                position.y + 10,
                position.z + 10
        );
        List<EntityLivingBase> list = world.getEntitiesWithinAABB(
                EntityLivingBase.class,
                axis, (EntityLivingBase e) ->
                        e != null
                                && e != player
                                && e.getDistance(player)<10f
        );
        for (EntityLivingBase entity2:list) {
            entity2.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.FOREVER_FIRE,100,0));
        }
    }
}
