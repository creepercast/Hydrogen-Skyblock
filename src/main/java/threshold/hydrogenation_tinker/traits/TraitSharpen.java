package threshold.hydrogenation_tinker.traits;

import c4.conarm.common.armor.utils.ArmorTagUtil;
import c4.conarm.lib.armor.ArmorNBT;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

public class TraitSharpen extends ModifierTrait {
    public TraitSharpen() {
        super("sharpen", 6536564,5,0);
        this.addItem("dustPsi");
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        if(TinkerUtil.hasTrait(rootCompound, identifier)) return;
        ArmorNBT armor = ArmorTagUtil.getArmorStats(rootCompound);
        ToolNBT tool = TagUtil.getToolStats(rootCompound);
        int level = ModifierNBT.readTag(modifierTag).level;
        if(armor.defense > 0) {
            armor.defense += 0.5 * level;
            armor.toughness += 0.25 * level;
            TagUtil.setToolTag(rootCompound, armor.get());
        }
        if(tool.attack > 0){
            tool.attack += level;
            tool.speed += 0.2 * level;
            TagUtil.setToolTag(rootCompound,tool.get());
        }
        super.applyEffect(rootCompound, modifierTag);
    }
}
