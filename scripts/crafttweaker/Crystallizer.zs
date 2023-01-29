import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;

val mC = <contenttweaker:mana_crystal>; // Mana Crystal
val eC = <contenttweaker:elven_crystal>; // Elven Crystal

var recipeList = {
    "mana":{
        [<botania:storage>*1,810]:[[mC*1,1],[mC*1,0.08],[<minecraft:iron_block>*1,0.85],[<minecraft:iron_ingot>*9,0.1],[<minecraft:iron_nugget>*81,0.05]],
        [<botania:manaresource>*1,90]:[[mC*1,0.12],[<minecraft:iron_ingot>*1,0.9],[<minecraft:iron_nugget>*9,0.1]],
        [<botania:manaresource:17>*1,10]:[[mC*1,0.0133],[<minecraft:iron_nugget>*1,1]],
        [<botania:manaresource:1>*1,180]:[[mC*1,0.24],[<minecraft:ender_pearl>*1,1]],
        [<botania:manaresource:2>*1,300]:[[mC*1,0.4],[<minecraft:diamond>*1]],
        [<botania:storage:3>*1,2700]:[[mC*3,1],[mC*1,0.6],[<minecraft:diamond_block>*1,0.9],[<minecraft:diamond>*9,0.1]],
        [<botanicadds:mana_lapis>*1,150]:[[mC*1,0.2],[<minecraft:dye:4>*1]],
        [<botanicadds:mana_lapis_block>*1,1350]:[[mC*1,1],[mC*1,0.8],[<minecraft:lapis_block>*1,0.9],[<minecraft:dye:4>*9,0.1]],
    },
    "elven":{
        [<botania:storage:2>*1,810]:[[eC*1,1],[eC*1,0.08],[<minecraft:iron_block>*1,0.85],[<minecraft:iron_ingot>*9,0.1],[<minecraft:iron_nugget>*81,0.05]],
        [<botania:manaresource:7>*1,90]:[[eC*1,0.12],[<minecraft:iron_ingot>*1,0.9],[<minecraft:iron_nugget>*9,0.1]],
        [<botania:manaresource:19>*1,10]:[[eC*1,0.0133],[<minecraft:iron_nugget>*1,1]],
        [<botania:manaresource:8>*1,180]:[[eC*1,0.24],[<minecraft:ender_pearl>*1,0.25]],
        [<botania:manaresource:9>*1,300]:[[eC*1,0.4],[<minecraft:diamond>*1]],
        [<botania:storage:4>*1,2700]:[[eC*3,1],[eC*1,0.6],[<minecraft:diamond_block>*1,0.9],[<minecraft:diamond>*9,0.1]],
        [<botanicadds:elven_lapis>*1,150]:[[eC*1,0.2],[<minecraft:dye:4>*1]],
        [<botanicadds:elven_lapis_block>*1,1350]:[[eC*1,1],[mC*1,0.8],[<minecraft:lapis_block>*1,0.9],[<minecraft:dye:4>*9,0.1]],
    }
};

// Mark Recipes
var mark = 0;

for type,Recipes in recipeList {
    for inputs,outputs in Recipes {
        reci = RecipeBuilder.newBuilder("crystallize_" + mark,type + "_crystallizer",inputs[1]);
        reci.addItemInput(inputs[0]);
        reci.addManaPerTickInput(10);
        for item in outputs{
            if (item[1] != 1) {
                reci.addItemOutput(item[0]).setChance(item[1]);
            } else {
                reci.addItemOutput(item[0]);
            }
        }
        reci.addManaPerTickOutput(20);
        reci.build();

        mark += 1;
    }
}
