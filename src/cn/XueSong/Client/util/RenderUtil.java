package cn.XueSong.Client.util;

import cn.XueSong.Client.util.shader.CShaders;

import java.awt.*;

public class RenderUtil {
    public static void drawRoundedGradientRect(double x, double y, double width, double height, double radius, Color firstColor, Color secondColor, boolean vertical) {
        CShaders.CGQ_SHADER.draw(x, y, width, height, radius, firstColor, secondColor, vertical);
    }

    public void roundedRectangle(double x, double y, double width, double height, double radius, Color color) {
        CShaders.CQ_SHADER.draw(x, y, width, height, radius, color);
    }

    public void roundedOutlineRectangle(double x, double y, double width, double height, double radius, double borderSize, Color color) {
        CShaders.COQ_SHADER.draw(x, y, width, height, radius, borderSize, color);
    }

    public void roundedOutlineGradientRectangle(double x, double y, double width, double height, double radius, double borderSize, Color color1, Color color2) {
        CShaders.COGQ_SHADER.draw(x, y, width, height, radius, borderSize, color1, color2);
    }
}
