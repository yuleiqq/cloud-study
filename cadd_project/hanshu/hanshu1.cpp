//
// Created by 余雷 on 2023/5/27.
//

/**
 * 计算两个数的和
 */
#include "iostream"
using namespace  std;


int sum(int,int); //函数原型

int main(){

    int result = sum(1,2);
    cout<< "result: " <<result <<endl;
    return 0;


}



int sum(int num1, int num2){\
    return  num1 +num2;
}




