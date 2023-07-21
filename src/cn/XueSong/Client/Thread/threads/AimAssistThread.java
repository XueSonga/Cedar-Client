package cn.XueSong.Client.Thread.threads;

import cn.XueSong.Client.util.EntityFinder;
import cn.XueSong.Client.util.rotation.RotationUtil;
import cn.XueSong.Client.util.team.TeamUtil;
import cn.XueSong.Client.util.vector.Vector2f;
import cn.XueSong.Client.util.vector.Vector3d;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class AimAssistThread implements Runnable{
    private final Minecraft mc;
    private EntityPlayer targetPlayer = null;
    private static boolean isAimAssistEnabled = false;

    private double maxAimAngle = 180.0;
    private double maxDistance = 5;
    private double aimSpeed = 0.8;

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
                Thread.sleep(3); // 设置短时间休眠，避免CPU占用率过高
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void adjustAimToTarget() {
        Vector2f currentRotation = new Vector2f(mc.thePlayer.rotationYaw, mc.thePlayer.rotationPitch);
        Vector2f targetRotation = RotationUtil.calculate(mc.thePlayer.getCustomPositionVector().add(0, mc.thePlayer.getEyeHeight(), 0), new Vector3d(targetPlayer.posX, targetPlayer.posY + targetPlayer.getEyeHeight(), targetPlayer.posZ));
        Vector2f smoothedRotation = RotationUtil.smooth(currentRotation, targetRotation, aimSpeed);
        mc.thePlayer.rotationYaw = smoothedRotation.x;
        mc.thePlayer.rotationPitch = smoothedRotation.y;
    }
    TeamUtil teamUtil = new TeamUtil();

    private boolean isPlayerValid(EntityPlayer player) {
        // 检查玩家是否还活着，不是隐形的，并且在指定的距离内，且不在同一个队伍中
        return player != null
                && player.isEntityAlive()
                && !player.isInvisible()
                && mc.thePlayer.getDistanceSqToEntity(player) <= maxDistance * maxDistance
                && !TeamUtil.isSameTeam(mc.thePlayer, player);
    }
}
