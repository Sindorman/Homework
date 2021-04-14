using System.Collections.Generic;
using ZombieHW.Zombie;

namespace ZombieHW
{
    // component
    internal abstract class ZombieClass
    {
        protected List<IDeathObserver> observers = new List<IDeathObserver>() { };

        public abstract void TakeDamage(int damage);

        public abstract void TakeDamageFromAbove(int damage);

        public abstract void die();

        public abstract int getHealth();

        public abstract string getInfo();

        public abstract void applyMagnet();

        public void Attach(IDeathObserver observer)
        {
            this.observers.Add(observer);
        }

        public void Detach(IDeathObserver observer)
        {
            this.observers.Remove(observer);
        }

        public abstract void Notify();
    }
}
