#priority 9

import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;

// [[Inputs ...],Time,Energy/tick]:[[Outputs]]
var RecList = {
    "applied": {
        // Quartz Fiber
        [[<appliedenergistics2:material:3>*1,<minecraft:glass>*2],20,20]:[<appliedenergistics2:material:140>*8],
        // ME Glass Cable - Fluix
        [[<appliedenergistics2:part:140>*2,<ore:fluixQuartz>*1],20,30]:[[<appliedenergistics2:part:16>*4]],
        // Formation Core
        [[<ore:certusQuartz>*1,<appliedenergistics2:material:8>*2,<appliedenergistics2:material:22>*1],30,50]:[[<appliedenergistics2:material:43>*1]],
        // Annihilation Core
        [[<ore:netherQuartz>*1,<appliedenergistics2:material:8>*2,<appliedenergistics2:material:22>*1],30,50]:[[<appliedenergistics2:material:44>*1]],
        // Inscriber
        [[<extendedcrafting:material:2>*4,<appliedenergistics2:sky_stone_block>*2,<minecraft:piston>*2,<appliedenergistics2:material:7>*1],40,50]:[[<appliedenergistics2:inscriber>*1]],
        // Energy Acceptor
        [[<minecraft:iron_ingot>*4,<appliedenergistics2:quartz_glass>*4,<ore:fluixQuartz>*4],40,100]:[[<appliedenergistics2:energy_acceptor>*1]],
        // Crystal Growth Accelerator
        [[<minecraft:iron_ingot>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:quartz_glass>*2,<appliedenergistics2:material:7>*8],40,100]:[[<appliedenergistics2:quartz_growth_accelerator>*1]],
        // ME Controller
        [[<appliedenergistics2:sky_stone_block>*4,<appliedenergistics2:material:24>*2,<appliedenergistics2:material:12>*6],80,120]:[[<appliedenergistics2:controller>*2]],
        // ME Drive
        [[<thermalfoundation:material:352>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:material:24>*2,<appliedenergistics2:material:39>*1],80,150]:[[<appliedenergistics2:drive>*1]],
        // ME Chest
        [[<thermalfoundation:material:352>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:quartz_glass>*2,<ore:fluixQuartz>*2,<minecraft:chest>*1],80,150]:[[<appliedenergistics2:chest>*1]],
        // Molecular Assembler
        [[<appliedenergistics2:quartz_glass>*1,<thermalfoundation:material:32>*2,<minecraft:crafting_table>*1,<appliedenergistics2:material:43>*1,<appliedenergistics2:material:44>*1],60,120]:[[<appliedenergistics2:molecular_assembler>*1]],
        // ME Interface
        [[<thermalfoundation:material:32>*2,<minecraft:glass>*4,<minecraft:hopper>*2,<appliedenergistics2:material:43>*1,<appliedenergistics2:material:44>*1],60,100]:[[<appliedenergistics2:interface>*1]],
        // ME Fluid Interface
        [[<thermalfoundation:material:32>*2,<minecraft:glass>*4,<minecraft:bucket>*5,<appliedenergistics2:material:43>*1,<appliedenergistics2:material:44>*1],60,100]:[[<appliedenergistics2:fluid_interface>*1]],
        // ME Terminal
        [[<ore:itemIlluminatedPanel>*1,<appliedenergistics2:material:43>*1,<appliedenergistics2:material:44>*1,<appliedenergistics2:material:22>*1,<ore:fluixQuartz>*1],20,120]:[[<appliedenergistics2:part:380>*1]],
    },

    "botania":{
        // Runic Altar
        [[<botania:livingrock>*3,<botania:manaresource:2>*1,<botania:manaresource:23>*1],20,20]:[[<botania:runealtar>*1]],
        // Botanical Brewery
        [[<minecraft:brewing_stand>*1,<botania:livingrock>*3,<botania:livingwood>*3,<botania:manaresource:1>*1,<botania:manaresource:23>*1],60,100]:[[<botania:brewery>*1]],
        // Mana Pylon
        [[<botania:manaresource:1>*2,<botania:manaresource:2>*2,<botania:gold_ingot>*2,<botania:manaresource:23>*1],60,50]:[[<botania:pylon>*1]],
        // Natura Pylon
        [[<botania:pylon>*1,<botania:manaresource:18>*3,<minecraft:emerald>*2,<botania:livingwood>*1],80,100]:[[<botania:pylon:1>*1]],
        // Gaia Pylon
        [[<botania:pylon>*1,<botania:manaresource:7>*8,<botania:manaresource:9>*2,<botania:manaresource:8>*1],100,2000]:[[<botania:pylon:2>*1]],
        // Terrestrial Agglomeration Plate
        [[<minecraft:lapis_block>*5,<botania:manaresource:2>*2,<botania:rune:8>*1,<botania:rune:0>*1,<botania:rune:1>*1,<botania:rune:2>*1,<botania:rune:3>*1],100,200]:[[<botania:terraplate>*1]],
        // Elven Gateway Core
        [[<botania:manaresource:18>*3,<botania:livingwood>*1],200,100]:[[<botania:alfheimportal>*1]],
        // Alchemy Catalyst
        [[<botania:livingrock>*1,<minecraft:brewing_stand>*2,<botania:manaresource:23>*1,<minecraft:gold_ingot>*2],100,20]:[[<botania:alchemycatalyst>*1]],
        // Conjuration Catalyst
        [[<botania:alchemycatalyst>*1,<minecraft:redstone>*8,<botania:manaresource:7>*2,<botania:manaresource:8>*1,200,20]]:[[<botania:conjurationcatalyst>*1]],
        // Mana Detector
        [[<botania:livingrock>*1,<minecraft:redstone>*4],40,0]:[[<botania:manadetector>*1]],
        // Mana Void
        [[<botania:livingrock>*1,<minecraft:obsidian>*1],40,0]:[[<botania:manavoid>*1]],
        // Mana Fluxfield
        [[<botania:livingrock>*1,<minecraft:redstone_block>*1,<mekanism:enrichedalloy>*6],40,20]:[[<botania:rfgenerator>*1]],
        // Hovering Hourglass
        [[<botania:managlass>*1,<minecraft:redstone>*2,<minecraft:gold_ingot>*2,<botania:manaresource:23>*1],40,10]:[[<botania:hourglass>*1]],
        // Crafty Crate
        [[<minecraft:crafting_table>*1,<botania:dreamwood>*4,<botania:manaresource:8>*1],20,0]:[[<botania:opencrate:1>*1]],
    },

    "ender":{
        // Ender Crafter
        [[<minecraft:crafting_table>*1,<minecraft:ender_eye>*2,<thermalfoundation:material:359>*5],600,1000]:[[<extendedcrafting:ender_crafter>*1]],
        // Ender Alternator
        [[<minecraft:ender_eye>*2,<thermalfoundation:material:167>*1,<thermalfoundation:material:359>*4],300,1000]:[[<extendedcrafting:ender_alternator>*1]],
        // Basic Capacitor
        [[<minecraft:redstone>*5,<minecraft:iron_ingot>*4,<enderio:item_material:20>*2],40,200]:[[<enderio:item_basic_capacitor>*1]],
    },

    "engineering":{
        // Iron Mechanical Components
        [[<immersiveengineering:metal:39>*2,<minecraft:iron_ingot>*2,<ore:ingotCopper>*1],20,20]:[[<immersiveengineering:material:8>*1]],
        // Steel Mechanical Components
        [[<immersiveengineering:metal:38>*2,<immersiveengineering:metal:8>,<ore:ingotCopper>*1],30,20]:[[<immersiveengineering:material:9>]],
        // Redstone Engineering Block
        [[<immersiveengineering:metal:38>*2,<immersiveengineering:metal:39>*2,<minecraft:redstone>*4],40,50]:[[<immersiveengineering:metal_decoration0:3>*1]],
        // Light Engineering Block
        [[<immersiveengineering:metal:39>*4,<immersiveengineering:material:8>*2,<ore:ingotCopper>*2],40,50]:[[<immersiveengineering:metal_decoration0:4>*1]],
        // Heavy Engineering Block
        [[<immersiveengineering:metal:38>*4,<immersiveengineering:material:9>*2,<ore:ingotElectrum>*2],40,50]:[[<immersiveengineering:metal_decoration0:5>*1]],
    },

    "mek":{
        // Advanced Control Circuit
        [[<mekanism:enrichedalloy>*4,<mekanism:controlcircuit>*1],20,200]:[[<mekanism:controlcircuit:1>*1]],
        // Elite Control Circuit
        [[<mekanism:reinforcedalloy>*4,<mekanism:controlcircuit:1>*1],20,300]:[[<mekanism:controlcircuit:2>*1]],
        // Ultimate Control Circuit
        [[<mekanism:atomicalloy>*4,<mekanism:controlcircuit:2>*1],20,400]:[[<mekanism:controlcircuit:3>*1]],

        // Speed Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:enrichedalloy>*2,<mekanism:ingot:3>*2],40,100]:[[<mekanism:speedupgrade>*1]],
        // Energy Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:enrichedalloy>*2,<minecraft:redstone>*10],40,100]:[[<mekanism:energyupgrade>*1]],
        // Filter Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:enrichedalloy>*2,<minecraft:paper>*5],40,100]:[[<mekanism:filterupgrade>*1]],
        // Muffling Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:enrichedalloy>*2,<minecraft:wool>*1],40,100]:[[<mekanism:mufflingupgrade>*1]],
        // Gas Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:enrichedalloy>*2,<mekanism:polyethene:2>*3],40,100]:[[<mekanism:gasupgrade>*1]],
        // Anchor Upgrade
        [[<thermalfoundation:material:32>*1,<mekanism:reinforcedalloy>*2,<mekanism:teleportationcore>*1],40,100]:[[<mekanism:anchorupgrade>*1]],

        // Tier Installer
        [[<thermalfoundation:material:32>*1,<mekanism:controlcircuit>*2,<minecraft:redstone>*8],100,200]:[[<mekanism:tierinstaller>*1]],
        [[<thermalfoundation:material:?>*1,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*4],120,400]:[[<mekanism:tierinstaller:1>*1]],
        [[<thermalfoundation:material:352>*1,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*4],140,800]:[[<mekanism:tierinstaller:2>*2]],
        [[<thermalfoundation:material:354>*1,<mekanism:controlcircuit:3>*2,<mekanism:atomicalloy>*4],160,1600]:[[<mekanism:tierinstaller:3>*1]],

        // Enrichment Chamber
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<minecraft:iron_ingot>*4,<minecraft:redstone>*4],150,50]:[[<mekanism:machineblock>*1]],
        // Osmium Compressor
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*2,<minecraft:bucket>*1,<mekanism:enrichedalloy>*2],150,50]:[[<mekanism:machineblock:1>*1]],
        // Combiner
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:2>*2,<mekanism:enrichedalloy>*2,<minecraft:piston>*2],100,50]:[[<mekanism:machineblock:2>*1]],
        // Crusher
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<minecraft:iron_ingot>*4,<minecraft:piston>*1],100,50]:[[<mekanism:machineblock:3>*1]],
        // Metallugic Infuser
        [[<mekanism:basicblock:8>*1,<minecraft:iron_ingot>*4,<minecraft:redstone>*4],100,50]:[[<mekanism:machineblock:8>*1]],
        // Purification Chamber
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*2,<minecraft:water_bucket>*1],150,50]:[[<mekanism:machineblock:9>*1,<minecraft:bucket>*1]],
        // Energized Smelter
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<minecraft:furance>*1],100,50]:[[<mekanism:machineblock:10>*1]],
        // Electric Pump
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<minecraft:bucket>*1],100,50]:[[<mekanism:machineblock:12>*1]],
        // Personal Chest
        [[<minecraft:chest>*2,<ore:ingotSteel>*2,<minecraft:paper>*1],80,50]:[[<mekanism:machineblock:13>*1]],
        // Rotary Condensetrator
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<mekanism:machineblock2:11>*1,<mekanism:gastank>*1],100,50]:[[<mekanism:machineblock2>*1]],

        // Chemical Oxidizer
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*2,<minecraft:hopper>*1,<mekanism:gastank>*1],120,75]:[[<mekanism:machineblock2:1>*1]],
        // Chemical Infuser
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*2,<mekanism:gastank>*1],120,75]:[[<mekanism:machineblock2:2>*1]],
        // Chemical Injection Chamber
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*2,<mekanism:gastank>*1],120,100]:[[<mekanism:machineblock2:4>*1]],
        // Electrolytic Separator
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*3,<mekanism:electrolyticcore>*1,<mekanism:gastank>*1],120,75]:[[<mekanism:machineblock2:4>*1]],
        // Precison Sawmill
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*4],100,50]:[[<mekanism:machineblock2:5>*1]],
        // Chemical Dissolution Chamber
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*2,<mekanism:gastank>*1],120,150]:[[<mekanism:machineblock2:6>*1]],
        // Chemical Washer
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:1>*3,<mekanism:machineblock2:11>*1,<mekanism:gastank>*1],120,100]:[[<mekanism:machineblock2:7>*1]],
        // Chemical Crystallizer
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:2>*2,<mekanism:atomicalloy>*2,<mekanism:gastank>*1],120,100]:[[<mekanism:machineblock2:8>*1]],
        // Pressurized Reaction Chamber
        [[<mekanism:machineblock>*1,<mekanism:controlcircuit:2>*2,<mekanism:gastank>*1,<mekanism:machineblock2:11>*1],120,100]:[[<mekanism:machineblock2:10>*1]],
        // Basic Fluid Tank
        [[<minecraft:glass>*1,<minecraft:iron_ingot>*2,<minecraft:bucket>*5],100,50]:[[<mekanism:machineblock2:11>*1]],

        // Solar Neutron Activator
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*2,<mekanismgenerators:solarpanel>*1,<mekanism:gastank>*1],150,150]:[[<mekanism:machineblock3:1>]],
        // Formulaic Assemblicator
        [[<mekanism:basicblock:8>*1,<mekanism:controlcircuit>*2,<minecraft:hopper>*1,<minecraft:crafting_table>*1],100,50]:[[<mekanism:machineblock3:5>*1]],
    },

    "modular":{
        // Machine Casing
        [[<modularmachinery:itemmodularium>*1,<minecraft:redstone>*2],10,50]:[[<modularmachinery:blockcasing:0>*1]],
        // Machine Controller
        [[<modularmachinery:blockcasing>*1,<modularmachinery:itemmodularium>*2,<minecraft:diamond>*1,<minecraft:redstone>*8],20,50]:[[<modularmachinery:blockcontroller>*1]],
        // Reinforced Machine Casing
        [[<modularmachinery:blockcasing>*1,<modularmachinery:itemmodularium>*2,<minecraft:obsidian>*2],20,50]:[[<modularmachinery:blockcasing:4>*1]],
        // Machine Vent
        [[<modularmachinery:blockcasing>*1,<modularmachinery:itemmodularium>*1,<minecraft:iron_bars>*6],20,50]:[[<modularmachinery:blockcasing:1>*1]],
        // Firebox Casing
        [[<modularmachinery:blockcasing>*1,<modularmachinery:itemmodularium>*1,<thermalfoundation:material:24>*8],20,50]:[[<modularmachinery:blockcasing:3>*1]],
        // Machine Circuitry
        [[<modularmachinery:blockcasing>*1,<modularmachinery:itemmodularium>*1,<mekanism:controlcircuit>*4],20,50]:[[<modularmachinery:blockcasing:5>*1]],
        // Mana Input
        [[<modularmachinery:blockcasing>*1,<botania:pool>*1],20,50]:[[<gugu-utils:sparkmanahatch:1>*1]],
        // Starlight Input (Basic)
        [[<modularmachinery:blockcasing>*1,<astralsorcery:itemcraftingcomponent>*2],20,50]:[[<gugu-utils:starlightinputhatch>*1]],
        // Starlight Input (Trait)
        [[<gugu-utils:starlightinputhatch>*1,<astralsorcery:itemcraftingcomponent:1>*4],20,50]:[[<gugu-utils:starlightinputhatch:1>*1]],
        // Starlight Input (Brilliant)
        [[<gugu-utils:starlightinputhatch:1>*1,<astralsorcery:itemcraftingcomponent:4>*12,],20,50]:[[<gugu-utils:starlightinputhatch:2>*1]],
    },

    "thermal":{
        // 
    },
};


// Mark Recipes
var mark = 0;

for name,Recipe in RecList {
    for input,output in Recipe {
        // Remove Recipe of First Item in output
        recipes.remove(output[0][0]);

        var reci = RecipeBuilder.newBuilder("assemble_" + mark,name + "_assembler",input[1]);
        for item in input[0] {
            reci.addItemInput(item);
        }
        if (name != "botania") { // Botania Assembler: Energy -> Mana
            reci.addEnergyPerTickInput(input[2]);
        } else {
            reci.addManaPerTickInput(input[2]);
        }
        for item in output[0] {
            reci.addItemOutput(item);
        }
        reci.build();

        mark += 1;
    }
}