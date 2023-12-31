package cn.XueSong.Client.util.render;

import cn.XueSong.Client.font.CFontRenderer;
import cn.XueSong.Client.util.player.PlayerUtil;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;

import java.awt.*;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;

public class RenderUtil {
    static Minecraft mc = Minecraft.getMinecraft();
    private static final CFontRenderer font_A = new CFontRenderer("Nunito", 18.0F, Font.PLAIN, false, false);//普通

    protected RenderItem itemRender;

    /**
     * The width of the screen object.
     */
    public int width;

    /**
     * The height of the screen object.
     */
    public int height;
    private float zLevel;

    public static void renderLabelPlayer(EntityPlayer entityIn, String str, int maxDistance, float Xoffset, float Yoffset, Color backdropColor, Boolean Shadow, float partialTicks) {

        double x = (entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks) - mc.getRenderManager().renderPosX;
        double y = (entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks) - mc.getRenderManager().renderPosY + Yoffset;
        double z = (entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks) - mc.getRenderManager().renderPosZ;

        double d0 = entityIn.getDistanceSqToEntity(mc.thePlayer);

        if (d0 <= (double) (maxDistance * maxDistance)) {
            float f = 1.8F;
            float f1 = 0.016666668F * f;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x + 0.0F, (float) y + entityIn.height + 0.5F, (float) z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            if(mc.gameSettings.showDebugInfo==2){
                GlStateManager.rotate(-mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            }else {
                GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            }
            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            int i = 0;

            if (str.equals("deadmau5")) {
                i = -10;
            }

            int j = font_A.getStringWidth(str) / 2;
            GlStateManager.disableTexture2D();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            String HP = String.valueOf((int) entityIn.getHealth());
            Color hpColor = new Color(0, 255, 3, 255);
            if (entityIn.getHealth() >= 10) {
                hpColor = new Color(0, 255, 3, 255);
            } else if (entityIn.getHealth() < 10 && entityIn.getHealth() >= 4) {
                hpColor = new Color(255, 223, 0, 255);
            } else if (entityIn.getHealth() < 4) {
                hpColor = new Color(255, 0, 0, 255);
            }
            float NameLong = font_A.getStringWidth(str);
            float HPLong = font_A.getStringWidth(HP);
            float StrLong = 0;
            if (NameLong >= HPLong) {
                StrLong = NameLong;
            } else {
                StrLong = HPLong;
            }
            if (Shadow) {
                RenderUtil.dropShadowWithColor(50, (float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") * 2 + 6, 22, 11, backdropColor);
            }
            //RenderUtil.dropShadow(10, (float) -font_A.getStringWidth(str) / 2 - 3, i - 3, font_A.getStringWidth(str)+6, font_A.getStringHeight("A") + 6, 40, 5);
            CShaders.CQ_SHADER.draw((float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") * 2 + 6, 3, backdropColor);
            tessellator.draw();
            GlStateManager.enableTexture2D();
            CFontRenderer.DisplayFontNormal(str, -StrLong / 2 + Xoffset, i, new Color(255, 255, 255, 255).getRGB());
            CFontRenderer.DisplayFontNormal(HP, -StrLong / 2 + NameLong / 2 - HPLong / 2 + Xoffset, i + font_A.getStringHeight("A") + 2, hpColor.getRGB());
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            //CFontRenderer.DisplayFont(str, -font_A.getStringWidth(str) / 2, i, -1);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void renderLabelEntity(Entity entityIn, String str, int maxDistance, float Xoffset, float Yoffset, Color backdropColor, Boolean Shadow, float partialTicks) {

        double x = (entityIn.lastTickPosX + (entityIn.posX - entityIn.lastTickPosX) * (double) partialTicks) - mc.getRenderManager().renderPosX;
        double y = (entityIn.lastTickPosY + (entityIn.posY - entityIn.lastTickPosY) * (double) partialTicks) - mc.getRenderManager().renderPosY + Yoffset;
        double z = (entityIn.lastTickPosZ + (entityIn.posZ - entityIn.lastTickPosZ) * (double) partialTicks) - mc.getRenderManager().renderPosZ;

        double d0 = entityIn.getDistanceSqToEntity(mc.thePlayer);

        if (d0 <= (double) (maxDistance * maxDistance)) {
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float) x + 0.0F, (float) y + entityIn.height + 0.5F, (float) z);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
            if(mc.gameSettings.showDebugInfo==2){
                GlStateManager.rotate(-mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            }else {
                GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
            }

            GlStateManager.scale(-f1, -f1, f1);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(false);
            GlStateManager.disableDepth();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            int i = 0;

            if (str.equals("deadmau5")) {
                i = -10;
            }

            int j = font_A.getStringWidth(str) / 2;
            GlStateManager.disableTexture2D();
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            float StrLong = font_A.getStringWidth(str);
            if (Shadow) {
                RenderUtil.dropShadowWithColor(50, (float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") + 6, 22, 11, backdropColor);
            }
            CShaders.CQ_SHADER.draw((float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") + 6, 3, backdropColor);
            tessellator.draw();
            GlStateManager.enableTexture2D();
            CFontRenderer.DisplayFontNormal(str, -StrLong / 2 + Xoffset, i, new Color(255, 255, 255, 255).getRGB());
            GlStateManager.enableDepth();
            GlStateManager.depthMask(true);
            //CFontRenderer.DisplayFont(str, -font_A.getStringWidth(str) / 2, i, -1);
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
    }

    public static void renderLabelXYZ(double x, double y, double z, String str, float Xoffset, float Yoffset, Color backdropColor, Boolean Shadow) {

        x=x - mc.getRenderManager().renderPosX;
        y=y - mc.getRenderManager().renderPosY + Yoffset;
        z=z - mc.getRenderManager().renderPosZ;
        //float f = PlayerUtil.distanceXYZ(x,y,z,mc.getRenderManager().renderPosX,mc.getRenderManager().renderPosYmc.getRenderManager().renderPosY,mc.getRenderManager().renderPosZ,)
        float f = 1.6F;
        float f1 = 0.016666668F * f;
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) x + 0.0F, (float) y + 0.5F, (float) z);
        GL11.glNormal3f(0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
        if(mc.gameSettings.showDebugInfo==2){
            GlStateManager.rotate(-mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        }else {
            GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);
        }
        GlStateManager.scale(-f1, -f1, f1);
        GlStateManager.disableLighting();
        GlStateManager.depthMask(false);
        //GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        int i = 0;

        if (str.equals("deadmau5")) {
            i = -10;
        }

        int j = font_A.getStringWidth(str) / 2;
        GlStateManager.disableTexture2D();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        float StrLong = font_A.getStringWidth(str);
        if (Shadow) {
            RenderUtil.dropShadowWithColor(50, (float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") + 6, 22, 11, backdropColor);
        }
        CShaders.CQ_SHADER.draw((float) -StrLong / 2 - 4 + Xoffset, i - 4, StrLong + 7, font_A.getStringHeight("A") + 6, 3, backdropColor);
        tessellator.draw();
        GlStateManager.enableTexture2D();
        CFontRenderer.DisplayFontNormal(str, -StrLong / 2 + Xoffset, i, new Color(255, 255, 255, 255).getRGB());
        //GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        //CFontRenderer.DisplayFont(str, -font_A.getStringWidth(str) / 2, i, -1);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public void image(final ResourceLocation imageLocation, final float x, final float y, final float width, final float height, final Color color) {
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0F);
        color(color);
        OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
        mc.getTextureManager().bindTexture(imageLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, width, height);
        GlStateManager.resetColor();
        GlStateManager.disableBlend();
    }

    public void image(final ResourceLocation imageLocation, final double x, final double y, final double width, final double height, Color color) {
        image(imageLocation, (float) x, (float) y, (float) width, (float) height, color);
    }

    public void image(final ResourceLocation imageLocation, final float x, final float y, final float width, final float height) {
        image(imageLocation, x, y, width, height, Color.WHITE);
    }

    public void image(final ResourceLocation imageLocation, final double x, final double y, final double width, final double height) {
        image(imageLocation, (float) x, (float) y, (float) width, (float) height);
    }

    public static void drawRoundedGradientRect(double x, double y, double width, double height, double radius, Color firstColor, Color secondColor, boolean vertical) {
        CShaders.CGQ_SHADER.draw(x, y, width, height, radius, firstColor, secondColor, vertical);
    }

    public static void roundedRectangle(double x, double y, double width, double height, double radius, Color color) {
        CShaders.CQ_SHADER.draw(x, y, width, height, radius, color);
    }

    public static void roundedOutlineRectangle(double x, double y, double width, double height, double radius, double borderSize, Color color) {
        CShaders.COQ_SHADER.draw(x, y, width, height, radius, borderSize, color);
    }

    public static void roundedOutlineGradientRectangle(double x, double y, double width, double height, double radius, double borderSize, Color color1, Color color2) {
        CShaders.COGQ_SHADER.draw(x, y, width, height, radius, borderSize, color1, color2);
    }

    public static void end() {
        GL11.glEnd();
    }

    /**
     * 画圆形
     */
    public void circle(final double x, final double y, final float radius) {//画圈圈
        GL11.glEnable(GL11.GL_POINT_SMOOTH);
        GL11.glPointSize(radius * 4);
        GL11.glBegin(GL11.GL_POINTS);
        GL11.glVertex2d(x, y);
        GL11.glEnd();
        GL11.glDisable(GL11.GL_POINT_SMOOTH);
    }

    public void circle(final double x, final double y, final double radius, final boolean filled, final Color color) {
        polygon(x, y, radius, 360, filled, color);
    }

    public void circle(final double x, final double y, final double radius, final double sides, final boolean filled, final Color color) {
        polygon(x, y, radius, sides, filled, color);
    }

    public void circle(final double x, final double y, final double radius, final boolean filled) {
        polygon(x, y, radius, 360, filled);
    }

    public void circle(final double x, final double y, final double radius, final Color color) {
        polygon(x, y, radius, 360, color);
    }

    /**
     * 绘制线段
     */
    public void drawLine(double x, double y, double z, double x1, double y1, double z1, final Color color, final float width) {
        x = x - mc.getRenderManager().renderPosX;
        x1 = x1 - mc.getRenderManager().renderPosX;
        y = y - mc.getRenderManager().renderPosY;
        y1 = y1 - mc.getRenderManager().renderPosY;
        z = z - mc.getRenderManager().renderPosZ;
        z1 = z1 - mc.getRenderManager().renderPosZ;

        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glLineWidth(width);

        color(color);
        GL11.glBegin(2);
        GL11.glVertex3d(x, y, z);
        GL11.glVertex3d(x1, y1, z1);
        GL11.glEnd();

        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glPopMatrix();
    }

    /**
     * 多边形绘制
     */
    public void polygon(final double x, final double y, double sideLength, final double amountOfSides, final boolean filled, final Color color) {
        sideLength /= 2;
        start();
        if (color != null)
            color(color);
        if (!filled) GL11.glLineWidth(2);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);
        begin(filled ? GL11.GL_TRIANGLE_FAN : GL11.GL_LINE_STRIP);
        {
            for (double i = 0; i <= amountOfSides / 4; i++) {
                final double angle = i * 4 * (Math.PI * 2) / 360;
                vertex(x + (sideLength * Math.cos(angle)) + sideLength, y + (sideLength * Math.sin(angle)) + sideLength);
            }
        }
        end();
        GL11.glDisable(GL11.GL_LINE_SMOOTH);
        stop();
    }

    public void polygon(final double x, final double y, final double sideLength, final int amountOfSides, final boolean filled) {
        polygon(x, y, sideLength, amountOfSides, filled, null);
    }

    public void polygon(final double x, final double y, final double sideLength, final int amountOfSides, final Color color) {
        polygon(x, y, sideLength, amountOfSides, true, color);
    }

    public void polygon(final double x, final double y, final double sideLength, final int amountOfSides) {
        polygon(x, y, sideLength, amountOfSides, true, null);
    }

    public static void color(final double red, final double green, final double blue, final double alpha) {
        GL11.glColor4d(red, green, blue, alpha);
    }

    public void color(final double red, final double green, final double blue) {
        color(red, green, blue, 1);
    }

    public static void color(Color color) {
        if (color == null)
            color = Color.white;
        color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, color.getAlpha() / 255F);
    }

    public void color(Color color, final int alpha) {
        if (color == null)
            color = Color.white;
        color(color.getRed() / 255F, color.getGreen() / 255F, color.getBlue() / 255F, 0.5);
    }

    public static void rainbowRectangle(final double x, final double y, final double width, final double height) {
        start();

        GL11.glBegin(GL11.GL_QUADS);

        for (double position = x; position <= x + width; position += 0.5) {
            color(Color.getHSBColor((float) ((position - x) / width), 1, 1));

            GL11.glVertex2d(position, y);
            GL11.glVertex2d(position + 0.5f, y);
            GL11.glVertex2d(position + 0.5f, y + height);
            GL11.glVertex2d(position, y + height);
        }

        GL11.glEnd();

        stop();
    }

    public void begin(final int glMode) {
        GL11.glBegin(glMode);
    }

    public void vertex(final double x, final double y) {
        GL11.glVertex2d(x, y);
    }

    public static void start() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
    }

    /**
     * 最好使用gl状态管理器来避免bug!
     */
    public static void stop() {
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    /**
     * gl顶点绘制
     */
    public static void glVertex3D(Vec3 vector3d) {
        GL11.glVertex3d(vector3d.xCoord, vector3d.yCoord, vector3d.zCoord);
    }

    public static Vec3 getRenderPos(double x, double y, double z) {

        x -= mc.getRenderManager().renderPosX;
        y -= mc.getRenderManager().renderPosY;
        z -= mc.getRenderManager().renderPosZ;

        return new Vec3(x, y, z);
    }

    /**
     * 绘制边界框
     */
    public static void drawBoundingBox(final AxisAlignedBB aa) {//绘制边界框

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        end();

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        end();

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        end();

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        end();

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        end();

        glBegin(GL_QUADS);
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.maxZ));
        glVertex3D(getRenderPos(aa.minX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.minX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.minZ));
        glVertex3D(getRenderPos(aa.maxX, aa.maxY, aa.maxZ));
        glVertex3D(getRenderPos(aa.maxX, aa.minY, aa.maxZ));
        end();
    }

    /**
     * 绘制阴影
     * <p>
     * dropShadow方法的每个参数的含义如下：
     * <p>
     * loops：循环次数，用于控制阴影的模糊效果。循环次数越大，阴影越模糊。
     * x：阴影的起始点横坐标。
     * y：阴影的起始点纵坐标。
     * width：阴影的宽度。
     * height：阴影的高度。
     * opacity：阴影的不透明度。取值范围为0到255
     * edgeRadius：圆角的半径。
     */
    public static void dropShadow(final int loops, final double x, final double y, final double width, final double height, final double opacity, final double edgeRadius) {
        GlStateManager.alphaFunc(516, 0);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        for (float margin = 0; margin <= loops / 2f; margin += 0.5f) {
            roundedRectangle(x - margin / 2f, y - margin / 2f,
                    width + margin, height + margin, edgeRadius,
                    new Color(0, 0, 0, (int) Math.max(0.5f, (opacity - margin * 1.2) / 5.5f)));
        }
    }

    public static void dropShadowWithColor(final int loops, final double x, final double y, final double width, final double height, final double opacity, final double edgeRadius, Color color) {
        GlStateManager.alphaFunc(516, 0);
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();

        for (float margin = 0; margin <= loops / 2f; margin += 0.5f) {
            roundedRectangle(x - margin / 2f, y - margin / 2f,
                    width + margin, height + margin, edgeRadius,
                    new Color(color.getRed(), color.getGreen(), color.getBlue(), (int) Math.max(0.5f, (opacity - margin * 1.2) / 5.5f)));
        }
    }

    /**
     * 绘制物品图标
     */
    public void renderItemIcon(final double x, final double y, final ItemStack itemStack) {
        if (itemStack != null) {
            GlStateManager.pushMatrix();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();

            mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, x, y);

            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            RenderHelper.disableStandardItemLighting();
            GlStateManager.popMatrix();
        }
    }

    public static void drawRect(int x, int y, int width, int height, int color) {
        Gui.drawRect(x, y, x + width, y + height, color);
    }

    public static void drawHoveringText(String text, double x, double y) {
        RenderUtil.dropShadow(20, x - 5, y - 6, font_A.getStringWidth(text) + 9, font_A.getStringHeight(text) + 12, 30, 5 + 5);
        CShaders.CQ_SHADER.draw(x - 5, y - 6, font_A.getStringWidth(text) + 9, font_A.getStringHeight(text) + 12, 5, new Color(0, 0, 0, 255));
        CFontRenderer.DisplayFontNormal(text, (float) x, (float) y, Color.white.getRGB());
    }
}
