package cn.XueSong.Client.mod.Combo;

import cn.XueSong.Client.util.EntityFinder;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class AimAssistThread extends Thread {
    private final Minecraft mc;
    private EntityPlayer targetPlayer = null;
    private static boolean isAimAssistEnabled = false;

    private double maxAimAngle = 180.0;
    private double maxDistance = 4;
    private double aimSpeed = 0.1;

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
                Thread.sleep(10); // ���ö�ʱ�����ߣ�����CPUռ���ʹ���
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
        double targetPitch = -Math.atan2(deltaY, Math.sqrt(deltaX * deltaX + deltaZ * deltaZ)) * 180.0D / Math.PI;

        // ʹ�ò�ֵƽ����׼
        mc.thePlayer.rotationYaw += (targetYaw - mc.thePlayer.rotationYaw) * aimSpeed;
        mc.thePlayer.rotationPitch += (targetPitch - mc.thePlayer.rotationPitch) * aimSpeed;
    }

    private boolean isPlayerValid(EntityPlayer player) {
        //�������Ƿ񻹻��ţ��������εģ�������ָ���ľ�����
        return player != null && player.isEntityAlive() && !player.isInvisible() && mc.thePlayer.getDistanceSqToEntity(player) <= maxDistance * maxDistance;
    }
}
