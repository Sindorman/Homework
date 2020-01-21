#include <stdio.h>
#include <stdlib.h>

int *FP;

int main(int argc, char *argv[ ], char *env[ ])
{
  int a,b,c;
  printf("enter main\n");
  
  printf("&argc=%x argv=%x env=%x\n", &argc, argv, env);
  printf("&a=%8x &b=%8x &c=%8x\n", &a, &b, &c);

 //Write C code to print values of argc and argv[] entries
  printf("argc: %i\n", argc);
  printf("argv: %s \n", *argv);

  a=1; b=2; c=3;
  A(a,b);
  printf("exit main\n");
}

int A(int x, int y)
{
  int d,e,f;
  printf("enter A\n");
  // write C code to PRINT ADDRESS OF d, e, f
  d=4; e=5; f=6;
  printf("&d=%x &e=%x &f=%x\n", &d, &e, &f);
  B(d,e);
  printf("exit A\n");
}

int B(int x, int y)
{
  int g,h,i;
  printf("enter B\n");
  // write C code to PRINT ADDRESS OF g,h,i
  g=7; h=8; i=9;
  printf("&g=%x &h=%x &i=%x\n", &g, &h, &i);
  C(g,h);
  printf("exit B\n");
}

int C(int x, int y)
{
  int u, v, w, i, *p;

  printf("enter C\n");
  // write C cdoe to PRINT ADDRESS OF u,v,w,i,p;
  u=10; v=11; w=12; i=13;
  printf("&u=%x &v=%x &w=%x &i=%x &p=%x\n", &u, &v, &w, &i, &p);

  FP = (int *)getebp();  // FP = stack frame pointer of the C() function

 //Write C code to print the stack frame link list.
  while(FP != 0)
  {
    printf("FP: %8X\n", FP);
    FP = *FP;
  }
  printf("FP: %8X\n", FP);

  p = (int *)&p;
  i = 0;
  while(i < 128)
  {
    printf("%d(FP): %8X\n%d(FP): %d\n\n", i, p, i, *p);

    p++; i++;
  }

}