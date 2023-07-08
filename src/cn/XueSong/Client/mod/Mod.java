package cn.XueSong.Client.mod;

public abstract class Mod {
    private final String name;
    private final String type;
    private boolean enable;

    private int key;

    public Mod(String name, String type,boolean enable) {
        this.name = name;
        this.type = type;
        this.enable = enable;
    }
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getKey(){
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void render(){

    }

    public void onUpdate(){
    }
}
