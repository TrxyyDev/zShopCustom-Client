package fr.maxlego08.zshop.packet;

import java.io.IOException;

import fr.maxlego08.zshop.ShopItem;
import fr.maxlego08.zshop.util.ShopAction;
import fr.maxlego08.zshop.util.ShopCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class PacketServerShop extends Packet{

	private final ShopAction action;
	private ShopCategory category;
	private int quantity;
	private ShopItem item;
	
	public PacketServerShop(ShopAction action) {
		this.action = action;
	}

	public PacketServerShop(ShopAction action, ShopCategory category) {
		this.action = action;
		this.category = category;
	}

	public PacketServerShop(ShopAction action, ShopItem item, int quantity) {
		this.action = action;
		this.quantity = quantity;
		this.item = item;
	}

	@Override
	public void readPacketData(PacketBuffer r) throws IOException { }

	@Override
	public void writePacketData(PacketBuffer r) throws IOException {
		
		r.writeStringToBuffer(Minecraft.getMinecraft().thePlayer.getCommandSenderName());
		r.writeInt(action.getId());
		if (action.equals(ShopAction.SHOW_SHOP)){
			r.writeInt(category.getId());
		}else if (action.equals(ShopAction.BUY_ITEMS) || action.equals(ShopAction.SELL_ITEMS)){
			writeItem(r);
		}
		
	}

	@Override
	public void processPacket(INetHandler h) { }

	private void writeItem(PacketBuffer r){
		try {
			r.writeItemStackToBuffer(item.getItem());
		} catch (IOException e) {
			e.printStackTrace();
		}
		r.writeDouble(item.getBuyPrice());
		r.writeDouble(item.getSellPrice());
		r.writeInt(quantity);
	}
	
}
