//
// Created by 余雷 on 2022/4/26.
//

#include "stdio.h"

int main(void){

    int zippo[4][2] = {{2,4},{6,8},{1,3},{5,7}};

    //zippo 包含两个int值 的数组的地址 ， 大小为 8
    printf(" zippo = %p , zippo +1 = %p\n", zippo, zippo + 1 );
    // zipp[0] 包含一个 int 值 数组的地址 ， 4个字节大小
    printf(" zippo[0] = %p , zippo[0] +1  = %p\n", zippo[0] ,zippo[0] +1 );

    // *zippo 代表数组首元素（zippo[0]）的值， 而 zippo[0] 本身是一个int类型值的地址
    printf(" *zippo = %p, *zippo +1 = %p\n", *zippo, *zippo +1 );

    printf(" zippo[0][0] = %d\n",zippo[0][0]);
    printf(" *zippo[0] =%d\n", *zippo[0]);
    // zippo 是地址的地址, 必须解引用两次才能获取对应的值
    printf(" **zippo = %d\n", **zippo);

}



