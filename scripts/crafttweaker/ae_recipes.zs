import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Certus Quartz recipe rebuild
// Sieve recipe of Certus Quartz has been removed in config.
mods.tconstruct.Casting.addBasinRecipe(<appliedenergistics2:material>,<minecraft:quartz>,<integrateddynamics:block_menril_resin>,100,true,20);

// Skystone recipe rebuild
recipes.remove(<appliedenergistics2:sky_stone_block>);
mods.tconstruct.Casting.addBasinRecipe(<appliedenergistics2:sky_stone_block>,<appliedenergistics2:material:45>,<liquid:obsidian>,288,true,40);