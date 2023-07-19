package cn.XueSong.Client.mod;

import cn.XueSong.Client.mod.Combo.AutoClick;
import cn.XueSong.Client.mod.Combo.AimAssist;
import cn.XueSong.Client.mod.MoveMent.Sprint;
import cn.XueSong.Client.mod.Other.Nick;
import cn.XueSong.Client.mod.Other.Serverdetection;
import cn.XueSong.Client.mod.Render.Hud;
import cn.XueSong.Client.mod.Render.NameTag;
import cn.XueSong.Client.mod.Render.fullbright;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModManager {
    private final List<Mod> mods = new ArrayList<>();

    public List<Mod> getMods() {
        return mods;
    }

    public List<Mod> getEnabledMods() {
        return mods.stream().filter(Mod::isEnabled).collect(Collectors.toList());
    }

    public void onKey(int key) {
        for (Mod mod : mods) {
            if (mod.getKey() == key) {
                mod.setEnabled(!mod.isEnabled());
                if (mod.isEnabled()){
                    mod.onEnable();
                }else {
                    mod.onDisable();
                }
            }
        }
    }

    public void load() {
        mods.add(new Sprint());
        mods.add(new Hud());
        mods.add(new AutoClick());
        mods.add(new fullbright());
        mods.add(new AimAssist());
        mods.add(new Serverdetection());
        mods.add(new Nick());
        mods.add(new NameTag());
    }
}
