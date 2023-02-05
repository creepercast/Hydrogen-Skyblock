package threshold.hydrogenation_tinker.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolBuilder;
import slimeknights.tconstruct.library.utils.ToolHelper;
import slimeknights.tconstruct.tools.TinkerTraits;
import twilightforest.compat.TConstruct;
import twilightforest.item.TFItems;

import java.util.*;


public class TraitAssociation extends ModifierTrait {
    ITrait[] targets = new AbstractTrait[]{
            TConstruct.precipitate,
            TConstruct.stalwart,
            TConstruct.synergy,
            TConstruct.twilit,
            TinkerTraits.autosmelt,
            TinkerTraits.superheat,
            TinkerTraits.flammable
    };
    public static int TAG_TYPE_STRING = (new NBTTagString()).getId();
    public TraitAssociation() {
        super("association", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    public NBTTagList setupNBT(ItemStack tool){
        if(tool.getTagCompound() == null) tool.setTagCompound(new NBTTagCompound());
        if(tool.getTagCompound().hasKey("association_initial")) return tool.getTagCompound().getTagList("association_initial",TAG_TYPE_STRING);
        NBTTagList stored = new NBTTagList();
        for (ITrait trait:ToolHelper.getTraits(tool)) {
            for (ITrait valid:targets) {
                if(trait==valid){
                    stored.appendTag(new NBTTagString(trait.getIdentifier()));
                    break;
                }
            }
        }
        tool.getTagCompound().setTag("association_initial",stored);
        return stored;
    }
    public Set<ITrait> getTraitShouldApply(EntityPlayer player){
        Set<ITrait> result = new HashSet<>();
        for (ItemStack stack:player.inventory.mainInventory) {
            if(stack.getItem() == TFItems.steeleaf_ingot){
                result.add(TConstruct.synergy);
                result.add(TConstruct.twilit);
            }
            if(stack.getItem() == TFItems.fiery_ingot){
                result.add(TinkerTraits.superheat);
                result.add(TinkerTraits.flammable);
                result.add(TinkerTraits.autosmelt);
                result.add(TConstruct.twilit);
            }
            if(stack.getItem() == TFItems.naga_scale){
                result.add(TConstruct.precipitate);
                result.add(TConstruct.twilit);
            }
            if(stack.getItem() == TFItems.knightmetal_ingot){
                result.add(TConstruct.stalwart);
                result.add(TConstruct.twilit);
            }
        }
        return result;
    }
    public void removeTrait(ItemStack tool,ITrait trait){
        if(TinkerUtil.hasTrait(tool.getTagCompound(),trait.getIdentifier())){
            NBTTagList newList1 = new NBTTagList();
            NBTTagList newList2 = new NBTTagList();
            for (NBTBase compound: TagUtil.getModifiersTagList(tool)) {
                if(!ModifierNBT.readTag((NBTTagCompound) compound).identifier.equals(trait.getIdentifier())){
                    newList1.appendTag(compound);
                }
            }
            for (NBTBase compound: TagUtil.getTraitsTagList(tool)) {
                if(!((NBTTagString)compound).getString().equals(trait.getIdentifier())){
                    newList2.appendTag(compound);
                }
            }
            TagUtil.setModifiersTagList(tool,newList1);
            TagUtil.setTraitsTagList(tool,newList2);
        }
    }
    public void refresh(ItemStack tool,EntityPlayer player){
        NBTTagList list = setupNBT(tool);
        List<ITrait> keep = new ArrayList<>();
        for (NBTBase identifier:list) {
            keep.add(TinkerRegistry.getTrait(((NBTTagString)identifier).getString()));
        }
        for (ITrait trait:targets) {
            if(!keep.contains(trait)){
                removeTrait(tool,trait);
            }
        }
        for (ITrait trait:getTraitShouldApply(player)) {
            ToolBuilder.addTrait(tool.getTagCompound(),trait,0xffddffdd);
        }
    }
    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound,modifierTag);
        if(rootCompound.hasKey("association_initial")){
            rootCompound.removeTag("association_initial");
        }
    }
    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        refresh(tool, (EntityPlayer) player);
    }
    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        refresh(tool, (EntityPlayer) player);
    }
}
