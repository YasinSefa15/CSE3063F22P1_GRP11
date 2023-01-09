import unittest

from iteration3.Models.Curriculum import Curriculum
from iteration3.Models.Mandatory import Mandatory
class CurriculumTest(unittest.TestCase):
    def setUp(self):
        self.curriculum = Curriculum("", None)

    def test_course_by_semester(self):
        # Test with empty list of courses
        result = self.curriculum.course_by_semester(1)
        self.assertEqual(result, [])

        # Test with courses in different semesters
        course1 = Mandatory('Computer Programming 1', '1001')
        course2 = Mandatory('Computer Programming 2', '1002')
        self.curriculum.add_course_to_semester(course1)
        self.curriculum.add_course_to_semester(course2)
        result = self.curriculum.course_by_semester(1)
        self.assertEqual(result, [course1])
        result = self.curriculum.course_by_semester(2)
        self.assertEqual(result, [course2])

    def test_add_course_to_semester(self):
        # Test adding course to new semester
        course1 = Mandatory('Computer Programming 1', '1001')
        result = self.curriculum.add_course_to_semester(course1)
        self.assertTrue(result)
        self.assertEqual(self.curriculum.courses, {1: [course1]})

        # Test adding course to existing semester
        course2 = Mandatory('Computer Programming 2', '1002')
        result = self.curriculum.add_course_to_semester(course2)
        self.assertTrue(result)
        self.assertEqual(self.curriculum.courses, {1: [course1, course2]})


if __name__ == '__main__':
    unittest.main()
