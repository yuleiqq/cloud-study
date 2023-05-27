//
// Created by 余雷 on 2023/5/20.
//

#include "iostream"
using namespace  std;

int main(){

    //1. 在运行阶段，为一个int 值分配未命名的内存
    //2. 使用指针来访问（指向）这个值（右->左）
    int * ptr_int = new int ;
    * ptr_int =90;
    //使用delete 释放内存
    delete ptr_int;
//    cout << *ptr_int;

    int num[5];
    int * nums = new int[5];
    int a = 4;

    //释放整个数组
    delete []nums;

    cout << sizeof (num) << "\t" <<sizeof (nums) << "\t" <<sizeof(a)<<endl;

}