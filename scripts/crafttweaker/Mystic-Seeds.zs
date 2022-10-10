import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import thaumcraft.aspect.CTAspect;
import thaumcraft.aspect.CTAspectStack;
import mods.thaumcraft.Infusion;

var seedList = {};

var essence = [ // Essence List
    <mysticalagriculture:crafting:0>, // Inferium
    <mysticalagriculture:crafting:1>, // Prudentium
    <mysticalagriculture:crafting:2>, // Intermedium
    <mysticalagriculture:crafting:3>, // Superium
    <mysticalagriculture:crafting:4>, // Supremium
];
var seed = [ // Crafting Seeds List
    <mysticalagriculture:crafting:17>, // Tier 1
    <mysticalagriculture:crafting:18>, // Tier 2
    <mysticalagriculture:crafting:19>, // Tier 3
    <mysticalagriculture:crafting:20>, // Tier 4
    <mysticalagriculture:crafting:21>, // Tier 5
];

// Mark recipes
var mark = 0;

for input,output in seedList {
    mark += 1;
}
