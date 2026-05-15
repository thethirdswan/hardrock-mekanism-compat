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
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class Items {
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, HardrockMekanismCompat.MOD_ID);
    public static final SlurryDeferredRegister MEKANISM_SLURRIES = new SlurryDeferredRegister(HardrockMekanismCompat.MOD_ID);

    public static void init(FMLJavaModLoadingContext context) {
        IEventBus eventBus = context.getModEventBus();
        ITEMS.register(eventBus);
        MEKANISM_SLURRIES.register(eventBus);
        itemInit();
    }

    public static Map<String, RegistryObject<Item>> dusts = new HashMap<>();
    public static Map<String, RegistryObject<Item>> dirty_dusts = new HashMap<>();
    public static Map<String, RegistryObject<Item>> clumps = new HashMap<>();
    public static Map<String, RegistryObject<Item>> shards = new HashMap<>();
    public static Map<String, RegistryObject<Item>> crystals = new HashMap<>();
    public static Map<String, SlurryRegistryObject<Slurry, Slurry>> slurries = new HashMap<>();

    public static void itemInit() {
        for (MiscOreResource resource : MiscOreResource.values()) {
            dusts.put(resource.name() + "_DUST", ITEMS.register("dust_" + resource.getName(), () -> new Item(new Item.Properties())));
            dirty_dusts.put(resource.name() + "_DUST_DIRTY", ITEMS.register("dust_dirty_" + resource.getName(), () -> new Item(new Item.Properties())));
            clumps.put(resource.name() + "_CLUMP", ITEMS.register("clump_" + resource.getName(), () -> new Item(new Item.Properties())));
            shards.put(resource.name() + "_SHARD", ITEMS.register("shard_" + resource.getName(), () -> new Item(new Item.Properties())));
            crystals.put(resource.name() + "_CRYSTAL", ITEMS.register("crystal_" + resource.getName(), () -> new Item(new Item.Properties())));
            slurries.put(resource.name() + "_SLURRY", MEKANISM_SLURRIES.register(resource.getName(), Slurry -> SlurryBuilder.builder(ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "slurry/" + resource.getName() + "_base_color"))));
        }
    }
}