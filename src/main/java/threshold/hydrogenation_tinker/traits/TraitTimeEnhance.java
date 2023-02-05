package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class TraitTimeEnhance extends AbstractTrait {
    public static final List<Integer> months = new ArrayList<>();
    static{
        months.add(1);
        months.add(2);
        months.add(6);
        months.add(7);
        months.add(8);
        months.add(12);
    }
    public TraitTimeEnhance() {
        super("time_enhance", 6536564);
    }
    @Override
    public float damage(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damage, float newDamage, boolean isCritical) {
        SimpleDateFormat calendar = new SimpleDateFormat("MM");
        int month = Integer.parseInt(calendar.format(new Date()));
        if(!months.contains(month)){
            return super.damage(tool, player, target, damage, newDamage * 1.5f, isCritical);
        }else {
            return super.damage(tool, player, target, damage, newDamage, isCritical);
        }
    }

    @Override
    public void miningSpeed(ItemStack tool, PlayerEvent.BreakSpeed event) {
        super.miningSpeed(tool, event);
        SimpleDateFormat calendar = new SimpleDateFormat("MM");
        int month = Integer.parseInt(calendar.format(new Date()));
        if(months.contains(month)) {
            event.setNewSpeed(event.getNewSpeed() * 1.5f);
        }
    }
}
