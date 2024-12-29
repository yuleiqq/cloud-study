//
// Created by 余雷 on 2022/4/27.
//
/**
* 数据被存储在何处
*/


#include "stdio.h"
#include "stdlib.h"
#include "string.h"

int static_store = 30;
const char * pcg = "String  Literal";

int main(){

    int auto_store = 40;
    char auto_string [] = "Auto char Array";

    int * pi;
    char * pcl;

    pi = (int *) malloc(sizeof(int));

    *pi =35;

    pcl = (char * ) malloc(strlen("Dynamic String") + 1 );
    strcpy(pcl, "Dynamic String");

    printf("static_store : %d at %p \n", static_store, &static_store);
    printf("auto_store : %d at %p \n", auto_store, &auto_store);

    printf("    *pi: %d at %p\n", *pi , pi);
    printf("    %s at %p\n", pcg , pcg);
    printf("    %s at %p\n", pcl , pcl);

    return  0;
}







