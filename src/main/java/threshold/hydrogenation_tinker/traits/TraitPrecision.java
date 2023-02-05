package threshold.hydrogenation_tinker.traits;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Enchantments;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.MinecraftForge;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.ToolBuilder;


public class TraitPrecision extends ModifierTrait {
    public TraitPrecision() {
        super("precision", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void applyEffect(NBTTagCompound rootCompound, NBTTagCompound modifierTag) {
        super.applyEffect(rootCompound,modifierTag);
        ToolBuilder.addEnchantment(rootCompound, Enchantments.SILK_TOUCH);
    }
}
