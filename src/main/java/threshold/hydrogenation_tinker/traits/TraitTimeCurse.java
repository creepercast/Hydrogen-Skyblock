package threshold.hydrogenation_tinker.traits;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitherSkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.utils.TagUtil;
import slimeknights.tconstruct.library.utils.TinkerUtil;
import threshold.hydrogenation_tinker.HydrogenationConfig;


public class TraitTimeCurse extends ModifierTrait {
    public TraitTimeCurse() {
        super("time_curse", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @SubscribeEvent
    public void onMobDrops(LivingDropsEvent event) {
        if (event.getDrops().size() <= 0){
            return;
        }

        World w = event.getEntity().getEntityWorld();
        if (event.getSource().getTrueSource() instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) event.getSource().getTrueSource();
            if (!w.isRemote && event.getEntity() instanceof EntityLiving && !(event.getEntity() instanceof EntityPlayer) &&
                    TinkerUtil.hasTrait(TagUtil.getTagSafe(player.getHeldItemMainhand()), identifier)) {
                if(HydrogenationConfig.joking){
                    if(event.getSource().getTrueSource() != null){
                        event.getSource().getTrueSource().sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.time_curse")));
                    }
                    event.getDrops().clear();
                    return;
                }
                for(EntityItem i : event.getDrops()) {
                    ItemStack item = i.getItem();
                    player.world.spawnEntity(new  EntityItem(
                            w,
                            event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ,
                            new ItemStack(item.getItem(), 2*item.getCount(), item.getMetadata(), item.getTagCompound())
                    ));
                }
                if(event.getEntity() instanceof EntityWitherSkeleton) {
                    event.getDrops().add(new EntityItem(
                            w,
                            event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ,
                            new ItemStack(Items.SKULL, 1, 1)
                    ));
                }
            }
        }
    }
}
