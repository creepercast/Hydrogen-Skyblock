package threshold.hydrogenation_tinker.traits;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import threshold.hydrogenation_tinker.utils.Utils;

public class TraitShinning extends AbstractTrait {
    public TraitShinning() {
        super("shinning", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onLivingTick(LivingEvent.LivingUpdateEvent event){
        int level = Utils.getTotalLevel(event.getEntityLiving(),this);
        if(level > 0 && !event.getEntityLiving().getEntityWorld().isRemote){
            EntityLivingBase user = event.getEntityLiving();
            if(user.getEntityWorld().isAirBlock(user.getPosition())) {
                user.getEntityWorld().setBlockState(user.getPosition(), Hydrogenation_tinker.Blocks.blockShinning.getDefaultState(), Constants.BlockFlags.DEFAULT);
            }
            if(user.getEntityWorld().isAirBlock(user.getPosition().add(0,1,0))) {
                user.getEntityWorld().setBlockState(user.getPosition().add(0,1,0), Hydrogenation_tinker.Blocks.blockShinning.getDefaultState(), Constants.BlockFlags.DEFAULT);
            }
        }
    }
}
