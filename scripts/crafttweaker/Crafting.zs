import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Modular Machinery recipes are not in this file.
var shapedList = {
    // Compact Machines 3 - Miniaturization Field Projector
    [<compactmachines3:fieldprojector>*1,true]:[
        [null,<minecraft:ender_pearl>,null],
        [<immersiveengineering:metal:38>,<minecraft:redstone_block>,<immersiveengineering:metal:38>],
        [<extrautils2:decorativesolid:3>,<immersiveengineering:metal:38>,<extrautils2:decorativesolid:3>]],
    // Mekanism - Steel Casing
    [<mekanism:basicblock:8>*1,true]:[
        [null,<thermalfoundation:material:352>,null],
        [<thermalfoundation:material:352>,<thermalexpansion:frame>,<thermalfoundation:material:352>],
        [null,<thermalfoundation:material:352>,null]],
    // Thermal Expansion - Thermal Casing
    [<additions:hsadd-thermal_casing>*1,false]:[
        [null,<thermalfoundation:material:354>,null],
        [<thermalfoundation:material:354>,<thermalexpansion:frame>,<thermalfoundation:material:354>],
        [null,<thermalfoundation:material:354>,null]],
};

var shapelessList = {
    // Botania - Red String
    [<botania:manaresource:12>*1,true]:[<minecraft:string>,<minecraft:redstone>]
};

// Mark Recipes
var mark = 0;

for output,input in shapedList {
    if (output[1]) {
        recipes.remove(output[0]);
    }
    recipes.addShaped("craft"+mark,output[0],input);
    mark += 1;
}

for output,input in shapelessList {
    if (output[1]) {
        recipes.remove(output[0]);
    }
    recipes.addShapeless("craft"+mark,output[0],input);
    mark += 1;
}