package cn.XueSong.Client.util.shader.impl;

import cn.XueSong.Client.util.shader.base.ShaderUniforms;
import cn.XueSong.Client.util.shader.base.CShaderProgram;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

import java.awt.*;

// Rounded Outline Gradient Quad
public class COGQShader {

    private final CShaderProgram program = new CShaderProgram("rogq.frag", "vertex.vsh");

    public void draw(float x, float y, float width, float height, float radius, float borderSize, Color color1, Color color2) {
        int programId = this.program.getProgramId();
        this.program.start();
        ShaderUniforms.uniform2f(programId, "u_size", width, height);
        ShaderUniforms.uniform1f(programId, "u_radius", radius);
        ShaderUniforms.uniform1f(programId, "u_border_size", borderSize);

        ShaderUniforms.uniform4f(programId, "u_color_1", color1.getRed() / 255.0F, color1.getGreen() / 255.0F,
                color1.getBlue() / 255.0F, color1.getAlpha() / 255.0F);
        ShaderUniforms.uniform4f(programId, "u_color_2", color2.getRed() / 255.0F, color2.getGreen() / 255.0F,
                color2.getBlue() / 255.0F, color2.getAlpha() / 255.0F);

        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
        CShaderProgram.drawQuad(x, y, width, height);
        GlStateManager.disableBlend();
        CShaderProgram.stop();
    }

    public void draw(double x, double y, double width, double height, double radius, double borderSize, Color color1, Color color2) {
        draw((float) x, (float) y, (float) width, (float) height, (float) radius, (float) borderSize, color1, color2);
    }

}
