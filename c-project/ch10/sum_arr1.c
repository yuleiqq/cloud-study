//
// Created by 余雷 on 2022/4/26.
//

/**
* 指针演示数组元素之和
*/

#include "stdio.h"
#define SIZE 10

int sum(int arr[], int n);

int main(void){

    int marbles[SIZE] = {20,5,39,4,16,19,26,31,20};
    long answer;

    answer = sum(marbles,SIZE);

    printf("marbles总和是: %d \n ",answer);

    printf("marbles字节大小: %zd \n", sizeof marbles);  // 40字节：  每个值占用4个字节。

    return  0;

}

int sum(int arr[], int n ) {

    int i ;
    int total =0;  // 不赋值，会自动赋一个负值

    for( i=0;i < n; i++){
        total += arr[i];
    }

    // 8 字节:  arr 并部署数组本身，它是一个指向marbles 数组首元素的指针。 我们的系统中用
    // 8字节存储地址，所以指针变量的大小是8字节。
    printf("arr数组的字节大小： %zd \n", sizeof arr);

    return  total;
}



