extern PROC proc[NPROC], *running, *freeList, *readyQueue, *sleepList;

/********* Required PRE-work ***********/
// 1. Modify kfork() to implement process tree as a BINARY tree

// 2. Implement ksleep() per the algorithm in 3.4.1
int ksleep(int event)
{
    running->event = event;
    running->status = SLEEP;
    enqueue(&sleepList, running);
    tswitch();
}

// 2. Implement kwakeup per the algorithm in 3.4.2
int kwakeup(int event)
{
    PROC *q = sleepList;
    PROC *q_prev = NULL;
    while(q)
    {
        if(q->event == event)
        {
            if(q_prev == NULL)
            {
                q_prev = q->next;
            }
            else
            {
                q_prev->next = q->next;
            }
            q->status = READY;
            enqueue(&readyQueue, q);
        }
        q_prev = q;
        q = q->next;
    }
}

// 4. Modify kexit() to give away children to P1:

int kexit(int value)
{
  // give away children, if any, to P1
  PROC *p; int i;
  for (i=0; i<NPROC; i++)
  {
      p = &proc[i];
      if(p->pid == 1)
      {
        if(running->child != NULL) enqueue(&(running->sibling), running->child);
        enqueue(&(p->sibling), (running->sibling));
      }
  }
  running->exitCode = value;
  running->status = ZOMBIE;
  kwakeup(running->parent);
  tswitch();
}

// 3. Implement kwait() per the algorithm in 3.5.3
int kwait(int *status)
{
    if (running->child ==  NULL) return -1;
    while(1)
    {
        PROC *zombie = NULL, *p = running->sibling;
        int child = 0; //figure out if it is a child or a sibling
        if(running->child->status == 4)
        {
            child = 1;
            zombie = running->child;
        } 
        while(p && zombie == NULL)
        {
            if(p->status == 4)
            {
              zombie = p;
              break;
            }
            p = p->next;
        }
        if (zombie != NULL)
        {
            int z_pid = zombie->pid;
            status = zombie->exitCode;
            removeFromQueue(&readyQueue, z_pid);
            enqueue(&freeList, zombie);
            if(child == 0)
            {
                removeFromQueue(&(running->sibling), z_pid);
            }
            else
            {
                int t_pid = running->sibling->pid;
                running->child = running->sibling;
                removeFromQueue(&(running->sibling), t_pid);
            }
            printf("Put %d into freeList\n", z_pid);
            return z_pid;
        }

        ksleep(running); // sleep on its PROC address
    }
}
