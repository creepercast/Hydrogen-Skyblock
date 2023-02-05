package threshold.hydrogenation_tinker.traits;

import net.minecraft.nbt.NBTTagCompound;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.tinkering.Category;
import slimeknights.tconstruct.library.tools.ProjectileLauncherNBT;
import slimeknights.tconstruct.library.tools.ToolNBT;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;


public class TraitChocolateMilk extends ModifierTrait {
    public TraitChocolateMilk() {
        super("chocolate_milk", 0xff000000);
    }
    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound, modifierTag);

        // add the attack speed boost
        ToolNBT data = TagUtil.getToolStats(rootCompound);

        // apply using the base value of 1.0f, as otherwise this will apply twice on tools with multiple lightweight parts
        data.attackSpeedMultiplier = 0.25f;
        data.attack = data.attack * 4;

        TagUtil.setToolTag(rootCompound, data.get());


        if(TinkerUtil.hasCategory(rootCompound, Category.LAUNCHER)) {
            ProjectileLauncherNBT launcherData = new ProjectileLauncherNBT(TagUtil.getToolTag(rootCompound));
            launcherData.drawSpeed *= 0.25f;
            launcherData.attack *= 4f;
            TagUtil.setToolTag(rootCompound, launcherData.get());
        }
    }
}
