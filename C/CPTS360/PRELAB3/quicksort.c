
#include<stdio.h>
#include <sys/time.h>
#define N 10

int a[N] = {5,1,6,4,7,2,9,8,0,3};  // unsorted data

int print()   // print current a[] contents
{
  int i;
  printf("[ ");
  for (i=0; i<N; i++)
    printf("%d ", a[i]);
  printf("]\n");
}

// Helper to swap to elements
void Swap(int* one, int* two)
{ 
    int temp = *one; 
    *one = *two;
    *two = temp;
}

// Partition array for low and high.
int Partition (int arr[], int low, int high) 
{
    int i = (low - 1);
    int pivot = arr[high];

    for (int j = low; j <= high- 1; j++)
    {

        if (arr[j] < pivot) 
        { 
            i++;
            Swap(&arr[i], &arr[j]);
        }
    }

    Swap(&arr[i + 1], &arr[high]);
    return (i + 1);
}


// Actual quicksort.
void QuickSort(int arr[], int low, int high) 
{ 
    if (low < high)
    { 
        /* pi is partitioning index, arr[p] is now 
           at right place */
        int pi = Partition(arr, low, high);
  
        // Separately sort elements before 
        // partition and after partition
        QuickSort(arr, low, pi - 1);
        QuickSort(arr, pi + 1, high);
    }
}

int main(int argc, char *argv[])
{
    struct timeval t1, t2;
    gettimeofday(&t1, NULL);

    printf("unsorted array = ");
    print();

    QuickSort(a, 0, N -1);

    printf("main sorted array = ");
    print();

    gettimeofday(&t2, NULL);

    printf("t2: sec=%ld usec=%ld\n", t2.tv_sec, t2.tv_usec);
    printf("t1: sec=%ld usec=%ld\n", t1.tv_sec, t1.tv_usec);
    printf("usec used = %ld\n", t2.tv_usec - t1.tv_usec);
}