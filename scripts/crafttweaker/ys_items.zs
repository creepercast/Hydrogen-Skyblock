import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

recipes.addShaped(
    <additions:hsadd-ys_goodmen>*1,[
        [null,<minecraft:wheat>,null],
        [<mekanism:salt>,<minecraft:water_bucket>,<tconstruct:nuggets>],
        [null,<additions:hsadd-ys_papercup>,null]
    ]
);
recipes.addShaped(
    <additions:hsadd-ys_papercup>*1,[
        [<minecraft:paper>,null,<minecraft:paper>],
        [<minecraft:paper>,null,<minecraft:paper>],
        [<minecraft:paper>,<minecraft:paper>,<minecraft:paper>]
    ]
);
