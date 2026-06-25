package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.*;

public class ItemModels extends ItemModelProvider {
    public ItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, HardrockMekanismCompat.MOD_ID, existingFileHelper);
    }

    // pattern for getting item type
    final Pattern pattern = Pattern.compile("(?<=_)[A-Z]\\w+");

    @Override
    protected void registerModels() {
        withExistingParent(NUTRIMIX.get().getRegistryName().getPath(), ResourceLocation.parse("item/generated")).texture("layer0", ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/" + NUTRIMIX.get().getRegistryName().getPath()));

        for (RegistryObject<Item> item : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == item) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String item_type = matcher.group();
                    itemModels(item.get(), item_type.toLowerCase());
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
                    itemModels(item.get(), item_type.toLowerCase());
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
                    itemModels(item.get(), item_type.toLowerCase());
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
                    itemModels(item.get(), item_type.toLowerCase());
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
                    itemModels(item.get(), item_type.toLowerCase());
                }
            }
        }
    }

    private ItemModelBuilder itemModels(Item item, String itemType) {
        if (Objects.equals(itemType, "dust_dirty")) {
            return withExistingParent(item.getRegistryName().getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/dusts/dirty/" + item.getRegistryName().getPath()));
        } else if (Objects.equals(itemType, "dust")) {
            return withExistingParent(item.getRegistryName().getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/dusts/clean/" + item.getRegistryName().getPath()));
        } else {
            return withExistingParent(item.getRegistryName().getPath(),
                    ResourceLocation.parse("item/generated")).texture("layer0",
                    ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "item/" + itemType + "/" + item.getRegistryName().getPath()));
        }
    }
}