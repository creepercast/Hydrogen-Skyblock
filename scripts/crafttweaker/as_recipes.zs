import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Marble recipe rebuild
recipes.remove(<astralsorcery:blockmarble>);
mods.tconstruct.Casting.addBasinRecipe(<astralsorcery:blockmarble>,<botania:livingrock>,<integrateddynamics:block_menril_resin>,250,true,20);

// Infused Wood recipe rebuild
recipes.remove(<astralsorcery:blockinfusedwood>);
mods.tconstruct.Casting.addBasinRecipe(<astralsorcery:blockinfusedwood>,<botania:livingwood>,<integrateddynamics:block_menril_resin>,250,true,20);
