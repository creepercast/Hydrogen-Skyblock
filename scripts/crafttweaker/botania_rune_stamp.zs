import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;

var runes = [<botania:rune:0>,<botania:rune:1>,<botania:rune:2>,<botania:rune:3>,<botania:rune:4>,<botania:rune:5>,<botania:rune:6>,<botania:rune:7>,<botania:rune:8>];

// [[Input ...],Level]:[Output ...]
// 'Level' must be Int/Float
var runeList = {
    // Rune of Water
    [[<minecraft:waterlily>*2,<minecraft:water_bucket>*1],1]:[[<botania:rune:0>*2,<minecraft:bucket>*1]],
    // Rune of Fire
    [[<minecraft:blaze_powder>*2,<minecraft:nether_wart>*16],1]:[[<botania:rune:1>*2]],
    // Rune of Earth
    [[<minecraft:stone>*16,<minecraft:coal_block>*2],1]:[[<botania:rune:2>*2]],
    // Rune of Air
    [[<minecraft:feather>*2,<minecraft:string>*2],1]:[[<botania:rune:3>*2]],
    // Rune of Spring
    [[<botania:rune:0>*1,<botania:rune:2>*2,<minecraft:sapling>*16],2]:[[<botania:rune:4>*1]],
    // Rune of Summer
    [[<botania:rune:1>*1,<botania:rune:2>*1,<minecraft:sand>*16],2]:[[<botania:rune:5>*1]],
    // Rune of Autumn
    [[<botania:rune:1>*1,<botania:rune:3>*1,<minecraft:wheat>*16],2]:[[<botania:rune:6>*1]],
    // Rune of Winter
    [[<botania:rune:0>*1,<botania:rune:2>*1,<minecraft:ice>*16],2]:[[<botania:rune:7>*1]],
    // Rune of Mana
    [[<botania:manaresource>*5,<botania:manaresource:1>*2,<botania:manaresource:23>*2],1.5]:[[<botania:rune:8>*1]],
    // Rune of ?
    [[<botania:rune:7>*1,<botania:rune:3>*1],3]:[[<botania:rune:9>*1]],
    // Rune of Gluttony
    [[<botania:rune:1>*1,<botania:rune:7>*1],3]:[[<botania:rune:10>*1]],
    // Rune of Greed
    [[<botania:rune:4>*1,<botania:rune:0>*1],3]:[[<botania:rune:11>*1]],
    // Rune of Sloth
    [[<botania:rune:6>*1,<botania:rune:3>*1],3]:[[<botania:rune:12>*1]],
    // Rune of Wrath
    [[<botania:rune:7>*1,<botania:rune:2>*1],3]:[[<botania:rune:13>*1]],
    // Rune of Envy
    [[<botania:rune:0>*1,<botania:rune:7>*1],3]:[[<botania:rune:14>*1]],
    // Rune of Pride
    [[<botania:rune:5>*1,<botania:rune:1>*1],3]:[[<botania:rune:15>*1]],

    // Rune of Teleporation
    [[<botania:rune:8>*1,<minecraft:ender_pearl>*8],2.5]:[[<botanicadds:rune_tp>*1]],
    // Rune of Energy
    [[<botania:rune:8>*1,<minecraft:redstone>*8],2.5]:[[<botanicadds:rune_energy>*1]],
};

// Mark recipes
var mark = 0;

for input,output in runeList {
    recipes.remove(output[0][0]);

    var reci = RecipeBuilder.newBuilder("stamp_"+mark,"botania_rune_stamp",input[1]*20);    
    if (input[1] == 1) { // All runes nend Blank Rune, Lv1 Runes need Mana Powder.
        reci.addItemInput(<additions:hsadd-blankrune>*2).addItemInput(<botania:manaresource:23>*2);
    } else {
        reci.addItemInput(<additions:hsadd-blankrune>*1);
    }
    for item in input[0] {
        if (item in runes) { // Runes only have a small consume chance.
            reci.addItemInput(item).setChance(0.05);
        } else {
            reci.addItemInput(item);
        }
    }
    if ((input[1] == 2.5) || (input[1] == 3)) { // Lv2.5~3 Runes needs ?.
        reci.addItemInput(<botania:manaresource:2>*2);
    }
    reci.addManaPerTickInput(80 + input[1]*20);
    for item in output[0] {
        reci.addItemOutput(item);
    }
    reci.build();

    mark += 1;
}
