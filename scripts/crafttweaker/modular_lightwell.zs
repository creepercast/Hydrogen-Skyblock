import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Aquamarine to Starlight
RecipeBuilder.newBuilder("m_aquam_star","modular_lightwell",600).
addItemInput(<astralsorcery:itemcraftingcomponent:0>*1).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*1000).
build();

// Aquamarine Block to Starlight
RecipeBuilder.newBuilder("m_aquab_star","modular_lightwell",5500).
addItemInput(<additions:hsadd-blockaquamarine>*1).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*9000).
build();

// Stardust to Starlight
RecipeBuilder.newBuilder("m_stard_star","modular_lightwell",400).
addItemInput(<astralsorcery:itemcraftingcomponent:2>*1).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*2500).
build();

// Starmetal to Starlight
RecipeBuilder.newBuilder("m_starm_star","modular_lightlwell",500).
addItemInput(<astralsorcery:itemcraftingcomponent:1>*1).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*3000).
build();

// Starmtal Block to Starlight
RecipeBuilder.newBuilder("m_starb_star","modular_lightwell",5000).
addItemInput(<additions:hsadd-blockstarmetal>*1).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*27000).
build();