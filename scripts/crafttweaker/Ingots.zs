import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// List <item>:<9items>
var itemList = {
    // Aquamarine
    <astralsorcery:itemcraftingcomponent>:<additions:hsadd-blockaquamarine>,
    // Starmetal
    <astralsorcery:itemcraftingcomponent:1>:<additions:hsadd-blockstarmetal>,
    // Modularium
    <additions:hsadd-nuggetmodularium>:<modularmachinery:itemmodularium>,
    <modularmachinery:itemmodularium>:<additions:hsadd-blockmodularium>,
};

for item1,item2 in itemList {
    recipes.addShapeless(item2*1,[item1,item1,item1,item1,item1,item1,item1,item1]);
    recipes.addShapeless(item1*9,[item2]);
}
