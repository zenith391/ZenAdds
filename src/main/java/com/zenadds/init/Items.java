package com.zenadds.init;

import com.zenadds.ZenAdds;
import com.zenadds.event.RegistryEventHandler;
import com.zenadds.oc.CreditCard;

import net.minecraft.item.Item;

public class Items {
	
	public static CreditCard oc_creditCard;
	
	private static void register(Item item) {
		RegistryEventHandler.REGISTER_ITEMS.add(item);
	}
	
	public static void init() {
		ZenAdds.logger.info("Registering items 2..");
		oc_creditCard = new CreditCard();
		register(oc_creditCard);
	}
	
}
