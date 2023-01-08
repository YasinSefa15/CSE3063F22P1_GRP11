import unittest

from iteration3.Controllers.LabelingController import LabelingController
from iteration3.Controllers.RandomizationController import RandomizationController


class TestRandomizationController(unittest.TestCase):
    def test_something(self):
        ran_con = RandomizationController()
        self.assertEqual(True, ran_con.choose_advisor())  # add assertion here


if __name__ == '__main__':
    unittest.main()
