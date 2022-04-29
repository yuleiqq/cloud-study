//
// Created by 余雷 on 2022/4/28.
//

/**
 *
 * const 放在 * 左侧任意位置，
 *
 */
#include "stdio.h"

int main(){

    int arr[10];

    // pf 指向一个float 类型的const 值.  pf 指向的值不能被改变, 但是pf本身的值可以被改变
    const float  *  pf ;  //  等于 float  const * pf;
    //  *pf =20;  这样是错误的

    //创建的pt本身的值不能更改. pt 必须指向同一个地址，但是它所指向的值可以改变
    float  * const pt ;  // pt 是一个const 指针



}





