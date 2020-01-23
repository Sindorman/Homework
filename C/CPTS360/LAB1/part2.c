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
            unsigned int val = (unsigned char)fmt[0] << CHAR_BIT | (unsigned char)fmt[1];
            char c;
            switch(*fmt)
            {
                case 'c':
                        c = va_arg(args, char);
                        putchar(c);
                        found = false;
                        break;
                case 's':
                        prints(fmt);
                        found = false;
                        break;
                case 'u':
                        printu(val);
                        found = false;
                        break;
                case 'd':
                        printd((int) (*fmt));
                        found = false;
                        break;
                case 'o':
                        printo(val);
                        found = false;
                        break;
                case 'x':
                        printx(val);
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
    printu(32);
    prints(argv[0]);
    printd(-3);
    printx(43);
    printo(32);

    myprintf("cha=%c string=%s      dec=%d hex=%x oct=%o neg=%d\n", 
	       'A', "this is a test", 100,    100,   100,  -100);

}

