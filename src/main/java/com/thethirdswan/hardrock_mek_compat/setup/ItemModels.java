package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.*;

public class ItemModels extends ItemModelProvider {
    public ItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HardrockMekanismCompat.MOD_ID, existingFileHelper);
    }

    // pattern for getting item type
    final Pattern pattern = Pattern.compile("(?<=_)[A-Z]\\w+");

    @Override
    protected void registerModels() {
        withExistingParent(NUTRIMIX.get().toString(), ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/" + NUTRIMIX.get()));

        for (RegistryObject<Item> item : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
        for (RegistryObject<Item> item : dirty_dusts.values()) {
            Set<String> keySet = dirty_dusts.keySet();
            for (String key : keySet) {
                if (dirty_dusts.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
        for (RegistryObject<Item> item : clumps.values()) {
            Set<String> keySet = clumps.keySet();
            for (String key : keySet) {
                if (clumps.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
        for (RegistryObject<Item> item : shards.values()) {
            Set<String> keySet = shards.keySet();
            for (String key : keySet) {
                if (shards.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
        for (RegistryObject<Item> item : crystals.values()) {
            Set<String> keySet = crystals.keySet();
            for (String key : keySet) {
                if (crystals.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    mekOreProcessingItemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
    }

    private ItemModelBuilder mekOreProcessingItemModels(Item item, String itemType) {
        if (Objects.equals(itemType, "dust_dirty")) {
            return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/dusts/dirty/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
        } else if (Objects.equals(itemType, "dust")) {
            return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/dusts/clean/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
        } else {
            return withExistingParent(ForgeRegistries.ITEMS.getKey(item).getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/" + itemType + "/" + ForgeRegistries.ITEMS.getKey(item).getPath()));
        }
    }
}