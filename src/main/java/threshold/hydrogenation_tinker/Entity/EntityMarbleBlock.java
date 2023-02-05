package threshold.hydrogenation_tinker.Entity;


import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityMarbleBlock extends EntityFlying {
    private int entityAge;
    private int playerId;
    private float damage;

    public EntityMarbleBlock(World worldIn) {
        super(worldIn);
        this.entityAge = 0;
        this.playerId = 0;
        this.damage = 0.0F;
        this.setSize(2.0F, 5.0F);
    }

    public EntityMarbleBlock(World worldIn, double x, double y, double z, float damage, EntityLivingBase user) {
        this(worldIn);
        this.setPositionAndRotation(x, y, z, 0.0F, 0.0F);
        this.playerId = user.getEntityId();
        this.damage = damage;
        this.noClip = true;
    }

    @Nullable
    public EntityLivingBase getFollowingEntity() {
        Entity e = this.getEntityWorld().getEntityByID(this.playerId);
        return e != null && !e.isDead && e instanceof EntityLivingBase ? (EntityLivingBase)e : null;
    }
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(Integer.MAX_VALUE);
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        if (!(entityIn instanceof EntityMarbleBlock)) {
            if (this.getFollowingEntity() != entityIn && !this.getEntityWorld().isRemote) {
                if (this.getFollowingEntity() != null) {
                    entityIn.attackEntityFrom(DamageSource.causeMobDamage(this.getFollowingEntity()), this.damage);
                } else {
                    entityIn.attackEntityFrom(DamageSource.GENERIC, this.damage);
                }
            }

        }
    }

    public void onEntityUpdate() {
        super.onEntityUpdate();
        ++this.entityAge;
        this.motionY = -0.5D;
        if (this.entityAge > 60) {
            this.setDead();
        }
    }




    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("entityAge", this.entityAge);
        compound.setInteger("owner", this.playerId);
    }
    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.entityAge = compound.getInteger("entityAge");
        this.playerId = compound.getInteger("owner");
    }
}
