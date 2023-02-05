package threshold.hydrogenation_tinker.traits;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierTrait;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.tools.TinkerTraits;
import threshold.hydrogenation_tinker.utils.Utils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

public class TraitQuestionMark extends ModifierTrait {
    public TraitQuestionMark() {
        super("question_mark", 0xffffffff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void afterBlockBreak(ItemStack tool, World world, IBlockState state, BlockPos pos, EntityLivingBase player, boolean wasEffective) {
        int level = Utils.getTotalLevel(player,this);
        if(level > 0){
            try{
                randomTrait((EntityPlayer) player).afterBlockBreak(tool,world,state,pos,player,wasEffective);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void afterHit(ItemStack tool, EntityLivingBase player, EntityLivingBase target, float damageDealt, boolean wasCritical, boolean wasHit) {
        int level = Utils.getTotalLevel(player,this);
        if(level > 0){
            try{
                randomTrait((EntityPlayer) player).afterHit(tool,player,target,damageDealt,wasCritical,wasHit);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void onPlayerHurt(ItemStack tool, EntityPlayer player, EntityLivingBase attacker, LivingHurtEvent event){
        int level = Utils.getTotalLevel(player,this);
        if(level > 0){
            try {
                randomTrait(player).onPlayerHurt(tool, player, attacker, event);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    private static AbstractTrait randomTrait(EntityPlayer player){
        Map<String, ITrait> traits = null;
        try {
            Field field = TinkerRegistry.class.getDeclaredField("traits");
            field.setAccessible(true);
            if(field.get(null) instanceof Map) {
                traits = (Map<String, ITrait>) field.get(null);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(traits != null){
            Set<String> key = traits.keySet();
            AbstractTrait result = (AbstractTrait) traits.get((String) key.toArray()[random.nextInt(key.size())]);
            player.sendStatusMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.question_mark") + result.getLocalizedName() + I18n.format("hydrogenation_tinker.tips.question_mark2")),true);
            return result;
        }
        return TinkerTraits.aridiculous;
    }
}
