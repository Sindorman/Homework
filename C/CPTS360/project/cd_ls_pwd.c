/************* cd_ls_pwd.c file **************/
// #include <stdio.h>
// #include <stdlib.h>
// #include <fcntl.h>
// #include <ext2fs/ext2_fs.h>
// #include <string.h>
// #include <libgen.h>
// #include <sys/stat.h>
// #include <time.h>

// #include "type.h"

struct tm lt;

int chdir(char *pathname)   
{
    printf("chdir %s\n", pathname);
    printf("under construction READ textbook HOW TO chdir!!!!\n");
    // READ Chapter 11.7.3 HOW TO chdir
}

int ls_file(MINODE *mip, char *name)
{
    struct stat *buf;
    buf = malloc(sizeof(struct stat));
    stat(name, &buf);
    // READ Chapter 11.7.3 HOW TO ls
    int size = buf->st_size;
    printf( (S_ISDIR(buf->st_mode)) ? "d" : "-");
    printf( (buf->st_mode & S_IRUSR) ? "r" : "-");
    printf( (buf->st_mode & S_IWUSR) ? "w" : "-");
    printf( (buf->st_mode & S_IXUSR) ? "x" : "-");
    printf( (buf->st_mode & S_IRGRP) ? "r" : "-");
    printf( (buf->st_mode & S_IWGRP) ? "w" : "-");
    printf( (buf->st_mode & S_IXGRP) ? "x" : "-");
    printf( (buf->st_mode & S_IROTH) ? "r" : "-");
    printf( (buf->st_mode & S_IWOTH) ? "w" : "-");
    printf( (buf->st_mode & S_IXOTH) ? "x" : "-");
    printf("   ");

    time_t t = buf->st_mtime;
    localtime_r(&t, &lt);
    char timebuf[80];
    strftime(timebuf, sizeof(timebuf), "%c", &lt);

    printf("%d \t %ld \t %s \t %s", buf->st_uid, (long)buf->st_size, timebuf, name);
    printf("\n");
    free(buf);
}

int ls_dir(MINODE *mip)
{
    printf("ls_dir: list CWD's file names; YOU do it for ls -l\n");

    char buf[BLKSIZE], temp[256];
    DIR *dp;
    char *cp;
    
    // Assume DIR has only one data block i_block[0]
    get_block(dev, mip->INODE.i_block[0], buf); 
    dp = (DIR *)buf;
    cp = buf;
    
    while (cp < buf + BLKSIZE){
        strncpy(temp, dp->name, dp->name_len);
        temp[dp->name_len] = 0;
        int type = (int) dp->file_type;
        if(type == 1)
        {
            ls_file(dp->inode, temp);
        }
        else if(type == 2)
        {
            
        }

        cp += dp->rec_len;
        dp = (DIR *)cp;
    }
    printf("\n");
}

int ls(char *pathname)  
{
    printf("ls %s\n", pathname);
    printf("ls CWD only! YOU do it for ANY pathname\n");
    ls_dir(running->cwd);
}

char *pwd(MINODE *wd)
{
    printf("pwd: READ HOW TO pwd in textbook!!!!\n");
    if (wd == root){
      printf("/\n");
      return;
    }
}



