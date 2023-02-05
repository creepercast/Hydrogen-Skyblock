package threshold.hydrogenation_tinker.utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import slimeknights.tconstruct.library.modifiers.ModifierNBT;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import slimeknights.tconstruct.library.utils.ToolHelper;
import threshold.hydrogenation_tinker.HydrogenationConfig;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Utils {
    private Utils(){}
    public static int getLevel(ItemStack stack, AbstractTrait trait){
        String name = trait.identifier;
        if(stack.isEmpty()) return 0;
        if(TinkerUtil.hasTrait(TagUtil.getTagSafe(stack),name) && !ToolHelper.isBroken(stack)) {
            return ModifierNBT.readTag(TinkerUtil.getModifierTag(stack, name)).level;
        }else{
            return 0;
        }
    }
    public static int getTotalLevel(EntityLivingBase player, AbstractTrait trait){
        int result = 0;
        result += getLevel(player.getHeldItemMainhand(),trait);
        result += getLevel(player.getHeldItemOffhand(),trait);
        for (ItemStack stack: player.getArmorInventoryList()) {
            result += getLevel(stack,trait);
        }
        return result;
    }
    public static int getTotalArmorLevel(EntityLivingBase player, AbstractTrait trait){
        int result = 0;
        for (ItemStack stack: player.getArmorInventoryList()) {
            result += getLevel(stack,trait);
        }
        return result;
    }
    public static void clean_debuff(EntityLivingBase target){
        List<Potion> removes = new ArrayList<>();
        List<String> debuff = new ArrayList<>(Arrays.asList(HydrogenationConfig.debuff_note));
        for (PotionEffect effect : target.getActivePotionEffects()) {
            if (effect.getPotion().isBadEffect()
                    || debuff.contains(Objects.requireNonNull(effect.getPotion().getRegistryName()).toString()))
                removes.add(effect.getPotion());
        }
        for (Potion remove : removes) {
            target.removePotionEffect(remove);
        }
    }
    public static void Joke_HatedTrait(EntityLivingBase target){
        if(!HydrogenationConfig.joking) return;
        target.setHealth(1);
        target.sendMessage(new TextComponentString("????????????\nIf you saw this message, it means you use a trait that author hate it..."));
    }
    public static String Colored(String target){
        return target.replaceAll("&", "\u00a7");
    }
}
