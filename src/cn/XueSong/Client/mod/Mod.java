package cn.XueSong.Client.mod;

public abstract class Mod {
    private final String name;

    private final Category category;

    private String type;
    private boolean enabled;
    private int key;

    public Mod(String name, String type, boolean enabled,Category category) {
        this.name = name;
        this.category = category;
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

    public void setType(String text){
        this.type = text;
    }
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }


    public void render(float partialTicks) {

    }

    public void renderWorld(float partialTicks) {

    }

    public void onWorldChange(){

    }

    public void subThread(){

    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public Category getCategory() {
        return category;
    }
}
