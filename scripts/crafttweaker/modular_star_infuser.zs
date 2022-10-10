import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Starmetal Infusion
RecipeBuilder.newBuilder("starmetal_infusion","modular_star_infuser",200).
addItemInput(<minecraft:iron_ingot>*3).
addItemInput(<minecraft:iron_ingot>*1).setChance(0.9).
addItemInput(<astralsorcery:itemcraftingcomponent>*1).setChance(0.1).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*100).
addStarlightInput(1000).
addItemOutput(<astralsorcery:itemcraftingcomponent:1>*3).
addItemOutput(<astralsorcery:itemcraftingcomponent:1>*1).setChance(0.9).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*50).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*50).setChance(0.9).
build();

// Resonating Gem Infusion
RecipeBuilder.newBuilder("resontatinggem_infusion","modular_star_infuser",500).
addItemInput(<astralsorcery:itemcraftingcomponent>*3).
addItemInput(<astralsorcery:itemcraftingcomponent>*1).setChance(0.9).
addItemInput(<astralsorcery:itemcraftingcomponent:2>*1).setChance(0.1).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*750).
addStarlightInput(2000).
addItemOutput(<astralsorcery:itemcraftingcomponent:4>*3).
addItemOutput(<astralsorcery:itemcraftingcomponent:4>*1).setChance(0.9).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*500).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*200).setChance(0.8).
build();

// Over-purified Celestal Collector Crystal
RecipeBuilder.newBuilder("overpurified_celcolcrystal","modular_star_infuser",3600).
addItemInput(<astralsorcery:blockcelestialcollectorcrystal>.withTag({astralsorcery: {crystalProperties: {collectiveCapability: 100, size: 900, purity: 100}}})).
addItemInput(<astralsorcery:itemcraftingcomponent:1>*32).
addStarlightInput(8000).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*1000).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*500).setChance(0.9).
addItemOutput(<astralsorcery:blockcelestialcollectorcrystal>.withTag({astralsorcery: {crystalProperties: {collectiveCapability: 100, size: 900, purity: 110}}})).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*10).setChance(0.05).
build();

// Starsteel Ingot Infusion
RecipeBuilder.newBuilder("starsteelingot_infusion","modular_star_infuser",6000).
addItemInput(<astralsorcery:itemcraftingcomponent:1>*8).setChance(0.95).
addItemInput(<astralsorcery:itemcraftingcomponent:4>*2).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*3000).setChance(0.9).
addFluidInput(<liquid:astralsorcery.liquidstarlight>*1750).setChance(0.5).
addStarlightInput(16000).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*500).setChance(0.2).
addFluidOutput(<liquid:astralsorcery.liquidstarlight>*750).setChance(0.1).
addItemOutput(<additions:hsadd-ingotstarsteel>*1).
build();
