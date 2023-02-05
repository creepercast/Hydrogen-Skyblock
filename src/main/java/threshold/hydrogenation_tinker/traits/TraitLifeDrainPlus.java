package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import slimeknights.tconstruct.library.modifiers.IToolMod;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.tools.TinkerModifiers;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitLifeDrainPlus extends ModifierTrait {
    public TraitLifeDrainPlus() {
        super("life_drain_plus", 6536564,10,0);
        this.addItem("boneDragon");
    }
    @Override
    public void onHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, boolean isCritical) {
        super.onHit(tool,player,target,damage,isCritical);
        int level = Utils.getLevel(tool,this);
        float drain = damage * level * 0.15f;
        if(drain>0) {
            player.heal(drain);
        }
    }
    @Override
    public boolean canApplyTogether(IToolMod otherModifier) {
        return otherModifier != TinkerModifiers.modNecrotic;
    }
}
