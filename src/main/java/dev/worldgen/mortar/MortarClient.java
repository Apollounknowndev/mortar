package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

public class MortarClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MortarBlocks.STAINED_GLASSES.stream().forEach(block -> put(block, ChunkSectionLayer.TRANSLUCENT));
        MortarBlocks.STAINED_GLASS_PANES.stream().forEach(block -> put(block, ChunkSectionLayer.TRANSLUCENT));
        put(MortarBlocks.BLUE_AMARANTH, ChunkSectionLayer.CUTOUT);
        put(MortarBlocks.SNAPDRAGON, ChunkSectionLayer.CUTOUT);
    }

    private static void put(Block block, ChunkSectionLayer layer) {
        BlockRenderLayerMap.putBlock(block, layer);
    }
}