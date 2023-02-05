package threshold.hydrogenation_tinker.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityRay extends EntityThrowable {
    public float attack;
    public EntityLivingBase thrower;
    public boolean penetrate;
    protected int livingtime;
    protected static final int MAX_LIVINGTIME = 200;
    public EntityRay(World world){
        this(world,0,0,0,0,0,0,0,null);
    }
    public EntityRay(World world, double x, double y, double z, double vx, double vy, double vz, float attack, EntityLivingBase thrower){
        this(world,x,y,z,vx,vy,vz,attack,thrower,false);
    }
    public EntityRay(World world, double x, double y, double z, double vx, double vy, double vz, float attack, EntityLivingBase thrower,boolean penetrate){
        super(world,x,y,z);
        setSize(0F, 0F);
        this.motionX = vx;
        this.motionY = vy;
        this.motionZ = vz;
        this.thrower = thrower;
        this.attack = attack;
        this.penetrate = penetrate;

        this.livingtime = MAX_LIVINGTIME;
    }
    @Override
    public boolean isPushedByWater(){
        return false;
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        spawnParticle();
        this.livingtime -= 1;
        if(this.livingtime <= 0){
            this.setDead();
        }
    }
    @SuppressWarnings("NullableProblems")
    @Override
    protected void onImpact(RayTraceResult pos) {
        if (world.isRemote)
            return;
        if(this.thrower == null){
            return;
        }
        if(this.thrower == pos.entityHit){
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
    protected float getGravityVelocity() {
        return 0F;
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setFloat("attack",this.attack);
        compound.setInteger("living_time",this.livingtime);
    }
    @Override
    public void readEntityFromNBT(NBTTagCompound compound){
        super.readEntityFromNBT(compound);
        this.attack = compound.getFloat("attack");
        this.livingtime = compound.getInteger("living_time");
    }



    public void DoEffect(Entity target){
        target.attackEntityFrom(new EntityDamageSource("generic",thrower),attack);
    }
    public void spawnParticle(){
        this.world.spawnParticle(EnumParticleTypes.END_ROD,this.posX,this.posY,this.posZ, 0,0,0);
    }
}