package cn.XueSong.Client.util.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtil {
    public static void sendPrivateMessage(String message) {
        Minecraft mc = Minecraft.getMinecraft();
        ChatComponentText chatComponent = new ChatComponentText(message);
        chatComponent.getChatStyle().setColor(EnumChatFormatting.RED); // 设置颜色（可选）
        mc.thePlayer.addChatComponentMessage(chatComponent);
    }
}
