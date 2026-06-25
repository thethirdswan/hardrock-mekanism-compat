package com.thethirdswan.hardrock_mek_compat;

import com.mojang.logging.LogUtils;
import com.thethirdswan.hardrock_mek_compat.setup.Items;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.*;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("hardrock_mek_compat")
public class HardrockMekanismCompat
{
    // Directly reference a slf4j logger
    public static final String MOD_ID = "hardrock_mek_compat";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final RegistryObject<CreativeModeTab> HMC_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.hardrock_mek_compat"))
            .icon(() -> shards.get("BISMUTH_SHARD").get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                shards.forEach((K, V) -> output.accept(V.get()));
                dusts.forEach((K, V) -> output.accept(V.get()));
                dirty_dusts.forEach((K, V) -> output.accept(V.get()));
                clumps.forEach((K, V) -> output.accept(V.get()));
                crystals.forEach((K, V) -> output.accept(V.get()));
                output.accept(NUTRIMIX.get());
            }).build());

    public HardrockMekanismCompat(FMLJavaModLoadingContext context)
    {
        Items.init(context);
        // Register the setup method for modloading
        context.getModEventBus().addListener(this::setup);
        // Register the enqueueIMC method for modloading
        context.getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        context.getModEventBus().addListener(this::processIMC);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        CREATIVE_MODE_TABS.register(context.getModEventBus());
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code

    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // Some example code to receive and process InterModComms from other mods

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts

    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents
    {
    }
}
