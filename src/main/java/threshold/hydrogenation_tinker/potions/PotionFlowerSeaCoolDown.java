package threshold.hydrogenation_tinker.potions;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("NullableProblems")
public class PotionFlowerSeaCoolDown extends Potion {
    public PotionFlowerSeaCoolDown(boolean isBadEffectIn, int liquidColorIn) {
        super(isBadEffectIn, liquidColorIn);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return new ArrayList<>();
    }
}
