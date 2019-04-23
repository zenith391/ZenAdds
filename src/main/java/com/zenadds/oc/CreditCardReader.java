package com.zenadds.oc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CreditCardReader extends Block {

	public CreditCardReader() {
		super(Material.IRON);
		this.setUnlocalizedName("atm_reader");
		this.setRegistryName("zenadds:atm_reader");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(1.5f);
	}

	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new ATMReaderTE();
	}
	
}
