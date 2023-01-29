#modloaded tconstruct

import crafttweaker.player.IPlayer;
import crafttweaker.block.IBlock;
import crafttweaker.entity.IEntity;
import crafttweaker.entity.IEntityLivingBase;
import crafttweaker.world.IWorld;

import mods.ctutils.utils.Math;

import mods.contenttweaker.tconstruct.TraitBuilder;

var Rand = 0;

// New Traits

val Chaos_un = TraitBuilder.create("chaos_unstable");
Chaos_un.color = 0x0F0F0F ;
Chaos_un.localizedName = "Unstable Chaos" ;
Chaos_un.localizedDescription = "Unthinkable Chaotic Power§r\n§fNot very stable. With so much Chaotic Energy...";
Chaos_un.onHit() = function(trait,tool,attacker,target,damage,isCritical) {
    if (Math.random(0,10000) == 1) {
        var player as IPlayer = attacker;
        server.commandManager.executeCommand(server,"/summon draconicevolution:entitychaosensergyvortex" + player.x + " " + player.y + " " + player.z);
    }
};
Chaos_un.register();



// Trait Fixes
// Only fixes, no other.
