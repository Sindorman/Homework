#include <stdio.h>
#include <limits.h>
#include <stdbool.h>
#include <stdarg.h>
#include <string.h>
char *ctable = "0123456789ABCDEF";
int  BASE = 10; //10 for unsigned int, 16 hexadecimanl, 8 octal integer.
typedef unsigned int   u32;

void rpu(u32 x)
{  
    char c;
    if (x){
        c = ctable[x % BASE];
        rpu(x / BASE);
        putchar(c);
    }
}

void printu(u32 x)
{
    BASE = 10;
    (x==0)? putchar('0') : rpu(x);
    putchar(' ');
}

void prints(char *s)
{
    char *temp = s;
    while(*temp != '\0')
    {
        putchar(*temp);
        temp++;
    }
    putchar(' ');
}

void printd(int x)
{
    unsigned int t = x;
    if(x < 0)
    {
        putchar('-');
        t = -x;
    }
    printu(t);
    putchar(' ');
}

void printx(u32 x)
{
    BASE = 16;
    (x==0)? putchar('0') : rpu(x);
    putchar(' ');
}

void printo(u32 x)
{
    BASE = 8;
    (x==0)? putchar('0') : rpu(x);
    putchar(' ');
}

void myprintf(char *fmt, ...)
{
    va_list args;
    va_start(args, fmt);
    bool found = false;
    while (*fmt != '\0')
    {
        if(*fmt == '%')
        {
            found = true;
            fmt++;
            continue;
        }

        if(found)
        {
            unsigned int val;
            char *c;
            switch(*fmt)
            {
                case 'c':
                        c = va_arg(args, int);
                        putchar(c);
                        found = false;
                        break;
                case 's':
                        c = va_arg(args, int);
                        prints(c);
                        found = false;
                        break;
                case 'u':
                        val = va_arg(args, unsigned int);
                        printu(val);
                        found = false;
                        break;
                case 'd':
                        val = va_arg(args, int);
                        printd(val);
                        found = false;
                        break;
                case 'o':
                        val = va_arg(args, unsigned int);
                        printo(val);
                        found = false;
                        break;
                case 'x':
                        val = va_arg(args, unsigned int);
                        printx(val);
                        found = false;
                        break;
                default:
                        printf("Not a number");
                        found = false;
                        break;
            }
        } else if(*fmt == '\n')
        {
            putchar('\n');
            putchar('\r');
        } else 
        {
            putchar(*fmt);
        }
        fmt++;
    }
    va_end(args);
}

int main(int argc, char *argv[ ], char *env[ ])
{
    printf("Tesing individual functions: \n");
    printf("printu: "); printu(32);
    printf("\nprints: "); prints(argv[0]);
    printf("\nprintd: "); printd(-3);
    printf("\nprintx: "); printx(43);
    printf("\nprinto: "); printo(32);

    printf("\nTesing myprintf: \n");
    myprintf("cha=%c string=%s      dec=%d hex=%x oct=%o neg=%d\n", 
	       'A', "this is a test", 100,    100,   100,  -100);

}

