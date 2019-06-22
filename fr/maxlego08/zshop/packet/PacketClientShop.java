package fr.maxlego08.zshop.packet;

import java.io.IOException;

import fr.maxlego08.zshop.ShopItem;
import fr.maxlego08.zshop.ShopManager;
import fr.maxlego08.zshop.gui.GuiShop;
import fr.maxlego08.zshop.gui.GuiShopCategories;
import fr.maxlego08.zshop.util.ShopAction;
import fr.maxlego08.zshop.util.ShopCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

public class PacketClientShop extends Packet{

	private ShopAction action;
	private ShopItem item;
	private ShopCategory category;
	private String errorMessage;
	
	@Override
	public void readPacketData(PacketBuffer r) throws IOException {
		this.action = ShopAction.get(r.readInt());
		if (action.equals(ShopAction.ADD_ITEMS)){
			ItemStack item = r.readItemStackFromBuffer();
			double buyPrice = r.readDouble();
			double sellPrice = r.readDouble();
			ShopCategory category = ShopCategory.get(r.readInt());
			this.item = new ShopItem(category, item, buyPrice, sellPrice);
		}else if (action.equals(ShopAction.SHOW_SHOP)){
			this.category = ShopCategory.get(r.readInt());
		}else if (action.equals(ShopAction.SEND_ERROR)){
			errorMessage = r.readStringFromBuffer(32767);
		}
	}

	@Override
	public void writePacketData(PacketBuffer p_148840_1_) throws IOException { }

	@Override
	public void processPacket(INetHandler packet) {
		switch (action) {
		case SHOW_CATEGORIES:
			Minecraft.getMinecraft().displayGuiScreen(new GuiShopCategories());
			break;
		case SHOW_SHOP:
			Minecraft.getMinecraft().displayGuiScreen(new GuiShop(category));
			break;
		case ADD_ITEMS:
			ShopManager.addItem(item.getCategory(), item);
			break;
		default:
			break;
		}
	}

}
