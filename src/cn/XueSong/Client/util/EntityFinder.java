package cn.XueSong.Client.util;

import cn.XueSong.Client.util.bot.AntiBotUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class EntityFinder {
    public EntityPlayer findNearestEntity(double maxAngle, double maxDistance, Minecraft mc) {
        EntityPlayer closestPlayer = null;
        double closestDistanceSq = Double.MAX_VALUE;
        AntiBotUtil antiBotUtil = new AntiBotUtil();

        List<EntityPlayer> playerList = mc.theWorld.playerEntities;
        for (EntityPlayer player : playerList) {
            if (player != mc.thePlayer && !player.isInvisible()) {
                try {
                    if (antiBotUtil.isPlayerBot(player, mc)) {
                        // Skip this player if they are a bot
                        continue;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // If there is an error when checking the bot status, skip this player
                    continue;
                }

                double distanceSq = mc.thePlayer.getDistanceSqToEntity(player);
                double angleDifference = Math.abs(mc.thePlayer.rotationYaw - getAngleToEntity(mc.thePlayer, player));
                if (angleDifference > 180.0) {
                    angleDifference = 360.0 - angleDifference;
                }
                if (distanceSq < closestDistanceSq && angleDifference <= maxAngle) {
                    closestPlayer = player;
                    closestDistanceSq = distanceSq;
                }
            }
        }
        return closestPlayer;
    }


    private float getAngleToEntity(Entity player, Entity target) {
        double deltaX = target.posX - player.posX;
        double deltaZ = target.posZ - player.posZ;
        double angle = Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0;
        if (angle < 0) {
            angle += 360.0;
        }
        return (float) angle;
    }
}