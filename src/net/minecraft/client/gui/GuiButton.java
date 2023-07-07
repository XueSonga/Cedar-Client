package net.minecraft.client.gui;

import cn.XueSong.Client.util.render.RenderUtil;
import cn.XueSong.Client.util.shader.CShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class GuiButton extends Gui
{
    protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");

    /** Button width in pixels */
    protected int width;

    /** Button height in pixels */
    protected int height;

    /** The x position of this control. */
    public int xPosition;

    /** The y position of this control. */
    public int yPosition;

    /** The string displayed on this control. */
    public String displayString;
    public int id;

    /** True if this control is enabled, false to disable. */
    public boolean enabled;

    /** Hides the button completely if false. */
    public boolean visible;
    protected boolean hovered;
    private boolean selected = false;
    // 按钮的初始宽度和高度

    private final float minScale = 1.0f; // 按钮的最小缩放比例
    private final float maxScale = 1.03f; // 按钮的最大缩放比例
    private final float scaleFadeSpeed = 0.002f; // 缩放渐变速度
    private float currentScale = minScale; // 当前按钮的缩放比例
    private final float initialX = this.xPosition; // 按钮的初始x坐标
    private final float initialY = this.yPosition; // 按钮的初始y坐标
    private float currentX = initialX; // 当前按钮的x坐标
    private float currentY = initialY; // 当前按钮的y坐标

    /** 渐变颜色 */
    private Color currentColor;

    private float EachColor;
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
    public GuiButton(int buttonId, int x, int y, String buttonText)
    {
        this(buttonId, x, y, 200, 20, buttonText);
    }

    public GuiButton(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText)
    {
        this.width = 200;
        this.height = 20;
        this.enabled = true;
        this.visible = true;
        this.id = buttonId;
        this.xPosition = x;
        this.yPosition = y;
        this.width = widthIn;
        this.height = heightIn;
        this.displayString = buttonText;
    }

    /**
     * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this button and 2 if it IS hovering over
     * this button.
     */
    protected int getHoverState(boolean mouseOver)
    {
        int i = 1;

        if (!this.enabled)
        {
            i = 0;
        }
        else if (mouseOver)
        {
            i = 2;
        }

        return i;
    }

    /**
     * Draws this button to the screen.
     */


    float fadeSpeed = 0.1f;// 颜色渐变的速度，根据需要调整

    private float sizeFadeSpeed = 0.01f;// 按钮的大小渐变速度
    private boolean isFadingOut = false;
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            FontRenderer fontrenderer = mc.fontRendererObj;
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;

            // 计算按钮颜色
            Color baseColor = new Color(38, 38, 38, 189);
            Color targetColor = this.hovered ? new Color(115, 115, 115, 189) : baseColor;

            // 更新按钮颜色
            if (this.currentColor == null) {
                this.currentColor = baseColor;
            }

            // 如果按钮之前处于选中状态但现在没有被选中，则启动渐变退色
            if (!this.hovered && this.isFadingOut) {
                targetColor = baseColor;
            }

            // 如果按钮被选中，则启动渐变变亮
            if (this.hovered && !this.isFadingOut) {
                targetColor = new Color(115, 115, 115, 189);
            }

            this.currentColor = fadeColor(this.currentColor, targetColor, fadeSpeed);

            // 检查是否需要停止渐变退色
            if (!this.hovered && this.currentColor.equals(baseColor)) {
                this.isFadingOut = false;
            }

            // 渐变按钮大小和位置
            if (this.hovered) {
                // 按钮被选中时逐渐变大和移动
                if (this.currentScale < this.maxScale) {
                    this.currentScale += this.scaleFadeSpeed;
                }
                this.currentX = this.xPosition - (this.currentScale - 1.0f) * this.width / 2.0f;
                this.currentY = this.yPosition - (this.currentScale - 1.0f) * this.height / 2.0f;
            } else {
                // 按钮未被选中时逐渐恢复初始大小和位置
                if (this.currentScale > this.minScale) {
                    this.currentScale -= this.scaleFadeSpeed;
                }
                this.currentX = this.xPosition - (this.currentScale - 1.0f) * this.width / 2.0f;
                this.currentY = this.yPosition - (this.currentScale - 1.0f) * this.height / 2.0f;
            }

            // 计算实际绘制的宽度和高度
            float drawWidth = this.width * this.currentScale;
            float drawHeight = this.height * this.currentScale;

            // 绘制阴影
            RenderUtil.dropShadow(15, this.currentX, this.currentY, drawWidth, drawHeight, 40, 5 + 5);

            // 绘制圆角按钮
            RenderUtil.roundedRectangle(this.currentX, this.currentY, drawWidth, drawHeight, 5, this.currentColor);

            //RenderUtil.dropShadow(15, this.xPosition, this.yPosition, this.width, this.height, 40, 5 + 5);
            //RenderUtil.roundedRectangle(this.xPosition, this.yPosition, this.width, this.height, 5, this.currentColor);
            RenderUtil.roundedOutlineRectangle(this.currentX, this.currentY, drawWidth, drawHeight, 3, 1, new Color(185, 185, 185, 47));

            this.mouseDragged(mc, mouseX, mouseY);
            int j = 14737632;

            if (!this.enabled) {
                j = new Color(72, 72, 72, 255).getRGB();
            } else if (this.hovered) {
                j = 16777120;
            }

            this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, j);

            // 如果按钮之前处于选中状态但现在没有被选中，则启动渐变退色
            if (!this.hovered && !this.isFadingOut) {
                this.isFadingOut = true;
            }
        }
    }



    /**
     * Fired when the mouse button is dragged. Equivalent of MouseListener.mouseDragged(MouseEvent e).
     */
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
    }

    /**
     * Fired when the mouse button is released. Equivalent of MouseListener.mouseReleased(MouseEvent e).
     */
    public void mouseReleased(int mouseX, int mouseY)
    {
    }

    /**
     * Returns true if the mouse has been pressed on this control. Equivalent of MouseListener.mousePressed(MouseEvent
     * e).
     */
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        return this.enabled && this.visible && mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    /**
     * Whether the mouse cursor is currently over the button.
     */
    public boolean isMouseOver()
    {
        return this.hovered;
    }

    public void drawButtonForegroundLayer(int mouseX, int mouseY)
    {
    }

    public void playPressSound(SoundHandler soundHandlerIn)
    {
        soundHandlerIn.playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
    }

    public int getButtonWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
    用于实现渐变效果
     */
    private Color fadeColor(Color currentColor, Color targetColor, float fadeSpeed) {
        int r = currentColor.getRed();
        int g = currentColor.getGreen();
        int b = currentColor.getBlue();
        int a = currentColor.getAlpha();

        if (r != targetColor.getRed()) {
            r = interpolate(r, targetColor.getRed(), fadeSpeed);
        }

        if (g != targetColor.getGreen()) {
            g = interpolate(g, targetColor.getGreen(), fadeSpeed);
        }

        if (b != targetColor.getBlue()) {
            b = interpolate(b, targetColor.getBlue(), fadeSpeed);
        }

        if (a != targetColor.getAlpha()) {
            a = interpolate(a, targetColor.getAlpha(), fadeSpeed);
        }

        return new Color(r, g, b, a);
    }

    private int interpolate(int start, int end, float fadeSpeed) {
        int diff = end - start;
        int step = (int) (fadeSpeed * Math.abs(diff));
        if (step < 1) {
            step = 1;
        }
        if (start < end) {
            return Math.min(start + step, end);
        } else {
            return Math.max(start - step, end);
        }
    }

}
