package threshold.hydrogenation_tinker;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.world.WorldServer;

@SuppressWarnings("NullableProblems")
public class TileEntityShinning extends TileEntity implements ITickable {
    int time = 5;
    @Override
    public void readFromNBT(NBTTagCompound tagCompound){
        super.readFromNBT(tagCompound);
        this.time = tagCompound.getInteger("time");
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound){
        super.writeToNBT(tagCompound);
        tagCompound.setInteger("time",time);
        return tagCompound;
    }
    @Override
    public void update() {
        this.time -= 1;
        if(this.time <=0){
            if(world instanceof WorldServer) {
                this.world.setBlockToAir(this.pos);
            }
        }
    }
}
