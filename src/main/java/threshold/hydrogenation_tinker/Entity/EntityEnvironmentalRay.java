package threshold.hydrogenation_tinker.Entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;

public class EntityEnvironmentalRay extends EntityRay {
    float type = 0.1f;
    public EntityEnvironmentalRay(World world) {
        super(world);
    }
    public EntityEnvironmentalRay(World world, double x, double y, double z, double vx, double vy, double vz, float attack, EntityLivingBase thrower,float type) {
        super(world, x, y, z, vx, vy, vz, attack, thrower);
        this.type = type;
    }
    @Override
    public void DoEffect(Entity target){
        target.attackEntityFrom(new EntityDamageSource("generic",thrower),attack);
    }
}