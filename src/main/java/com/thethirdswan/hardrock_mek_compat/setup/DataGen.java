package com.thethirdswan.hardrock_mek_compat.setup;
import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HardrockMekanismCompat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        ExistingFileHelper exHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(new RecipeProvider(generator));
            BlockTags BlockTags = new BlockTags(generator, exHelper);
            generator.addProvider(new ItemTags(generator, BlockTags, exHelper));
        }
        if (event.includeClient()) {
            generator.addProvider(new ItemModels(generator, exHelper));
            generator.addProvider(new LanguageProviders(generator, "en_us"));
        }
    }
}