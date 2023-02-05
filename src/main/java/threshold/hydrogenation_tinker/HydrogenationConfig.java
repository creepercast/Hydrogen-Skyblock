package threshold.hydrogenation_tinker;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;

@Config(modid = Hydrogenation_tinker.MOD_ID)
@Mod.EventBusSubscriber(modid = Hydrogenation_tinker.MOD_ID)
public class HydrogenationConfig {
    @Config.Ignore
    private static final String config = Hydrogenation_tinker.MOD_ID + ".config.";
    @Config.LangKey(config + "test")
    @Config.Comment("True means add test material,false means don't add test material")
    public static boolean test = true;
    @Config.LangKey(config + "debuff_note")
    @Config.Comment("Note some potion effects to be negative")
    public static String[] debuff_note = {};
    @Config.LangKey(config + "meme")
    @Config.Comment("Adding some joke effect")
    public static boolean joking = false;
}
