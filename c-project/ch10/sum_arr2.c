//
// Created by 余雷 on 2022/4/26.
//


#include "stdio.h"
#define SIZE 10

int sump(int * start ,int * end);

int main(void){

    int marbles[SIZE] = {20,5,39,4,16,19,26,31,20};
    long answer;

    answer = sump (marbles, marbles + SIZE);
    printf("marbles总和是: %d \n ",answer);

}

int sump(int * start ,int * end){
    int i ;
    int total =0;  // 不赋值，会自动赋一个负值

    while (start < end){

        total += *start;
        start  = start + 1;
    }

    return  total;
}

