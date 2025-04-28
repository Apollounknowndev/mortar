package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class MortarClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MortarBlocks.STAINED_GLASSES.stream().forEach(block -> put(block, RenderLayer.getTranslucent()));
        MortarBlocks.STAINED_GLASS_PANES.stream().forEach(block -> put(block, RenderLayer.getTranslucent()));
        put(MortarBlocks.BLUE_AMARANTH, RenderLayer.getCutout());
        put(MortarBlocks.SNAPDRAGON, RenderLayer.getCutout());
    }

    private static void put(Block block, RenderLayer layer) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
    }
}