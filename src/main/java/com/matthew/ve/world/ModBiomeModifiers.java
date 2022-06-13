package com.matthew.ve.world;

import com.matthew.ve.VE;
import com.matthew.ve.world.gen.ModOreGeneration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBiomeModifiers {
    static DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, VE.MOD_ID);
    public static RegistryObject<Codec<ModOreGeneration>> ORE_GENERATION_CODEC = BIOME_MODIFIER_SERIALIZERS.register("ore_generation", () ->
            RecordCodecBuilder.create(builder -> builder.group(

            PlacedFeature.CODEC.fieldOf("feature").forGetter(ModOreGeneration::feature)

            ).apply(builder, ModOreGeneration::new)));

    public static void register(IEventBus eventBus) {
        BIOME_MODIFIER_SERIALIZERS.register(eventBus);
    }
}
