package threshold.hydrogenation_tinker.traits;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovementInput;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.melee.TinkerMeleeWeapons;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;
import threshold.hydrogenation_tinker.Entity.EntityEnvironmentalRay;
import threshold.hydrogenation_tinker.Entity.EntityMarbleBlock;
import threshold.hydrogenation_tinker.Entity.EntityRay;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.network.PacketEnvironmentalSwing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TraitEnvironmental extends ModifierTrait {
    public TraitEnvironmental() {
        super("environmental", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public static float getState(ItemStack stack) {
        if (stack == null) {
            return 0.0F;
        } else {
            NBTTagCompound nbt = stack.getTagCompound();
            if (nbt == null) {
                nbt = new NBTTagCompound();
                stack.setTagCompound(nbt);
                nbt.setFloat("conditional_blade_state", 0.0F);
            }

            return nbt.getFloat("conditional_blade_state");
        }
    }

    public static void setState(ItemStack stack, float state) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
            nbt.setFloat("conditional_blade_state", 0.0F);
        }

        nbt.setFloat("conditional_blade_state", state);
    }
    public void printHint(EntityPlayer player){
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_1")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_2")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_3")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_4")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_5")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_6")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_7")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_8")));
        player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.environmental_9")));
    }
    @SubscribeEvent
    public void onMouseClick(LeftClickEmpty event) {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack stack = player.getHeldItemMainhand();
        if (this.isToolWithTrait(stack)) {
            Hydrogenation_tinker.network.sendToServer(new PacketEnvironmentalSwing(getState(stack)));
        }

    }

    @SubscribeEvent
    public void onInteractBlock(RightClickBlock event) {
        ItemStack item = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();
        if (player.isSneaking() && this.isToolWithTrait(item) && !ToolHelper.isBroken(item)) {
            player.swingArm(event.getHand());
            if (world.getBlockState(pos).getBlock() == Blocks.GRASS) {
                setState(item, 0.1F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.SNOW) {
                setState(item, 0.2F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.SAND) {
                setState(item, 0.3F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.DEADBUSH) {
                setState(item, 0.4F);
                return;
            }

            if (pos.getY() > 180) {
                setState(item, 0.5F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.END_STONE) {
                setState(item, 0.6F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.ICE) {
                setState(item, 0.7F);
                return;
            }

            if (world.getBlockState(pos).getBlock() == Blocks.LEAVES) {
                setState(item, 0.8F);
                return;
            }
            printHint(player);
            setState(item, 0.0F);
        }

    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        ItemStack usingItem;
        if (entity instanceof EntityPlayerSP) {
            EntityPlayerSP playerSP = (EntityPlayerSP)entity;
            usingItem = playerSP.getHeldItemMainhand();
            if (!usingItem.isEmpty() && usingItem.getItem() == TinkerMeleeWeapons.battleSign && usingItem.equals(tool) && getState(tool) == 0.6F) {
                MovementInput var10000 = playerSP.movementInput;
                var10000.moveForward *= 4.0F;
                var10000.moveStrafe *= 4.0F;
            }
        }

        if (!world.isRemote) {
            if (entity instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)entity;
                usingItem = player.getActiveItemStack();
                int using = -1;
                if (!usingItem.isEmpty() && usingItem.equals(tool)) {
                    if (usingItem.getItem() == TinkerMeleeWeapons.longSword) {
                        using = 1;
                    }

                    if (usingItem.getItem() == TinkerMeleeWeapons.battleSign) {
                        using = 2;
                    }

                    if (usingItem.getItem() == TinkerRangedWeapons.longBow) {
                        using = 3;
                    }

                    player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.USING_NOTE, 8, using));
                }

                if (Math.random() < 0.05D && ToolHelper.getCurrentDurability(tool) < ToolHelper.getMaxDurability(tool) && getState(tool) == 0.1F) {
                    ToolHelper.healTool(tool, 1, player);
                }

                if (isSelected) {
                    if (getState(tool) == 0.1F) {
                        List<Potion> removes = new ArrayList<>();
                        for (PotionEffect effect:player.getActivePotionEffects()) {
                            if(effect.getPotion().isBadEffect()) removes.add(effect.getPotion());
                        }
                        for (Potion remove:removes) {
                            player.removePotionEffect(remove);
                        }
                    } else if (getState(tool) == 0.3F) {
                        player.addPotionEffect(new PotionEffect(MobEffects.HASTE, 10, 1));
                    } else if (getState(tool) == 0.4F) {
                        if (player.isSprinting()) {
                            player.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 10, 1));
                            player.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 10, 1));
                        }
                    } else if (getState(tool) == 0.5F) {
                        if (player.getItemInUseCount() % 5 == 1 && (using == 1 || using == 3)) {
                            Vec3d direction = player.getLookVec();
                            EntityRay ray = new EntityEnvironmentalRay(player.getEntityWorld(),
                                    player.posX, player.posY + (double)player.getEyeHeight(), player.posZ,
                                    direction.x * 2.0D, direction.y * 2.0D, direction.z * 2.0D,
                                    0.1F * ToolHelper.getActualAttack(tool), player,0.5f
                            );
                            player.getEntityWorld().spawnEntity(ray);
                        }
                    } else if (getState(tool) == 0.7F) {
                        player.addPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 10, 1));
                    } else if (getState(tool) == 0.8F) {
                        player.addPotionEffect(new PotionEffect(MobEffects.SPEED, 10, 1));
                    }
                }
                PotionEffect note = player.getActivePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
                if (note != null) {
                    if (note.getDuration() < 4) {
                        player.removePotionEffect(Hydrogenation_tinker.Potions.USING_NOTE);
                        if (getState(tool) == 0.4F && note.getAmplifier() == 1) {
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.COLLIDE_ATTACK, 10));
                        }
                        Vec3d direction = player.getLookVec();
                        if (getState(tool) == 0.5F && note.getAmplifier() == 2) {
                            player.getEntityWorld().spawnEntity(
                                    new EntityEnvironmentalRay(player.getEntityWorld(),
                                            player.posX, player.posY + (double) player.getEyeHeight(), player.posZ,
                                            direction.x * 2.0D, direction.y * 2.0D, direction.z * 2.0D,
                                            ToolHelper.getActualAttack(tool), player, 0.5f
                                    )
                            );
                        }

                        if (getState(tool) == 0.6F && note.getAmplifier() == 1) {
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.STAR_COLLIDE, 30));
                        }

                        if (getState(tool) == 0.6F && note.getAmplifier() == 3) {
                            for (int i = 1; i < 10; ++i) {
                                EntityMarbleBlock block = new EntityMarbleBlock(world, player.posX + Math.random() * 5.0D - 2.5D, player.posY + Math.random() * 5.0D + 2.5D, player.posZ + Math.random() * 5.0D - 2.5D, 10.0F, player);
                                block.motionX = direction.x * 2.0D;
                                block.motionZ = direction.z * 2.0D;
                                world.spawnEntity(block);
                            }
                        }
                    }
                }
            }
        }
    }

    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        boolean sprinting = player.getActivePotionEffect(MobEffects.STRENGTH) != null && Objects.requireNonNull(player.getActivePotionEffect(MobEffects.STRENGTH)).getDuration() < 20;
        if ((double)getState(tool) == 0.4D) {
            double dx = target.posX - player.posX;
            double dy = target.posY + 1.0D - player.posY;
            double dz = target.posZ - player.posZ;
            double velocityFactor = 5.0D / Math.sqrt(dx * dx + dy * dy + dz * dz);
            target.motionX = velocityFactor * dx;
            target.motionY = velocityFactor * dy;
            target.motionZ = velocityFactor * dz;
        }

        if (player instanceof EntityPlayer && !player.getEntityWorld().isRemote) {
            if (getState(tool) == 0.2F) {
                if (target.getActivePotionEffect(MobEffects.SLOWNESS) != null) {
                    target.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 100, 128));
                    target.hurtResistantTime = 0;
                    target.attackEntityFrom(DamageSource.causeMobDamage(player).setDamageBypassesArmor().setMagicDamage(), 2.0F);
                }

                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0));
            }

            if (getState(tool) == 0.3F && target.isBurning()) {
                target.attackEntityFrom(DamageSource.causeMobDamage(player).setFireDamage(), ToolHelper.getActualAttack(tool));
            }

            if (getState(tool) == 0.6F) {
                if (target instanceof EntityEndermite || target instanceof EntityEnderman || target instanceof EntityShulker) {
                    return;
                }
                target.hurtResistantTime = 0;
                player.getEntityWorld().spawnEntity(new EntityMarbleBlock(
                        player.getEntityWorld(),
                        target.posX,target.posY,target.posZ,
                        8.0f,player
                ));
            } else if (getState(tool) == 0.4F) {
                if (!sprinting) {
                    return;
                }

                PotionEffect glowing = player.getActivePotionEffect(MobEffects.GLOWING);
                if (glowing == null) {
                    player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, 0));
                } else if (glowing.getAmplifier() == 3) {
                    target.hurtResistantTime = 0;
                    target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), ToolHelper.getActualDamage(tool, player) * 3.0F);
                    player.removePotionEffect(MobEffects.GLOWING);
                    player.heal(4.0F);
                } else {
                    player.addPotionEffect(new PotionEffect(MobEffects.GLOWING, 100, Math.min(glowing.getAmplifier() + 1, 3)));
                }
            }

        }
    }


}
