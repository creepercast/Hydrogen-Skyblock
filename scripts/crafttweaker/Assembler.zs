#priority 9

import crafttweaker.item.IItemStack;
import crafttweaker.item.IIngredient;
import crafttweaker.liquid.ILiquidStack;
import crafttweaker.oredict.IOreDict;
import crafttweaker.oredict.IOreDictEntry;
import crafttweaker.data.IData;

import mods.modularmachinery.RecipePrimer;
import mods.modularmachinery.RecipeBuilder;

// [[Inputs ...], Ticks, Energy/tick]: [[Outputs ...]]
var RecList = {
    "applied": {
        // Quartz Fiber
        [[<appliedenergistics2:material:3>,<minecraft:glass>*2],20,20]:[[<appliedenergistics2:material:140>*8]],
        // ME Glass Cable - Fluix
        [[<appliedenergistics2:part:140>*2,<ore:fluixQuartz>],20,30]:[[<appliedenergistics2:part:16>*4]],
        // Formation Core
        [[<ore:certusQuartz>,<appliedenergistics2:material:8>*2,<appliedenergistics2:material:22>],30,50]:[[<appliedenergistics2:material:43>]],
        // Annihilation Core
        [[<ore:netherQuartz>,<appliedenergistics2:material:8>*2,<appliedenergistics2:material:22>],30,50]:[[<appliedenergistics2:material:44>]],
        // Inscriber
        [[<extendedcrafting:material:2>*4,<appliedenergistics2:sky_stone_block>*2,<minecraft:piston>*2,<appliedenergistics2:material:7>],40,50]:[[<appliedenergistics2:inscriber>]],
        // Energy Acceptor
        [[<minecraft:iron_ingot>*4,<appliedenergistics2:quartz_glass>*4,<ore:fluixQuartz>*4],40,100]:[[<appliedenergistics2:energy_acceptor>]],
        // Crystal Growth Accelerator
        [[<minecraft:iron_ingot>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:quartz_glass>*2,<appliedenergistics2:material:7>*8],40,100]:[[<appliedenergistics2:quartz_growth_accelerator>]],
        // ME Controller
        [[<appliedenergistics2:sky_stone_block>*4,<appliedenergistics2:material:24>*2,<appliedenergistics2:material:12>*6],80,120]:[[<appliedenergistics2:controller>*2]],
        // ME Drive
        [[<thermalfoundation:material:352>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:material:24>*2,<appliedenergistics2:material:39>],80,150]:[[<appliedenergistics2:drive>]],
        // ME Chest
        [[<thermalfoundation:material:352>*4,<appliedenergistics2:part:16>*2,<appliedenergistics2:quartz_glass>*2,<ore:fluixQuartz>*2,<minecraft:chest>],80,150]:[[<appliedenergistics2:chest>]],
        // Molecular Assembler
        [[<appliedenergistics2:quartz_glass>,<thermalfoundation:material:32>*2,<minecraft:crafting_table>,<appliedenergistics2:material:43>,<appliedenergistics2:material:44>],60,120]:[[<appliedenergistics2:molecular_assembler>]],
        // ME Interface
        [[<thermalfoundation:material:32>*2,<minecraft:glass>*4,<minecraft:hopper>*2,<appliedenergistics2:material:43>,<appliedenergistics2:material:44>],60,100]:[[<appliedenergistics2:interface>]],
        // ME Fluid Interface
        [[<thermalfoundation:material:32>*2,<minecraft:glass>*4,<minecraft:bucket>*5,<appliedenergistics2:material:43>,<appliedenergistics2:material:44>],60,100]:[[<appliedenergistics2:fluid_interface>]],
        // ME Terminal
        [[<ore:itemIlluminatedPanel>,<appliedenergistics2:material:43>,<appliedenergistics2:material:44>,<appliedenergistics2:material:22>,<ore:fluixQuartz>],20,120]:[[<appliedenergistics2:part:380>]],
    },

    "botania":{
        // Runic Altar
        [[<botania:livingrock>*3,<botania:manaresource:2>,<botania:manaresource:23>],20,20]:[[<botania:runealtar>]],
        // Botanical Brewery
        [[<minecraft:brewing_stand>,<botania:livingrock>*3,<botania:livingwood>*3,<botania:manaresource:1>,<botania:manaresource:23>],60,100]:[[<botania:brewery>]],
        // Mana Pylon
        [[<botania:manaresource:1>*2,<botania:manaresource:2>*2,<minecraft:gold_ingot>*2,<botania:manaresource:23>],60,50]:[[<botania:pylon>]],
        // Natura Pylon
        [[<botania:pylon>,<botania:manaresource:18>*3,<minecraft:emerald>*2,<botania:livingwood>],80,100]:[[<botania:pylon:1>]],
        // Gaia Pylon
        [[<botania:pylon>,<botania:manaresource:7>*8,<botania:manaresource:9>*2,<botania:manaresource:8>],100,2000]:[[<botania:pylon:2>]],
        // Terrestrial Agglomeration Plate
        [[<minecraft:lapis_block>*5,<botania:manaresource:2>*2,<botania:rune:8>,<botania:rune:0>,<botania:rune:1>,<botania:rune:2>,<botania:rune:3>],100,200]:[[<botania:terraplate>]],
        // Elven Gateway Core
        [[<botania:manaresource:18>*3,<botania:livingwood>],200,100]:[[<botania:alfheimportal>]],
        // Alchemy Catalyst
        [[<botania:livingrock>,<minecraft:brewing_stand>*2,<botania:manaresource:23>,<minecraft:gold_ingot>*2],100,20]:[[<botania:alchemycatalyst>]],
        // Conjuration Catalyst
        [[<botania:alchemycatalyst>,<minecraft:redstone>*8,<botania:manaresource:7>*2,<botania:manaresource:8>,200,20]]:[[<botania:conjurationcatalyst>]],
        // Mana Detector
        [[<botania:livingrock>,<minecraft:redstone>*4],40,0]:[[<botania:manadetector>]],
        // Mana Void
        [[<botania:livingrock>,<minecraft:obsidian>],40,0]:[[<botania:manavoid>]],
        // Mana Fluxfield
        [[<botania:livingrock>,<minecraft:redstone_block>,<mekanism:enrichedalloy>*6],40,20]:[[<botania:rfgenerator>]],
        // Hovering Hourglass
        [[<botania:managlass>,<minecraft:redstone>*2,<minecraft:gold_ingot>*2,<botania:manaresource:23>],40,10]:[[<botania:hourglass>]],
        // Crafty Crate
        [[<minecraft:crafting_table>,<botania:dreamwood>*4,<botania:manaresource:8>],20,0]:[[<botania:opencrate:1>]],
    },

    "ender":{
        // Ender Crafter
        [[<minecraft:crafting_table>,<minecraft:ender_eye>*2,<thermalfoundation:material:359>*5],600,1000]:[[<extendedcrafting:ender_crafter>]],
        // Ender Alternator
        [[<minecraft:ender_eye>*2,<thermalfoundation:material:167>,<thermalfoundation:material:359>*4],300,1000]:[[<extendedcrafting:ender_alternator>]],

        // Basic Capacitor
        [[<minecraft:redstone>*2,<ore:ingotCopper>*2,<minecraft:iron_ingot>,<enderio:item_material:20>*2],40,200]:[[<enderio:item_basic_capacitor>]],
        // Double-Layer Capacitor
        [[<enderio:item_basic_capacitor>*2,<enderio:item_alloy_ingot:1>*2,<enderio:item_material:23>],50,300]:[[<enderio:item_basic_capacitor:1>]],
        // Octadic Capacitor
        [[<enderio:item_basic_capacitor:1>*2,<enderio:item_alloy_ingot:2>*2,<minecraft:glowstone>],60,400]:[[<enderio:item_basic_capacitor:2>]],
    },

    "engineering":{
        // Iron Mechanical Components
        [[<immersiveengineering:metal:39>*2,<minecraft:iron_ingot>*2,<ore:ingotCopper>],20,20]:[[<immersiveengineering:material:8>]],
        // Steel Mechanical Components
        [[<immersiveengineering:metal:38>*2,<immersiveengineering:metal:8>,<ore:ingotCopper>],30,20]:[[<immersiveengineering:material:9>]],
        // Redstone Engineering Block
        [[<immersiveengineering:metal:38>*2,<immersiveengineering:metal:39>*2,<minecraft:redstone>*4],40,50]:[[<immersiveengineering:metal_decoration0:3>]],
        // Light Engineering Block
        [[<immersiveengineering:metal:39>*4,<immersiveengineering:material:8>*2,<ore:ingotCopper>*2],40,50]:[[<immersiveengineering:metal_decoration0:4>]],
        // Heavy Engineering Block
        [[<immersiveengineering:metal:38>*4,<immersiveengineering:material:9>*2,<ore:ingotElectrum>*2],40,50]:[[<immersiveengineering:metal_decoration0:5>]],
    },

    "mek":{
        // Advanced Control Circuit
        [[<mekanism:enrichedalloy>*4,<mekanism:controlcircuit>],20,200]:[[<mekanism:controlcircuit:1>]],
        // Elite Control Circuit
        [[<mekanism:reinforcedalloy>*4,<mekanism:controlcircuit:1>],20,300]:[[<mekanism:controlcircuit:2>]],
        // Ultimate Control Circuit
        [[<mekanism:atomicalloy>*4,<mekanism:controlcircuit:2>],20,400]:[[<mekanism:controlcircuit:3>]],

        // Speed Upgrade
        [[<thermalfoundation:material:32>,<mekanism:enrichedalloy>*2,<mekanism:ingot:3>*2],40,100]:[[<mekanism:speedupgrade>]],
        // Energy Upgrade
        [[<thermalfoundation:material:32>,<mekanism:enrichedalloy>*2,<minecraft:redstone>*10],40,100]:[[<mekanism:energyupgrade>]],
        // Filter Upgrade
        [[<thermalfoundation:material:32>,<mekanism:enrichedalloy>*2,<minecraft:paper>*5],40,100]:[[<mekanism:filterupgrade>]],
        // Muffling Upgrade
        [[<thermalfoundation:material:32>,<mekanism:enrichedalloy>*2,<minecraft:wool>],40,100]:[[<mekanism:mufflingupgrade>]],
        // Gas Upgrade
        [[<thermalfoundation:material:32>,<mekanism:enrichedalloy>*2,<mekanism:polyethene:2>*3],40,100]:[[<mekanism:gasupgrade>]],
        // Anchor Upgrade
        [[<thermalfoundation:material:32>,<mekanism:reinforcedalloy>*2,<mekanism:teleportationcore>],40,100]:[[<mekanism:anchorupgrade>]],

        // Tier Installer
        [[<thermalfoundation:material:32>,<mekanism:controlcircuit>*2,<minecraft:redstone>*8],100,200]:[[<mekanism:tierinstaller>]],
        [[<thermalfoundation:material:?>,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*4],120,400]:[[<mekanism:tierinstaller:1>]],
        [[<thermalfoundation:material:352>,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*4],140,800]:[[<mekanism:tierinstaller:2>*2]],
        [[<thermalfoundation:material:354>,<mekanism:controlcircuit:3>*2,<mekanism:atomicalloy>*4],160,1600]:[[<mekanism:tierinstaller:3>]],

        // Enrichment Chamber
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<minecraft:iron_ingot>*4,<minecraft:redstone>*4],150,50]:[[<mekanism:machineblock>]],
        // Osmium Compressor
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:2>*2,<minecraft:bucket>,<mekanism:enrichedalloy>*2],150,50]:[[<mekanism:machineblock:1>]],
        // Combiner
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:2>*2,<mekanism:enrichedalloy>*2,<minecraft:piston>*2],100,50]:[[<mekanism:machineblock:2>]],
        // Crusher
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit>*2,<minecraft:iron_ingot>*4,<minecraft:piston>],100,50]:[[<mekanism:machineblock:3>]],
        // Metallugic Infuser
        [[<mekanism:basicblock:8>,<minecraft:iron_ingot>*4,<minecraft:redstone>*4],100,50]:[[<mekanism:machineblock:8>]],
        // Purification Chamber
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<minecraft:water_bucket>],150,50]:[[<mekanism:machineblock:9>,<minecraft:bucket>]],
        // Energized Smelter
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit>*2,<minecraft:furance>],100,50]:[[<mekanism:machineblock:10>]],
        // Electric Pump
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit>*2,<minecraft:bucket>],100,50]:[[<mekanism:machineblock:12>]],
        // Personal Chest
        [[<minecraft:chest>*2,<ore:ingotSteel>*2,<minecraft:paper>],80,50]:[[<mekanism:machineblock:13>]],
        // Rotary Condensetrator
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit>*2,<mekanism:machineblock2:11>,<mekanism:gastank>],100,50]:[[<mekanism:machineblock2>]],

        // Chemical Oxidizer
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<minecraft:hopper>,<mekanism:gastank>],120,75]:[[<mekanism:machineblock2:1>]],
        // Chemical Infuser
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*2,<mekanism:gastank>],120,75]:[[<mekanism:machineblock2:2>]],
        // Chemical Injection Chamber
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*2,<mekanism:gastank>],120,100]:[[<mekanism:machineblock2:4>]],
        // Electrolytic Separator
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit>*3,<mekanism:electrolyticcore>,<mekanism:gastank>],120,75]:[[<mekanism:machineblock2:4>]],
        // Precison Sawmill
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<mekanism:enrichedalloy>*4],100,50]:[[<mekanism:machineblock2:5>]],
        // Chemical Dissolution Chamber
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:2>*2,<mekanism:reinforcedalloy>*2,<mekanism:gastank>],120,150]:[[<mekanism:machineblock2:6>]],
        // Chemical Washer
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*3,<mekanism:machineblock2:11>,<mekanism:gastank>],120,100]:[[<mekanism:machineblock2:7>]],
        // Chemical Crystallizer
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:2>*2,<mekanism:atomicalloy>*2,<mekanism:gastank>],120,100]:[[<mekanism:machineblock2:8>]],
        // Pressurized Reaction Chamber
        [[<mekanism:machineblock>,<mekanism:controlcircuit:2>*2,<mekanism:gastank>,<mekanism:machineblock2:11>],120,100]:[[<mekanism:machineblock2:10>]],
        // Basic Fluid Tank
        [[<minecraft:glass>,<minecraft:iron_ingot>*2,<minecraft:bucket>*5],100,50]:[[<mekanism:machineblock2:11>]],

        // Solar Neutron Activator
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:3>*2,<mekanism:reinforcedalloy>*2,<mekanismgenerators:solarpanel>,<mekanism:gastank>],150,150]:[[<mekanism:machineblock3:1>]],
        // Formulaic Assemblicator
        [[<mekanism:basicblock:8>,<mekanism:controlcircuit:1>*2,<minecraft:hopper>,<minecraft:crafting_table>],100,50]:[[<mekanism:machineblock3:5>]],
    },

    "modular":{
        // Machine Casing
        [[<modularmachinery:itemmodularium>,<minecraft:redstone>*2],10,50]:[[<modularmachinery:blockcasing:0>]],
        // Machine Controller
        [[<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>*2,<minecraft:diamond>,<minecraft:redstone>*8],20,50]:[[<modularmachinery:blockcontroller>]],
        // Reinforced Machine Casing
        [[<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>*2,<minecraft:obsidian>*2],20,50]:[[<modularmachinery:blockcasing:4>]],
        // Machine Vent
        [[<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>,<minecraft:iron_bars>*6],40,50]:[[<modularmachinery:blockcasing:1>]],
        // Firebox Casing
        [[<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>,<thermalfoundation:material:24>*8],40,50]:[[<modularmachinery:blockcasing:3>]],
        // Machine Circuitry
        [[<modularmachinery:blockcasing>,<modularmachinery:itemmodularium>,<mekanism:controlcircuit>*4],40,50]:[[<modularmachinery:blockcasing:5>]],
        // Mana Input
        [[<modularmachinery:blockcasing>,<botania:pool>],50,50]:[[<gugu-utils:sparkmanahatch:1>]],
        // Starlight Input (Basic)
        [[<modularmachinery:blockcasing>,<astralsorcery:itemcraftingcomponent>*2],50,50]:[[<gugu-utils:starlightinputhatch>]],
        // Starlight Input (Trait)
        [[<gugu-utils:starlightinputhatch>,<astralsorcery:itemcraftingcomponent:1>*4],50,50]:[[<gugu-utils:starlightinputhatch:1>]],
        // Starlight Input (Brilliant)
        [[<gugu-utils:starlightinputhatch:1>,<astralsorcery:itemcraftingcomponent:4>*12,],60,50]:[[<gugu-utils:starlightinputhatch:2>]],
    },

    "thermal":{
        // Conduance Coil
        [[<minecraft:redstone>*2,<thermalfoundation:material:161>],20,30]:[[<thermalexpansion:material:3>]],
        // Trasmission Coil
        [[<minecraft:redstone>*2,<thermalfoundation:material:130>],20,30]:[[<thermalexpansion:material:2>]],
        // Reception Coil
        [[<minecraft:redstone>*2,<minecraft:gold_ingot>],20,30]:[[<thermalexpansion:material:1>]],

        // Leadstone Energy Cell
        [[<minecraft:redstone_block>,<minecraft:glass>*4,<ore:ingotLead>*4,<ore:ingotCopper>*3,<thermalexpansion:material:2>],70,100]:[[<thermalexpansion:cell:1>]],
        // Hardend Energy Cell
        [[<thermalexpansion:cell:1>,<thermalfoundation:material:162>*4],70,100]:[[<thermalexpansion:cell:2>]],
        // Redstone Energy Cell
        [[<thermalexpansion:cell:2>,<thermalfoundation:material:161>*4],70,100]:[[<thermalexpansion:cell:3>]],
        // Resonant Energy Cell
        [[<thermalexpansion:cell:3>,<thermalfoundation:material:167>*4],70,100]:[[<thermalexpansion:cell:4>]],
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
