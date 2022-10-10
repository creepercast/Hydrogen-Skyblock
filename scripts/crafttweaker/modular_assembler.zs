import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;
import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// 1 to 6 Ports
var allPorts as IItemStack[][IItemStack] = {
    <minecraft:hopper>:[
        <modularmachinery:blockcasing>,
        <modularmachinery:blockinputbus>,<modularmachinery:blockinputbus:1>,<modularmachinery:blockinputbus:2>,
        <modularmachinery:blockcasing:4>,
        <modularmachinery:blockinputbus:3>,<modularmachinery:blockinputbus:4>,<modularmachinery:blockinputbus:5>,<modularmachinery:blockinputbus:6>
    ],
    <minecraft:bucket>:[
        <modularmachinery:blockcasing>,
        <modularmachinery:blockfluidinputhatch>,<modularmachinery:blockfluidinputhatch:1>,<modularmachinery:blockfluidinputhatch:2>,
        <modularmachinery:blockcasing:4>,
        <modularmachinery:blockfluidinputhatch:3>,<modularmachinery:blockfluidinputhatch:4>,<modularmachinery:blockfluidinputhatch:5>,<modularmachinery:blockfluidinputhatch:6>
    ],
    <minecraft:redstone>:[
        <modularmachinery:blockcasing>,
        <modularmachinery:blockenergyinputhatch>,<modularmachinery:blockenergyinputhatch:1>,<modularmachinery:blockenergyinputhatch:2>,
        <modularmachinery:blockcasing:4>,
        <modularmachinery:blockenergyinputhatch:3>,<modularmachinery:blockenergyinputhatch:4>,<modularmachinery:blockenergyinputhatch:5>,<modularmachinery:blockenergyinputhatch:6>
    ]
};

// Mark Recipes
var mark = 0;
// Recipe Levels
var amount = 1;

for item,Ports in allPorts {
    for type in [1,2,3,5,6,7,8] { // All types can be added
        RecipeBuilder.newBuilder("assemble_port"+mark,"modular_assembler",amount * 10).
        addItemInput(Ports[type - 1] * 1).
        addItemInput(<modularmachinery:itemmodularium>*1).
        addItemInput(item * 1).
        addEnergyPerTickInput(50).
        addItemOutput(Ports[type] * 1).
        build();

        amount += 1;
        mark += 1;
    }
    amount = 1;
}
