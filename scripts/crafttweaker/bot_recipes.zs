import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Blank Rune
// This Recipe will change to other.
recipes.addShapeless(
    <additions:hsadd-blankrune>*2,[
        <botania:livingrock>
    ]
);

// Tweak all Botania Runes
recipes.remove(<botania:rune>);
recipes.remove(<botania:rune:1>);
recipes.remove(<botania:rune:2>);
recipes.remove(<botania:rune:3>);
recipes.remove(<botania:rune:4>);
recipes.remove(<botania:rune:5>);
recipes.remove(<botania:rune:6>);
recipes.remove(<botania:rune:7>);
recipes.remove(<botania:rune:8>);
recipes.remove(<botania:rune:9>);
recipes.remove(<botania:rune:10>);
recipes.remove(<botania:rune:11>);
recipes.remove(<botania:rune:12>);
recipes.remove(<botania:rune:13>);
recipes.remove(<botania:rune:14>);
recipes.remove(<botania:rune:15>);

// Tweak Red String
recipes.remove(<botania:manaresource:12>);
recipes.addShapeless(
    <botania:manaresource:12>*1,[
        <minecraft:string>,<minecraft:redstone>
    ]
);


// Tweak Rune of Teleportation
recipes.remove(<botanicadds:rune_tp>);

// Tweak Rune of Energy
recipes.remove(<botanicadds:rune_energy>);
