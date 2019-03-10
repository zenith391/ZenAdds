package com.zenadds.init;

import com.zenadds.ZenAdds;
import com.zenadds.event.RegistryEventHandler;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

public class Blocks {
	
	private static void register(Block block) {
		RegistryEventHandler.REGISTER_BLOCKS.add(block);
		System.out.println(block.getUnlocalizedName().substring(5));
		RegistryEventHandler.REGISTER_ITEMS.add(new ItemBlock(block).
				setRegistryName(block.getRegistryName()).
				setUnlocalizedName(block.getUnlocalizedName().substring(5)));
	}
	
	public static void init() {
		ZenAdds.logger.info("Registering blocks 2..");
		
	}
	
}
