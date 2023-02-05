package threshold.hydrogenation_tinker;

import blusunrize.immersiveengineering.ImmersiveEngineering;
import c4.conarm.ConstructsArmory;
import crazypants.enderio.endergy.EnderIOEndergy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import slimeknights.tconstruct.TConstruct;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.traits.AbstractTrait;
import slimeknights.tconstruct.library.traits.ITrait;
import threshold.hydrogenation_tinker.Entity.EntityEnvironmentalRay;
import threshold.hydrogenation_tinker.Entity.EntityFlowerSeaRay;
import threshold.hydrogenation_tinker.Entity.EntityMarbleBlock;
import threshold.hydrogenation_tinker.Entity.EntityRay;
import threshold.hydrogenation_tinker.Entity.renders.RenderMarbleBlock;
import threshold.hydrogenation_tinker.network.PacketEnvironmentalSwing;
import threshold.hydrogenation_tinker.network.PacketFlowerSea;
import threshold.hydrogenation_tinker.network.PacketMotions;
import threshold.hydrogenation_tinker.potions.*;
import threshold.hydrogenation_tinker.traits.*;
import threshold.hydrogenation_tinker.utils.Tester;
import twilightforest.TwilightForestMod;
import twilightforest.compat.tcon.trait.TraitPrecipitate;
import xyz.phanta.tconevo.TconEvoMod;


@Mod(
        modid = Hydrogenation_tinker.MOD_ID,
        name = Hydrogenation_tinker.MOD_NAME,
        version = Hydrogenation_tinker.VERSION,
        dependencies = "required-after:" + TConstruct.modID + ";" +
                "after:" + ConstructsArmory.MODID + ";" +
                "after:" + TwilightForestMod.ID + ";" +
                "after:" + TconEvoMod.MOD_ID
)
public class Hydrogenation_tinker {

    public static final String MOD_ID = "hydrogenation_tinker";
    public static final String MOD_NAME = "hydrogenation_tinker";
    public static final String VERSION = "0.0.5-alpha";

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static Hydrogenation_tinker INSTANCE;

