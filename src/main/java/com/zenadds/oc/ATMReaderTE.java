package com.zenadds.oc;

import com.zenadds.init.Items;

import li.cil.oc.api.Network;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.TileEntityEnvironment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ATMReaderTE extends TileEntityEnvironment {

	private ItemStackHandler inventory;
	
	public ATMReaderTE() {
		inventory = new ItemStackHandler(1);
		node = Network.newNode(this, Visibility.Network)
				.withConnector()
				.withComponent("atm_reader")
				.create();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		if (nbt.hasKey("inventory")) {
			inventory.deserializeNBT((NBTTagCompound) nbt.getCompoundTag("inventory"));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setTag("inventory", inventory.serializeNBT());
		return nbt;
	}

	@Callback
	public Object[] isCreditCardInserted(Context context, Arguments args) {
		return new Object[] {inventory.getStackInSlot(0).getItem() == Items.oc_creditCard};
	}
	
	/**
	 * Used to init the service of a credit card.<br/>
	 * This is required to access credit card methods.
	 */
	@Callback
	public Object[] creditCardService(Context context, Arguments args) {
		args.checkString(1);
		ItemStack stack = inventory.getStackInSlot(0);
		if (stack.getItem() != Items.oc_creditCard) {
			return new Object[] {false, "no credit card inserted"};
		}
		NBTTagCompound nbt = stack.getTagCompound();
		nbt.setString("service", args.checkString(1));
		stack.setTagCompound(nbt);
		return new Object[] {true};
	}
	
	@Callback
	public Object[] getCreditCardUUID(Context context, Arguments args) {
		ItemStack stack = inventory.getStackInSlot(0);
		if (stack.getItem() != Items.oc_creditCard) {
			return new Object[] {false, "no credit card inserted"};
		}
		NBTTagCompound nbt = stack.getTagCompound();
		if (!nbt.hasKey("service")) {
			return new Object[] {false, "service not registered"};
		}
		return new Object[] {true, nbt.getUniqueId("creditCardID").toString()};
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return (T) inventory;
		}
		return super.getCapability(capability, facing);
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			return true;
		}
		return super.hasCapability(capability, facing);
	}

}
