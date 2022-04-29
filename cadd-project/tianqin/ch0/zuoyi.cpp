//
// Created by 余雷 on 2022/4/29.
//
/**
 * 题目:
 *  设讲 n (n>1) 个整数存放到一维数组R中， 设计一个算法，讲R中的序列循环左移P (0<P<n) 个位置，即将R中的数据由 {x0,x1,..,xn-1}
 *  变换为 {xp,xp+1, xn-1, x0,x1, ..,xp-1}
 *
 */

#include<iostream>
#define  N 50
using namespace  std;
/**
 * 变换位置
 * @param R
 * @param l
 * @param r
 */
void Reverse(int R[], int l, int r ){
    int i, j;
    int temp;
    for(i= l; j<r; ++i, --j){
        temp = R[i];
        R[i] = R[j];
        R[j] = R[i];
    }
}


/**
 * 对 R 数组 左移 P 个 单位
 * @param R
 * @param n
 * @param p
 */
void RCR (int R[], int n , int p){

    if(p<=0 || p>=n){
        cout << "ERROR" <<endl;
    }
    else {
          Reverse(R,0,p-1);
          Reverse(R,p,n-1);
          Reverse(R,0,n-1);
    }
}

int main (){
    int L,i;
    int R[N], n;




}


















