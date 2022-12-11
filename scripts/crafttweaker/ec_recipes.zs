import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Extended Crafting recipes rebuild

recipes.removeByMod("extendedcrafting");

// Black Iron Slate
mods.immersiveengineering.MetalPress.addRecipe(
    <extendedcrafting:material:2>,<extendedcrafting:material>,<immersiveengineering:mold:0>,2000,1);

// Black Iron Rod
mods.immersiveengineering.MetalPress.addRecipe(
    <extendedcrafting:material:3>,<extendedcrafting:material>,<immersiveengineering:mold:2>,2000,1);

// Crafting Catalyst
var CataList = {
    <extendedcrafting:material:14>:<extendedcrafting:material:8>,
    <extendedcrafting:material:15>:<extendedcrafting:material:9>,
    <extendedcrafting:material:16>:<extendedcrafting:material:10>,
    <extendedcrafting:material:17>:<extendedcrafting:material:11>,
    <extendedcrafting:material:18>:<extendedcrafting:material:12>,
    <extendedcrafting:material:19>:<extendedcrafting:material:13>,
};
for input,output in CataList {
    recipes.remove(output);
    mods.immersiveengineering.MetalPress.addRecipe(output,input,<extendedcrafting:material:2>,2000,1);
}
