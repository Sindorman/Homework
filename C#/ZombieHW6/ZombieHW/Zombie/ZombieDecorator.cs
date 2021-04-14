using System.Collections.Generic;

namespace ZombieHW.Zombie
{
    // abstract decorator class
    internal abstract class ZombieDecorator : ZombieClass
    {
        protected ZombieClass originalZombie;
    }
}
