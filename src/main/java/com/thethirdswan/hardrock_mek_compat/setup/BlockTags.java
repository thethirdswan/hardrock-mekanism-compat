package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class BlockTags extends BlockTagsProvider {
	public BlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
		super(output, provider, HardrockMekanismCompat.MOD_ID, helper);
	}
	
	@Override
	public String getName() {
		return "Block Tags";
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {

	}
}