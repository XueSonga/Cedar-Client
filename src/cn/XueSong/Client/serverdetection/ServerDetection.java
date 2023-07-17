package cn.XueSong.Client.serverdetection;

import cn.XueSong.Client.font.CFontRenderer;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ServerDetection {
    static Minecraft mc = Minecraft.getMinecraft();
    public static String getFirstLineOfScoreboard() {
        Minecraft mc = Minecraft.getMinecraft();
        Scoreboard scoreboard = mc.theWorld.getScoreboard();

        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(1);
        if (scoreObjective != null) {
            Score lastScore = null;
            Score firstScore = null;

            for (Score score : scoreboard.getSortedScores(scoreObjective)) {
                lastScore = score;
                if (firstScore == null) {
                    firstScore = score;
                }
            }

            if (firstScore != null) {
                return firstScore.getObjective().getDisplayName();
            }
        }
        return "";
    }
    public static String getLastLineOfScoreboard() {
        Scoreboard scoreboard = mc.theWorld.getScoreboard();
        ScoreObjective scoreObjective = scoreboard.getObjectiveInDisplaySlot(1);
        if (scoreObjective == null) {
            return "";
        }
        Collection<Score> collection = scoreboard.getSortedScores(scoreObjective);
        List<Score> list = Lists.newArrayList(Iterables.filter(collection, new Predicate<Score>() {
            public boolean apply(Score p_apply_1_) {
                return p_apply_1_.getPlayerName() != null && !p_apply_1_.getPlayerName().startsWith("#");
            }
        }));

        if (list.size() > 15) {
            collection = Lists.newArrayList(Iterables.skip(list, collection.size() - 15));
        } else {
            collection = list;
        }

        for (Score score : collection) {
            ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            String s = ScorePlayerTeam.formatPlayerName(scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
        }

        int j = 0;
        for (Score score1 : collection) {
            ++j;
            ScorePlayerTeam scoreplayerteam1 = scoreboard.getPlayersTeam(score1.getPlayerName());
            return ScorePlayerTeam.formatPlayerName(scoreplayerteam1, score1.getPlayerName());
        }
        return "";
    }
    public static String getSeverName(){
        if(!Objects.equals(getLastLineOfScoreboard(), "")){
            String SeverName = CFontRenderer.getStringWithoutColor(getLastLineOfScoreboard());
            if(SeverName.equals("")){
                return "Unknow";
            }
            if(filterString(SeverName).equals("www.hypixel.net")){
                return "Hypixel";
            }
            if(SeverName.equals("dcjnw.top")){
                return "Dcjnw";
            }
        }
        return "UnKnow";
    }

    public static String filterString(String input) {
        // 使用正则表达式过滤非英文字母和符号"."的字符
        String filteredString = input.replaceAll("[^A-Za-z.]", "");
        return filteredString;
    }

    public static String getSeverGameName(){
        if (getSeverName().equals("Hypixel")){
            String SeverGame = CFontRenderer.getStringWithoutColor(getFirstLineOfScoreboard());
            if (SeverGame.equals("HYPIXEL")){
                return "lobby";
            } else if (SeverGame.equals("BED WARS")) {
                return "bedwars";
            }else if (SeverGame.equals("SKYWARS")) {
                return "skywars";
            }else if (SeverGame.equals("SKYBLOCK")) {
                return "skyblock";
            }else if (SeverGame.equals("DUELS")) {
                return "duels";
            }else if (SeverGame.equals("ARCADE GAMES")) {
                return "lobby";
            }else if (SeverGame.equals("FARM HUNT")) {
                return "farmhunt";
            } else {
                return SeverGame;
            }
        }
        return "UnKnow";
    }
}
