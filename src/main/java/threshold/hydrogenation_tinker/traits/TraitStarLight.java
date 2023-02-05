package threshold.hydrogenation_tinker.traits;

import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.Util;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;

import java.util.List;

public class TraitStarLight extends ModifierTrait {
    public TraitStarLight() {
        super("starlight", 0xff9999ff);
    }
    private final float bonus = 0.3f;
    @Override
    public void updateNBTforTrait(NBTTagCompound modifierTag, int newColor) {
        super.updateNBTforTrait(modifierTag, newColor);
        modifierTag.removeTag("counter");
    }
    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound, modifierTag);
        if(!modifierTag.hasKey("counter")) {
            modifierTag.setBoolean("counter",true);
            // add the attack speed boost
            ToolNBT data = TagUtil.getToolStats(rootCompound);
            // apply using the base value of 1.0f, as otherwise this will apply twice on tools with multiple lightweight parts
            data.attackSpeedMultiplier *= 1.0f + bonus;
            data.speed *= 1.0 + bonus;
            TagUtil.setToolTag(rootCompound, data.get());

            if (TinkerUtil.hasCategory(rootCompound, Category.LAUNCHER)) {
                ProjectileLauncherNBT launcherData = new ProjectileLauncherNBT(TagUtil.getToolTag(rootCompound));
                launcherData.drawSpeed += launcherData.drawSpeed * bonus;
                TagUtil.setToolTag(rootCompound, launcherData.get());
            }
        }
    }

    @Override
    public List<String> getExtraInfo(ItemStack tool, NBTTagCompound modifierTag) {
        String loc = String.format(LOC_Extra, "lightweight");

        return ImmutableList.of(Util.translateFormatted(loc, Util.dfPercent.format(bonus)));
    }
}
