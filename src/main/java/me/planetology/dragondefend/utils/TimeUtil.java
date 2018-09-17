package me.planetology.dragondefend.utils;

public class TimeUtil {

    public String convertToClock(int time) {
        int minutes = time / 60, seconds = time % 60;
        String distanceMinute = (minutes < 10 ? "0" : "") + minutes, distanceSecond = (seconds < 10 ? "0" : "") + seconds;

        return distanceMinute + ":" + distanceSecond;
    }
}
