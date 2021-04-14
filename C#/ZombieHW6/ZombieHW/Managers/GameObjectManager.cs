using System.Collections.Generic;
using System.Text;
using ZombieHW.Zombie;

namespace ZombieHW.Managers
{
    // concrete observer
    class GameObjectManager : IDeathObserver
    {
        //Keep track of all the enemies. 
        //Accessed by GameEventManger when calculating collision.
        //The List here is filled when the user creates the Zombies.
        public List<ZombieClass> enemies = new List<ZombieClass>() { };

        public void CreateNewZombie(string type)
        {
            ZombieClass zombie = ZombieFactory.CreateZombie(type, this);
            this.enemies.Add(zombie);
        }

        void IDeathObserver.Update(ZombieClass subject, ZombieClass newSubject)
        {
            subject.Detach(this);
            enemies.Remove(subject);

            if (newSubject != null)
            {
                newSubject.Attach(this);
                enemies.Add(newSubject);
            }
        }

        public string printZombieInfo()
        {
            if (this.enemies.Count == 0)
            {
                return "[]";
            }
            StringBuilder info = new StringBuilder("[");
            for (int x = 0; x < this.enemies.Count - 1; x++)
            {
                ZombieClass zombie = this.enemies[x];
                info.Append(zombie.getInfo() + ", ");
            }

            info.Append(this.enemies[this.enemies.Count - 1].getInfo() + "]");

            return info.ToString();
        }
    }
}
