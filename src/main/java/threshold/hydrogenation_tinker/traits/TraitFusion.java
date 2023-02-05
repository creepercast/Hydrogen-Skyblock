package threshold.hydrogenation_tinker.traits;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;
import threshold.hydrogenation_tinker.Entity.EntityRay;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;

public class TraitFusion extends ModifierTrait {
    public TraitFusion() {
        super("fusion", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        ItemStack usingItem;
        if (entity instanceof EntityPlayerSP) {
            EntityPlayerSP playerSP = (EntityPlayerSP)entity;
            usingItem = playerSP.getHeldItemMainhand();
            if (!usingItem.isEmpty() && usingItem.getItem() == TinkerRangedWeapons.longBow) {
                MovementInput var10000 = playerSP.movementInput;
                var10000.moveForward *= 4.0F;
                var10000.moveStrafe *= 4.0F;
            }
        }
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            PotionEffect note = player.getActivePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
            if(note != null) {
                if (note.getAmplifier() < 40) {
                    player.getEntityWorld().spawnParticle(
                            EnumParticleTypes.PORTAL,
                            player.posX + Math.random() - 0.5,
                            player.posY + player.getEyeHeight()+ Math.random() - 0.5,
                            player.posZ + Math.random() - 0.5,
                            0, -0.5, 0
                    );
                }
            }
        }
        if (!world.isRemote) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                usingItem = player.getActiveItemStack();
                if (!usingItem.isEmpty()
                        && usingItem.equals(tool)
                        && usingItem.getItem() == TinkerRangedWeapons.longBow
                        && player.getItemInUseCount() <= 71940
                ) {
                    PotionEffect note = player.getActivePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
                    if(note == null) {
                        player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.USING_NOTE, 8, 0));
                    }else {
                        if (note.getAmplifier() < 40) {
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.USING_NOTE, 8, note.getAmplifier() + 1));
                        }else{
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.USING_NOTE, 8, 40));
                        }
                    }
                }
                PotionEffect note = player.getActivePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
                if (note != null) {
                    if (note.getDuration() < 4) {
                        player.removePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
                        int length = note.getAmplifier();
                        Vec3d look = player.getLookVec();
                        player.getEntityWorld().spawnEntity(new EntityRay(
                                player.getEntityWorld(),
                                player.posX,player.posY+player.getEyeHeight(),player.posZ,
                                look.x * 5,look.y * 5,look.z * 5,
                                 ProjectileLauncherNBT.from(tool).bonusDamage * (length/10f),player
                        ));
                    }
                }
            }
        }
    }
}
