package cn.XueSong.Client.util.team;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;

public class TeamUtil {
    public static boolean isInSameTeam(EntityPlayer player1, EntityPlayer player2) {
        ScorePlayerTeam team1 = player1.getWorldScoreboard().getPlayersTeam(player1.getName());
        ScorePlayerTeam team2 = player2.getWorldScoreboard().getPlayersTeam(player2.getName());
        return team1 != null && team1.equals(team2);
    }
}

