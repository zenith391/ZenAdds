package com.zenadds;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

import com.zenadds.proxy.CommonProxy;

@Mod(modid = ZenAdds.MODID, name = ZenAdds.NAME, version = ZenAdds.VERSION)
public class ZenAdds {
	
    public static final String MODID = "zenadds";
    public static final String NAME = "Zen' Adds";
    public static final String VERSION = "0.7";
    public static final String CLIENT_PROXY_CLASS = "com.zenadds.proxy.ClientProxy";
    public static final String SERVER_PROXY_CLASS = "com.zenadds.proxy.ServerProxy";

    public static Logger logger;

    @SidedProxy(clientSide = CLIENT_PROXY_CLASS, serverSide = SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }
    
}
