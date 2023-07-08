package cn.XueSong.Client.mod;

import cn.XueSong.Client.mod.Combo.autoblock;
import cn.XueSong.Client.mod.MoveMent.Sprint;
import cn.XueSong.Client.mod.Render.Hud;
import cn.XueSong.Client.mod.Mod;
import cn.XueSong.Client.mod.Render.fullbright;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    public List<Mod> mods = new ArrayList<>();

    public List<Mod> getMods(){
        return mods;
    }

    public List<Mod> getEnableMods(){
        return mods.stream().filter(Mod::isEnable).collect(Collectors.toList());
    }
    public void onkey(int key){
        for (Mod enableMod : getMods()) {
            if (enableMod.getKey() == key) {
                enableMod.setEnable(!enableMod.isEnable());
            }
        }

    }
    public void load(){
        mods.add(new Sprint());
        mods.add(new Hud());
        mods.add(new autoblock());
        mods.add(new fullbright());
    }
}
