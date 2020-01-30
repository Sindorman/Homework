/****************** queue.c file ********************/
int enqueue(PROC **queue, PROC *p)
{
    PROC *q = *queue;
    if (q == 0 || p->priority > q->priority){
       *queue = p;
        p->next = q;
    }
    else{
       while (q->next && p->priority <= q->next->priority)
          q = q->next;
       p->next = q->next;
       q->next = p;
   }
}

// remove and return first PROC in queue 
PROC *dequeue(PROC **queue)
{
     PROC *p = *queue;
     if (p)
        *queue = (*queue)->next;
     return p;
}

int printList(char *name, PROC *p)
{
   printf("%s = ", name);
   while(p){
     printf("[%d %d]->", p->pid, p->priority);
       p = p->next;
   }
   printf("NULL\n");
}

int *removeFromQueue(PROC **queue, int pid)
{
   PROC *q = *queue;
   PROC *q_prev = NULL;
   while(q)
   {
      if(q->pid == pid)
      {
         if(q_prev == NULL)
         {
            q_prev = q->next;
         }
         else
         {
            q_prev->next = q->next;
         }

         break;
      }
      q_prev = q;
      q = q->next;
   }
}