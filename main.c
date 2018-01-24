/******************************************************************************
*
* A program that takes fps, quality and resolution and returns the closest
* allowed values.
*
*******************************************************************************/

#include <stdio.h>
#include <limits.h>
#include "main.h"

const resolution allowed_res[] =
{
    { W1, H1, PX1 },
    { W2, H2, PX2 },
    { W3, H3, PX3 },
    { W4, H4, PX4 },
    { W5, H5, PX5 },
};

const int allowed_res_px[] = { PX1, PX2, PX3, PX4, PX5};

const int allowed_fps[] =
{
    30, 28, 26, 24, 22, 20, 18,
    16, 14, 12, 10, 8, 6, 4, 2
};
const int allowed_quality[] =
{
    80, 75, 70, 65, 60, 55, 50, 45,
    40, 35, 30, 25, 20, 15, 10, 5
};

int main(void)
{
    video_settings settings_in = { 3, 73, { 685, 685, 685*685}};
    video_settings settings_out = get_approximate(settings_in);

    printf("settings in:\n");
    print_video_settings(settings_in);
    printf("settings out:\n");
    print_video_settings(settings_out);

    return 0;
}

video_settings get_approximate(video_settings settings_in)
{
    video_settings settings_out;
    settings_out.fps = allowed_fps[get_index_of_approximate(settings_in.fps, allowed_fps, sizeof(allowed_fps) / sizeof(int))];
    settings_out.quality = allowed_quality[get_index_of_approximate(settings_in.quality, allowed_quality, sizeof(allowed_quality) / sizeof(int))];
    settings_out.res = allowed_res[get_index_of_approximate(settings_in.res.pixels, allowed_res_px, sizeof(allowed_res_px) / sizeof(int))];

    return settings_out;
}

int get_index_of_approximate(int inVal, const int * arr, int size)
{
    int i = 0;
    int stdVal = arr[i];
    int dif = stdVal - inVal;
    int prevDif = INT_MAX;

    while (dif > 0 && i < size - 1)
    {
        stdVal = arr[++i];
        prevDif = dif;
        dif = stdVal - inVal;
    }

    /* if negative or eq: previous index was closer - decrement index
     * if positive: current index is closer - do nothing */
    if (prevDif + dif <= 0)
    {
        i--;
    }

    return i;
}

void print_video_settings(video_settings s)
{
    printf("framerate:\t%d,\nquality:\t%d,\n", s.fps, s.quality);
    printf("resolution:\t[width:%d, height:%d, pixels:%d]\n\n", s.res.width, s.res.height, s.res.pixels);
}


