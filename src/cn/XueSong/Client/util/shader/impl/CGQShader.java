package cn.XueSong.Client.util.shader.impl;

import cn.XueSong.Client.util.shader.ShaderUniforms;
import cn.XueSong.Client.util.shader.base.CShaderProgram;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.awt.*;

public class CGQShader {

    private final CShaderProgram program = new CShaderProgram("rgq.glsl", "vertex.vsh");

    /**
     *�ڸ��������괦�Ը����ĳ��Ȼ���һ��Բ�Ǿ���
     *
     *@param x ���ε����Ͻ�x����
     *@param y ���ε����Ͻ�y����
     *@param width ����ȷ���������½�x����Ŀ��
     *@param height ����ȷ���������½�y����ĸ߶�
     *@param radius ���ε�Բ�ǰ뾶��>0��
     *@param firstColor ʹ�õĵ�һ����ɫ
     *@param secondColor ʹ�õĵڶ�����ɫ
     *@param vertical �����Ƿ���ˮƽ�Ļ�ֱ��
     **/
    public void draw(float x, float y, float width, float height, float radius, Color firstColor, Color secondColor, boolean vertical) {
        int programId = this.program.getProgramId();
        this.program.start();
        ShaderUniforms.uniform2f(programId, "u_size", width, height);
        ShaderUniforms.uniform1f(programId, "u_radius", radius);
        ShaderUniforms.uniform4f(programId, "u_first_color", firstColor.getRed() / 255.0F, firstColor.getGreen() / 255.0F, firstColor.getBlue() / 255.0F, firstColor.getAlpha() / 255.0F);
        ShaderUniforms.uniform4f(programId, "u_second_color", secondColor.getRed() / 255.0F, secondColor.getGreen() / 255.0F, secondColor.getBlue() / 255.0F, secondColor.getAlpha() / 255.0F);
        ShaderUniforms.uniform1i(programId, "u_direction", vertical ? 1 : 0);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
        drawQuad(x, y, width, height);
        GL20.glUseProgram(0);
    }

    /**
     �ڸ��������괦�Ը����ĳ��Ȼ���һ��Բ�Ǿ���
     *@param x ���ε����Ͻ�x����
     *@param y ���ε����Ͻ�y����
     *@param width ����ȷ���������½�x����Ŀ��
     *@param height ����ȷ���������½�y����ĸ߶�
     *@param radius ���ε�Բ�ǰ뾶��>0��
     *@param firstColor ʹ�õĵ�һ����ɫ
     *@param secondColor ʹ�õĵڶ�����ɫ
     *@param vertical ������ˮƽ���Ǵ�ֱ
     **/
    public void draw(double x, double y, double width, double height, double radius, Color firstColor, Color secondColor, boolean vertical) {
        draw((float) x, (float) y, (float) width, (float) height, (float) radius, firstColor, secondColor, vertical);
    }

    public static void drawQuad(final double x, final double y, final double width, final double height) {
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0.0F, 0.0F);
        GL11.glVertex2d(x, y + height);
        GL11.glTexCoord2f(1.0F, 0.0F);
        GL11.glVertex2d(x + width, y + height);
        GL11.glTexCoord2f(1.0F, 1.0F);
        GL11.glVertex2d(x + width, y);
        GL11.glTexCoord2f(0.0F, 1.0F);
        GL11.glVertex2d(x, y);
        GL11.glEnd();
    }
}
