package com.matthew.ve.world.gen;

import com.matthew.ve.VE;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(ForgeRegistries.FEATURES, VE.MOD_ID);


   public static final RegistryObject<Feature<ModOreFeatureConfiguration>> SULFUR_GENERATION = FEATURES.register("sulfur_generation",
            () -> new ModCustomOreFeature(ModOreFeatureConfiguration.CODEC));

   public static void register(IEventBus eventBus) {
        FEATURES.register(eventBus);
    }

}
