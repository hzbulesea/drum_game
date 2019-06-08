package drumgame.example.drum_game;

import android.util.DisplayMetrics;

public class DeviceInfor {
    private float deviceIn[] = new float[4];    // density, width, height, dpi
    private float density, width, height, dpi;

    public DeviceInfor(DisplayMetrics metrics) {
        deviceIn[0] = metrics.density;      // density
        deviceIn[1] = metrics.widthPixels;  // px
        deviceIn[2] = metrics.heightPixels; // px
        deviceIn[3] = metrics.xdpi;         // x-dpi
        System.out.println(deviceIn[0] + " " + deviceIn[1] + " " + deviceIn[2] + " "+ deviceIn[3]);
    }

    public int dpiToPx(int dpi) {
        int scale_px = (int) (dpi * deviceIn[0]);        // imagePx = imagedip * density
        return scale_px;
    }

    public int getHeight(){
        this.height = deviceIn[2];
        return (int) height;
    }

    public int pxTodpi(int px) {
        int scale_dpi = (int) (px / deviceIn[0]);        // imagePx = imagedip * density
        return scale_dpi;
    }
}
