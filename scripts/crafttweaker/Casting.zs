import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// [Item Output, remove old recipes?]:[Item Input, Fluid Input, Fluid Amount, Consume cast?, Ticks]
var BasinList = {
    // AE2 - Skystone
    [<appliedenergistics2:sky_stone_block>,false]:[<appliedenergistics2:material:45>,<liquid:obsidian>,288,true,40],
    // Astral Sorcery - Marble
    [<astralsorcery:blockmarble>,true]:[<botania:livingrock>,<integrateddynamics:menril_resin>,250,true,20],
    // Infused Wood
    [<astralsorcery:blockinfusedwood>,true]:[<botania:livingwood>,<integrateddynamics:menril_resin>,250,true,20],
    // Integrated Dynamics - Menril Log
    [<integrateddynamics:menril_log>,false]:[<ore:logWood>,<fluid:blue_slime>,500,true,20],
    // Thermal Expansion - Machine Frame
    [<thermalexpansion:frame>,true]:[<immersiveengineering:metal_decoration1:1>,<liquid:tin>,288,true,40],
};

var TableList = {
    // AE2 - Certus Quartz
    [<appliedenergistics2:material>,false]:[<minecraft:quartz>,<integrateddynamics:menril_resin>,250,true,20]
};

// Mark Recipes
var mark = 0;
for output,input in BasinList {
    if (output[1]) {
        recipes.remove(output[0]);
    }
    mods.tconstruct.addBasinRecipe(output[0],input[0],input[1],input[2],input[3],input[4]);
}

for output,input in TableList {
    if (output[1]) {
        recipes.remove(output[0]);
    }
    mods.tconstruct.addTableRecipe(output[0],input[0],input[1],input[2],input[3],input[4]);
}
