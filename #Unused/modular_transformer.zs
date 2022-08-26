import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// M Transformer

// M Transformer crafting Modularium Ingot
RecipeBuilder.newBuilder("m_transform","modular_transformer",100).
addItemInput(<minecraft:iron_ingot>*16).
addItemInput(<minecraft:gold_ingot>*1).
addItemInput(<minecraft:redstone>*8).
addItemInput(<minecraft:glowstone_dust>*8).
addEnergyPerTickInput(10).
addItemOutput(<modularmachinery:itemmodularium>*16).
addItemOutput(<modularmachinery:itemmodularium>*4).setChance(0.2).
build();

//M Transformer crafting Modularium Block
RecipeBuilder.newBuilder("m_transform_b","modular_transformer",1000).
addItemInput(<minecraft:iron_block>*16).
addItemInput(<minecraft:gold_block>*1).
addItemInput(<minecraft:redstone_block>*8).
addItemInput(<minecraft:glowstone>*18).
addEnergyPerTickInput(20).
addItemOutput(<additions:hsadd-blockmodularium>*16).
addItemOutput(<modularmachinery:itemmodularium>*48).setChance(0.5).
build();
