import unittest
import final

class TestFinal(unittest.TestCase):
    def test_get_C_max_negative(self):
        self.assertEqual(final.get_C_max([-3, -3, 4]), 6)

    def test_get_C_max_positive(self):
        self.assertEqual(final.get_C_max([-3, -0, 4]), 5)

    def test_convert_to_binary(self):
        self.assertEqual(final.convert_C_to_binary(5), "101")
        self.assertEqual(final.convert_C_to_binary(34), "100010")
        self.assertEqual(final.convert_C_to_binary(18), "10010")

    def test_get_K(self):
        self.assertEqual(final.get_K(5), 3)
        self.assertEqual(final.get_K(34), 6)
        self.assertEqual(final.get_K(18), 5)

    def test_get_bi(self):
        self.assertEqual(final.get_bi(18, 4), 1)
        self.assertEqual(final.get_bi(18, 5), 0)
        self.assertEqual(final.get_bi(18, 6), None)


if __name__ == '__main__':
    unittest.main()