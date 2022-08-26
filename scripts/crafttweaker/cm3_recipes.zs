import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Miniaturization Field Projector
recipes.remove(<compactmachines3:fieldprojector>);

recipes.addShaped(
    <compactmachines3:fieldprojector>*1,[
        [null,<minecraft:ender_pearl>,null],
        [<immersiveengineering:metal:38>,<minecraft:redstone_block>,<immersiveengineering:metal:38>],
        [<extrautils2:decorativesolid:3>,<immersiveengineering:metal:38>,<extrautils2:decorativesolid:3>]
    ]
);
