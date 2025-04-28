package dev.worldgen.mortar.misc;

import com.mojang.serialization.Codec;
import dev.worldgen.mortar.Mortar;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.DyeColor;

public interface MortarAttachments {
    AttachmentType<DyeColor> COLOR = AttachmentRegistry.create(
        Mortar.id("color"),
        builder -> builder.persistent(DyeColor.CODEC).syncWith(DyeColor.PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    AttachmentType<Boolean> SHEARED = AttachmentRegistry.create(
        Mortar.id("sheared"),
        builder -> builder.persistent(Codec.BOOL).syncWith(PacketCodecs.BOOLEAN, AttachmentSyncPredicate.all())
    );

    static void init() {}
}
