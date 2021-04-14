using System;
using System.Collections.Generic;
using ZombieHW.Managers;

namespace ZombieHW
{
    internal class Program
    {
        static void Main(string[] args)
        {
            GameObjectManager objectManager = new GameObjectManager();
            GameEventManager eventManager = new GameEventManager(objectManager.enemies);

            string input = "";

            while (input != "Exit")
            {
                printMenu();
                input = Console.ReadLine();
                if (input == "Exit")
                {
                    return;
                }

                int res;
                if (!int.TryParse(input, out res))
                {
                    Console.WriteLine("That is not a number or \"Exit\", try again");
                }
                switch (res)
                {
                    case 1:
                        Console.WriteLine("Which kind?");
                        createEnemy(objectManager);
                        Console.WriteLine(objectManager.printZombieInfo());
                        break;
                    case 2:
                        Console.WriteLine("Which type of plant?");
                        Console.WriteLine("1. Peashooter (25 damage)");
                        Console.WriteLine("2. Watermelon (40 damage from above)");
                        Console.WriteLine("3. Magnet (0 damage)");
                        input = Console.ReadLine();
                        int damage;
                        if (!int.TryParse(input, out damage))
                        {
                            Console.WriteLine("That is not a number, try again");
                        }

                        eventManager.simulateCollisionDetection(damage);
                        Console.WriteLine(objectManager.printZombieInfo());
                        break;
                }
            }
        }
        static void printMenu()
        {
            Console.WriteLine("1. Create Zombies?");
            Console.WriteLine("2. Demo game play?\n");
        }

        static void createEnemy(GameObjectManager objectManager)
        {
            List<String> zombieTypes = new List<string>() { "Regular", "Cone", "Bucket", "ScreenDoor" };
            for (int x = 0; x < zombieTypes.Count; x++)
            {
                Console.WriteLine((x + 1) + ". " + zombieTypes[x]);
            }

            string input = Console.ReadLine();
            int res;
            while(!int.TryParse(input, out res))
            {
                Console.WriteLine("That is not a number, try again");
                input = Console.ReadLine();
            }

            objectManager.CreateNewZombie(zombieTypes[res - 1]);

        }


    }
}
