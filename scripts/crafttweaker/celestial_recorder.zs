import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;

// Write Celestial Notes

// Sun (Note 0)
RecipeBuilder.newBuilder("tc_n_day","celestial_recorder",200).
addItemInput(<additions:hsadd-blankcelestialnotes>*1).
setAltitude(128,256).
setTime(450,11000).
setDimension([0]).
addEnergyPerTickInput(50).
addItemOutput(<thaumcraft:celestial_notes:0>*1).
build();

var noteList1 = [ // Stars
    <thaumcraft:celestial_notes:1>,<thaumcraft:celestial_notes:2>,<thaumcraft:celestial_notes:3>,<thaumcraft:celestial_notes:4>
];
var noteList2 = [ // Moon
    <thaumcraft:celestial_notes:5>,<thaumcraft:celestial_notes:6>,<thaumcraft:celestial_notes:7>,<thaumcraft:celestial_notes:8>,
    <thaumcraft:celestial_notes:9>,<thaumcraft:celestial_notes:10>,<thaumcraft:celestial_notes:11>,<thaumcraft:celestial_notes:12>
];
// Stars & Moon
for i in [0,1,2,3,4,5,6,7] {
    var reci = RecipeBuilder.newBuilder("tc_n_night"+i,"celestial_recorder",200);
    reci.addItemInput(<additions:hsadd-blankcelestialnotes>*1);
    reci.setAltitude(128,256).setTime(13800,22000).setDimension([0]).setMoonPhase([i]).addEnergyPerTickInput(50);
    for item in noteList1 {
        reci.addItemOutput(item*1).setChance(0.18);
    }
    reci.addItemOutput(noteList2[i]).setChance(0.18);
    reci.addItemOutput(<additions:hsadd-blankcelestialnotes>*1).setChance(0.1);
    reci.build();
}
