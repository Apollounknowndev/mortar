package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarAttachments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SheepEntity.class)
public abstract class SheepEntityMixin extends Entity {
    public SheepEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "getColor", at = @At("HEAD"), cancellable = true)
    public void getColor(CallbackInfoReturnable<DyeColor> cir) {
        DyeColor color = this.getAttached(MortarAttachments.COLOR);
        if (color != null) {
            cir.setReturnValue(color);
        }
    }

    @Inject(method = "setColor", at = @At("HEAD"), cancellable = true)
    public void setColor(DyeColor color, CallbackInfo ci) {
        if (this.getWorld() instanceof ServerWorld) {
            this.setAttached(MortarAttachments.COLOR, color);
            ci.cancel();
        }
    }

    @Inject(method = "isSheared", at = @At("HEAD"), cancellable = true)
    public void isSheared(CallbackInfoReturnable<Boolean> cir) {
        Boolean sheared = this.getAttached(MortarAttachments.SHEARED);
        if (sheared != null) {
            cir.setReturnValue(sheared);
        }
    }

    @Inject(method = "setSheared", at = @At("HEAD"), cancellable = true)
    public void setSheared(boolean sheared, CallbackInfo ci) {
        if (this.getWorld() instanceof ServerWorld) {
            this.setAttached(MortarAttachments.SHEARED, sheared);
            ci.cancel();
        }
    }
}
