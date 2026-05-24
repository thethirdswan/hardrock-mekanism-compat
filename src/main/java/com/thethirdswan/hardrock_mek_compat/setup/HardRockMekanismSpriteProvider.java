package com.thethirdswan.hardrock_mek_compat.setup;

import com.thethirdswan.hardrock_mek_compat.HardrockMekanismCompat;
import com.thethirdswan.hardrock_mek_compat.MiscOreResource;
import net.minecraft.client.renderer.texture.atlas.sources.SingleFile;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.SpriteSourceProvider;

import java.util.Optional;

public class HardRockMekanismSpriteProvider extends SpriteSourceProvider {
    public HardRockMekanismSpriteProvider(PackOutput output, ExistingFileHelper fileHelper) {
        super(output, fileHelper, HardrockMekanismCompat.MOD_ID);
    }

    @Override
    protected void addSources() {
        for (MiscOreResource res : MiscOreResource.values()) {
            atlas(BLOCKS_ATLAS).addSource(new SingleFile(ResourceLocation.fromNamespaceAndPath(HardrockMekanismCompat.MOD_ID, "slurry/" + res.getName() + "_base_color"), Optional.empty()));
        }
    }
}
