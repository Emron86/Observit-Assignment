#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

#define W1 800
#define H1 600
#define PX1 W1*H1
#define W2 800
#define H2 450
#define PX2 W2*H2
#define W3 640
#define H3 480
#define PX3 W3*H3
#define W4 480
#define H4 300
#define PX4 W4*H4
#define W5 176
#define H5 144
#define PX5 W5*H5

typedef struct resolution
{
    int width;
    int height;
    int pixels;
} resolution;

typedef struct video_settings
{
    int fps;
    int quality;
    resolution res;
} video_settings;

video_settings get_approximate(video_settings);
int get_index_of_approximate(int, const int*, int);
void print_video_settings(video_settings);

#endif
