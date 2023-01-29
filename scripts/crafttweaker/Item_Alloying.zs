#priority 9

import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// IE: Alloying Kiln
var kilnList = {
    // Enriched Alloy
    [<ore:dustSteel>,<minecraft:redstone>,200]:<mekanism:enrichedalloy>
};

// TE: Introduction Smelter
var introList = {
    // Black Iron
    [<minecraft:iron_ingot>,<minecraft:dye>,200]:<extendedcrafting:material>,
    // Quartz Enriched Iron
    [<minecraft:iron_ingot>*3,<appliedenergistics2:material>,1000]:<refinedstorage:quartz_enriched_iron>*3
};

// Mek: Combiner
var combiList = {
    // Quartz Enriched Iron
    [<minecraft:iron_ingot>*3,<appliedenergistics2:material>]:<refinedstorage:quartz_enriched_iron>*3
};

// EIO: Alloy Smelter
var alloyList = {
    // Black Iron
    [<minecraft:iron_ingot>,<minecraft:dye>,200]:<extendedcrafting:material>,
    // Quartz Enriched Iron
    [<minecraft:iron_ingot>*3,<appliedenergistics2:material>,800]:<refinedstorage:quartz_enriched_iron>*3
};

for input,output in klinList {
    mods.immersiveengineering.AlloySmelter.addRecipe(output,input[0],input[1],input[2]);
}

for input,output in introList {
    mods.thermalexpansion.InductionSmelter.addRecipe(output,input[0],input[1],input[2]);
}

for input,output in combList {
    mods.mekanism.combiner.addRecipe(input[0],input[1],output);
}

for input,output in alloyList {
    mods.enderio.AlloySmelter.addRecipe(output,[input[0],input[1]],input[2]);
}
