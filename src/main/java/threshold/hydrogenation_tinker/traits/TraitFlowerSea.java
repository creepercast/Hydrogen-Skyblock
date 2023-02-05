package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.Keys;
import threshold.hydrogenation_tinker.network.PacketFlowerSea;

public class TraitFlowerSea extends ModifierTrait {
    public TraitFlowerSea() {
        super("flower_sea", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onMouseClick(LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (this.isToolWithTrait(stack)) {
            if(player.isPotionActive(Hydrogenation_tinker.Potions.FLOWER_SEA_ACTIVE)) {
                Hydrogenation_tinker.network.sendToServer(new PacketFlowerSea(1));
            }else {
                if (player.isPotionActive(Hydrogenation_tinker.Potions.SPRINT_CHARGE)) {
                    PotionEffect effect = player.getActivePotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE);
                    assert effect != null;
                    if (effect.getAmplifier() == 3) {
                        player.setPosition(
                                player.posX + player.getLookVec().x * 3,
                                player.posY,
                                player.posZ + player.getLookVec().z * 3
                        );
                    }
                }
                Hydrogenation_tinker.network.sendToServer(new PacketFlowerSea(0));
            }
        }
    }
    @SubscribeEvent
    public void onAttack(AttackEntityEvent event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (this.isToolWithTrait(stack)) {
            if(player.isPotionActive(Hydrogenation_tinker.Potions.FLOWER_SEA_ACTIVE)) {
                Hydrogenation_tinker.network.sendToServer(new PacketFlowerSea(1));
            }else {
                if (player.isPotionActive(Hydrogenation_tinker.Potions.SPRINT_CHARGE)) {
                    PotionEffect effect = player.getActivePotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE);
                    assert effect != null;
                    if (effect.getAmplifier() == 3) {
                        player.setPosition(
                                player.posX + player.getLookVec().x * 3,
                                player.posY,
                                player.posZ + player.getLookVec().z * 3
                        );
                        event.getTarget().hurtResistantTime = 0;
                    }
                }
                if(!player.getEntityWorld().isRemote) {
                    PacketFlowerSea.Handler.normal_effect(player.getEntityWorld(), player, player.getLookVec());
                }
            }
        }
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(!world.isRemote) return;
        if(!(entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) entity;
        if(Keys.flower_sea.isKeyDown()
                && !(player.isPotionActive(Hydrogenation_tinker.Potions.FLOWER_SEA_COOL_DOWN))
                && isToolWithTrait(player.getHeldItemMainhand())
                && player.getHealth()-1 > player.getMaxHealth() / 2
        ){
            Hydrogenation_tinker.network.sendToServer(new PacketFlowerSea(2));
        }
    }
}
