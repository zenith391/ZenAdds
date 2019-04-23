package com.zenadds.oc;

import java.util.List;
import java.util.UUID;

import com.zenadds.IHasModel;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.UsernameCache;
import net.minecraft.entity.player.EntityPlayer;

public class CreditCard extends Item implements IHasModel {

	public CreditCard() {
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setMaxStackSize(1);
		this.setRegistryName("zenadds:credit_card");
		this.setUnlocalizedName("credit_card");
	}

	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
	
	@Override
	public void addInformation(ItemStack stack, World world, List<String> lore, ITooltipFlag flag) {
		if (stack.getTagCompound() != null) {
			lore.add("ID: " + stack.getTagCompound().getUniqueId("creditCardID"));
			if (UsernameCache.containsUUID(stack.getTagCompound().getUniqueId("creditCardOwner"))) {
				lore.add("Owner: " + UsernameCache.getLastKnownUsername(stack.getTagCompound().getUniqueId("creditCardOwner")));
			} else {
				lore.add("Owner: ?");
			}
		}
	}
	
	@Override
	public boolean getShareTag() {
		return true;
	}
	
	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int meta, boolean bool) {
		if (!world.isRemote) {
			if (stack.getTagCompound() == null) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setUniqueId("creditCardID", UUID.randomUUID());
				nbt.setUniqueId("creditCardOwner", entity.getUniqueID());
				stack.setTagCompound(nbt);
			}
		}
	}
	
}
