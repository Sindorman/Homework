using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW
{
    internal class ZombieWithAccessory : IZombie
    {
        private int health;
        private string type;

        public ZombieWithAccessory(int health, string type)
        {
            this.health = health;
            this.type = type;
        }
        IZombie IZombie.die()
        {
            if (50 - this.health <= 0)
            {
                return null;
            }
            else
            {
                return new RegularZombie(50 - Math.Abs(this.health));
            }
        }

        int IZombie.getHealth()
        {
            return this.health;
        }

        string IZombie.getInfo()
        {
            return this.type + "/" + (this.health + 50);
        }

        void IZombie.TakeDamage(int damage)
        {
            this.health = this.health - damage;

        }
    }
}
