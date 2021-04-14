using System;
using System.Collections.Generic;

namespace ZombieHW
{
    internal class Program
    {
        static void Main(string[] args)
        {
            string input = "";
            ZombieList zombies = new ZombieList();
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
                        zombies.addZombie(createEnemy());
                        zombies.printZombieInfo();
                        break;
                    case 2:
                        Console.WriteLine("How much, 25 or 40?");
                        Console.WriteLine("1. 25");
                        Console.WriteLine("2. 40");
                        input = Console.ReadLine();
                        int damage;
                        if (!int.TryParse(input, out damage))
                        {
                            Console.WriteLine("That is not a number, try again");
                        }

                        IZombie zombie = zombies;
                        switch(damage)
                        {
                            case 1:
                                zombie.TakeDamage(25);
                                zombies.printZombieInfo();
                                break;
                            case 2:
                                zombie.TakeDamage(40);
                                zombies.printZombieInfo();
                                break;
                        }
                        
                        break;
                }
            }
        }
        static void printMenu()
        {
            Console.WriteLine("1. Create Zombies?");
            Console.WriteLine("2. Demo game play?\n");
        }

        static IZombie createEnemy()
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

            return ZombieFactory.CreateZombie(zombieTypes[res - 1]);

        }


    }
}
