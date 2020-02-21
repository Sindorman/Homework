/** C4.3.c: matrix sum by threads with mutex lock **/
#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>

#define  N   128
int A[N][N];

long int total = 0;           // global total

void func()         // working thread function
{
   int i, j, sum = 0;

   for (i=0; i<N; i++)
   {
      for (j=0; j<N; j++)
      {
         sum += A[j][i];
      } 
   }       // compute partial sum of A[row]in 
      
   total += sum;           // update global total inside a CR
   printf("total = %ld\n", total);
}

int main (int argc, char *argv[])
{
   int i, j, r;
   void *status;

   struct timeval t1, t2;
   gettimeofday(&t1, NULL);

   printf("Main: initialize A matrix\n");
   for (i=0; i<N; i++){
     for (j=0; j<N; j++){
         A[i][j] = i*N + j + 1;
         printf("%4d ", A[i][j]);
     }
     printf("\n");
   }

   func();

   printf("Main: total = %ld\n", total);

   gettimeofday(&t2, NULL);
   printf("t2: sec=%ld usec=%ld\n", t2.tv_sec, t2.tv_usec);
   printf("t1: sec=%ld usec=%ld\n", t1.tv_sec, t1.tv_usec);
   printf("usec used = %ld\n", t2.tv_usec - t1.tv_usec);

}
