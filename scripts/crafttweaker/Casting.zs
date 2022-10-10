import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// [Item Output, remove old recipes?]:[Item Input, Fluid Input, Fluid Amount, Consume cast?, Ticks]
var BasinList = {
    // Astral Sorcery - Marble
    [<astralsorcery:blockmarble>,true]:[<botania:livingrock>,<integrateddynamics:block_menril_resin>,250,true,20],
    // Infused Wood
    [<astralsorcery:blockinfusedwood>,true]:[<botania:livingwood>,<integrateddynamics:block_menril_resin>,250,true,20],
    // Mekanism - Steel Ingot
    [<mekanism:ingot:4>,false]:[<minecraft:coal>,<liquid:iron>,144,true,100],
    [<mekanism:ingot:4>,false]:[<minecraft:coal>,<liquid:iron>,144,true,100],
    // Thermal Expansion - Machine Frame
    [<thermalexpansion:frame>,true]:[<immersiveengineering:metal_decoration1:1>,<liquid:tin>,288,true,40],
};

// Mark Recipes
var mark = 0;
for output,input in BasinList {
    if (output[1]) {
        recipes.remove(output[0]);
    }
    mods.tconstruct.addBasinRecipe(output[0],input[0],input[1],input[2],input[3],input[4]);
}
