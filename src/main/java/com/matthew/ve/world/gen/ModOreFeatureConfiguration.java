package com.matthew.ve.world.gen;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;

import java.util.List;

public class ModOreFeatureConfiguration implements FeatureConfiguration {
    public static final Codec<ModOreFeatureConfiguration> CODEC = RecordCodecBuilder.create((p_67849_) -> {
        return p_67849_.group(Codec.list(ModOreFeatureConfiguration.TargetBlockState.CODEC).fieldOf("targets").forGetter((p_161027_) -> {
            return p_161027_.targetStates;
        }), Codec.intRange(0, 64).fieldOf("size").forGetter((p_161025_) -> {
            return p_161025_.size;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("discard_chance_on_air_exposure").forGetter((p_161020_) -> {
            return p_161020_.discardChanceOnAirExposure;
        })).apply(p_67849_, ModOreFeatureConfiguration::new);
    });
    public final List<ModOreFeatureConfiguration.TargetBlockState> targetStates;
    public final int size;
    public final float discardChanceOnAirExposure;

    public ModOreFeatureConfiguration(List<ModOreFeatureConfiguration.TargetBlockState> p_161016_, int p_161017_, float p_161018_) {
        this.size = p_161017_;
        this.targetStates = p_161016_;
        this.discardChanceOnAirExposure = p_161018_;
    }

    public ModOreFeatureConfiguration(List<ModOreFeatureConfiguration.TargetBlockState> p_161013_, int p_161014_) {
        this(p_161013_, p_161014_, 0.0F);
    }

    public ModOreFeatureConfiguration(RuleTest p_161008_, BlockState p_161009_, int p_161010_, float p_161011_) {
        this(ImmutableList.of(new ModOreFeatureConfiguration.TargetBlockState(p_161008_, p_161009_)), p_161010_, p_161011_);
    }

    public ModOreFeatureConfiguration(RuleTest p_67843_, BlockState p_67844_, int p_67845_) {
        this(ImmutableList.of(new ModOreFeatureConfiguration.TargetBlockState(p_67843_, p_67844_)), p_67845_, 0.0F);
    }

    public static ModOreFeatureConfiguration.TargetBlockState target(RuleTest p_161022_, BlockState p_161023_) {
        return new ModOreFeatureConfiguration.TargetBlockState(p_161022_, p_161023_);
    }

    public static class TargetBlockState {
        public static final Codec<ModOreFeatureConfiguration.TargetBlockState> CODEC = RecordCodecBuilder.create((p_161039_) -> {
            return p_161039_.group(RuleTest.CODEC.fieldOf("target").forGetter((p_161043_) -> {
                return p_161043_.target;
            }), BlockState.CODEC.fieldOf("state").forGetter((p_161041_) -> {
                return p_161041_.state;
            })).apply(p_161039_, ModOreFeatureConfiguration.TargetBlockState::new);
        });
        public final RuleTest target;
        public final BlockState state;

        TargetBlockState(RuleTest p_161036_, BlockState p_161037_) {
            this.target = p_161036_;
            this.state = p_161037_;
        }
    }
}