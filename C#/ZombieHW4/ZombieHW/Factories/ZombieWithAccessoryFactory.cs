using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW
{
    internal class ZombieWithAccessoryFactory
    {
        internal static IZombie CreateZombie(string type)
        {
            switch (type)
            {
                case "Cone":
                    return new ZombieWithAccessory(25, "C");
                case "Bucket":
                    return new ZombieWithAccessory(100 ,"B");
                case "ScreenDoor":
                    return new ZombieWithAccessory(25, "S");
                default:
                    throw new ArgumentException("the type " + type + " is not supported!");
            }
        }
    }
}
