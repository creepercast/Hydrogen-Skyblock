package threshold.hydrogenation_tinker.Entity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

import java.util.List;

public class EntityFlowerSeaRay extends EntityRay implements IEntityAdditionalSpawnData {
    public static int SWING_TIME = 8;
    public static int BIG_RADIUS = 5;
    public static int SMALL_RADIUS = 3;
    public static int LIMIT = 5;
    int radius = -1;
    int limit = 1;
    @SuppressWarnings("unused")
    public EntityFlowerSeaRay(World world) {
        super(world);
    }
    public EntityFlowerSeaRay(World world, double x, double y, double z, double vx, double vy, double vz, float attack, EntityLivingBase thrower, int radius){
        this(world,x,y,z,vx,vy,vz,attack,thrower,radius,0);
    }
    public EntityFlowerSeaRay(World world, double x, double y, double z, double vx, double vy, double vz, float attack, EntityLivingBase thrower, int radius,int limit) {
        super(world, x, y, z, vx, vy, vz, attack, thrower,true);
        this.radius = radius;
        if(this.radius > 0){
            this.livingtime = SWING_TIME;
        }
        this.limit = limit;
    }
    @Override
    public void spawnParticle() {
        if(radius > 0) {
            if(world.isRemote) {
                double angle = ((double) livingtime / SWING_TIME) * 360;
                double x = this.posX;
                double y = this.posY;
                double z = this.posZ;
                for (int i = 0; i < 72 / SWING_TIME; i++) {
                    double dx = Math.cos(Math.toRadians(angle + 5 * i));
                    double dy = 0;
                    double dz = Math.sin(Math.toRadians(angle + 5 * i));
                    for (int j = 0; j < 20; j++) {
                        world.spawnParticle(
                                EnumParticleTypes.FLAME,
                                x + dx * j * radius / 20, y + dy * j * radius / 20, z + dz * j * radius / 20,
                                0, 0, 0
                        );
                    }
                }
            }
            if(livingtime == 4 && !world.isRemote){
                List<EntityLivingBase> targets = world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(
                        this.posX - radius, this.posY - radius, this.posZ - radius,
                        this.posX + radius, this.posY + radius, this.posZ + radius
                ), input -> input.getDistance(this) < radius && input != this.thrower);
                for (EntityLivingBase target:targets) {
                    DoEffect(target);
                }
                if(this.limit>0) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        for (int dy = -1; dy < 2; dy++) {
                            for (int dz = -radius; dz <= radius; dz++) {
                                if (this.getPositionVector().add(dx, dy, dz).distanceTo(this.getPositionVector()) <= this.radius) {
                                    BlockPos position = this.getPosition().add(dx, dy, dz);
                                    IBlockState state = world.getBlockState(position);
                                    if (state.getBlock() instanceof BlockFlower) {
                                        world.spawnEntity(new EntityFlowerSeaRay(
                                                world,
                                                position.getX(), position.getY(), position.getZ(),
                                                0, 0, 0,
                                                this.attack, this.thrower, BIG_RADIUS, this.limit - 1
                                        ));
                                        world.setBlockState(position, Blocks.DEADBUSH.getDefaultState(), Constants.BlockFlags.DEFAULT);
                                    } else if (state.getBlock() == Blocks.SAPLING) {
                                        world.spawnEntity(new EntityFlowerSeaRay(
                                                world,
                                                position.getX() + 0.5, position.getY() + 0.5, position.getZ() + 0.5,
                                                0, 0, 0,
                                                this.attack, this.thrower, SMALL_RADIUS, this.limit - 1
                                        ));
                                        world.setBlockToAir(position);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }else{
            super.spawnParticle();
        }
    }
    @Override
    protected void onImpact(RayTraceResult pos) {
        if (world.isRemote)
            return;
        if(this.thrower == null){
            return;
        }
        if(this.thrower == pos.entityHit){
            this.setDead();
            return;
        }
        if(pos.entityHit != null && !pos.entityHit.isDead && (pos.entityHit instanceof EntityLivingBase)){
            EntityLivingBase target = (EntityLivingBase) pos.entityHit;
            DoEffect(target);
            if(!this.penetrate) this.setDead();
            return;
        }
        if(!this.penetrate) this.setDead();
    }
    @Override
    public void DoEffect(Entity target){
        if(!(target instanceof EntityLivingBase)) return;
        if(radius > 0){
            target.attackEntityFrom(
                    DamageSource.causeMobDamage(thrower),
                    (float) (attack + 0.25*((EntityLivingBase) target).getMaxHealth() + ((EntityLivingBase) target).getTotalArmorValue() + thrower.getHealth())
            );
        }else{
            target.attackEntityFrom(DamageSource.causeMobDamage(thrower),((EntityLivingBase)target).getHealth() / 2);
        }
    }

    @Override
    public void writeSpawnData(ByteBuf buffer) {
        buffer.writeInt(radius);
        buffer.writeInt(livingtime);
    }

    @Override
    public void readSpawnData(ByteBuf additionalData) {
        radius = additionalData.readInt();
        if(radius > 0){
            livingtime = Math.min(additionalData.readInt(),MAX_LIVINGTIME);
        }else{
            livingtime = additionalData.readInt();
        }
    }
}