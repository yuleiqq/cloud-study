#include <stdio.h>
#include <math.h>

#define PI 3.14159265
int main()
{
    double angle, result;
    angle = 30.0;
    result = sin (angle * PI / 180.0);
    printf ("result = %f \n", result);
    return 0;
}
