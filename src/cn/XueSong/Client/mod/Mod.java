package cn.XueSong.Client.mod;

import cn.XueSong.Client.util.math.MathUtil;

public abstract class Mod {
    private final String name;
    private final String type;
    private boolean enabled;
    private int key;

    public Mod(String name, String type, boolean enabled) {
        this.name = name;
        this.type = type;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            if (enabled) {
                onEnable();
            } else {
                onDisable();
            }
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void render() {

    }

    public void onUpdate() {

    }

    public void onEnable() {

    }

    public void onDisable() {

    }
}
