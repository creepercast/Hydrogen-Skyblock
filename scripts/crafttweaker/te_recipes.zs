import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

// Frozen equ Pear
mods.thermalexpansion.Transformer.addFillRecipe(5000,<additions:hsadd-frozen_equ_pear>*1,<additions:hsadd-equ_pear>,<liquid:cryotheum>*500);

// Machine Frame
mods.tconstruct.Casting.addBasinRecipe(<thermalexpansion:frame>,<immersiveengineering:metal_decoration1:1>,<liquid:tin>,144,true,30);

// Machine Casing
recipes.addShaped(
    <additions:hsadd-thermal_casing>*1,[
        [null,<thermalfoundation:material:354>,null],
        [<thermalfoundation:material:354>,<thermalexpansion:frame>,<thermalfoundation:material:354>],
        [null,<thermalfoundation:material:354>,null]
    ]
);
