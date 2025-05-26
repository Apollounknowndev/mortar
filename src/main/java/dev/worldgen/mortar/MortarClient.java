package dev.worldgen.mortar;

import dev.worldgen.mortar.block.MortarBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;

public class MortarClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        MortarBlocks.STAINED_GLASSES.stream().forEach(block -> put(block, RenderType.translucent()));
        MortarBlocks.STAINED_GLASS_PANES.stream().forEach(block -> put(block, RenderType.translucent()));
        put(MortarBlocks.BLUE_AMARANTH, RenderType.cutout());
        put(MortarBlocks.SNAPDRAGON, RenderType.cutout());
    }

    private static void put(Block block, RenderType layer) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
    }
}