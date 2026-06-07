package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTags extends ItemTagsProvider {
	public ItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
		super(generator, blockTags, HardrockMekanismCompat.MOD_ID, helper);
	}

	public static final TagKey<Item> NICKEL_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/nickel"));
	public static final TagKey<Item> SILVER_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/silver"));
	public static final TagKey<Item> BISMUTH_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/bismuth"));
	public static final TagKey<Item> ALUMINUM_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/aluminum"));
	public static final TagKey<Item> CHROMIUM_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/chromium"));
	public static final TagKey<Item> ZINC_INGOT = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "ingots/zinc"));

	public static final TagKey<Item> NICKEL_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/nickel"));
	public static final TagKey<Item> SILVER_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/silver"));
	public static final TagKey<Item> BISMUTH_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/bismuth"));
	public static final TagKey<Item> ALUMINUM_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/aluminium"));
	public static final TagKey<Item> CHROMIUM_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/chromium"));
	public static final TagKey<Item> ZINC_DUST = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts/zinc"));

	public static final TagKey<Item> DUSTS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("forge", "dusts"));
	public static final TagKey<Item> DIRTY_DUSTS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("mekanism", "dirty_dusts"));
	public static final TagKey<Item> CLUMPS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("mekanism", "clumps"));
	public static final TagKey<Item> SHARDS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("mekanism", "shards"));
	public static final TagKey<Item> CRYSTALS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("mekanism", "crystals"));
	public static final TagKey<Item> HOURGLASS_DUSTS = TagKey.create(Registry.ITEM_REGISTRY,
			ResourceLocation.fromNamespaceAndPath("supplementaries", "hourglass_dusts"));


	@Override
	protected void addTags() {
		// come on, you can do a little better than to reference an ingot with a custom tag
//		tag(NICKEL_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/nickel"))));
//		tag(ZINC_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/zinc"))));
//		tag(BISMUTH_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/bismuth"))));
//		tag(SILVER_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("tfc", "metal/ingot/silver"))));
//		tag(ALUMINUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("immersiveengineering", "ingot_aluminum"))));
//		tag(CHROMIUM_INGOT).add(Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(ResourceLocation.fromNamespaceAndPath("firmalife", "metal/ingot/chromium"))));

		tag(NICKEL_DUST).add(Items.dusts.get("NICKEL_DUST").get());
		tag(SILVER_DUST).add(Items.dusts.get("SILVER_DUST").get());
		tag(BISMUTH_DUST).add(Items.dusts.get("BISMUTH_DUST").get());
		tag(ALUMINUM_DUST).add(Items.dusts.get("ALUMINUM_DUST").get());
		tag(CHROMIUM_DUST).add(Items.dusts.get("CHROMIUM_DUST").get());
		tag(ZINC_DUST).add(Items.dusts.get("ZINC_DUST").get());

		Items.dusts.forEach((k, v) -> {
			tag(DUSTS).add(v.get());
			tag(HOURGLASS_DUSTS).add(v.get());
		});
		Items.dirty_dusts.forEach((k, v) -> tag(DIRTY_DUSTS).add(v.get()));
		Items.clumps.forEach((k, v) -> tag(CLUMPS).add(v.get()));
		Items.shards.forEach((k, v) -> tag(SHARDS).add(v.get()));
		Items.crystals.forEach((k, v) -> tag(CRYSTALS).add(v.get()));
	}
	
	@Override
	public String getName() {
		return "Hardrock Mekanism Compat Item Tags";
	}
}