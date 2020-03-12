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

char *t1 = "xwrxwrxwr-------";
char *t2 = "----------------";

int chdir(char *pathname)   
{
    printf("chdir %s\n", pathname);
    int ino = getino(pathname);
    MINODE *mip = iget(dev, ino);
    
    
    char buf[BLKSIZE], temp[256];
    DIR *dp;
    char *cp;
    
    // Assume DIR has only one data block i_block[0]
    get_block(dev, mip->INODE.i_block[0], buf); 
    dp = (DIR *)buf;
    cp = buf;
    int type = (int) dp->file_type;
    if(type != 2)
    {
        printf("No such directory: %s\n", pathname);
        return;
    }

    iput(running->cwd);
    running->cwd = mip;
    printf("after cd: cwd = [%d, %d]\n", mip->dev, mip->ino);
}

int ls_file(MINODE *mip, char *name)
{
    struct stat fstat, *buf;
    int r;
    buf = &fstat;
    if ( (r = lstat("test.txt", &fstat)) < 0){
        printf("can’t stat %s\n", "test.txt");
        exit(1);
    }


    if ((buf->st_mode & 0xF000) == 0x8000)
        printf("%s", "-");
    if ((buf->st_mode & 0xF000) == 0x4000)
        printf("%s", "d");
    if ((buf->st_mode & 0xF000) == 0xA000)
        printf("%s", "l");

    int i;
    for (i=8; i >= 0; i--){
        if (buf->st_mode & (1 << i)) 
        printf("%c", t1[i]);
        else
        printf("%c", t2[i]);

    }
    printf("   ");

    char ftime[64];

    strcpy(ftime, ctime(&buf->st_ctime));
    ftime[strlen(ftime)-1] = 0;
    // kill \n at end

    printf("%d \t %ld \t %s \t %s", buf->st_uid, (long)buf->st_size, ftime, name);
    printf("\n");
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

    while (cp < buf + BLKSIZE)
    {
        strncpy(temp, dp->name, dp->name_len);
        temp[dp->name_len] = 0;
        int type = (int) dp->file_type;
        if(type == 1 || type == 2)
        {
            ls_file(dp->inode, temp);
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

char *rpwd(MINODE *wd)
{
    if(wd == root)
    {
        return;
    }

    int *my_ino;
    int *parent_ino = findino(wd, &my_ino);
    MINODE *pip = iget(dev, parent_ino);

    char *my_name = findmyname(pip, my_ino);
    rpwd(pip);
    printf("/%s", my_name);
    free(my_name);
}


char *pwd(MINODE *wd)
{
    if (wd == root){
        printf("/\n");
        return;
    }
    else
    {
        rpwd(wd);
        print("\n");
    }
    
}



