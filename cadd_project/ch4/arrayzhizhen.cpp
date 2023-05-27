//
// Created by 余雷 on 2023/5/21.
//

#include "iostream"
using namespace  std;


int main(){

    int * p = new int[10];
    //使用指针创建二维数组
    int (*p2)[3] = new int[5][3];
    p2[3][2] = 998;

    for(int i=0;i<5;i++){
        for( int j=0;j<3;j++){
            cout << * (*(p2+i) +j ) <<",";
        }
        cout << endl;
    }

    /*
    int arrays[] = {15,23,30,40 ,50};
    int * p_arrays =arrays;

    for(int i=0;i <5; i++){
        cout << *p_arrays ++ <<endl;
    }
     */

}




