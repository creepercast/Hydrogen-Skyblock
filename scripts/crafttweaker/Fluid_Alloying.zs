#priority 9

import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.tconstruct.Alloy;

var AlloyList = {
    // Nucleum
    [<liquid:proxii>*2,<liquid:obsidiorite>*4,<liquid:uranium>*2]:[<liquid:nucleum>*4,true],
    // Yrdeen
    [<liquid:valyrium>*3,<liquid:osram>*3,<liquid:manyullyn>*2,<liquid:uru>]:[<liquid:yrdeen>*6,true]
};

for input,output in AlloyList {
    if (output[1]) {
        mods.tconstruct.Alloy.removeRecipe(output[0]);
    }
    mods.tconstruct.Alloy.addRecipe(output,input);
}
