package cn.XueSong.Client.font;


import cn.XueSong.Client.mod.Other.Nick;
import cn.XueSong.Client.serverdetection.ServerDetection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CFontRenderer extends CFont {
    protected CFont.CharData[] boldChars = new CFont.CharData[256];
    protected CFont.CharData[] italicChars = new CFont.CharData[256];
    protected CFont.CharData[] boldItalicChars = new CFont.CharData[256];

    private final int[] colorCode = new int[32];

    /*
        CREDIT G0dwhitelight.
     */
    public CFontRenderer(Font font, boolean antiAlias, boolean fractionalMetrics) {
        super(font, antiAlias, fractionalMetrics);
        setupMinecraftColorcodes();
        setupBoldItalicIDs();
    }

    public CFontRenderer(String NameFontTTF, float size, int fonttype, boolean antiAlias, boolean fractionalMetrics) {
        super(FontUtil.getFontFromTTF(new ResourceLocation("font/" + NameFontTTF+".ttf"), size,fonttype), antiAlias, fractionalMetrics);
        setupMinecraftColorcodes();
        setupBoldItalicIDs();
    }

    public static boolean isChinese(char c) {
        String s = String.valueOf(c);
        boolean isEnglish = "1234567890abcdefghijklmnopqrstuvwxyz:;!<>@#$%^&*()-_=+[]{}|\\/'\",.~`".contains(s.toLowerCase());
        return !isEnglish;
    }

    public float drawString(String text, float x, float y, int color) {
        return drawString(text, x, y, color, false);
    }

    public float drawString(String text, double x, double y, int color) {
        return drawString(text, x, y, color, false);
    }

    public float drawStringWithShadow(String text, float x, float y, int color) {
        float shadowWidth = drawString(text, x + 1D, y + .5D, color, true);
        return Math.max(shadowWidth, drawString(text, x, y, color, false));
    }

    public float drawStringWithShadow(String text, double x, double y, int color) {
        float shadowWidth = drawString(text, x + 1, y + .5, color, true);
        return Math.max(shadowWidth, drawString(text, x, y, color, false));
    }

    public float drawCenteredString(String text, float x, float y, int color) {
        return drawString(text, x - getStringWidth(text) / 2, y, color);
    }

    public float drawCenteredString(String text, double x, double y, int color) {
        return drawString(text, x - getStringWidth(text) / 2, y, color);
    }

    public float drawCenteredStringWithShadow(String text, float x, float y, int color) {
        float shadowWidth = drawString(text, x - getStringWidth(text) / 2 + 0.45D, y + 0.5D, color, true);
        return drawString(text, x - getStringWidth(text) / 2, y, color);
    }

    public void drawStringWithOutline(String text, double x, double y, int color) {
        drawString(text, x - .5, y, 0x000000);

        drawString(text, x + .5, y, 0x000000);

        drawString(text, x, y - .5, 0x000000);

        drawString(text, x, y + .5, 0x000000);

        drawString(text, x, y, color);
    }

    public void drawCenteredStringWithOutline(String text, double x, double y, int color) {
        drawCenteredString(text, x - .5, y, 0x000000);

        drawCenteredString(text, x + .5, y, 0x000000);

        drawCenteredString(text, x, y - .5, 0x000000);

        drawCenteredString(text, x, y + .5, 0x000000);

        drawCenteredString(text, x, y, color);
    }
    public float drawCenteredStringWithShadow(String text, double x, double y, int color) {
        float shadowWidth = drawString(text, x - getStringWidth(text) / 2 + 0.45D, y + 0.5D, color, true);
        return drawString(text, x - getStringWidth(text) / 2, y, color);
    }

    public float drawString(String text, double x, double y, int color, boolean shadow) {
        Minecraft mc = Minecraft.getMinecraft();
        x -= 1;

        if (text == null) {
            return 0.0F;
        }

        if (color == 553648127) {
            color = 16777215;
        }

        if ((color & 0xFC000000) == 0) {
            color |= -16777216;
        }

        if (shadow) {
            color = (color & 0xFCFCFC) >> 2 | color & new Color(20, 20, 20, 200).getRGB();
        }

        CFont.CharData[] currentData = this.charData;
        float alpha = (color >> 24 & 0xFF) / 255.0F;
        boolean randomCase = false;
        boolean bold = false;
        boolean italic = false;
        boolean strikethrough = false;
        boolean underline = false;
        boolean render = true;
        x *= 2.0D;
        y = (y - 3.0D) * 2.0D;

        if (render) {
            GL11.glPushMatrix();
            GlStateManager.scale(0.5D, 0.5D, 0.5D);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
            int size = text.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(tex.getGlTextureId());
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, tex.getGlTextureId());

            for (int i = 0; i < size; i++) {
                char character = text.charAt(i);
                            if ((String.valueOf(character).equals("\247")) && (i < size)) {
                                int colorIndex = 21;

                                try {
                                    colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                                if (colorIndex < 16) {
                                    bold = false;
                                    italic = false;
                                    randomCase = false;
                                    underline = false;
                                    strikethrough = false;
                                    GlStateManager.bindTexture(tex.getGlTextureId());
                                    // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                    // tex.getGlTextureId());
                                    currentData = this.charData;

                                    if ((colorIndex < 0) || (colorIndex > 15)) {
                                        colorIndex = 15;
                                    }

                                    if (shadow) {
                                        colorIndex += 16;
                                    }

                                    int colorcode = this.colorCode[colorIndex];
                                    GlStateManager.color((colorcode >> 16 & 0xFF) / 255.0F, (colorcode >> 8 & 0xFF) / 255.0F, (colorcode & 0xFF) / 255.0F, alpha);
                                } else if (colorIndex == 16) {
                                    randomCase = true;
                                } else if (colorIndex == 17) {
                                    bold = true;

                                    if (italic) {
                                        GlStateManager.bindTexture(texItalicBold.getGlTextureId());
                                        // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                        // texItalicBold.getGlTextureId());
                                        currentData = this.boldItalicChars;
                                    } else {
                                        GlStateManager.bindTexture(texBold.getGlTextureId());
                                        // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                        // texBold.getGlTextureId());
                                        currentData = this.boldChars;
                                    }
                                } else if (colorIndex == 18) {
                                    strikethrough = true;
                                } else if (colorIndex == 19) {
                                    underline = true;
                                } else if (colorIndex == 20) {
                                    italic = true;

                                    if (bold) {
                                        GlStateManager.bindTexture(texItalicBold.getGlTextureId());
                                        // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                        // texItalicBold.getGlTextureId());
                                        currentData = this.boldItalicChars;
                                    } else {
                                        GlStateManager.bindTexture(texItalic.getGlTextureId());
                                        // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                        // texItalic.getGlTextureId());
                                        currentData = this.italicChars;
                                    }
                                } else if (colorIndex == 21) {
                                    bold = false;
                                    italic = false;
                                    randomCase = false;
                                    underline = false;
                                    strikethrough = false;
                                    GlStateManager.color((color >> 16 & 0xFF) / 255.0F, (color >> 8 & 0xFF) / 255.0F, (color & 0xFF) / 255.0F, alpha);
                                    GlStateManager.bindTexture(tex.getGlTextureId());
                                    // GL11.glfunc_179144_i(GL11.GL_TEXTURE_2D,
                                    // tex.getGlTextureId());
                                    currentData = this.charData;
                                }

                                i++;
                            } else if ((character < currentData.length) && (character >= 0)) {
                                GL11.glBegin(GL11.GL_TRIANGLES);
                                drawChar(currentData, character, (float) x, (float) y);
                                GL11.glEnd();
                                if (strikethrough) {
                                    drawLine(x, y + currentData[character].height / 2, x + currentData[character].width - 8.0D, y + currentData[character].height / 2, 1.0F);
                                }
                                if  (underline) {
                                    drawLine(x, y + currentData[character].height - 2.0D, x + currentData[character].width - 8.0D, y + currentData[character].height - 2.0D, 1.0F);
                                }

                                x += currentData[character].width - 8 + this.charOffset;
                            }
                    }
            GL11.glHint(GL11.GL_POLYGON_SMOOTH_HINT, GL11.GL_DONT_CARE);
            GL11.glPopMatrix();
        }

        return (float) x / 2.0F;
    }
    public int getStringWidth(String text) {
        if (Objects.equals(text, "")){
            return 0;
        }
        text = replaceChineseSymbols(text);
        int width = 0;
        CFont.CharData[] currentData = this.charData;
        boolean bold = false;
        boolean italic = false;
        int size = text.length();

        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);
                    if ((String.valueOf(character).equals("\247")) && (i < size)) {
                        int colorIndex = "0123456789abcdefklmnor".indexOf(character);

                        if (colorIndex < 16) {
                            bold = false;
                            italic = false;
                        } else if (colorIndex == 17) {
                            bold = true;

                            if (italic) {
                                currentData = this.boldItalicChars;
                            } else {
                                currentData = this.boldChars;
                            }
                        } else if (colorIndex == 20) {
                            italic = true;

                            if (bold) {
                                currentData = this.boldItalicChars;
                            } else {
                                currentData = this.italicChars;
                            }
                        } else if (colorIndex == 21) {
                            bold = false;
                            italic = false;
                            currentData = this.charData;
                        }

                        i++;
                    } else if ((character < currentData.length) && (character >= 0)) {
                        width += currentData[character].width - 8 + this.charOffset;
                    }else if (isChinese(character)){
                width += Minecraft.getMinecraft().fontRendererObj.getStringWidth(String.valueOf(character))*2;
            }
                }
        return width / 2;
    }

    public int getStringWidthCust(String text) {
        if (text == null) {
            return 0;
        }

        int width = 0;
        CFont.CharData[] currentData = this.charData;
        boolean bold = false;
        boolean italic = false;
        int size = text.length();

        for (int i = 0; i < size; i++) {
            char character = text.charAt(i);

            if ((String.valueOf(character).equals("§")) && (i < size)) {
                int colorIndex = "0123456789abcdefklmnor".indexOf(character);

                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                } else if (colorIndex == 17) {
                    bold = true;

                    if (italic) {
                        currentData = this.boldItalicChars;
                    } else {
                        currentData = this.boldChars;
                    }
                } else if (colorIndex == 20) {
                    italic = true;

                    if (bold) {
                        currentData = this.boldItalicChars;
                    } else {
                        currentData = this.italicChars;
                    }
                } else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                    currentData = this.charData;
                }

                i++;
            } else if ((character < currentData.length) && (character >= 0)) {
                width += currentData[character].width - 8 + this.charOffset;
            }
        }

        return (width - this.charOffset) / 2;
    }

    public static int getColor(String str) {
        switch(str.hashCode()) {
            case 48:
                if (str.equals("0")) {
                    return (new Color(0, 0, 0)).getRGB();
                }
                break;
            case 49:
                if (str.equals("1")) {
                    return (new Color(0, 0, 189)).getRGB();
                }
                break;
            case 50:
                if (str.equals("2")) {
                    return (new Color(0, 192, 0)).getRGB();
                }
                break;
            case 51:
                if (str.equals("3")) {
                    return (new Color(0, 190, 190)).getRGB();
                }
                break;
            case 52:
                if (str.equals("4")) {
                    return (new Color(190, 0, 0)).getRGB();
                }
                break;
            case 53:
                if (str.equals("5")) {
                    return (new Color(189, 0, 188)).getRGB();
                }
                break;
            case 54:
                if (str.equals("6")) {
                    return (new Color(218, 163, 47)).getRGB();
                }
                break;
            case 55:
                if (str.equals("7")) {
                    return (new Color(190, 190, 190)).getRGB();
                }
                break;
            case 56:
                if (str.equals("8")) {
                    return (new Color(63, 63, 63)).getRGB();
                }
                break;
            case 57:
                if (str.equals("9")) {
                    return (new Color(63, 64, 253)).getRGB();
                }
                break;
            case 97:
                if (str.equals("a")) {
                    return (new Color(63, 254, 63)).getRGB();
                }
                break;
            case 98:
                if (str.equals("b")) {
                    return (new Color(62, 255, 254)).getRGB();
                }
                break;
            case 99:
                if (str.equals("c")) {
                    return (new Color(254, 61, 62)).getRGB();
                }
                break;
            case 100:
                if (str.equals("d")) {
                    return (new Color(255, 64, 255)).getRGB();
                }
                break;
            case 101:
                if (str.equals("e")) {
                    return (new Color(254, 254, 62)).getRGB();
                }
                break;
            case 102:
                if (str.equals("f")) {
                    return (new Color(255, 255, 255)).getRGB();
                }
        }

        return (new Color(255, 255, 255)).getRGB();
    }

    public static String replaceChineseSymbols(String str) {
        str = str.replace("（", "(");
        str = str.replace("）", ")");
        str = str.replace("【", "[");
        str = str.replace("】", "]");
        str = str.replace("｛", "{");
        str = str.replace("｝", "}");
        str = str.replace("［", "[");
        str = str.replace("］", "]");
        str = str.replace("“", "\"");
        str = str.replace("”", "\"");
        str = str.replace("‘", "'");
        str = str.replace("’", "'");
        str = str.replace("，", ",");
        str = str.replace("。", ".");
        str = str.replace("：", ":");
        str = str.replace("；", ";");
        str = str.replace("！", "!");
        str = str.replace("—", "-");
        str = str.replace("～", "~");
        str = str.replace("§l", "");
        str = str.replace("§k", "");
        str = str.replace("§m", "");
        str = str.replace("§n", "");
        str = str.replace("§o", "");
        str = str.replace("§r", "");
        str = str.replace("CedarClient", "§aCedarClient§r");
        // 添加其他中文全角符号的替换规则...
        str = Nick.Nick(str);//设置nick
        return str;
    }

    private static final CFontRenderer font_A = new CFontRenderer("Roboto-Medium", 18.0F, Font.PLAIN, true, true);//普通
    private static final CFontRenderer font_Title = new CFontRenderer("Roboto-Medium", 18.0F*4F, Font.PLAIN, true, true);//普通
    private static final CFontRenderer font_SubTitle = new CFontRenderer("Roboto-Medium", 18.0F*2F, Font.PLAIN, true, true);//普通

    public static void DisplayFont( String str, int x, int y, int color) {
        DisplayFont(str, (float) x, (float) y, color);
    }
    public static float DisplayFontWithShadow(String str, float x, float y, int color) {
        str = replaceChineseSymbols(str);

        for (int iF = 0; iF < str.length(); ++iF) {
            String s = String.valueOf(str.toCharArray()[iF]);
            if (s.contains("§") && iF + 1 <= str.length()) {
                color = getColor(String.valueOf(str.toCharArray()[iF + 1]));
                iF++;
            } else if (isChinese(s.charAt(0))) {
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x + 1.25f, y + 0.5f, new Color(20, 20, 20, 200).getRGB(), false);
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x - 0.25f + 1, y, color, false);
                x += (float) Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);
            } else {
                font_A.drawString(s, x + 0.25f, y + 2.5, new Color(20, 20, 20, 200).getRGB());
                font_A.drawString(s, x, y + 2, color);
                x += (float) font_A.getStringWidth(s);
            }
        }
        return x;
    }

    public static float _TitleDisplayFont(String str, float x, float y, int color) {
        //str=" "+str;
        //ClientUtils.INSTANCE.displayAlert(str);
        for(int iF = 0; iF < str.length(); ++iF) {
            String s = String.valueOf(str.toCharArray()[iF]);
            if (s.contains("§") && iF + 1 <= str.length()) {
                color = getColor(String.valueOf(str.toCharArray()[iF + 1]));
                iF++;
            }else if (isChinese(s.charAt(0))) {
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x+1.25f, y+0.5f, new Color(20, 20, 20, 200).getRGB(),false);
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x-0.25f + 1, y, color,false);
                x += (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);
            } else {
                font_A.drawString(s, x+0.25f, y+2.5, new Color(20, 20, 20, 200).getRGB());
                font_A.drawString(s, x, y+2, color);
                x += (float)font_A.getStringWidth(s);
            }
        }
        return x;
        //return font.drawString(str, x, y, color);
    }

    public static float _SubTitleDisplayFont(String str, float x, float y, int color) {
        //str=" "+str;
        //ClientUtils.INSTANCE.displayAlert(str);
        for(int iF = 0; iF < str.length(); ++iF) {
            String s = String.valueOf(str.toCharArray()[iF]);
            if (s.contains("§") && iF + 1 <= str.length()) {
                color = getColor(String.valueOf(str.toCharArray()[iF + 1]));
                iF++;
            }else if (isChinese(s.charAt(0))) {
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
                GlStateManager.pushMatrix();
                GlStateManager.scale(4.0F, 4.0F, 4.0F);
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x+0.25f, y+0.5f, new Color(20, 20, 20, 200).getRGB(),false);
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x-0.25f, y, color,false);
                x += (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(s);
            } else {
                font_A.drawString(s, x+0.25f, y+2.5, new Color(20, 20, 20, 200).getRGB());
                font_A.drawString(s, x, y+2, color);
                x += (float)font_A.getStringWidth(s);
            }
        }
        return x;
        //return font.drawString(str, x, y, color);
    }

    public static float DisplayFont(String str, float x, float y, int color) {
       // str=" "+str;
        str = replaceChineseSymbols(str);
        for(int iF = 0; iF < str.length(); ++iF) {
            String s = String.valueOf(str.toCharArray()[iF]);
            if (s.contains("§") && iF + 1 <= str.length()) {
                color = getColor(String.valueOf(str.toCharArray()[iF + 1]));
                iF++;
            } else if (isChinese(s.charAt(0))) {
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x, y, color,false);
                x += (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(s)-0.25F+1;
            } else{
                font_A.drawString(s, x, y, color);
                x += (float)font_A.getStringWidth(s);
            }
        }
        return x;
    }

    public static float DisplayFonts(String str, float x, float y, int color) {
        //str=" "+str;
        str = replaceChineseSymbols(str);
        for(int iF = 0; iF < str.length(); ++iF) {
            String s = String.valueOf(str.toCharArray()[iF]);
            if (s.contains("§") && iF + 1 <= str.length()) {
                color = getColor(String.valueOf(str.toCharArray()[iF + 1]));
                iF++;
            } else if (isChinese(s.charAt(0))) {
                Minecraft.getMinecraft().fontRendererObj.drawString(s, x-1.5f, y-1, color,false);
                x += (float)Minecraft.getMinecraft().fontRendererObj.getStringWidth(s)-0.25F+1;
            } else{
                font_A.drawString(s, x+0.5f, y+1, color);
                x += font_A.getStringWidth(s);
            }
        }
        return x;
    }


    public void setFont(Font font) {
        super.setFont(font);
        setupBoldItalicIDs();
    }

    public void setAntiAlias(boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        setupBoldItalicIDs();
    }

    public void setFractionalMetrics(boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        setupBoldItalicIDs();
    }

    protected DynamicTexture texBold;
    protected DynamicTexture texItalic;
    protected DynamicTexture texItalicBold;

    private void setupBoldItalicIDs() {
        texBold = setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        texItalic = setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
        texItalicBold = setupTexture(this.font.deriveFont(3), this.antiAlias, this.fractionalMetrics, this.boldItalicChars);
    }

    private void drawLine(double x, double y, double x1, double y1, float width) {
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glLineWidth(width);
        GL11.glBegin(GL11.GL_LINES);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }

    public List<String> wrapWords(String text, double width) {
        List finalWords = new ArrayList();

        if (getStringWidth(text) > width) {
            String[] words = text.split(" ");
            String currentWord = "";
            char lastColorCode = 65535;

            for (String word : words) {
                for (int i = 0; i < word.toCharArray().length; i++) {
                    char c = word.toCharArray()[i];

                    if ((String.valueOf(c).equals("§")) && (i < word.toCharArray().length - 1)) {
                        lastColorCode = word.toCharArray()[(i + 1)];
                    }
                }

                if (getStringWidth(currentWord + word + " ") < width) {
                    currentWord = currentWord + word + " ";
                } else {
                    finalWords.add(currentWord);
                    currentWord = "" + lastColorCode + word + " ";
                }
            }

            if (currentWord.length() > 0) if (getStringWidth(currentWord) < width) {
                finalWords.add("" + lastColorCode + currentWord + " ");
                currentWord = "";
            } else {
                for (String s : formatString(currentWord, width)) {
                    finalWords.add(s);
                }
            }
        } else {
            finalWords.add(text);
        }

        return finalWords;
    }

    public List<String> formatString(String string, double width) {
        List finalWords = new ArrayList();
        String currentWord = "";
        char lastColorCode = 65535;
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];

            if ((String.valueOf(c).equals("§")) && (i < chars.length - 1))//Why does intelij replace "§" with §
            {
                lastColorCode = chars[(i + 1)];
            }

            if (getStringWidth(currentWord + c) < width) {
                currentWord = currentWord + c;
            } else {
                finalWords.add(currentWord);
                currentWord = "" + lastColorCode + String.valueOf(c);
            }
        }

        if (currentWord.length() > 0) {
            finalWords.add(currentWord);
        }

        return finalWords;
    }

    private void setupMinecraftColorcodes() {
        for (int index = 0; index < 32; index++) {
            int noClue = (index >> 3 & 0x1) * 85;
            int red = (index >> 2 & 0x1) * 170 + noClue;
            int green = (index >> 1 & 0x1) * 170 + noClue;
            int blue = (index >> 0 & 0x1) * 170 + noClue;

            if (index == 6) {
                red += 85;
            }

            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }

            this.colorCode[index] = ((red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF);
        }
    }

    public static String getStringWithoutColor(String text) {
        // 定义颜色字符的正则表达式模式
        String colorPattern = "§[0-9a-fk-oA-FK-O]";
        // 使用正则表达式替换颜色字符为空字符串
        return text.replaceAll(colorPattern, "");
    }

}