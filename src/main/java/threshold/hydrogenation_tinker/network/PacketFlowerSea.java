package threshold.hydrogenation_tinker.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import slimeknights.tconstruct.library.utils.ToolHelper;
import threshold.hydrogenation_tinker.Entity.EntityFlowerSeaRay;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;

import javax.annotation.Nullable;

public class PacketFlowerSea implements IMessage {

    private int option;
    //0:normal swing,1:option swing,2:option
    public PacketFlowerSea() {
        // NO-OP
    }

    public PacketFlowerSea(int option) {
        this.option = option;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(option);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        option = buf.readInt();
    }
    public static class Handler implements IMessageHandler<PacketFlowerSea, IMessage> {

        @Nullable
        @Override
        public IMessage onMessage(PacketFlowerSea message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            MinecraftServer server = player.getServer();
            if (server != null) {
                server.addScheduledTask(() -> {
                    World world = player.getEntityWorld();
                    if (player.getCooledAttackStrength(0.5F) >= 0.95) {
                        Vec3d direction = player.getLookVec();
                        if(message.option == 0){
                            normal_effect(world,player,direction);
                        }else if(message.option == 1){
                            active_effect(world,player,direction);
                        }else{
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.FLOWER_SEA_ACTIVE,15*20,0));
                            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.FLOWER_SEA_COOL_DOWN,30*20,0));
                            player.attackEntityFrom(DamageSource.causePlayerDamage(player), player.getMaxHealth() / 2);
                        }

                    }
                });
            }

            return null;
        }
        private static void active_effect(World world,EntityPlayer player,Vec3d direction){
            world.spawnEntity(new EntityFlowerSeaRay(world,
                    player.posX,player.posY + 0.5,player.posZ,
                    0,0,0,
                    ToolHelper.getActualAttack(player.getHeldItemMainhand()),player,EntityFlowerSeaRay.BIG_RADIUS,
                    EntityFlowerSeaRay.LIMIT
            ));
        }
        public static void normal_effect(World world,EntityPlayer player,Vec3d direction){
            if(player.isPotionActive(Hydrogenation_tinker.Potions.SPRINT_CHARGE)){
                PotionEffect effect = player.getActivePotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE);
                player.removePotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE);
                assert effect != null;
                if(effect.getAmplifier() == 3){
                    player.removePotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE);
                    Vec3d pos_before = new Vec3d(player.posX - direction.x *3,player.posY + player.getEyeHeight(),player.posZ - direction.z * 3);
                    world.spawnEntity(new EntityFlowerSeaRay(
                            world,
                            pos_before.x,pos_before.y-0.2,pos_before.z,
                            direction.x * 2,0,direction.z*2,
                            0,player,-1
                    ));
                    world.spawnEntity(new EntityFlowerSeaRay(
                            world,
                            pos_before.x,pos_before.y-0.6,pos_before.z,
                            direction.x * 2,0,direction.z*2,
                            0,player,-1
                    ));
                }else{
                    player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE,100,effect.getAmplifier() + 1));
                }
            }else{
                player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.SPRINT_CHARGE,100,0));
            }
        }
    }
}
