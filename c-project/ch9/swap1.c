//
// Created by 余雷 on 2022/4/25.
//

/**
 * 第一个交换函数
 */

#include "stdio.h"

void interchange(int u, int v);

int main(){
     int x=5, y =10;
     printf("%d, %d\n",x,y);

    interchange(x,y);

    //发现最后并未交换
    printf("%d, %d\n",x,y);

}

void interchange(int u,int v){

    int temp;
    temp = u;
    u =v ;
    v =temp;

}


