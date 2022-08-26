import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Starlight Collection
RecipeBuilder.newBuilder("starlight_collection","starlight_collector",200).
addItemInput(<astralsorcery:itemcraftingcomponent:2>*1).setChance(0).   // Catalyst, DON'T DELETE THIS LINE!!!
addStarlightInput(4000).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*50).
build();
