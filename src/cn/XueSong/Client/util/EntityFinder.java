package cn.XueSong.Client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

import java.util.List;

public class EntityFinder {
    public EntityPlayer findNearestEntity(double maxAngle, double maxDistance, Minecraft mc) {
        EntityPlayer closestPlayer = null;
        double closestDistanceSq = Double.MAX_VALUE;

        List<EntityPlayer> playerList = mc.theWorld.playerEntities;
        for (EntityPlayer player : playerList) {
            if (player != mc.thePlayer && !player.isInvisible()) {
                double distanceSq = mc.thePlayer.getDistanceSqToEntity(player);
                double angleDifference = Math.abs(mc.thePlayer.rotationYaw - getAngleToEntity(mc.thePlayer, player));
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
        return (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0F;
    }
}