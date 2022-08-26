import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Steel Casing
recipes.remove(<mekanism:basicblock:8>);
recipes.addShaped(
    <mekanism:basicblock:8>*1,[
        [null,<thermalfoundation:material:352>,null],
        [<thermalfoundation:material:352>,<thermalexpansion:frame>,<thermalfoundation:material:352>],
        [null,<thermalfoundation:material:352>,null]
    ]
);

// Steel Ingot
mods.tconstruct.Casting.addTableRecipe(<mekanism:ingot:4>,<minecraft:coal>,<liquid:iron>,144,true,100);
mods.tconstruct.Casting.addTableRecipe(<mekanism:ingot:4>,<minecraft:coal:1>,<liquid:iron>,144,true,100);

// Enriched Alloy
mods.immersiveengineering.AlloySmelter.addRecipe(<mekanism:enrichedalloy>,<minecraft:redstone>,<ore:dustSteel>,100);

// Reinforced Alloy
mods.immersiveengineering.AlloySmelter.addRecipe(<mekanism:reinforcedalloy>,<minecraft:diamond>,<mekanism:enrichedalloy>,500);
mods.immersiveengineering.AlloySmelter.addRecipe(<mekanism:reinforcedalloy>,<ore:dustDiamond>,<mekanism:enrichedalloy>,300);
// Diamond Dust
mods.immersiveengineering.Crusher.addRecipe(<mekanism:otherdust>,<minecraft:diamond>,2000);

// Atomic Alloy
mods.immersiveengineering.AlloySmelter.addRecipe(<mekanism:atomicalloy>,<mekanism:ingot>,<mekanism:reinforcedalloy>,1500);
mods.immersiveengineering.AlloySmelter.addRecipe(<mekanism:atomicalloy>,<mekanism:otherdust:5>,<mekanism:reinforcedalloy>,1200);
// Reinforced Obsidian Dust
mods.immersiveengineering.Crusher.addRecipe(<mekanism:otherdust:5>,<mekanism:ingot>,5000);
