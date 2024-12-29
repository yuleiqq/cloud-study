//
// Created by 余雷 on 2024/12/28.
//
#include "stdio.h"
extern unsigned int rand0();

int main(){

    int count;
    for(count=0;count<5;count++){
        printf("%d\n",rand0());
    }

    return 0;

}

