//
// Created by 余雷 on 2022/4/26.
//

/**
 * 指针运算中的优先级
 */
#include "stdio.h"

int data[2] = {100,200};
int moredata[2] = {300,400};



int main(){

    int * p1, *p2, *p3;

    p1=p2 =data;
    p3 = moredata;

    printf(" *p1=%d, *p2=%d,  *p3=%d\n", *p1,*p2,*p3);  // 指向数组的第一个元素，获取其中的值

    printf(" *p1++ = %d, *++p2 = %d, (*p3)++ = %d\n", *p1++, *++p2, (*p3)++);

    printf(" *p1=%d, *p2=%d,  *p3=%d\n", *p1,*p2,*p3);


}




