
/**
 * 使用指针操作数据的交换
 */

#include "stdio.h"


void swap(int * u ,int * v);


int main(){

    int x =5, y=10;
    printf("交换前 :  x = %d, y = %d\n",x,y);

    swap(&x,&y);  //把地址发送给函数

    printf("交换后:   x = %d, y = %d\n",x,y);

    return  0;
}



/**
 * 利用指针进行数据交换
 * @param u
 * @param v
 */

void swap(int * u ,int * v){
    int temp;
    temp = *u;   //获取 u 所指对象的值
    *u = *v;
    *v = temp;
}











