package threshold.hydrogenation_tinker.traits;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.library.utils.ToolHelper;
import threshold.hydrogenation_tinker.Entity.EntityMarbleBlock;

import java.util.ArrayList;
import java.util.List;

public class TraitEnvironmentalBless extends ModifierTrait {
    public TraitEnvironmentalBless() {
        super("environmental_bless", 6536564);
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
                nbt.setFloat("conditional_blade_state_assistant", 0.0F);
            }

            return nbt.getFloat("conditional_blade_state_assistant");
        }
    }
    public static void resetEnchant(ItemStack stack){
        if(stack.getTagCompound() == null) stack.setTagCompound(new NBTTagCompound());
        NBTTagList enchantments = stack.getTagCompound().getTagList("ench", 10);
        NBTTagList enchantments2 = new NBTTagList();
        for(int i = 0; i < enchantments.tagCount(); ++i) {
            if (enchantments.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.SILK_TOUCH)) {
                continue;
            }
            if (enchantments.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.LOOTING)) {
                continue;
            }
            if (enchantments.getCompoundTagAt(i).getShort("id") == Enchantment.getEnchantmentID(Enchantments.FORTUNE)) {
                continue;
            }
            enchantments2.appendTag(enchantments.getCompoundTagAt(i));
        }

        stack.getTagCompound().setTag("ench", enchantments2);
    }
    public static void setState(ItemStack stack, float state) {
        NBTTagCompound nbt = stack.getTagCompound();
        if (nbt == null) {
            nbt = new NBTTagCompound();
            stack.setTagCompound(nbt);
            nbt.setFloat("conditional_blade_state_assistant", 0.0F);
        }

        nbt.setFloat("conditional_blade_state_assistant", state);
    }
    @SubscribeEvent
    public void onInteractBlock(RightClickBlock event) {
        ItemStack tool = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        BlockPos pos = event.getPos();

        if (player.isSneaking() && this.isToolWithTrait(tool) && !ToolHelper.isBroken(tool)) {
            player.swingArm(event.getHand());
            if (world.getBlockState(pos).getBlock() == Blocks.GRASS || world.getBlockState(pos).getBlock() == Blocks.SNOW) {
                setState(tool, 0.1F);
            } else if (world.getBlockState(pos).getBlock() == Blocks.SAND || world.getBlockState(pos).getBlock() == Blocks.DEADBUSH) {
                setState(tool, 0.2F);
            } else if (pos.getY() > 180 || world.getBlockState(pos).getBlock() == Blocks.END_STONE) {
                setState(tool, 0.3F);
            }else if (world.getBlockState(pos).getBlock() == Blocks.ICE || world.getBlockState(pos).getBlock() == Blocks.LEAVES) {
                setState(tool, 0.4F);
            }else {
                setState(tool, 0.0F);
            }
            resetEnchant(tool);
            if(getState(tool) == 0.1f){
                if(tool.getTagCompound() == null) tool.setTagCompound(new NBTTagCompound());
                ToolBuilder.addEnchantment(tool.getTagCompound(), Enchantments.SILK_TOUCH);
            }
            if(getState(tool) == 0.3f){
                if(tool.getTagCompound() == null) tool.setTagCompound(new NBTTagCompound());
                for (int i = 0; i < 3; i++) {
                    ToolBuilder.addEnchantment(tool.getTagCompound(), Enchantments.FORTUNE);
                    ToolBuilder.addEnchantment(tool.getTagCompound(), Enchantments.LOOTING);
                }

            }
        }

    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof EntityLivingBase) {
            if (Math.random() < 0.05D && ToolHelper.getCurrentDurability(tool) < ToolHelper.getMaxDurability(tool) && getState(tool) == 0.4F) {
                ToolHelper.healTool(tool, 1, (EntityLivingBase) entity);
            }
        }
    }
    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        if (player instanceof EntityPlayer && !player.getEntityWorld().isRemote) {
            if (getState(tool) == 0.1F) {
                target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 0));
                Potion remove = null;
                for (PotionEffect effect:target.getActivePotionEffects()) {
                    if(effect.getPotion().isBeneficial()){
                        remove = effect.getPotion();
                        break;
                    }
                }
                if(remove != null){
                    target.removePotionEffect(remove);
                }
            }

            if (getState(tool) == 0.2F) {
                target.setFire(3);
                return;
            }
            if (getState(tool) == 0.3F) {
                if (target.getHealth() < target.getMaxHealth()) {
                    return;
                }
                target.hurtResistantTime = 0;
                player.getEntityWorld().spawnEntity(new EntityMarbleBlock(
                        player.getEntityWorld(),
                        target.posX,target.posY,target.posZ,
                        ToolHelper.getActualAttack(tool),player
                ));
            }

        }
    }
    @SubscribeEvent
    public void onDamage(LivingHurtEvent event){
        Entity target = event.getSource().getTrueSource();
        EntityLivingBase player = event.getEntityLiving();
        if(isToolWithTrait(player.getHeldItemMainhand()) && getState(player.getHeldItemMainhand()) == 0.2f){
            if(target == null) return;
            target.setFire(10);
        }
    }
    @Override
    public int onToolDamage(ItemStack tool, int damage, int newDamage, EntityLivingBase entity) {

        if(getState(tool) == 0.2f) {
            entity.heal(1);
        }

        return newDamage;
    }
}
