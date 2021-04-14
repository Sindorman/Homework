using System;
using System.Collections.Generic;
using ZombieHW.Zombie;

namespace ZombieHW
{
    // concrete component and concrete subject
    internal class RegularZombie : ZombieClass
    {
        private int health;

        public RegularZombie(int health)
        {
            this.health = health;
        }

        public override void applyMagnet()
        {
            return;
        }

        public override void die()
        {
            this.Notify();
        }

        public override int getHealth()
        {
            return this.health;
        }

        public override string getInfo()
        {
            return "R/" + this.health;
        }

        public override void TakeDamage(int damage)
        {
            this.health = Math.Max(this.health - damage, 0);

            if (this.health <= 0)
            {
                this.die();
            }
        }

        public override void TakeDamageFromAbove(int damage)
        {
            this.TakeDamage(damage);
        }

        public override void Notify()
        {
            for (int x = 0; x < this.observers.Count; x++)
            {
                IDeathObserver observer = this.observers[x];
                observer.Update(this, null);
            }
        }
    }
}
