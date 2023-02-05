package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import threshold.hydrogenation_tinker.utils.Utils;

import java.util.UUID;

public class TraitWaterRely extends AbstractArmorTrait {
    public static final UUID target = UUID.fromString("4dd40d86-656f-4cb6-ac94-10509e7156b3");
    public TraitWaterRely() {
        super("water_rely", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntityLiving() == null) return;
        EntityLivingBase user = event.getEntityLiving();
        if(user.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getModifier(target) != null) {
            user.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).removeModifier(target);
        }
        AttributeModifier modifier = new AttributeModifier(
                target,
                "water_rely_health",
                user.isInWater()?user.getMaxHealth() * 0.1:-user.getMaxHealth() * 0.05, 0
        );
        if(Utils.getTotalArmorLevel(user,this) > 0) {
            user.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).applyModifier(modifier);
        }
    }
}
