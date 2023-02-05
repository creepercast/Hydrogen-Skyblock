package threshold.hydrogenation_tinker.traits;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.library.utils.ToolHelper;

import java.util.HashMap;
import java.util.Map;

public class TraitHeatAbsorb extends ModifierTrait {
    public static final Map<Block,Block> TARGETS = new HashMap<>();
    static {
        TARGETS.put(Blocks.LAVA,Blocks.OBSIDIAN);
        TARGETS.put(Blocks.FLOWING_LAVA,Blocks.AIR);
        TARGETS.put(Blocks.WATER,Blocks.ICE);
        TARGETS.put(Blocks.FLOWING_WATER,Blocks.ICE);
        TARGETS.put(Blocks.FIRE,Blocks.AIR);
    }
    public TraitHeatAbsorb() {
        super("heat_absorb", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onInteractBlock(RightClickBlock event) {
        ItemStack item = event.getItemStack();
        EntityPlayer player = event.getEntityPlayer();
        World world = event.getWorld();
        if (this.isToolWithTrait(item) && !ToolHelper.isBroken(item)) {
            player.swingArm(event.getHand());
            for (int x = -1; x < 2; x++) {
                for (int y = -1; y < 2; y++) {
                    for (int z = -1; z < 2; z++) {
                        if(TARGETS.containsKey(world.getBlockState(player.getPosition().add(x, y, z)).getBlock())) {
                            world.setBlockState(
                                    player.getPosition().add(x, y, z),
                                    TARGETS.get(world.getBlockState(player.getPosition().add(x, y, z)).getBlock()).getDefaultState()
                            );
                        }
                    }
                }
            }
            if(item.getTagCompound() == null){
                item.setTagCompound(new NBTTagCompound());
            }
            ToolBuilder.addEnchantment(item.getTagCompound(), Enchantments.FIRE_ASPECT);
        }
    }
    @Override
    public void onUpdate(ItemStack tool, World world, Entity entity, int itemSlot, boolean isSelected) {
        if(isSelected && entity.isBurning()){
            entity.setFire(-1);
        }
    }
}
