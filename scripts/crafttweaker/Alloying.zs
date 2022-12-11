#priority 9

import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// IE:Alloying Kiln; TE:Introduction Smelter; Mek:Combiner; EIO:Alloy Smelter
// [Input1,Input2,Output]:[IE time,TE energy,Mek,EIO energy]
var Alloylist = {
    // EC
    // Black Iron Ingot
    [<minecraft:iron_ingot>,<minecraft:dye>,<extendedcrafting:material>]:[0,200,0,200],

    // Mek
    // Enriched Alloy
    [<ore:dustSteel>,<minecraft:redstone>,<mekanism:enrichedalloy>]:[100,0,0,0],

    // RS
    // Quartz Enriched Iron
    [<minecraft:iron_ingot>*3,<minecraft:quartz>,<refinedstorage:quartz_enriched_iron>*3]:[0,1000,1,800],
};

// Mark Recipes
var mark = 0;
for items,machine in Alloylist {
    if (machine[0] != 0) {
        mods.immersiveengineering.AlloySmelter.addRecipe(items[2],items[0],items[1],machine[0]);
    }
    if (machine[1] != 0) {
        mods.thermalexpansion.InductionSmelter.addRecipe(items[2],items[0],items[1],machine[1]);
    }
    if (machine[2] != 0) {
        mods.mekanism.combiner.addRecipe(items[0],items[1],items[2]);
    }
    if (machine[3] != 0) {
        mods.enderio.AlloySmelter.addRecipe(items[2],[items[0],items[1]],machine[3]);
    }
}