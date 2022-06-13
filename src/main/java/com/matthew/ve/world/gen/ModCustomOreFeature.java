package com.matthew.ve.world.gen;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.BulkSectionAccess;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.BitSet;
import java.util.function.Function;


public class ModCustomOreFeature extends Feature<ModOreFeatureConfiguration> {

    private static final BlockPos[] offsets =  {
        new BlockPos(1,0,0),
        new BlockPos(0,0,1),
        new BlockPos(-1,0,0),
        new BlockPos(0,0,-1)

    };
    public ModCustomOreFeature(Codec<ModOreFeatureConfiguration> p_65786_) {
        super(p_65786_);
    }

    @Override
    public boolean place(FeaturePlaceContext<ModOreFeatureConfiguration> p_159749_) {
        RandomSource randomsource = p_159749_.random();
        BlockPos blockpos = p_159749_.origin();
        WorldGenLevel worldgenlevel = p_159749_.level();
        ModOreFeatureConfiguration oreconfiguration = p_159749_.config();

        ChunkAccess chunk = worldgenlevel.getChunk(blockpos);
        for (int y = 0; y < 40; y++) {
            for (int x = 0; x < 16; x++) {
                for (int z = 0; z < 16; z++) {
                    BlockPos newPos = blockpos.offset(x, y, z);

                        if(isLava(chunk, newPos)) {
                            placeOre(chunk, newPos, oreconfiguration);
                        }
                }
            }
        }

        return true;

    }


    private void placeOre(ChunkAccess chunk, BlockPos blockPos, ModOreFeatureConfiguration oreconfiguration) {
        for(int side = 0; side < 4; side++) {
            BlockPos newPos = blockPos.offset(offsets[side]);
            BlockState id = chunk.getBlockState(newPos);
            if(id == Blocks.DEEPSLATE.defaultBlockState()) {
                chunk.setBlockState(newPos, oreconfiguration.targetStates.get(1).state, true);
            }

        }

    }

    private boolean isLava(ChunkAccess chunk, BlockPos blockPos) {
        return (chunk.getBlockState(blockPos) == Blocks.LAVA.defaultBlockState());
    }


}
