package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.thethirdswan.hardrock_mek_compat.setup.Items.*;

public class LanguageProviders extends LanguageProvider {
	public LanguageProviders(DataGenerator gen, String locale) {
		super(gen, HardrockMekanismCompat.MOD_ID, locale);
	}
	// pattern to obtain metal type
	final Pattern pattern = Pattern.compile("^[A-Za-z]+");
	public static String textNormalizer(String text) {
		String lowercase = text.toLowerCase();
		return Character.toUpperCase(lowercase.charAt(0)) + lowercase.substring(1);
	}

	@Override
	protected void addTranslations() {
		add("itemGroup.hardrock_mek_compat", "Hardrock Mekanism Compat");
		add(NUTRIMIX.get(), "Nutritional Mix");
		for (RegistryObject<Item> dust : dusts.values()) {
			Set<String> keySet = dusts.keySet();
			for (String key : keySet) {
				if (dusts.get(key) == dust) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(dust.get(), textNormalizer(metal_type) + " Dust");
				}
			}
		}
		for (RegistryObject<Item> dust : dirty_dusts.values()) {
			Set<String> keySet = dirty_dusts.keySet();
			for (String key : keySet) {
				if (dirty_dusts.get(key) == dust) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(dust.get(), "Dirty " + textNormalizer(metal_type) + " Dust");
				}
			}
		}
		for (RegistryObject<Item> clump : clumps.values()) {
			Set<String> keySet = clumps.keySet();
			for (String key : keySet) {
				if (clumps.get(key) == clump) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(clump.get(), textNormalizer(metal_type) + " Clump");
				}
			}
		}
		for (RegistryObject<Item> shard : shards.values()) {
			Set<String> keySet = shards.keySet();
			for (String key : keySet) {
				if (shards.get(key) == shard) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(shard.get(), textNormalizer(metal_type) + " Shard");
				}
			}
		}
		for (RegistryObject<Item> crystal : crystals.values()) {
			Set<String> keySet = crystals.keySet();
			for (String key : keySet) {
				if (crystals.get(key) == crystal) {
					final Matcher matcher = pattern.matcher(key);
					matcher.find();
					String metal_type = matcher.group();
					add(crystal.get(), textNormalizer(metal_type) + " Crystal");
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
					add(slurry.getCleanSlurry().getTranslationKey(), textNormalizer(metal_type) + " Slurry");
					add(slurry.getDirtySlurry().getTranslationKey(), "Dirty " + textNormalizer(metal_type) + " Slurry");
				}
			}
		}
    }
}