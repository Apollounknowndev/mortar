package dev.worldgen.mortar.mixin.dye;

import dev.worldgen.mortar.misc.MortarAttachments;
import net.fabricmc.fabric.api.attachment.v1.AttachmentTarget;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Sheep.class)
public abstract class SheepMixin extends Entity {
    public SheepMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "getColor()Lnet/minecraft/world/item/DyeColor;", at = @At("HEAD"), cancellable = true)
    public void getColor(CallbackInfoReturnable<DyeColor> cir) {
        DyeColor color = ((AttachmentTarget)this).getAttached(MortarAttachments.COLOR);
        if (color != null) {
            cir.setReturnValue(color);
        }
    }

    @Inject(method = "setColor", at = @At("HEAD"), cancellable = true)
    public void setColor(DyeColor color, CallbackInfo ci) {
        if (this.level() instanceof ServerLevel) {
            ((AttachmentTarget)this).setAttached(MortarAttachments.COLOR, color);
            ci.cancel();
        }
    }

    @Inject(method = "isSheared", at = @At("HEAD"), cancellable = true)
    public void isSheared(CallbackInfoReturnable<Boolean> cir) {
        Boolean sheared = ((AttachmentTarget)this).getAttached(MortarAttachments.SHEARED);
        if (sheared != null) {
            cir.setReturnValue(sheared);
        }
    }

    @Inject(method = "setSheared", at = @At("HEAD"), cancellable = true)
    public void setSheared(boolean sheared, CallbackInfo ci) {
        if (this.level() instanceof ServerLevel) {
            ((AttachmentTarget)this).setAttached(MortarAttachments.SHEARED, sheared);
            ci.cancel();
        }
    }
}
