//
// Created by 余雷 on 2022/4/26.
//

/**
* 不修改数组元素，使用const 进行修饰
*/

#include "stdio.h"


// 使用const 修饰之后，对数组修改时，编译器会报错.
int sum(int arr[], int n);

int main(){

     int arr[] = {1,2,3,4,5};
     int total =  sum(arr,5);
     for(int i=0; i<5 ;i++){
         printf("%d\n", arr[i]);
    }
}


int sum (int arr[],int n){

    int i;
    int total;

    for( i=0;i< n; i++){
        total += arr[i]++;  //错误递增了每个元素的值
    }

    return  total;

}






