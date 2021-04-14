using System;
using ZombieHW.Zombie;

namespace ZombieHW
{
    internal class ZombieWithAccessoryFactory
    {
        internal static ZombieAccessoryDecorator CreateZombie(string type, IDeathObserver observer)
        {
            ZombieAccessoryDecorator zombie;
            switch (type)
            {
                case "Cone":
                    zombie = new ZombieAccessoryDecorator(25, "C");
                    zombie.Attach(observer);
                    return zombie;
                case "Bucket":
                    zombie = new ZombieAccessoryDecorator(100, "B");
                    zombie.Attach(observer);
                    return zombie;
                case "ScreenDoor":
                    zombie = new ZombieAccessoryDecorator(25, "S");
                    zombie.Attach(observer);
                    return zombie;
                default:
                    throw new ArgumentException("the type " + type + " is not supported!");
            }
        }
    }
}
