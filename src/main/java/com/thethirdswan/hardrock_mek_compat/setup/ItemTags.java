package com.thethirdswan.hardrock_mek_compat.setup;

import com.eerussianguy.firmalife.common.items.FLItems;
import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.dries007.tfc.common.items.TFCItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public class ItemTags extends ItemTagsProvider {
	public ItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, CompletableFuture<TagLookup<Block>> blockTags, ExistingFileHelper helper) {
		super(output, provider, blockTags, HardrockMekanismCompat.MOD_ID, helper);
	}

	public static final TagKey<Item> NICKEL_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/nickel"));
	public static final TagKey<Item> SILVER_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/silver"));
	public static final TagKey<Item> BISMUTH_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/bismuth"));
	public static final TagKey<Item> ALUMINUM_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/aluminium"));
	public static final TagKey<Item> CHROMIUM_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/chromium"));
	public static final TagKey<Item> ZINC_INGOT = TagKey.create(Registries.ITEM,
			ResourceLocation.fromNamespaceAndPath("hardrock_mek_compat", "ingot/zinc"));

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		// come on, you can do a little better than to reference an ingot with a custom tag
		tag(NICKEL_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/nickel"))));
		tag(ZINC_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/zinc"))));
		tag(BISMUTH_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/bismuth"))));
		tag(SILVER_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/silver"))));
		tag(ALUMINUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("immersiveengineering", "ingot_aluminum"))));
		tag(CHROMIUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("firmalife", "metal/ingot/chromium"))));
	}
	
	@Override
	public String getName() {
		return "Hardrock Mekanism Compat Item Tags";
	}
}