using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW.Zombie
{
    // observer
    interface IDeathObserver
    {
        void Update(ZombieClass subject, ZombieClass newSubject);
    }
}
