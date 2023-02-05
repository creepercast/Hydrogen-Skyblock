package threshold.hydrogenation_tinker.traits;

import net.minecraft.world.World;
import slimeknights.tconstruct.library.entity.EntityProjectileBase;
import slimeknights.tconstruct.library.traits.AbstractProjectileTrait;

public class TraitRelative extends AbstractProjectileTrait {
    public TraitRelative() {
        super("relative", 6536564);
    }
    @Override
    public void onMovement(EntityProjectileBase projectile, World world, double slowdown) {
        if(projectile.motionX * projectile.motionX + projectile.motionY * projectile.motionY + projectile.motionZ * projectile.motionZ < 400.0) {
            projectile.motionX = projectile.motionX * 1.05;
            projectile.motionY = projectile.motionY * 1.05;
            projectile.motionZ = projectile.motionZ * 1.05;
        }
    }
}
