#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#include <stdlib.h>

typedef unsigned char  u8;
typedef unsigned short u16;
typedef unsigned int   u32;

static int parent_sector = 0;

#define SECTOR_SIZE 512
#define PARTITION_TABLE_OFFSET 0x1BE

typedef struct partition {
	u8 drive;             /* drive number FD=0, HD=0x80, etc. */

	u8  head;             /* starting head */
	u8  sector;           /* starting sector */
	u8  cylinder;         /* starting cylinder */

	u8  sys_type;         /* partition type: NTFS, LINUX, etc. */

	u8  end_head;         /* end head */
	u8  end_sector;       /* end sector */
	u8  end_cylinder;     /* end cylinder */

	u32 start_sector;     /* starting sector counting from 0 */
	u32 nr_sectors;       /* number of of sectors in partition */
}PARTITION;

int read_sector(int fd, int sector, char *buf)
{
  int n;
  lseek(fd, sector*512, SEEK_SET);
  n = read(fd, buf, 512);
  if (n <= 0){
    printf("read failed\n");
    return -1;
  }
  return n;
}

PARTITION *p;

int fd;
char buf[512];

void print_partition(struct partition *p)
{
    printf("%5X %10d %10d %7u %6X\n",
        p->drive, p->start_sector + parent_sector, p->start_sector + p->nr_sectors + parent_sector - 1, p->nr_sectors, p->sys_type);
}

//TODO Make it so that start sector of extended added to the parent

void recursive_printPartition(struct partition *p, unsigned long sector)
{
    unsigned long abs_start_sector = 0;

    if (p->start_sector == 0)
        return;

    if (sector == 0)
    {
        sector = p->start_sector;
        abs_start_sector = p->start_sector;
    }
    else
    {
        abs_start_sector = p->start_sector + sector;
    }
    lseek(fd, (long)abs_start_sector * SECTOR_SIZE, 0);

    if (read(fd, buf, sizeof(buf)) == -1)
    {
        perror("Read");
        exit(1);
    }

    //TODO figure this out
    parent_sector = abs_start_sector;
    for (int i = 0; i < 2; i++)
    {
        p = (struct partition *)(buf + PARTITION_TABLE_OFFSET + i * 16);
        if(p->sys_type == 5 || p->sys_type == 0) continue;
        print_partition(p);
    }

    recursive_printPartition(p, sector);
}

int main()
{
    fd = open("vdisk", O_RDONLY);
    printf("fd = %d\n", fd);

    read_sector(fd, 0, buf);

    printf("Main Partitions:\n");
    printf("%5s %10s %10s %6s %6s\n", "Drive", "Start", "End", "Sectors", "ID");
    for (int i = 0; i < 4; i++)
    {
        p = (PARTITION *)(buf + PARTITION_TABLE_OFFSET + i * 16);
        print_partition(p);
        
        if (p->sys_type == 5)
        {
            parent_sector = p->start_sector;
            printf("\nExtended Partitions:\n");
            printf("%5s %10s %10s %6s %6s\n", "Drive", "Start", "End", "Sectors", "ID");
            recursive_printPartition(p, 0);
        }
    }

}