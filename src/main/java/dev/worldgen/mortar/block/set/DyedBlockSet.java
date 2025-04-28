package dev.worldgen.mortar.block.set;

import dev.worldgen.mortar.Mortar;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.CandleCakeBlock;
import net.minecraft.block.ConcretePowderBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

import static dev.worldgen.mortar.block.MortarBlockUtils.coloredSettings;
import static dev.worldgen.mortar.block.MortarBlockUtils.rawRegister;
import static dev.worldgen.mortar.misc.MortarDyes.*;

public record DyedBlockSet(String name, Block maroon, Block scarlet, Block amber, Block pear, Block pine, Block slate, Block lavender, Block salmon) {

    public static DyedBlockSet generic(String suffix, Block copy) {
        return create(BlockCreator.GENERIC, suffix, copy, null);
    }

    public static DyedBlockSet create(BlockCreator creator, String suffix, Block copy) {
        return create(creator, suffix, copy, null);
    }

    public static DyedBlockSet create(BlockCreator creator, String suffix, Block copy, @Nullable BlockEntityType<?> blockEntity) {
        List<Block> blocks = new ArrayList<>();
        for (DyeColor color : mortarValues()) {
            String name = color.getId() + "_" + suffix;

            Block block = rawRegister(name, creator.apply(color, name, copy));
            blocks.add(block);

            if (blockEntity != null) blockEntity.addSupportedBlock(block);
        }
        return new DyedBlockSet(
            suffix,
            blocks.get(0),
            blocks.get(1),
            blocks.get(2),
            blocks.get(3),
            blocks.get(4),
            blocks.get(5),
            blocks.get(6),
            blocks.get(7)
        );
    }

    private static Block trimmedId(String name, int amount) {
        return Registries.BLOCK.get(Mortar.id(name.substring(0, name.length() - amount)));
    }

    public Block match(DyeColor color) {
        if (color.equals(MAROON)) return this.maroon;
        else if (color.equals(SCARLET)) return this.scarlet;
        else if (color.equals(AMBER)) return this.amber;
        else if (color.equals(PEAR)) return this.pear;
        else if (color.equals(PINE)) return this.pine;
        else if (color.equals(SLATE)) return this.slate;
        else if (color.equals(LAVENDER)) return this.lavender;
        else if (color.equals(SALMON)) return this.salmon;
        else return null;
    }

    public Stream<Block> stream() {
        return Stream.of(this.maroon, this.scarlet, this.amber,this.pear, this.pine, this.slate, this.lavender, this.salmon);
    }

    public interface BlockCreator {
        BlockCreator GENERIC = colorless(Block::new);
        BlockCreator CANDLE_CAKE = ((color, name, copied) -> new CandleCakeBlock(trimmedId(name, 5), coloredSettings(color, name, copied)));
        BlockCreator CONCRETE_POWDER = ((color, name, copied) -> new ConcretePowderBlock(trimmedId(name, 7), coloredSettings(color, name, copied)));

        static BlockCreator colorless(Function<AbstractBlock.Settings, Block> creator) {
            return ((color, name, copied) -> creator.apply(coloredSettings(color, name, copied)));
        }

        static BlockCreator colored(BiFunction<DyeColor, AbstractBlock.Settings, Block> creator) {
            return ((color, name, copied) -> creator.apply(color, coloredSettings(color, name, copied)));
        }

        Block apply(DyeColor color, String name, Block copy);
    }
}
