//
// Created by 余雷 on 2022/4/25.
//
#include "stdio.h"

int main(){

    const int days[] = {31,28,31,30,31};

    printf("%d\n",sizeof days[0]);
    printf("%d\n",sizeof days[1]);

    int t = sizeof days /sizeof days[1];


    return 0;
}



