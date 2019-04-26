package com.zenadds.init;

import com.zenadds.ZenAdds;
import com.zenadds.event.RegistryEventHandler;
import com.zenadds.oc.ATMReaderTE;
import com.zenadds.oc.CreditCardReader;
import com.zenadds.oc.GERTRouter;
import com.zenadds.oc.GERTRouterTE;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Blocks {
	
	public static CreditCardReader oc_atmReader;
	public static GERTRouter oc_gertRouter;
	
	private static void register(Block block) {
		RegistryEventHandler.REGISTER_BLOCKS.add(block);
		System.out.println(block.getUnlocalizedName().substring(5));
		ItemBlock itemBlock = (ItemBlock) new ItemBlock(block).
				setRegistryName(block.getRegistryName()).
				setUnlocalizedName(block.getUnlocalizedName().substring(5))
				.setCreativeTab(CreativeTabs.TOOLS);
		RegistryEventHandler.ITEM_BLOCKS.add(itemBlock);
		RegistryEventHandler.REGISTER_ITEMS.add(itemBlock);
	}
	
	public static void init() {
		ZenAdds.logger.info("Registering blocks..");
		
		oc_atmReader = new CreditCardReader();
		oc_gertRouter = new GERTRouter();
		
		register(oc_atmReader);
		register(oc_gertRouter);
		GameRegistry.registerTileEntity(ATMReaderTE.class, new ResourceLocation(ZenAdds.MODID, "atm_reader"));
		GameRegistry.registerTileEntity(GERTRouterTE.class, new ResourceLocation(ZenAdds.MODID, "gert_router"));
	}
	
}
