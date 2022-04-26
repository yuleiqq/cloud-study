//
// Created by 余雷 on 2022/4/26.
//

/**
* 指定初始化器 ，初始化数组的某一个
*/

#include "stdio.h"
#define  MONTHS 12

int main(void){

    int days[MONTHS] = {31, 28 ,[4]=31,30,31,[1]=29};

    int i ;

    for(i=0; i < MONTHS; i++){
        printf("%2d %d\n", i + 1 , days[i]);
    }

}
















