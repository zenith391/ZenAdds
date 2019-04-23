package com.zenadds.event;

import java.util.ArrayList;

import com.zenadds.IHasModel;
import com.zenadds.ZenAdds;
import com.zenadds.init.Blocks;
import com.zenadds.init.Items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryEventHandler {

	public static final ArrayList<Item> REGISTER_ITEMS = new ArrayList<Item>();
	public static final ArrayList<Block> REGISTER_BLOCKS = new ArrayList<Block>();
	public static final ArrayList<ItemBlock> ITEM_BLOCKS  = new ArrayList<ItemBlock>();
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event) {
		ZenAdds.logger.info("Registering items..");
		Items.init();
		event.getRegistry().registerAll(REGISTER_ITEMS.toArray(new Item[REGISTER_ITEMS.size()]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		ZenAdds.logger.info("Registering blocks..");
		Blocks.init();
		event.getRegistry().registerAll(REGISTER_BLOCKS.toArray(new Block[REGISTER_BLOCKS.size()]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event) {
		for (Item item : REGISTER_ITEMS) {
			if (item instanceof IHasModel) {
				((IHasModel) item).registerModels();
			}
		}
		for (Block block : REGISTER_BLOCKS) {
			if (block instanceof IHasModel) {
				((IHasModel) block).registerModels();
				
			}
		}
		for (ItemBlock itemBlock : ITEM_BLOCKS) {
			ModelLoader.setCustomModelResourceLocation(itemBlock, 0, new ModelResourceLocation(itemBlock.getRegistryName(), "inventory"));
		}
	}
	
}
