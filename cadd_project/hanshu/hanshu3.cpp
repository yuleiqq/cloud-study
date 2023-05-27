//
// Created by 余雷 on 2023/5/27.
//
//
#include "iostream"
using namespace  std;

/*使用二维数组作为参数*/
void show(double (*) [5] ,int );

int main(){
    double powers[3][5] = {
            {1.1,1.2,1.3,1.4,1.5},
            {2.1,2.2,2.3,2.4,2.5},
            {3.1,3.2,3.3,3.4,3.5}
    };

    show(powers,3);


}

void show(double (*arr)  [5] , int len ){

    for(int i=0; i<len;i++){
        cout << arr<< endl;
        cout << *arr<< endl;
        cout << *(arr+i) <<endl;
        for(int j=0;j<4;j++){
            cout <<*(*(arr+i)+j)<<",";
        }

        cout << endl;
    }
}