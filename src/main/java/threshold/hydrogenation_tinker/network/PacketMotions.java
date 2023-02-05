package threshold.hydrogenation_tinker.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import javax.annotation.Nullable;

public class PacketMotions implements IMessage {

    private int id;
    private double x,y,z,dx,dy,dz;

    public PacketMotions() {
        // NO-OP
    }

    public PacketMotions(int id,double x,double y,double z,double dx,double dy, double dz) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(id);
        buf.writeDouble(x);
        buf.writeDouble(y);
        buf.writeDouble(z);
        buf.writeDouble(dx);
        buf.writeDouble(dy);
        buf.writeDouble(dz);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.id=buf.readInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.dx = buf.readDouble();
        this.dy = buf.readDouble();
        this.dz = buf.readDouble();
    }
    public static class Handler implements IMessageHandler<PacketMotions, IMessage> {

        @Nullable
        @Override
        public IMessage onMessage(PacketMotions message, MessageContext ctx) {
            World world = Minecraft.getMinecraft().world;
            Entity target = world.getEntityByID(message.id);
            if (target != null) {
                target.posX = message.x;
                target.posY = message.y;
                target.posZ = message.z;
                target.motionX = message.dx;
                target.motionY = message.dy;
                target.motionZ = message.dz;
            }
            return null;
        }

    }

}
