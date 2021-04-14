using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW
{
    internal class ZombieList : IZombie
    {
        private List<IZombie> zombies = new List<IZombie>() { };

        IZombie IZombie.die()
        {
            throw new NotImplementedException();
        }

        int IZombie.getHealth()
        {
            int health = 0;
            foreach(IZombie zombie in this.zombies)
            {
                health += zombie.getHealth();
            }

            return health;
        }

        void IZombie.TakeDamage(int damage)
        {
            if (this.zombies.Count == 0)
            {
                return;
            }

            IZombie zombie = this.zombies[0];
            zombie.TakeDamage(damage);

            if (zombie.getHealth() <= 0)
            {
                IZombie newZombie = zombie.die();
                if (newZombie != null)
                {
                    this.addZombie(newZombie);
                }
                this.zombies.Remove(zombie);
            }
        }

        internal void addZombie(IZombie zombie)
        {
            this.zombies.Add(zombie);
        }

        internal void removeZombie(IZombie zombie)
        {
            this.zombies.Remove(zombie);
        }

        internal void printZombieInfo()
        {
            if (this.zombies.Count == 0)
            {
                Console.WriteLine("[]");
                return;
            }
            StringBuilder info = new StringBuilder("[");
            for(int x = 0; x < this.zombies.Count - 1; x++)
            {
                IZombie zombie = this.zombies[x];
                info.Append(zombie.getInfo() + ", ");
            }

            info.Append(this.zombies[this.zombies.Count - 1].getInfo() + "]");

            Console.WriteLine(info);
        }

        string IZombie.getInfo()
        {
            return "";
        }
    }
}
