using System;
using System.Collections.Generic;
using System.Text;

namespace ZombieHW.Managers
{
    class GameEventManager
    {
        private List<ZombieClass> enemies;

        public GameEventManager(List<ZombieClass> enemies)
        {
            this.enemies = enemies;
        }

        //Called when collision is detected between a Bullet b and an Enemy e
        //In a real implementation, the operation will get the damage from the //Bullet b. For instance:
        //public void doDamage(Bullet b, Enemy e)
        //{
        //    int damage = b.getDamage();
        //    e.takeDamage(damage);
        //}

        //For this test, simply pass in the damage of the Peashooter/Watermelon
        //to the following operation (25 or 30, specifically)
        public void doDamage(int d, ZombieClass e)
        {
            e.TakeDamage(d);
        }

        public void doDamageFromAbove(int d, ZombieClass e)
        {
            e.TakeDamageFromAbove(d);
        }

        //Called when "collision" is detected between
        //a magnet-shroom and an Enemy e
        //i.e, when the user select the magnet-shroom attack.
        public void applyMagnetForce(ZombieClass e)
        {
            //If it's a metal accessory, we need to remove the “metal” accessory //from e. How? It’s up to you

            //TODO: complete this method.
            e.applyMagnet();
        }

        //To separate the responsibilities, the above methods should not 
        //be called directly from your code handling user-interaction. 
        //Instead, it should be done in this “hub” operation in the control 
        //class. Since we are simulating, pass an “int” to represent the plant. 
        public void simulateCollisionDetection(int plant)
        {
            //The method gets access to the “enemies” list in GameObjectManager
            //and finds the first Enemy to be the one to collide with.  
            //Then, it passes e to one of the functions above.
            if (this.enemies.Count == 0)
            {
                return;
            }

            ZombieClass zombie = enemies[0];

            switch(plant)
            {
                case 1:
                    this.doDamage(25, zombie);
                    break;
                case 2:
                    this.doDamageFromAbove(40, zombie);
                    break;
                case 3:
                    this.applyMagnetForce(zombie);
                    break;
            }
        }
    }
}
