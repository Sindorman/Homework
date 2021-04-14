using System;
using System.Collections.Generic;

namespace ZombieHW.Zombie
{
    // Zombie accessory decorator and subject for the observer
    internal class ZombieAccessoryDecorator : ZombieDecorator
    {
        private int accessoryHealth;
        private string type;

        public ZombieAccessoryDecorator(int health, string type)
        {
            this.accessoryHealth = health;
            this.type = type;
            this.originalZombie = new RegularZombie(50);
        }
        public override void die()
        {
            if (this.originalZombie == null || this.originalZombie.getHealth() <= 0)
            {
                this.originalZombie = null;
                this.Notify();
                return;
            }
            else if (this.accessoryHealth <= 0 && this.originalZombie.getHealth() > 0)
            {
                this.originalZombie.TakeDamage(Math.Abs(this.accessoryHealth));
                this.Notify();
                return;
            }
        }

        public override int getHealth()
        {
            return this.accessoryHealth + this.originalZombie.getHealth();
        }

        public override string getInfo()
        {
            return this.type + "/" + (this.accessoryHealth + this.originalZombie.getHealth());
        }

        public override void TakeDamage(int damage)
        {
            this.accessoryHealth = this.accessoryHealth - damage;
            this.die();
        }

        public override void TakeDamageFromAbove(int damage)
        {
            if (type == "S")
            {
                this.originalZombie.TakeDamage(damage);
                this.die();
            }
            else
            {
                this.TakeDamage(damage);
            }
        }

        public override void applyMagnet()
        {
            if (this.type == "S" || this.type == "B")
            {
                this.Notify();
            }
        }

        public override void Notify()
        {
            for (int x = 0; x < this.observers.Count; x++)
            {
                IDeathObserver observer = this.observers[x];
                observer.Update(this, this.originalZombie);
            }
        }
    }
}
