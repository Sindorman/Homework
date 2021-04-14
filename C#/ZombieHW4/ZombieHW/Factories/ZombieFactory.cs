using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW
{
    internal class ZombieFactory
    {
        internal static IZombie CreateZombie(string type)
        {
            switch(type)
            {
                case "Regular":
                    return new RegularZombie(50);
                default:
                    return ZombieWithAccessoryFactory.CreateZombie(type);
            }
        }
    }
}
