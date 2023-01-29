import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.appliedenergistics2.Inscriber;

// Sieve recipe of Certus Quartz has been removed in config.

// First Bool : false:Consume All ; true:Consume First
// Second Bool : true:remove old recipes ; true:remain
var inscriberRecipe = {
    // Fluix Pearl
    [<minecraft:ender_pearl>,<enderio:item_material:34>,<appliedenergistics2:material:12>]:[<appliedenergistics2:material:9>,false,true]
};

// Mark Recipe
var mark = 0;
for input,output in inscriberRecipe {
    if (output[2]) {
        recipes.remove(output[0]);
    }
    Inscriber.addRecipe(output[0],input[0],output[1],input[1],input[2]);
}
