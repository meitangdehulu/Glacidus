package com.legacy.glacidus.client.renders;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.legacy.glacidus.blocks.BlocksGlacidus;
import com.legacy.glacidus.client.renders.tiles.GlacidusPortalRenderer;
import com.legacy.glacidus.tiles.TileEntityGlacidusPortal;
import com.legacy.glacidus.util.ModInfo;

public class BlockRenders 
{

	@SubscribeEvent
	public void onModelRegisterEvent(ModelRegistryEvent event)
	{
		register(BlocksGlacidus.small_crystal, "small_crystal");
		register(BlocksGlacidus.crysial_flower, "crysial_flower");
		register(BlocksGlacidus.lumicia_grass, "lumicia_grass");
		register(BlocksGlacidus.lumicia_dirt, "lumicia_dirt");
		register(BlocksGlacidus.antinatric_stone, "antinatric_stone");

		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGlacidusPortal.class, new GlacidusPortalRenderer());
	}

	private static void registerBlockWithStateMapper(Block block, IStateMapper mapper)
	{
		ModelLoader.setCustomStateMapper(block, mapper);

		ModelLoader.setCustomMeshDefinition(Item.getItemFromBlock(block), new ItemMeshDefinition() 
		{
			@Override
			@SuppressWarnings("deprecation")
			public ModelResourceLocation getModelLocation(ItemStack stack) 
			{
				Block block = Block.getBlockFromItem(stack.getItem());

				return Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getModelManager().getBlockModelShapes().getBlockStateMapper().getVariants(block).get(block.getStateFromMeta(stack.getMetadata()));
			}
		});
	}

	private static void register(Block block, String model)
	{
		register(block, 0, model);
	}

	private static void register(Block block, int meta, String model)
	{
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(ModInfo.MOD_ID + ":" + model, "inventory"));
	}

	private static void registerMetadata(Block block, ResourceLocation... model)
	{
		ModelLoader.registerItemVariants(Item.getItemFromBlock(block), model);
	}

}