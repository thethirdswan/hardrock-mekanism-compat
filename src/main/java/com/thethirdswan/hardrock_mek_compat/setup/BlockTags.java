package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTags extends BlockTagsProvider {
	public BlockTags(DataGenerator generator, ExistingFileHelper helper) {
		super(generator, HardrockMekanismCompat.MOD_ID, helper);
	}
	
	@Override
	protected void addTags() {
		
	}
	
	@Override
	public String getName() {
		return "Block Tags";
	}
}