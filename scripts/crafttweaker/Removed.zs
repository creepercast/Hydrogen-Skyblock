import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

var Items = [
    // MC
    // All vanilla tools are banned. They can only used to craft other items. Please make tools by Tinker's Construct 2.

    // Applied Energistics 2
    // Crank and Quartz Grindstone.
    <appliedenergistics2:grindstone>,<appliedenergistics2:crank>,
    // Metorite Compass. There won't be any Metorites in Skyblock world.
    <appliedenergistics2:sky_compass>,
];

for item in Items {
    mods.jei.JEI.removeAndHide(item);
}