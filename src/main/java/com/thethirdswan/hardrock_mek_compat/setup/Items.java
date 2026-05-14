package com.thethirdswan.hardrock_mek_compat.setup;

import com.mojang.logging.LogUtils;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import com.thethirdswan.hardrock_mek_compat.MiscOreResource;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Items {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final ItemDeferredRegister MEKANISM_ITEMS = new ItemDeferredRegister(HardrockMekanismCompat.MOD_ID);
    public static final SlurryDeferredRegister MEKANISM_SLURRIES = new SlurryDeferredRegister(HardrockMekanismCompat.MOD_ID);

    public static void init(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        MEKANISM_ITEMS.register(eventBus);
        MEKANISM_SLURRIES.register(eventBus);
        mekItemInit();
    }

    public static Map<String, ItemRegistryObject<Item>> dusts = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> dirty_dusts = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> clumps = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> shards = new HashMap<>();
    public static Map<String, ItemRegistryObject<Item>> crystals = new HashMap<>();
    public static Map<String, SlurryRegistryObject<Slurry, Slurry>> slurries = new HashMap<>();

    public static void mekItemInit() {
        for (MiscOreResource resource : MiscOreResource.values()) {
            dusts.put(resource.name() + "_DUST", MEKANISM_ITEMS.register("dust_" + resource.getName()));
            dirty_dusts.put(resource.name() + "_DUST_DIRTY", MEKANISM_ITEMS.register("dust_dirty_" + resource.getName()));
            clumps.put(resource.name() + "_CLUMP", MEKANISM_ITEMS.register("clump_" + resource.getName()));
            shards.put(resource.name() + "_SHARD", MEKANISM_ITEMS.register("shard_" + resource.getName()));
            crystals.put(resource.name() + "_CRYSTAL", MEKANISM_ITEMS.register("crystal_" + resource.getName()));
            slurries.put(resource.name() + "_SLURRY", MEKANISM_SLURRIES.register(resource.getName(), Slurry -> SlurryBuilder.builder(ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "slurry/" + resource.getName() + "_base_color"))));
        }
    }
}