package threshold.hydrogenation_tinker.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import slimeknights.tconstruct.library.utils.ToolHelper;
import threshold.hydrogenation_tinker.Entity.EntityEnvironmentalRay;

import javax.annotation.Nullable;

public class PacketEnvironmentalSwing implements IMessage {

    private float type;

    public PacketEnvironmentalSwing() {
        // NO-OP
    }

    public PacketEnvironmentalSwing(float type) {
        this.type = type;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeFloat(type);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        type = buf.readFloat();
    }
    public static class Handler implements IMessageHandler<PacketEnvironmentalSwing, IMessage> {

        @Nullable
        @Override
        public IMessage onMessage(PacketEnvironmentalSwing message, MessageContext ctx) {
            EntityPlayer player = ctx.getServerHandler().player;
            MinecraftServer server = player.getServer();
            if (server != null) {
                server.addScheduledTask(() -> {
                    World world = player.getEntityWorld();
                    if (player.getCooledAttackStrength(0.5F) >= 0.95) {
                        Vec3d direction = player.getLookVec();
                        if(message.type == 0.1f) {
                            Entity entity = new EntityEnvironmentalRay(world,
                                    player.posX, player.posY + player.getEyeHeight(), player.posZ,
                                    direction.x * 2, direction.y * 2, direction.z * 2,
                                    ToolHelper.getActualAttack(player.getHeldItemMainhand()), player, message.type
                            );
                            world.spawnEntity(entity);
                        }
                        if(message.type == 0.3f) {
                            EntitySmallFireball entity = new EntitySmallFireball(world,player,direction.x, direction.y, direction.z);
                            float entity_pitch = player.rotationPitch;
                            float entity_yaw = player.rotationYaw;
                            entity.setLocationAndAngles(
                                    player.posX + direction.x * 2,
                                    player.posY + player.getEyeHeight() + direction.y * 2,
                                    player.posZ + direction.z * 2,
                                    entity_pitch,
                                    entity_yaw
                            );
                            entity.addVelocity(direction.x * 2,direction.y * 2,direction.z * 2);

                            world.spawnEntity(entity);
                        }
                        if(message.type == 0.5f) {
                            Entity entity = new EntityEnvironmentalRay(world,
                                    player.posX, player.posY + player.getEyeHeight(), player.posZ,
                                    direction.x * 2, direction.y * 2, direction.z * 2,
                                    ToolHelper.getActualAttack(player.getHeldItemMainhand()), player, message.type
                            );
                            world.spawnEntity(entity);
                        }
                    }
                });
            }

            return null;
        }

    }

}
