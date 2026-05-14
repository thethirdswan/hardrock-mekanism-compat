package com.thethirdswan.hardrock_mek_compat.setup;

import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mojang.logging.LogUtils;
import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import com.thethirdswan.hardrock_mek_compat.MiscOreResource;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.recipe.builder.*;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.ItemRegistryObject;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.*;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(DataGenerator generator) {
        super(generator);
    }

    // pattern to obtain metal type
    final Pattern pattern = Pattern.compile("^[A-Za-z]+");
    private static final Logger LOGGER = LogUtils.getLogger();

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        buildMekOreProcessingRecipes(consumer);
    }

    protected void buildMekOreProcessingRecipes(Consumer<FinishedRecipe> consumer) {
        // general ore processing
        for (ItemRegistryObject<Item> resultItem : dusts.values()) {
            Set<String> keySet = dusts.keySet();
            for (String key : keySet) {
                if (dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = dirty_dusts.get(metal_type + "_DUST_DIRTY");
                    ItemStackToItemStackRecipeBuilder.enriching(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "dust/" + metal_type.toLowerCase() + "/from_dirty_dust"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : dirty_dusts.values()) {
            Set<String> keySet = dirty_dusts.keySet();
            for (String key : keySet) {
                if (dirty_dusts.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = clumps.get(metal_type + "_CLUMP");
                    ItemStackToItemStackRecipeBuilder.crushing(IngredientCreatorAccess.item().from(inputItem), resultItem.getItemStack()).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "dirty_dust/" + metal_type.toLowerCase() + "/from_clump"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : clumps.values()) {
            Set<String> keySet = clumps.keySet();
            for (String key : keySet) {
                if (clumps.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = shards.get(metal_type + "_SHARD");
                    ItemStackChemicalToItemStackRecipeBuilder.purifying(
                            IngredientCreatorAccess.item().from(inputItem),
                            IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN, 1),
                            resultItem.getItemStack()
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "clump/" + metal_type.toLowerCase() + "/from_shard"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : shards.values()) {
            Set<String> keySet = shards.keySet();
            for (String key : keySet) {
                if (shards.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    ItemRegistryObject<Item> inputItem = crystals.get(metal_type + "_CRYSTAL");
                    ItemStackChemicalToItemStackRecipeBuilder.injecting(
                            IngredientCreatorAccess.item().from(inputItem),
                            IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE, 1),
                            resultItem.getItemStack()
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "shard/" + metal_type.toLowerCase() + "/from_crystal"));
                }
            }
        }
        for (ItemRegistryObject<Item> resultItem : crystals.values()) {
            Set<String> keySet = crystals.keySet();
            for (String key : keySet) {
                if (crystals.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    SlurryRegistryObject<Slurry, Slurry> slurry = slurries.get(metal_type + "_SLURRY");
                    ChemicalCrystallizerRecipeBuilder.crystallizing(IngredientCreatorAccess.slurry().from(slurry.getCleanSlurry(), 200), resultItem.getItemStack()).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "crystal/" + metal_type.toLowerCase() + "/from_slurry"));
                }
            }
        }
        for (SlurryRegistryObject<Slurry, Slurry> slurry : slurries.values()) {
            Set<String> keySet = slurries.keySet();
            for (String key : keySet) {
                if (slurries.get(key) == slurry) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    FluidSlurryToSlurryRecipeBuilder.washing(
                            IngredientCreatorAccess.fluid().from(FluidTags.WATER, 5),
                            IngredientCreatorAccess.slurry().from(slurry.getDirtySlurry(), 1),
                            slurry.getCleanSlurry().getStack(1)
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "slurry/" + metal_type.toLowerCase() + "/clean_slurry"));
                }
            }
        }
        // from ore chunks
        // clumps
        for (ItemRegistryObject<Item> resultItem : clumps.values()) {
            Set<String> keySet = clumps.keySet();
            for (String key : keySet) {
                if (clumps.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ItemStackChemicalToItemStackRecipeBuilder.purifying(
                            IngredientCreatorAccess.item().from(inputItem, 10),
                            IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN, 1),
                            resultItem.getItemStack()
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "clump/" + metal_type.toLowerCase() + "/from_chunks"));
                }
            }
        }
        // shards
        for (ItemRegistryObject<Item> resultItem : shards.values()) {
            Set<String> keySet = shards.keySet();
            for (String key : keySet) {
                if (shards.get(key) == resultItem) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ItemStackChemicalToItemStackRecipeBuilder.injecting(
                            IngredientCreatorAccess.item().from(inputItem, 5),
                            IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE, 1),
                            resultItem.getItemStack()
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "shard/" + metal_type.toLowerCase() + "/from_chunks"));
                }
            }
        }
        // dirty slurry
        for (SlurryRegistryObject<Slurry, Slurry> slurry : slurries.values()) {
            Set<String> keySet = slurries.keySet();
            for (String key : keySet) {
                if (slurries.get(key) == slurry) {
                    final Matcher matcher = pattern.matcher(key);
                    matcher.find();
                    String metal_type = matcher.group();
                    Item inputItem = MiscOreResource.getChunkItemFromName(metal_type.toLowerCase());
                    assert inputItem != null;
                    ChemicalDissolutionRecipeBuilder.dissolution(
                            IngredientCreatorAccess.item().from(inputItem, 20),
                            IngredientCreatorAccess.gas().from(MekanismGases.SULFURIC_ACID, 1),
                            slurry.getDirtySlurry().getStack(1_000)
                    ).build(consumer, ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "slurry/" + metal_type.toLowerCase() + "/dirty_slurry"));
                }
            }
        }
    }
}