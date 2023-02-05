package threshold.hydrogenation_tinker.utils;

import c4.conarm.ConstructsArmory;
import c4.conarm.lib.materials.CoreMaterialStats;
import c4.conarm.lib.materials.PlatesMaterialStats;
import c4.conarm.lib.materials.TrimMaterialStats;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.Loader;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.*;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import threshold.hydrogenation_tinker.Hydrogenation_tinker;

import java.util.ArrayList;
import java.util.List;

public class Tester {
    public static List<Material> materials;
    public static void init(){
        materials = new ArrayList<>();
        for (AbstractTrait trait: Hydrogenation_tinker.Traits.traits) {
            Material material = new Material("hydrogen_test_" + materials.size(), TextFormatting.AQUA){
                public String getLocalizedName() {
                    if(trait == null) {
                        return I18n.format("material.hydrogen_test.name");
                    }else{
                        return I18n.format("material.hydrogen_test.name") + " " + trait.getLocalizedName();
                    }
                }
            };
            material.setRenderInfo(0xff00ffff);
            TinkerRegistry.integrate(material);
            if(trait != null){
                material.addTrait(trait);
            }
            materials.add(material);
            TinkerRegistry.addMaterialStats(material,
                    new HeadMaterialStats(114514, 1f, 1f, 1919810),
                    new HandleMaterialStats(1.0f, 0),
                    new ExtraMaterialStats(0),
                    new BowMaterialStats(100f, 2f, -1f),
                    new BowStringMaterialStats(2),
                    new FletchingMaterialStats(1.0f,1.0f),
                    new ArrowShaftMaterialStats(1.0f,1)
            );
            if(Loader.isModLoaded(ConstructsArmory.MODID)) {
                TinkerRegistry.addMaterialStats(material,
                        new CoreMaterialStats(114514, 20),
                        new PlatesMaterialStats(114514, 0, 1919810),
                        new TrimMaterialStats(1919810)
                );
            }
            material.setRepresentativeItem(ItemBlock.getItemFromBlock(Blocks.COMMAND_BLOCK));
            TinkerRegistry.addMaterial(material);
        }
    }
}
