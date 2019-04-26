package com.zenadds.oc;

import java.util.ArrayList;

import li.cil.oc.api.machine.Machine;
import li.cil.oc.api.machine.MachineHost;
import li.cil.oc.api.network.Node;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GERTRouterTE extends TileEntity implements MachineHost {

	private Machine machine = new li.cil.oc.server.machine.Machine(this);
	private ArrayList<ItemStack> components = new ArrayList<>();
	
	public GERTRouterTE() {
		machine.setCostPerTick(1d);
		components.add(new ItemStack(Item.getByNameOrId("opencomputers:component"), 1, 18)); // creative tier APU
		components.add(new ItemStack(Item.getByNameOrId("opencomputers:component"), 1, 11)); // 1x 3.5 memory
		components.add(new ItemStack(Item.getByNameOrId("opencomputers:card"), 1, 7)); // 1x wireless card tier 2
		components.add(new ItemStack(Item.getByNameOrId("opencomputers:storage"), 1, 2)); // 1x tier 1 HDD
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		if (nbt.hasKey("machine")) {
			machine.load(nbt.getCompoundTag("machine"));
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		NBTTagCompound mch = new NBTTagCompound();
		machine.save(mch);
		nbt.setTag("machine", mch);
		return nbt;
	}
	
	@Override
	public void markChanged() {
		
	}

	@Override
	public World world() {
		return this.world;
	}

	@Override
	public double xPosition() {
		return this.pos.getX();
	}

	@Override
	public double yPosition() {
		return this.pos.getY();
	}

	@Override
	public double zPosition() {
		return this.pos.getZ();
	}

	@Override
	public int componentSlot(String arg0) {
		return 0;
	}

	@Override
	public Iterable<ItemStack> internalComponents() {
		return components;
	}

	@Override
	public Machine machine() {
		return machine;
	}

	@Override
	public void onMachineConnect(Node arg0) {
		
	}
	
	public void onClick(EntityPlayer player) {
		if (player.isSneaking()) {
			machine.start();
			machine.beep("-.-");
			System.out.println("beeped");
		}
		System.out.println(player.isSneaking());
	}

	@Override
	public void onMachineDisconnect(Node arg0) {
		
	}

}
