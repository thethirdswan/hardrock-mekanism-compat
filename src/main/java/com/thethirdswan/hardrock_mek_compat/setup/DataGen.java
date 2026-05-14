package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = HardrockMekanismCompat.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGen {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> dataGenFuture = event.getLookupProvider();

        ExistingFileHelper exHelper = event.getExistingFileHelper();


        generator.addProvider(event.includeServer(), new RecipeProvider(output));
        BlockTags BlockTags = generator.addProvider(event.includeServer(), new BlockTags(output, dataGenFuture, exHelper));
        generator.addProvider(event.includeServer(), new ItemTags(output, dataGenFuture, BlockTags.contentsGetter(), exHelper));


        generator.addProvider(event.includeClient(), new ItemModels(output, exHelper));
        generator.addProvider(event.includeClient(), new LanguageProviders(output, "en_us"));

    }
}