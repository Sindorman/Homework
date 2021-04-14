using ZombieHW.Zombie;

namespace ZombieHW
{
    internal class ZombieFactory
    {
        internal static ZombieClass CreateZombie(string type, IDeathObserver observer)
        {
            switch(type)
            {
                case "Regular":
                    RegularZombie zombie = new RegularZombie(50);
                    zombie.Attach(observer);
                    return zombie;
                default:
                    return ZombieWithAccessoryFactory.CreateZombie(type, observer);
            }
        }
    }
}
