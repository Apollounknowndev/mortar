package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.Mortar;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.world.item.equipment.EquipmentAssets.ROOT_ID;

@Mixin(EquipmentAssets.class)
public interface EquipmentAssetsMixin {
    @Inject(method = "createId", at = @At("HEAD"), cancellable = true)
    private static void fixMortarDyeIds(String string, CallbackInfoReturnable<ResourceKey<EquipmentAsset>> cir) {
        if (
            string.startsWith("maroon") ||
            string.startsWith("scarlet") ||
            string.startsWith("amber") ||
            string.startsWith("pear") ||
            string.startsWith("pine") ||
            string.startsWith("slate") ||
            string.startsWith("lavender") ||
            string.startsWith("salmon")
        ) {
            cir.setReturnValue(ResourceKey.create(ROOT_ID, Mortar.id(string)));
        }
    }
}
