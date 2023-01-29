import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import thaumcraft.aspect.CTAspect;
import thaumcraft.aspect.CTAspectStack;
import mods.thaumcraft.Infusion;

// [Output,Instability,RemoveOldRecipe?]:[CentralItem,[Ingredients],[Aspects]]
var infuseList = {
    "":{
    // Air Infused Thaumium
    [<thaumicrestoration:item_ingot:0>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:aer>*64]],
    // Fire Infused Thaumium
    [<thaumicrestoration:item_ingot:1>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:ignis>*64]],
    // Water Infused Thaumium
    [<thaumicrestoration:item_ingot:2>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:aqua>*64]],
    // Earth Infused Thaumium
    [<thaumicrestoration:item_ingot:3>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:terra>*64]],
    // Order Infused Thaumium
    [<thaumicrestoration:item_ingot:4>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:ordo>*64]],
    // Entropy Infused Thaumium
    [<thaumicrestoration:item_ingot:5>,5,true]:[<contenttweaker:blank_thaumium_ingot>,
        [<extrautils2:ingredients:12>*4,<thaumcraft:salis_mundus>*4],
        [<aspect:metallum>*16,<aspect:perditio>*64]]
    }
};

// Mark recipes
var mark = 0;

for research,Recipes in infuseList {
    for output,input in Recipes {
        if (output[2]) {mods.thaumcraft.Infusion.removeRecipe(output[0]);}
        mods.thaumcraft.Infusion.registerRecipe("infusion_" + mark,research,output[0],output[1],input[2],input[0],input[1]);
    }
}
