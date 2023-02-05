package threshold.hydrogenation_tinker.traits;

import c4.conarm.lib.traits.AbstractArmorTrait;
import crazypants.enderio.endergy.capacitor.EndergyCapacitorData;
import crazypants.enderio.endergy.capacitor.ItemEndergyCapacitor;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;
import xyz.phanta.tconevo.capability.EnergyShield;
import xyz.phanta.tconevo.handler.EnergyShieldHandler;

public class TraitEmergencyEnergy extends AbstractArmorTrait {
    public TraitEmergencyEnergy() {
        super("emergency_energy", 6536564);
        MinecraftForge.EVENT_BUS.register(this);
    }
    @Override
    public void onArmorTick(ItemStack tool, World world, EntityPlayer player) {
        super.onArmorTick(tool, world, player);
        if(world.isRemote) return;
        if(player.isPotionActive(Hydrogenation_tinker.Potions.FLUX_DISTURB)){
            for (ItemStack armor : player.getArmorInventoryList()) {
                if (armor.hasCapability(CapabilityEnergy.ENERGY, null)) {
                    IEnergyStorage capability = armor.getCapability(CapabilityEnergy.ENERGY, null);
                    assert capability != null;
                    capability.extractEnergy(100,false);
                }
            }
            return;
        }
        float max_shield = 0;
        float now_shield = 0;
        int max_rf = 0;
        int now_rf = 0;
        for (EnergyShield shield:EnergyShieldHandler.collectArmourShields(player.inventory)) {
            max_shield += shield.getShieldCapacity();
            now_shield += shield.getShieldPoints();
        }
        if(max_shield <= 0) {
            for (ItemStack armor : player.getArmorInventoryList()) {
                if (armor.hasCapability(CapabilityEnergy.ENERGY, null)) {
                    IEnergyStorage capability = armor.getCapability(CapabilityEnergy.ENERGY, null);
                    assert capability != null;
                    max_rf += capability.getMaxEnergyStored();
                    now_rf += capability.getEnergyStored();
                }
            }
        }else{
            max_rf = 10;
            now_rf = 20;
        }
        if(now_shield < 0.1*max_shield || now_rf < max_rf*0.1){
            if(!searchItem(player)){
                return;
            }
            for (EnergyShield shield:EnergyShieldHandler.collectArmourShields(player.inventory)) {
                shield.setShield(shield.getShieldCapacity());
                shield.setEntropy(0);
            }
            for (ItemStack armor:player.getArmorInventoryList()) {
                if(armor.hasCapability(CapabilityEnergy.ENERGY,null)){
                    IEnergyStorage capability = armor.getCapability(CapabilityEnergy.ENERGY,null);
                    assert capability != null;
                    int time = 0;
                    do {
                        capability.receiveEnergy(Integer.MAX_VALUE, false);
                        time += 1;
                    }while (capability.getEnergyStored() < capability.getMaxEnergyStored() - 20 && time < 50);
                }
            }
            player.sendMessage(new TextComponentString(I18n.format("hydrogenation_tinker.tips.emergency_energy")));
            player.addPotionEffect(new PotionEffect(Hydrogenation_tinker.Potions.FLUX_DISTURB,45*20));
        }
    }
    private boolean searchItem(EntityPlayer player){
        for (ItemStack stack:player.inventory.mainInventory) {
            if(stack.getItem() instanceof ItemEndergyCapacitor){
                ItemEndergyCapacitor capacitor = (ItemEndergyCapacitor) stack.getItem();
                if(capacitor.getData().getLocalizedName().equals(EndergyCapacitorData.STELLAR_CAPACITOR.getLocalizedName())) {
                    stack.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }
}
