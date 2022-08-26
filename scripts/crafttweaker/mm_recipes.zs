import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Modular Machinery Recipe Rebuild

recipes.removeByMod("modularmachinery");

// Machine Controller
recipes.addShaped(
    <modularmachinery:blockcontroller>*1,[
        [null,<minecraft:diamond>,null],
        [<modularmachinery:itemmodularium>,<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>],
        [<minecraft:redstone>,<minecraft:quatz>,<minecraft:redstone>]
    ]
);

// Tiny Item Input Bus
recipes.addShaped(
    <modularmachinery:blockinputbus>*1,[
        [null,<minecraft:redstone>,null],
        [<modularmachinery:itemmodularium>,<minecraft:hopper>,<modularmachinery:itemmodularium>],
        [null,<modularmachinery:blockcasing>,null]
    ]
);

// Small Item Input Bus
recipes.addShaped(
    <modularmachinery:blockinputbus:1>*1,[
        [null,<minecraft:hopper>,null],
        [<minecraft:redstone>,<minecraft:hopper>,<minecraft:redstone>],
        [<modularmachinery:itemmodularium>,<modularmachinery:blockinputbus>,<modularmachinery:itemmodularium>]
    ]
);

// Tiny Fluid Input Hatch
recipes.addShaped(
    <modularmachinery:blockfluidinputhatch>*1,[
        [null,<minecraft:bucket>,null],
        [<modularmachinery:itemmodularium>,<minecraft:bucket>,<modularmachinery:itemmodularium>],
        [null,<modularmachinery:blockcasing>,null]
    ]
);

// Small Fluid Input Hatch
recipes.addShaped(
    <modularmachinery:blockfluidinputhatch:1>*1,[
        [null,<minecraft:bucket>,null],
        [<modularmachinery:itemmodularium>,<minecraft:bucket>,<modularmachinery:itemmodularium>],
        [null,<modularmachinery:blockfluidinputhatch>,null]
    ]
);

// Tiny Energy Input Hatch
recipes.addShaped(
    <modularmachinery:blockenergyinputhatch>*1,[
        [null,<modularmachinery:itemmodularium>,null],
        [<minecraft:redstone>,<modularmachinery:blockcasing>,<minecraft:redstone>],
        [<minecraft:repeater>,<modularmachinery:itemmodularium>,<minecraft:repeater>]
    ]
);

// Small Energy Input Hatch
recipes.addShaped(
    <modularmachinery:blockenergyinputhatch:1>,[
        [<minecraft:redstone_torch>,<modularmachinery:itemmodularium>,<minecraft:redstone_torch>],
        [null,<modularmachinery:blockenergyinputhatch>,null],
        [<minecraft:comparator>,<modularmachinery:itemmodularium>,<minecraft:comparator>]
    ]
);



// Inputs/Outputs
var changeList = {
    // Item
    <modularmachinery:blockinputbus>:<modularmachinery:blockoutputbus>,
    <modularmachinery:blockinputbus:1>:<modularmachinery:blockoutputbus:1>,
    <modularmachinery:blockinputbus:2>:<modularmachinery:blockoutputbus:2>,
    <modularmachinery:blockinputbus:3>:<modularmachinery:blockoutputbus:3>,
    <modularmachinery:blockinputbus:4>:<modularmachinery:blockoutputbus:4>,
    <modularmachinery:blockinputbus:5>:<modularmachinery:blockoutputbus:5>,
    // Fluid
    <modularmachinery:blockfluidinputhatch>:<modularmachinery:blockfluidoutputhatch>,
    <modularmachinery:blockfluidinputhatch:1>:<modularmachinery:blockfluidoutputhatch:1>,
    <modularmachinery:blockfluidinputhatch:2>:<modularmachinery:blockfluidoutputhatch:2>,
    <modularmachinery:blockfluidinputhatch:3>:<modularmachinery:blockfluidoutputhatch:3>,
    <modularmachinery:blockfluidinputhatch:4>:<modularmachinery:blockfluidoutputhatch:4>,
    <modularmachinery:blockfluidinputhatch:5>:<modularmachinery:blockfluidoutputhatch:5>,
    // Energy
    <modularmachinery:blockenergyinputhatch>:<modularmachinery:blockenergyoutputhatch>,
    <modularmachinery:blockenergyinputhatch:1>:<modularmachinery:blockenergyoutputhatch:1>,
    <modularmachinery:blockenergyinputhatch:2>:<modularmachinery:blockenergyoutputhatch:2>,
    <modularmachinery:blockenergyinputhatch:3>:<modularmachinery:blockenergyoutputhatch:3>,
    <modularmachinery:blockenergyinputhatch:4>:<modularmachinery:blockenergyoutputhatch:4>,
    <modularmachinery:blockenergyinputhatch:5>:<modularmachinery:blockenergyoutputhatch:5>,
    // Mana
    <gugu-utils:sparkmanahatch>:<gugu:sparkmanahatch:1>,
};

for item1,item2 in changeList {
    recipes.addShapeless(item1,[item2]);
    recipes.addShapeless(item2,[item1]);
}