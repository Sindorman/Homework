namespace ZombieHW
{
    internal interface IZombie
    {
        internal void TakeDamage(int damage);
        internal IZombie die();

        internal int getHealth();

        internal string getInfo();
    }
}
