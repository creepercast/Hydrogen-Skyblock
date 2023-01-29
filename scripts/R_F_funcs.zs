/*
Function File (Tool Box) for starrain_xy177.
Some useful functions in this file, can be called in all script files.
Coded by Rain_Flying, licensed to starrain_xy177.
*/
#priority 9999

import crafttweaker.block.IBlock;
import crafttweaker.block.IBlockDefinition;
import crafttweaker.block.IBlockState;
import crafttweaker.item.IItemStack;
import crafttweaker.player.IPlayer;
import crafttweaker.player.IFoodStats;
import crafttweaker.world.IWorld;
import crafttweaker.world.IBlockPos;
import crafttweaker.entity.IEntity;
import crafttweaker.entity.IEntityLivingBase;
import crafttweaker.event.EntityLivingHurtEvent;
import crafttweaker.event.IEventPositionable;
import crafttweaker.event.IBlockEvent;
import crafttweaker.event.BlockBreakEvent;
import crafttweaker.util.IRandom;
import crafttweaker.util.Position3f;
import mods.ctutils.utils.Math;

function ScaledRandomFloat(multiplyer as float) {
    return Math.random() * multiplyer;
}

function sum(list as float[]) {
    var output as float = 0;
    for num in list {
        output += num;
    }
    return num;
}

function WeightedRandomChoice(list as int[IItemStack]) {
    var Rand = ScaledRandomFloat(sum(list.values));
    for key in list.keys {
        if (Rand < list[key]) {
            return key;
        } else {
            Rand -= list[key];
        }
    }
    return;
}

function SquaredDistance(point1,point2) {
    var distX = point1.x - point2.x;
    var distY = point1.y - point2.y;
    var distZ = point1.z - point2.z;
    return (distX * distX) + (distY * distY) + (distZ * distZ);
}

function SquaredHorizontalDistance(point1,point2) {
    var distX = point1.x - point2.x;
    var distZ = point1.z - point2.z;
    return (distX * distX) + (distZ * distZ);
}
