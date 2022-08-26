import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Compact Machine Wall
RecipeBuilder.newBuilder("ac_m_compactwall","modular_compactor",50).
addItemInput(<minecraft:iron_block>*1).
addItemInput(<minecraft:redstone>*2).
addEnergyPerTickInput(50).
addItemOutput(<compactmachines3:wallbreakable>*16).
build();

RecipeBuilder.newBuilder("m_compactwall","modular_compactor",200).
addItemInput(<minecraft:iron_block>*1).
addItemInput(<minecraft:redstone>*2).
addItemOutput(<compactmachines3:wallbreakable>*16).
build();

// Compact Modularium Block
RecipeBuilder.newBuilder("ac_m_compactmodularium","modular_compactor",250).
addItemInput(<minecraft:iron_block>*8).
addItemInput(<minecraft:gold_block>*1).
addItemInput(<minecraft:redstone_block>*6).
addItemInput(<minecraft:glowstone>*12).
addItemInput(<mekanism:reinforcedalloy>*1).
addEnergyPerTickInput(50).
addItemOutput(<additions:hsadd-blockmodularium>*4).
build();

RecipeBuilder.newBuilder("m_compactmodularium","modular_compactor",1000).
addItemInput(<minecraft:iron_block>*8).
addItemInput(<minecraft:gold_block>*1).
addItemInput(<minecraft:redstone_block>*6).
addItemInput(<minecraft:glowstone>*12).
addItemInput(<mekanism:reinforcedalloy>*1).
addItemOutput(<additions:hsadd-blockmodularium>*4).
build();

// Compact Machine Casing
RecipeBuilder.newBuilder("ac_m_compactcasing","modular_compactor",200).
addItemInput(<minecraft:redstone_block>*20).
addItemInput(<additions:hsadd-blockmodularium>*7).
addItemInput(<additions:hsadd-nuggetmodularium>*1).
addEnergyPerTickInput(50).
addItemOutput(<modularmachinery:blockcasing>*64).
build();

RecipeBuilder.newBuilder("m_compactcasing","modular_compactor",800).
addItemInput(<minecraft:redstone_block>*20).
addItemInput(<additions:hsadd-blockmodularium>*7).
addItemInput(<additions:hsadd-nuggetmodularium>*1).
addItemOutput(<modularmachinery:blockcasing>*64).
build();

// Compact Machine Controller
RecipeBuilder.newBuilder("ac_m_compactcontroller","modular_compactor",125).
addItemInput(<modularmachinery:blockcasing>*2).
addItemInput(<minecraft:diamond_block>*1).
addItemInput(<minecraft:redstone_block>*4).
addItemInput(<minecraft:glowstone>*4).
addItemInput(<additions:hsadd-nuggetmodularium>*1).
addEnergyPerTickInput(50).
addItemOutput(<modularmachinery:blockcontroller>*1).
build();

RecipeBuilder.newBuilder("m_compactcontroller","modular_compator",500).
addItemInput(<modularmachinery:blockcasing>*2).
addItemInput(<minecraft:diamond_block>*1).
addItemInput(<minecraft:redstone_block>*4).
addItemInput(<minecraft:glowstone>*4).
addItemInput(<additions:hsadd-nuggetmodularium>*1).
addItemOutput(<modularmachinery:blockcontroller>*1).
build();