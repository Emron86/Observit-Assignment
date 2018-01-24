
//*******************************************************************
// 
// A program that takes fps, quality and resolution and returns 
// the closest allowed values
//
//*******************************************************************

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        VideoSettingsAdjuster adjuster = new VideoSettingsAdjuster();

        VideoSettings settingsIn = new VideoSettings(3, 73, new Resolution(685, 685));
        VideoSettings settingsOut = adjuster.getApproximate(settingsIn);

        System.out.println("settingsIn: " + settingsIn);
        System.out.println("settingsOut: " + settingsOut);
    }
}

// *******************************************************************
//
// This class is used to validate VideoSettings and adjust them
// to the closest approximate values if needed.
//
// *******************************************************************
class VideoSettingsAdjuster {
    public static final int MAX_FPS = 30;
    public static final int FPS_STEP = 2;
    public static final int MAX_QUALITY = 80;
    public static final int QUALITY_STEP = 5;

    private Resolution[] allowedResolutions = { new Resolution(800, 600), new Resolution(800, 450),
            new Resolution(640, 480), new Resolution(480, 300), new Resolution(176, 144) };
    private List<Integer> allowedResolutionsInPixels = new ArrayList<Integer>();
    private List<Integer> allowedFps = new ArrayList<Integer>();
    private List<Integer> allowedQuality = new ArrayList<Integer>();

    public VideoSettingsAdjuster() {
        for (int fps = MAX_FPS; fps >= FPS_STEP; fps -= FPS_STEP) {
            allowedFps.add(fps);
        }

        for (int q = MAX_QUALITY; q >= QUALITY_STEP; q -= QUALITY_STEP) {
            allowedQuality.add(q);
        }

        for (int i = 0; i < allowedResolutions.length; i++) {
            allowedResolutionsInPixels.add(allowedResolutions[i].getPixels());
        }
    }

    /**
     * Adjust values of a VideoSettings-instance according to the allowed
     * values.
     * 
     * @param settingsIn
     *            The settings to be reviewed
     * @return a new VideoSettings-instance with adjusted values
     * @see VideoSettings
     */
    public VideoSettings getApproximate(VideoSettings settingsIn) {
        Integer fps = allowedFps.get(getIndexOfApproximate(allowedFps, settingsIn.getFps()));
        Integer q = allowedQuality.get(getIndexOfApproximate(allowedQuality, settingsIn.getQuality()));
        Resolution res = allowedResolutions[getIndexOfApproximate(allowedResolutionsInPixels,
                settingsIn.getResolution().getPixels())];

        VideoSettings settingsOut = new VideoSettings(fps, q, new Resolution(res.getWidth(), res.getHeight()));

        return settingsOut;
    }

    private int getIndexOfApproximate(List<Integer> list, Integer inVal) {
        int i = 0;
        int stdVal = list.get(i);
        int dif = stdVal - inVal;
        int prevDif = Integer.MAX_VALUE;
        
        while (dif > 0 && i < list.size() - 1) {
            stdVal = list.get(++i);
            prevDif = dif;
            dif = stdVal - inVal;
        }

        // if negative or eq: previous index was closer - decrement index
        // if positive: current index is closer - do nothing
        if (prevDif + dif <= 0) {
            i--;
        }
        
        return i;
    }
}

// *******************************************************************
//
// This class contains settings for video: framerate(fps), quality
// in percent and display-resolution.
//
// *******************************************************************
class VideoSettings {
    private int fps;
    private int quality;
    private Resolution res;

    public VideoSettings(int fps, int quality, Resolution res) {
        this.fps = fps;
        this.quality = quality;
        this.res = res;
    }

    public int getFps() {
        return fps;
    }

    public int getQuality() {
        return quality;
    }

    public Resolution getResolution() {
        return res;
    }

    public String toString() {
        return "VideoSettings[fps=" + fps + ",quality=" + quality + "," + res + "]";
    }
}

// *******************************************************************
//
// A class that holds width/height of a display-resolution as well as
// the amount of pixels contained within that resolution.
//
// *******************************************************************
class Resolution {
    private int width;
    private int height;
    private int pixels;

    public Resolution(int width, int height) {
        this.width = width;
        this.height = height;
        this.pixels = width * height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPixels() {
        return pixels;
    }

    public String toString() {
        return "Resolution[width=" + width + ",height=" + height + ",pixels=" + pixels + "]";
    }
}