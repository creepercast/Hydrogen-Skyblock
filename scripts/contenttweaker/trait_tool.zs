#modloaded tconstruct

import crafttweaker.player.IPlayer;
import crafttweaker.block.IBlock;
import crafttweaker.entity.IEntity;
import crafttweaker.entity.IEntityLivingBase;
import crafttweaker.world.IWorld;

import mods.ctutils.utils.Math;

import mods.contenttweaker.tconstruct.TraitBuilder;



// New Traits

TraitBuilder.create("chaos_unstable").
color = 0x0F0F0F .
localizedName = "Unstable Chaos".
localizedDescription = "Unthinkable Chaotic Power§r\n§fNot very stable. With so much Chaotic Energy...".
onHit() = function(trait, tool, attacker, target, damage, isCritical){
    if(Math.random(0,10000) = 1){
        var player as IPlayer = attacker;
        server.commandManager.executeCommand(server,"/summon draconicevolution:entitychaosensergyvortex" + player.x + " " + player.y + " " + player.z);
    }
}.
register();



// Trait Fixes
// Only fixes, no other.

// TAIGA: Heroic
// This script is from TAIGA source code by zkafaceTV and randomtz. No longer divide by zero.

TraitBuilder.create("heroic_fix").
color = 0x000000 .
localizedName = "Heroic".
localizedDescription = "Until you'll die.§r\nWhen the end is near, you grow stronger.".
damage() = function(tool,player,target,damage,newDamage,isCritical){
    var durability = Toolhelper.getCurrentDurability(tool);
    var durabilitymax = Toolhelper.getMaxDurability(tool);

    var safeDenominator = durabilitymax - durability - 1;
    if(safeDenominator <= 0){
        safeDenominator = 1;
    }

    var calc = 0;
    if((durability * durabilitymax / (durabilitymax - durability - 1 / safeDenominator)) != 0){
        calc = newDamage + (newDamage / 2) / (durability * durabilitymax / safeDenominator);
    } else {
        calc = newDamage + (newDamage / 2) / ((durability * durabilitymax / safeDenominator) + 1);
    }
    if (durability < (0.10 * durabilitymax) || player.getHealth() < player.getMaxHealth() / 8 || (target.getHealth() == target.getMaxHealth() && random.nextFloat() > 0.8)) {
        return super.damage(tool, player, target, damage, calc, isCritical);
    } else {
        return super.damage(tool, player, target, damage, newDamage * 0.9f, isCritical);
    }
}.
register();
