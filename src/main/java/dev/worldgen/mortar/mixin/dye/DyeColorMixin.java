package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarDyes;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(DyeColor.class)
public abstract class DyeColorMixin {
    @Shadow
    @Final
    @Mutable
    private static DyeColor[] $VALUES; // $VALUES

    @Inject(method = "<clinit>", at = @At(value = "FIELD", target = "Lnet/minecraft/world/item/DyeColor;$VALUES:[Lnet/minecraft/world/item/DyeColor;", shift = At.Shift.AFTER))
    private static void addMortarDyes(CallbackInfo ci) {
        MortarDyes.setVanillaValues($VALUES);
        List<DyeColor> colors = new ArrayList<>(List.of($VALUES));

        colors.add(MortarDyes.MAROON);
        colors.add(MortarDyes.SCARLET);
        colors.add(MortarDyes.AMBER);
        colors.add(MortarDyes.PEAR);
        colors.add(MortarDyes.PINE);
        colors.add(MortarDyes.SLATE);
        colors.add(MortarDyes.LAVENDER);
        colors.add(MortarDyes.SALMON);

        $VALUES = colors.toArray(new DyeColor[0]);
    }
}
