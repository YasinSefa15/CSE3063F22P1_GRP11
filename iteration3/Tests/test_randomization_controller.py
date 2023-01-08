import unittest

from iteration3.Controllers.LabelingController import LabelingController
from iteration3.Controllers.RandomizationController import RandomizationController


class TestRandomizationController(unittest.TestCase):
    def setUp(self):
        self.advisors = ['Çiğdem Eroğlu Erdem', 'Müjdat Soytürk']
        self.curriculums = ['Digital Logic Design', 'Modeling and Discrete Simulation']
        self.controller = RandomizationController(self.advisors, self.curriculums)

    def test_generate_students(self):
        students = self.controller.generate_students(5, 1)
        self.assertEqual(len(students), 5)
        for student in students:
            self.assertIsInstance(student, Student)

    def test_generate_transcript(self):
        self.assertTrue(self.controller.generate_transcript())

    def test_choose_advisor(self):
        self.assertIn(self.controller.choose_advisor(), self.advisors)

if __name__ == '__main__':
    unittest.main()
