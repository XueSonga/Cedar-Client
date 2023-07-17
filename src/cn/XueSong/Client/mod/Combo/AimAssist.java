package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.mod.Mod;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class AimAssist extends Mod {

    public AimAssist() {
        super("AimAssist", "自瞄辅助", true);
    }

    @Override
    public void render() {

    }


class AimThe extends Thread{
    private EntityPlayer targetPlayer;
    private boolean isAiming;
    private double prevYaw;
    private double prevPitch;
    private final double range = 6.0; // 瞄准范围，单位：方块
    private final double maxAngle = 60.0; // 最大瞄准角度，单位：度
    @Override
    public void run() {
        while (true){
            Minecraft mc = Minecraft.getMinecraft();
            if (Minecraft.getMinecraft().gameSettings.keyBindPickBlock.isKeyDown()) {
                if (isAiming && targetPlayer != null) {
                    double deltaX = targetPlayer.posX - mc.thePlayer.posX;
                    double deltaY = (targetPlayer.posY + targetPlayer.getEyeHeight()) - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
                    double deltaZ = targetPlayer.posZ - mc.thePlayer.posZ;
                    double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ);

                    double yaw = Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI - 90.0D;
                    double pitch = -Math.atan2(deltaY, Math.sqrt(deltaX * deltaX + deltaZ * deltaZ)) * 180.0D / Math.PI;

                    // 限制偏航角和俯仰角的瞄准范围在180度以内
                    double yawDifference = Math.abs(yaw - prevYaw);
                    double pitchDifference = Math.abs(pitch - prevPitch);
                    if (yawDifference > 180.0D) {
                        yawDifference = 360.0D - yawDifference;
                    }
                    if (pitchDifference > 180.0D) {
                        pitchDifference = 360.0D - pitchDifference;
                    }

                    // 添加条件以限制旋转角度
                    if (yawDifference <= 180.0D && Math.abs(yawDifference) <= 90.0D) {
                        mc.thePlayer.rotationYaw = (float) yaw;
                        prevYaw = yaw;
                    }
                    if (pitchDifference <= 180.0D && Math.abs(pitchDifference) <= 90.0D) {
                        mc.thePlayer.rotationPitch = (float) pitch;
                        prevPitch = pitch;
                    }
                } else {
                    targetPlayer = findTargetPlayer(mc, range, maxAngle); // 设置距离和最大角度限制
                    isAiming = true;
                }
            } else {
                isAiming = false;
            }
        }
    }
    private EntityPlayer findTargetPlayer(Minecraft mc, double range, double maxAngle) {
        double closestDistanceSq = Double.MAX_VALUE;
        EntityPlayer closestPlayer = null;
        double playerPosX = mc.thePlayer.posX;
        double playerPosY = mc.thePlayer.posY;
        double playerPosZ = mc.thePlayer.posZ;
        double playerYaw = mc.thePlayer.rotationYaw;

        for (EntityPlayer player : mc.theWorld.playerEntities) {
            if (player != mc.thePlayer && mc.thePlayer.getDistanceSqToEntity(player) <= range * range) {
                double distanceSq = player.getDistanceSq(playerPosX, playerPosY, playerPosZ);

                if (distanceSq < closestDistanceSq) {
                    double angleDifference = Math.abs(playerYaw - getAngleToEntity(mc.thePlayer, player));
                    if (angleDifference <= maxAngle) {
                        closestPlayer = player;
                        closestDistanceSq = distanceSq;
                    }
                }
            }
        }

        return closestPlayer;
    }

    private float getAngleToEntity(EntityPlayer self, Entity target) {
        double deltaX = target.posX - self.posX;
        double deltaZ = target.posZ - self.posZ;
        return (float) Math.toDegrees(Math.atan2(deltaZ, deltaX)) - 90.0F;
    }
}
}