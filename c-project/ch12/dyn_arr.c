
/**
 * 动态分配数组
 */
#include "stdio.h"
#include "stdlib.h"  /* 为malloc 、free() 提供原型*/

int main(void ){

    double * ptd;

    int max;
    int number;
    int i =0;

    puts("What is the maxinum number of type double entries ?");
    if(scanf("%d", &max) != 1){
        puts("Number not correctly entered -- bye .");
        exit(EXIT_FAILURE);
    }
    ptd = (double *) malloc(max * sizeof (double ));
    //另一种分配内存的方式
    //ptd =(double *) calloc(100,sizeof (double ));

    if(ptd == NULL){
        puts(" Memory allocation failed. Goodbye. ");
        exit(EXIT_FAILURE);
    }

    /** ptd 现在指向有max 个元素的数组 **/
    puts("Enter the values (q to quit): ");
    while (i < max && scanf("%lf",&ptd[i]) == 1)
        ++i ;
    printf("Here are your %d entries: \n", number =i );
    for(i= 0; i< number; i++){
        printf("%7.2f" ,ptd[i]);
        if(i % 7 == 6)
            putchar('\n');
    }

    if( i %7 !=0 )
        putchar('\n');
    puts("Done");
    free(ptd);
    return  0;

}














