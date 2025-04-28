package dev.worldgen.mortar.mixin.dye;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends Entity {
    public ShulkerEntityMixin(EntityType<?> type, World world) {super(type, world);}

    @Shadow @Final
    protected static TrackedData<Byte> COLOR;

    // Keeps 16b reserved for uncolored, with 17b-24b for Mortar colors
    @ModifyReturnValue(method = "getColor", at = @At("RETURN"))
    private DyeColor fixMortarColors(DyeColor color) {
        if (color == null) {
            byte b = this.dataTracker.get(COLOR);
            if (b > 16 && b < 25) {
                return DyeColor.byIndex(b - 1);
            }
        }
        return color;
    }
}
