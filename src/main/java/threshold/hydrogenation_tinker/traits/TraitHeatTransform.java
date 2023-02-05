package threshold.hydrogenation_tinker.traits;

import blusunrize.immersiveengineering.common.IEContent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;

import java.util.ArrayList;
import java.util.List;


public class TraitHeatTransform extends ModifierTrait {
    public static final List<Item> targets = new ArrayList<>();
    static {
        targets.add(ItemBlock.getItemFromBlock(Blocks.LOG));
        targets.add(ItemBlock.getItemFromBlock(Blocks.LOG2));
        targets.add(ItemBlock.getItemFromBlock(Blocks.CACTUS));
        targets.add(ItemBlock.getItemFromBlock(Blocks.REEDS));
        targets.add(ItemBlock.getItemFromBlock(Blocks.HAY_BLOCK));
        targets.add(Items.COAL);
    }
    public TraitHeatTransform() {
        super("heat_transform", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onBlockDrops(HarvestDropsEvent event){
        EntityPlayer player = event.getHarvester();
        if(player != null && isToolWithTrait(player.getHeldItemMainhand())){
            List<ItemStack> drops = event.getDrops();
            for(int index=0;index<drops.size();index++){
                if(drops.get(index).getItem() == ItemBlock.getItemFromBlock(Blocks.COAL_BLOCK)){
                    drops.set(index,ItemStack.EMPTY);
                }
                if(targets.contains(drops.get(index).getItem())){
                    event.getDrops().set(
                            index,
                            new ItemStack(
                                    IEContent.itemMaterial,
                                    drops.get(index).getCount(),
                                    6
                            )
                    );
                }
            }
        }
    }
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        Entity attacker =  event.getSource().getTrueSource();
        if(attacker instanceof EntityPlayer && isToolWithTrait(((EntityPlayer)attacker).getHeldItemMainhand())){
            List<EntityItem> drops = event.getDrops();
            for(int index=0;index<drops.size();index++){
                if(targets.contains(drops.get(index).getItem().getItem())){
                    event.getDrops().get(index).setItem(new ItemStack(
                            IEContent.itemMaterial,
                            drops.get(index).getItem().getCount(),
                            6
                    ));
                }
            }
        }
    }
}
