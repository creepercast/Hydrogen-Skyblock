import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

var shapedList = {};

var shapelessList = {};

// Mark Recipes
var mark = 0;

for input,output in shapedList {
    recipes.addShaped("craft"+mark,input,output);
    mark += 1;
}

for input,output in shapelessList {
    recipes.addShapeless("craft"+mark,input,output);
    mark += 1;
}