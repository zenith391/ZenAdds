package com.zenadds.oc;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GERTRouter extends Block {

	private GERTRouterTE te;
	
	public GERTRouter() {
		super(Material.IRON);
		this.setUnlocalizedName("gert_router");
		this.setRegistryName("zenadds:gert_router");
		this.setHarvestLevel("pickaxe", 2);
		this.setHardness(2.5f);
		
	}
	
	public void onBlockClicked(World world, BlockPos pos, EntityPlayer player) {
		if (te != null) {
			te.onClick(player);
		} else {
			System.out.println("No TE");
		}
	}

	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	public TileEntity createTileEntity(World world, IBlockState state) {
		te = new GERTRouterTE();
		return te;
	}
	
}
