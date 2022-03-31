package com.Fishmod.mod_LavaCow.init;

import com.Fishmod.mod_LavaCow.mod_LavaCow;
import com.Fishmod.mod_LavaCow.block.FURShroomBlock;
import com.Fishmod.mod_LavaCow.config.FURConfig;
import com.Fishmod.mod_LavaCow.core.SpawnUtil;
import com.Fishmod.mod_LavaCow.world.gen.WorldGenCemeterySmall;
import com.Fishmod.mod_LavaCow.world.gen.WorldGenLargeGlowShroom;
import com.Fishmod.mod_LavaCow.world.structure.DesertTombStructure;
import net.minecraft.entity.EntityClassification;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.DimensionSettings;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BigMushroomFeatureConfig;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.settings.StructureSeparationSettings;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.StructureSpawnListGatherEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = mod_LavaCow.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FURWorldRegistry {
	public static List<Feature<?>> featureList = new ArrayList<>();	
    public static final DeferredRegister<Structure<?>> STRUCTURES =
            DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, mod_LavaCow.MODID);	
    
	public static Feature<BigMushroomFeatureConfig> HUGE_GLOWSHROOM;
	public static Feature<NoFeatureConfig> SMALL_CEMETERY;
	
	public static final RegistryObject<Structure<NoFeatureConfig>> DESERT_TOMB = STRUCTURES.register("desert_tomb", DesertTombStructure::new);
	
	public static ConfiguredFeature<?, ?> GLOWSHROOM_CF;
	public static ConfiguredFeature<?, ?> BLOODTOOTH_SHROOM_CF;
	public static ConfiguredFeature<?, ?> CORDY_SHROOM_CF;
	public static ConfiguredFeature<?, ?> VEIL_SHROOM_CF;
	public static ConfiguredFeature<?, ?> HUGE_GLOWSHROOM_CF;
	public static ConfiguredFeature<?, ?> SMALL_CEMETERY_CF;
	public static StructureFeature<?, ?> DESERT_TOMB_CF;
	
	public static void register() {
		HUGE_GLOWSHROOM = registerFeature(new WorldGenLargeGlowShroom(BigMushroomFeatureConfig.CODEC), "mod_lavacow:huge_glowshroom");
		SMALL_CEMETERY = registerFeature(new WorldGenCemeterySmall(NoFeatureConfig.CODEC), "mod_lavacow:small_cemetery");
	    
		GLOWSHROOM_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:glowshroom", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FURBlockRegistry.GLOWSHROOM.defaultBlockState().setValue(FURShroomBlock.AGE, Integer.valueOf(new Random().nextInt(2)))), SimpleBlockPlacer.INSTANCE).tries(8).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(FURConfig.pSpawnRate_Glowshroom.get()));		
		BLOODTOOTH_SHROOM_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:bloodtooth_shroom", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FURBlockRegistry.BLOODTOOTH_SHROOM.defaultBlockState().setValue(FURShroomBlock.AGE, Integer.valueOf(new Random().nextInt(2)))), SimpleBlockPlacer.INSTANCE).tries(64).noProjection().build()).range(128).chance(8).count(3));		
		CORDY_SHROOM_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:cordy_shroom", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FURBlockRegistry.CORDY_SHROOM.defaultBlockState().setValue(FURShroomBlock.AGE, Integer.valueOf(new Random().nextInt(2)))), SimpleBlockPlacer.INSTANCE).tries(64).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4).count(3));		
		VEIL_SHROOM_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:veil_shroom", Feature.RANDOM_PATCH.configured(new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(FURBlockRegistry.VEIL_SHROOM.defaultBlockState().setValue(FURShroomBlock.AGE, Integer.valueOf(new Random().nextInt(2)))), SimpleBlockPlacer.INSTANCE).tries(64).noProjection().build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).chance(4).count(3));				
		HUGE_GLOWSHROOM_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:huge_glowshroom", HUGE_GLOWSHROOM.configured(new BigMushroomFeatureConfig(new SimpleBlockStateProvider(FURBlockRegistry.GLOWSHROOM_BLOCK_CAP.defaultBlockState()), new SimpleBlockStateProvider(FURBlockRegistry.GLOWSHROOM_BLOCK_STEM.defaultBlockState()), 3)));
		SMALL_CEMETERY_CF = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "mod_lavacow:small_cemetery", SMALL_CEMETERY.configured(IFeatureConfig.NONE).chance(FURConfig.Cemetery_SpawnRate.get()));		
	}
	
	public static void setupStructures() {
        putStructureOnAList(DESERT_TOMB.get());
        addStructureSeperation(DimensionSettings.OVERWORLD, DESERT_TOMB.get(), new StructureSeparationSettings(Math.max(FURConfig.SpawnRate_Desert_Tomb.get(), 2), Math.max(FURConfig.SpawnRate_Desert_Tomb.get() / 2, 1), 214748364));	
        DESERT_TOMB_CF = Registry.register(WorldGenRegistries.CONFIGURED_STRUCTURE_FEATURE, "mod_lavacow:desert_tomb", DESERT_TOMB.get().configured(IFeatureConfig.NONE));
	}
	
	public static void onBiomesLoad(BiomeLoadingEvent event) {
		Biome biome = ForgeRegistries.BIOMES.getValue(event.getName());

		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(!(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.COLD) && 
				!(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.DRY))))) {
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, GLOWSHROOM_CF);
		}
		
		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.CRIMSON_FOREST)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BLOODTOOTH_SHROOM_CF);
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.MUSHROOM) || 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.WET))) {
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CORDY_SHROOM_CF);
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.MUSHROOM) || 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.FOREST))) {
			event.getGeneration().addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, VEIL_SHROOM_CF);
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD)) {
			event.getGeneration().addFeature(GenerationStage.Decoration.SURFACE_STRUCTURES, SMALL_CEMETERY_CF);
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(SpawnUtil.getRegistryKey(biome).equals(Biomes.DESERT) || 
					SpawnUtil.getRegistryKey(biome).equals(Biomes.DESERT_HILLS) || 
					SpawnUtil.getRegistryKey(biome).equals(Biomes.DESERT_LAKES))) {
			event.getGeneration().addStructureStart(DESERT_TOMB_CF);
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.WET) || 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.RIVER))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.MYCOSIS, FURConfig.pSpawnRate_ZombieMushroom.get(), 8, 16));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.WET) || 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.RIVER))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.FOGLET, FURConfig.pSpawnRate_Foglet.get(), 8, 16));
		}
		
		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.CRIMSON_FOREST)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.IMP, FURConfig.pSpawnRate_Imp.get(), 8, 16));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.COLD)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.FRIGID, FURConfig.pSpawnRate_ZombieFrozen.get(), 8, 16));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.FOREST)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.UNDEADSWINE, FURConfig.pSpawnRate_UndeadSwine.get(), 4, 8));
		}
		
		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.NETHER_WASTES)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.SALAMANDER, FURConfig.pSpawnRate_Salamander.get(), 4, 8));
		}
		
		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.SOUL_SAND_VALLEY)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.SALAMANDER, FURConfig.pSpawnRate_Salamander.get() / 10, 4, 8));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.CONIFEROUS)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.WENDIGO, FURConfig.pSpawnRate_Wendigo.get(), 1, 1));
		}

		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SPOOKY) ||
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.CONIFEROUS)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FUREntityRegistry.RAVEN, FURConfig.pSpawnRate_Raven.get(), 4, 8));
		}

		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.BEACH)) {
			event.getSpawns().getSpawner(EntityClassification.CREATURE).add(new MobSpawnInfo.Spawners(FUREntityRegistry.SEAGULL, FURConfig.pSpawnRate_Seagull.get(), 4, 8));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.JUNGLE))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.PTERA, FURConfig.pSpawnRate_Ptera.get(), 4, 8));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.DRY))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.PTERA, FURConfig.pSpawnRate_Ptera.get(), 2, 4));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.JUNGLE))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.VESPA, FURConfig.pSpawnRate_Vespa.get(), 2, 4));
		}

		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.PLAINS))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.SCARECROW, FURConfig.pSpawnRate_Scarecrow.get(), 1, 1));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.WET))) {
			event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FUREntityRegistry.PIRANHA, FURConfig.pSpawnRate_Piranha.get(), 2, 4));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.WET))) {
			event.getSpawns().getSpawner(EntityClassification.WATER_AMBIENT).add(new MobSpawnInfo.Spawners(FUREntityRegistry.SWARMER, FURConfig.pSpawnRate_Swarmer.get(), 2, 4));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SANDY))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.BONEWORM, FURConfig.pSpawnRate_BoneWorm.get(), 1, 2));
		}

		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.SOUL_SAND_VALLEY)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.BONEWORM, FURConfig.pSpawnRate_BoneWorm.get() / 10, 1, 2));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SNOWY))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.PINGU, FURConfig.pSpawnRate_Pingu.get(), 4, 8));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.UNDERTAKER, FURConfig.pSpawnRate_Undertaker.get(), 1, 1));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.MESA))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.GHOSTRAY, FURConfig.pSpawnRate_GhostRay.get(), 1, 2));
		}
		
		if(SpawnUtil.getRegistryKey(biome).equals(Biomes.SOUL_SAND_VALLEY)) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.GHOSTRAY, FURConfig.pSpawnRate_GhostRay.get() / 3, 1, 1));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.RARE)) &&
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.HILLS) ||
				 BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.MOUNTAIN))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.BANSHEE, FURConfig.pSpawnRate_Banshee.get(), 1, 2));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SAVANNA))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.WETA, FURConfig.pSpawnRate_Weta.get(), 4, 8));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SAVANNA))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.AVATON, FURConfig.pSpawnRate_Avaton.get(), 1, 2));
		}
		
		if(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.OVERWORLD) && 
				(BiomeDictionary.getTypes(SpawnUtil.getRegistryKey(biome)).contains(Type.SANDY))) {
			event.getSpawns().getSpawner(EntityClassification.MONSTER).add(new MobSpawnInfo.Spawners(FUREntityRegistry.FORSAKEN, FURConfig.pSpawnRate_Forsaken.get(), 4, 8));
		}
	}
	
	public static void onStructuresLoad(StructureSpawnListGatherEvent event) {
		if(event.getStructure().equals(Structure.DESERT_PYRAMID)) {
			event.addEntitySpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(FUREntityRegistry.MUMMY, FURConfig.pSpawnRate_Mummy.get(), 4, 8));
		}
		
		if(event.getStructure().equals(Structure.BASTION_REMNANT) || 
				event.getStructure().equals(Structure.JUNGLE_TEMPLE) ||
				event.getStructure().equals(Structure.MINESHAFT) ||
				event.getStructure().equals(Structure.NETHER_BRIDGE) ||
				event.getStructure().equals(Structure.PILLAGER_OUTPOST) ||
				event.getStructure().equals(Structure.STRONGHOLD) ||
				event.getStructure().equals(Structure.VILLAGE) ||
				event.getStructure().equals(Structure.WOODLAND_MANSION)) {
			event.addEntitySpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(FUREntityRegistry.MIMIC, FURConfig.pSpawnRate_Mimic.get(), 1, 1));
		}
	}
	
    public static void addStructureSeperation(RegistryKey<DimensionSettings> preset, Structure<?> structure, StructureSeparationSettings settings) {
        WorldGenRegistries.NOISE_GENERATOR_SETTINGS.get(preset).structureSettings().structureConfig().put(structure, settings);
    }

    public static <F extends Structure<?>> void putStructureOnAList(Structure<NoFeatureConfig> structure) {
        Structure.STRUCTURES_REGISTRY.put(structure.getRegistryName().toString(), structure);       
    }
    
    private static <C extends IFeatureConfig, F extends Feature<C>> F registerFeature(F feature, String registryName) {
        featureList.add(feature.setRegistryName(registryName));
        return feature;
    }
    
    public static void register(IEventBus eventBus) {
        STRUCTURES.register(eventBus);
    }
}