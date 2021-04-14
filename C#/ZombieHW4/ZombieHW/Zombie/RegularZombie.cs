using System;

namespace ZombieHW
{
    internal class RegularZombie : IZombie
    {
        private int health;

        public RegularZombie(int health)
        {
            this.health = health;
        }

        IZombie IZombie.die()
        {
            return null;
        }

        int IZombie.getHealth()
        {
            return this.health;
        }

        string IZombie.getInfo()
        {
            return "R/" + this.health;
        }

        void IZombie.TakeDamage(int damage)
        {
            this.health = Math.Max(this.health - damage, 0);
        }
    }
}
