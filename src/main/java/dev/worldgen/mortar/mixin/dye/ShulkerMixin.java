package dev.worldgen.mortar.mixin.dye;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Shulker.class)
public abstract class ShulkerMixin extends Entity {
    public ShulkerMixin(EntityType<?> type, Level world) {super(type, world);}

    @Shadow @Final
    protected static EntityDataAccessor<Byte> DATA_COLOR_ID;

    // Keeps 16b reserved for uncolored, with 17b-24b for Mortar colors
    @ModifyReturnValue(method = "getColor", at = @At("RETURN"))
    private DyeColor fixMortarColors(DyeColor color) {
        if (color == null) {
            byte b = this.entityData.get(DATA_COLOR_ID);
            if (b > 16 && b < 25) {
                return DyeColor.byId(b - 1);
            }
        }
        return color;
    }
}
