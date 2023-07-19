package cn.XueSong.Client.Thread.threads;

import cn.XueSong.Client.util.EntityFinder;
import cn.XueSong.Client.util.team.TeamUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class AimAssistThread implements Runnable{
    private final Minecraft mc;
    private EntityPlayer targetPlayer = null;
    private static boolean isAimAssistEnabled = false;

    private double maxAimAngle = 180.0;
    private double maxDistance = 4;
    private double aimSpeed = 0.08;

    public AimAssistThread(Minecraft mc) {
        this.mc = mc;
    }

    public static void setAimAssistEnabled(boolean enabled) {
        isAimAssistEnabled = enabled;
    }

    @Override
    public void run() {
        while (true) {
            if (isAimAssistEnabled && mc.gameSettings.keyBindAttack.isKeyDown()) {
                if (targetPlayer == null || !isPlayerValid(targetPlayer)) {
                    EntityFinder finder = new EntityFinder();
                    targetPlayer = finder.findNearestEntity(maxAimAngle, maxDistance, mc);
                } else {
                    adjustAimToTarget();
                }
            } else {
                targetPlayer = null;
            }
            try {
                Thread.sleep(4); // 设置短时间休眠，避免CPU占用率过高
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void adjustAimToTarget() {
        double deltaX = targetPlayer.posX - mc.thePlayer.posX;
        double deltaY = (targetPlayer.posY + targetPlayer.getEyeHeight()) - (mc.thePlayer.posY + mc.thePlayer.getEyeHeight());
        double deltaZ = targetPlayer.posZ - mc.thePlayer.posZ;

        double targetYaw = Math.atan2(deltaZ, deltaX) * 180.0D / Math.PI - 90.0D;
        if (targetYaw < 0) {
            targetYaw += 360.0;
        }
        double targetPitch = -Math.atan2(deltaY, Math.sqrt(deltaX * deltaX + deltaZ * deltaZ)) * 180.0D / Math.PI;

        // Use interpolation to smooth the aiming
        double yawDifference = targetYaw - mc.thePlayer.rotationYaw;
        if (yawDifference > 180.0) {
            yawDifference -= 360.0;
        } else if (yawDifference < -180.0) {
            yawDifference += 360.0;
        }
        mc.thePlayer.rotationYaw += yawDifference * aimSpeed;
        mc.thePlayer.rotationPitch += (targetPitch - mc.thePlayer.rotationPitch) * aimSpeed;
    }


    private boolean isPlayerValid(EntityPlayer player) {
        // 检查玩家是否还活着，不是隐形的，并且在指定的距离内，且不在同一个队伍中
        return player != null
                && player.isEntityAlive()
                && !player.isInvisible()
                && mc.thePlayer.getDistanceSqToEntity(player) <= maxDistance * maxDistance
                && !TeamUtil.isInSameTeam(player, mc.thePlayer);
    }

}