    public static SimpleNetworkWrapper network;
    static {
        network = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
    }
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
        Traits.environmental = new TraitEnvironmental();
        Traits.questionMark = new TraitQuestionMark();
        Traits.silence = new TraitSilence();
        Traits.starLight = new TraitStarLight();
        Traits.starBless = new TraitStarBless();
        Traits.chocolateMilk = new TraitChocolateMilk();
        Traits.environmentalBless = new TraitEnvironmentalBless();
        if(Loader.isModLoaded(TwilightForestMod.ID)) {
            Traits.association = new TraitAssociation();
        }
        Traits.defensiveFusion = new TraitDefensiveFusion();
        Traits.fusion = new TraitFusion();
        Traits.precision = new TraitPrecision();
        Traits.fireOfSacrifice = new TraitFireOfSacrifice();
        Traits.bitingWind = new TraitBitingWind();
        Traits.aetherWind = new TraitAetherWind();
        if(Loader.isModLoaded(ConstructsArmory.MODID)) {
            Traits.holyWind = new TraitHolyWind();
        }
        Traits.cureWind = new TraitCureWind();
        Traits.aetherBind = new TraitAetherBind();
        if(Loader.isModLoaded(ConstructsArmory.MODID)) {
            Traits.phaeton = new TraitPhaeton();
        }
        Traits.timeCurse = new TraitTimeCurse();
        if(Loader.isModLoaded(ConstructsArmory.MODID)) {
            Traits.timeShield = new TraitTimeShield();
        }
        Traits.cloudy = new TraitCloudy();
        Traits.lifeDrainPlus = new TraitLifeDrainPlus();
        if(Loader.isModLoaded(ConstructsArmory.MODID)) {
            Traits.mainCharacter = new TraitMainCharacter();
            Traits.evilDeal = new TraitEvilDeal();
        }
        if(Loader.isModLoaded(ImmersiveEngineering.MODID)){
            Traits.heatTransform = new TraitHeatTransform();
        }
        Traits.timeEnhance = new TraitTimeEnhance();
        Traits.shinning = new TraitShinning();
        Traits.halfEatable = new TraitHalfEatable();
        if(Loader.isModLoaded(TconEvoMod.MOD_ID) && Loader.isModLoaded(EnderIOEndergy.MODID)) {
            Traits.emergencyEnergy = new TraitEmergencyEnergy();
        }
        Traits.flowerSea = new TraitFlowerSea();
        Traits.sharpen = new TraitSharpen();
        Traits.alone = new TraitAlone();
        if(Loader.isModLoaded(ConstructsArmory.MODID)){
            Traits.cloudPlayerDeal = new TraitCloudPlayerDeal();
            Traits.waterRely = new TraitWaterRely();
        }
        Traits.heatAbsorb = new TraitHeatAbsorb();
        Traits.relative = new TraitRelative();
        Traits.pressure = new TraitPressure();
        Traits.magicNemesis = new TraitMagicNemesis();
        Traits.penetration = new TraitPenetration();
        Traits.minor = new TraitMinor();
        Traits.traits = new AbstractTrait[]{
                Traits.environmental,
                Traits.questionMark,
                Traits.silence,
                Traits.starLight,
                Traits.starBless,
                Traits.chocolateMilk,
                Traits.environmentalBless,
                Traits.association,
                Traits.defensiveFusion,
                Traits.fusion,
                Traits.precision,
                Traits.fireOfSacrifice,
                Traits.bitingWind,
                Traits.aetherWind,
                Traits.holyWind,
                Traits.cureWind,
                Traits.aetherBind,
                Traits.phaeton,
                Traits.timeCurse,
                Traits.timeShield,
                Traits.cloudy,
                Traits.lifeDrainPlus,
                Traits.mainCharacter,
                Traits.evilDeal,
                Traits.heatTransform,
                Traits.timeEnhance,
                Traits.shinning,
                Traits.halfEatable,
                Traits.emergencyEnergy,
                Traits.flowerSea,
                Traits.alone,
                Traits.sharpen,
                Traits.cloudPlayerDeal,
                Traits.waterRely,
                Traits.heatAbsorb,
                Traits.relative,
                Traits.pressure,
                Traits.magicNemesis,
                Traits.penetration,
                Traits.minor
        };
        for (ITrait trait: Traits.traits) {
            if(trait != null) TinkerRegistry.addTrait(trait);
        }
        if(HydrogenationConfig.test){
            Tester.init();
        }
    }

    @SuppressWarnings("UnusedAssignment")
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientRegistry.registerKeyBinding(Keys.flower_sea);
        int id = 0;
        network.registerMessage(PacketEnvironmentalSwing.Handler.class, PacketEnvironmentalSwing.class, id++, Side.SERVER);
        network.registerMessage(PacketFlowerSea.Handler.class, PacketFlowerSea.class, id++, Side.SERVER);
        network.registerMessage(PacketMotions.Handler.class, PacketMotions.class, id++, Side.CLIENT);
        id = 5000;
        EntityRegistry.registerModEntity(
                new ResourceLocation(MOD_ID,"ray"),
                EntityRay.class,
                "ray",
                id++, Hydrogenation_tinker.INSTANCE,
                256,
                2,
                true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MOD_ID,"environmental_ray"),
                EntityEnvironmentalRay.class,
                "environmental_ray",
                id++, Hydrogenation_tinker.INSTANCE,
                256,
                2,
                true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MOD_ID,"marble_block"),
                EntityMarbleBlock.class,
                "marble_block",
                id++, Hydrogenation_tinker.INSTANCE,
                256,
                2,
                true
        );
        EntityRegistry.registerModEntity(
                new ResourceLocation(MOD_ID,"flower_sea_ray"),
                EntityFlowerSeaRay.class,
                "flower_sea_ray",
                id++, Hydrogenation_tinker.INSTANCE,
                256,
                2,
                true
        );
    }

    @Mod.EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }

    /*
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks {

          public static final MySpecialBlock mySpecialBlock = null; // placeholder for special block below

    }
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Items {
          public static final ItemBlock mySpecialBlock = null; // itemblock for the block above
          public static final MySpecialItem mySpecialItem = null; // placeholder for special item below
    }
    */
    //@GameRegistry.ObjectHolder(MOD_ID)
    public static class Traits {
        public static TraitEnvironmental environmental;
        public static TraitQuestionMark questionMark;
        public static TraitSilence silence;
        public static TraitStarLight starLight;
        public static TraitStarBless starBless;
        public static TraitChocolateMilk chocolateMilk;
        public static TraitEnvironmentalBless environmentalBless;
        public static TraitAssociation association;
        public static TraitDefensiveFusion defensiveFusion;
        public static TraitFusion fusion;
        public static TraitPrecision precision;
        public static TraitFireOfSacrifice fireOfSacrifice;
        public static TraitBitingWind bitingWind;
        public static TraitAetherWind aetherWind;
        public static TraitHolyWind holyWind;
        public static TraitCureWind cureWind;
        public static TraitAetherBind aetherBind;
        public static TraitPhaeton phaeton;
        public static TraitTimeCurse timeCurse;
        public static TraitTimeShield timeShield;
        public static TraitCloudy cloudy;
        public static TraitLifeDrainPlus lifeDrainPlus;
        public static TraitMainCharacter mainCharacter;
        public static TraitEvilDeal evilDeal;
        public static TraitHeatTransform heatTransform;
        public static TraitTimeEnhance timeEnhance;
        public static TraitShinning shinning;
        public static TraitHalfEatable halfEatable;
        public static TraitEmergencyEnergy emergencyEnergy;
        public static TraitFlowerSea flowerSea;
        public static TraitSharpen sharpen;
        public static TraitAlone alone;
        public static TraitCloudPlayerDeal cloudPlayerDeal;
        public static TraitWaterRely waterRely;
        public static TraitHeatAbsorb heatAbsorb;
        public static TraitRelative relative;
        public static TraitPressure pressure;
        public static TraitMagicNemesis magicNemesis;
        public static TraitPenetration penetration;
        public static TraitMinor minor;
        public static AbstractTrait[] traits;
    }
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Potions{
        public static PotionUsingNote USING_NOTE = null;
        public static PotionCollideAttack COLLIDE_ATTACK = null;
        public static PotionTaunt TAUNT = null;
        public static PotionForeverFire FOREVER_FIRE = null;
        public static PotionHolyWind HOLY_WIND = null;
        public static PotionStarCollide STAR_COLLIDE = null;
        public static PotionFluxDisturb FLUX_DISTURB = null;
        public static PotionSprintCharge SPRINT_CHARGE = null;
        public static PotionFlowerSeaActive FLOWER_SEA_ACTIVE = null;
        public static PotionFlowerSeaCoolDown FLOWER_SEA_COOL_DOWN = null;
        public static PotionSilenceNote SILENCE_NOTE = null;
    }
    @GameRegistry.ObjectHolder(MOD_ID)
    public static class Blocks{
        public static BlockShinning blockShinning = new BlockShinning();
    }
    @Mod.EventBusSubscriber
    public static class ObjectRegistryHandler {
        @SubscribeEvent
        public static void addItems(RegistryEvent.Register<Item> event) {
        }

        @SubscribeEvent
        public static void addBlocks(RegistryEvent.Register<Block> event) {
            event.getRegistry().register(Blocks.blockShinning.setRegistryName(MOD_ID,"block_shinning"));
            GameRegistry.registerTileEntity(TileEntityShinning.class,
                    new ResourceLocation(MOD_ID, "block_shinning")
            );
        }
        @SubscribeEvent
        public static void addPotions(RegistryEvent.Register<Potion> event) {
            Potions.USING_NOTE = (PotionUsingNote) new PotionUsingNote(false,0x0000000)
                    .setRegistryName(MOD_ID,"using_note")
                    .setPotionName("potion." + MOD_ID + ":using_note");
            event.getRegistry().register(Potions.USING_NOTE);
            Potions.COLLIDE_ATTACK = (PotionCollideAttack) new PotionCollideAttack(false,0x0000000)
                    .setRegistryName(MOD_ID,"collide_attack")
                    .setPotionName("potion." + MOD_ID + ":collide_attack");
            event.getRegistry().register(Potions.COLLIDE_ATTACK);
            Potions.TAUNT = (PotionTaunt) new PotionTaunt(false,0x0000000)
                    .setRegistryName(MOD_ID,"taunt")
                    .setPotionName("potion." + MOD_ID + ":taunt");
            event.getRegistry().register(Potions.TAUNT);
            Potions.FOREVER_FIRE = (PotionForeverFire) new PotionForeverFire(true,0x0000000)
                    .setRegistryName(MOD_ID,"forever_fire")
                    .setPotionName("potion." + MOD_ID + ":forever_fire");
            event.getRegistry().register(Potions.FOREVER_FIRE);
            Potions.HOLY_WIND = (PotionHolyWind) new PotionHolyWind(false,0x0000000)
                    .setRegistryName(MOD_ID,"holy_wind")
                    .setPotionName("potion." + MOD_ID + ":holy_wind");
            event.getRegistry().register(Potions.HOLY_WIND);
            Potions.STAR_COLLIDE = (PotionStarCollide) new PotionStarCollide(false,0x0000000)
                    .setRegistryName(MOD_ID,"star_collide")
                    .setPotionName("potion." + MOD_ID + ":star_collide");
            event.getRegistry().register(Potions.STAR_COLLIDE);
            Potions.FLUX_DISTURB = (PotionFluxDisturb) new PotionFluxDisturb(false,0x0000000)
                    .setRegistryName(MOD_ID,"flux_disturb")
                    .setPotionName("potion." + MOD_ID + ":flux_disturb");
            event.getRegistry().register(Potions.FLUX_DISTURB);
            Potions.SPRINT_CHARGE = (PotionSprintCharge) new PotionSprintCharge(false,0x0000000)
                    .setRegistryName(MOD_ID,"sprint_charge")
                    .setPotionName("potion." + MOD_ID + ":sprint_charge");
            event.getRegistry().register(Potions.SPRINT_CHARGE);
            Potions.FLOWER_SEA_ACTIVE = (PotionFlowerSeaActive) new PotionFlowerSeaActive(false,0x0000000)
                    .setRegistryName(MOD_ID,"flower_sea_active")
                    .setPotionName("potion." + MOD_ID + ":flower_sea_active");
            event.getRegistry().register(Potions.FLOWER_SEA_ACTIVE);
            Potions.FLOWER_SEA_COOL_DOWN = (PotionFlowerSeaCoolDown) new PotionFlowerSeaCoolDown(false,0x0000000)
                    .setRegistryName(MOD_ID,"flower_sea_cool_down")
                    .setPotionName("potion." + MOD_ID + ":flower_sea_cool_down");
            event.getRegistry().register(Potions.FLOWER_SEA_COOL_DOWN);
            Potions.SILENCE_NOTE = (PotionSilenceNote) new PotionSilenceNote(false,0x0000000)
                    .setRegistryName(MOD_ID,"silence_note")
                    .setPotionName("potion." + MOD_ID + ":silence_note");
            event.getRegistry().register(Potions.SILENCE_NOTE);
        }
        @SubscribeEvent
        public static void bindEntityRenderer(ModelRegistryEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(EntityMarbleBlock.class, RenderMarbleBlock::new);
        }
    }
}
