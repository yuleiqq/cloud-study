//
// Created by 余雷 on 2024/12/28.
//

#include "hiding.h"

#include "stdio.h"
int main(){

    int x =30;  //原始的x
    printf("x 在外面: %d at %p\n", x,&x);
    {
        int x =77; //新的x,隐藏了原始的x
        printf("x 在内部: %d at %p\n", x,&x);
    }
    printf("x 在外面: %d at %p\n", x,&x);

    while(x++ < 33){
        int x =100;
        x++;
        printf(" x in while loop: %d at %p\n", x,&x);
    }
    printf("x 在外面: %d at %p\n", x,&x);


}
