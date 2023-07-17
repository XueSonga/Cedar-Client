package cn.XueSong.Client.util.chat;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class ChatUtil {
    public static void sendPrivateMessage(String message) {
        Minecraft mc = Minecraft.getMinecraft();
        ChatComponentText chatComponent = new ChatComponentText(message);
        chatComponent.getChatStyle().setColor(EnumChatFormatting.RED); // ������ɫ����ѡ��
        mc.thePlayer.addChatComponentMessage(chatComponent);
    }
}
