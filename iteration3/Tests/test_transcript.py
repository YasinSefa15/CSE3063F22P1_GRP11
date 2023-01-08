import unittest
from iteration3.Models.Mandatory import Mandatory
from iteration3.Models.Transcript import Transcript


class TestTranscript(unittest.TestCase):

    def test_init(self):
        t = Transcript(3.5, 120, [], [])
        assert t.get_gpa() == 3.5
        assert t.get_completed_credit() == 120
        assert t.get_completed_courses() == []
        assert t.get_failed_courses() == []

    def test_calculate_gpa(self):
        t1 = Transcript(4, 120, [], [])
        self.assertEqual(t1.get_gpa(), 4)

        t2 = Transcript(4, 150, [], [])
        course = Mandatory("CSE1000", "1000", 6, 2, 100, 1, [], [])
        course.set_letter_grade("AA")
        t2.add_to_completed_courses(course)
        t2.calculate_gpa()
        self.assertEqual(t2.get_gpa(), 4.0)

        t3 = Transcript(0, 120, [], [])
        course1 = Mandatory("CSE1001", "1001", 3, 2, 100, 1, [], [])
        course2 = Mandatory("CSE1002", "1002", 3, 2, 100, 1, [], [])
        course3 = Mandatory("CSE1003", "1003", 4, 2, 100, 1, [], [])
        course1.set_letter_grade("CC")
        course2.set_letter_grade("DD")
        course3.set_letter_grade("FF")
        t3.add_to_completed_courses(course1)
        t3.add_to_completed_courses(course2)
        t3.add_to_completed_courses(course3)
        t3.calculate_gpa()
        self.assertEqual(t3.get_gpa(), 0.9)

    def test_set_gpa(self):
        t4 = Transcript(3.5, 120, [], [])
        t4.set_gpa(4.0)
        assert t4.get_gpa() == 4.0

    def test_set_gpa_negative(self):
        t5 = Transcript(3.5, 120, [], [])
        t5.set_gpa(-1)
        assert t5.get_gpa() == 3.5

    def test_set_completed_credit(self):
        t6 = Transcript(3.5, 120, [], [])
        t6.set_completed_credit(150)
        assert t6.get_completed_credit() == 150

    def test_set_completed_credit_negative(self):
        t7 = Transcript(3.5, 120, [], [])
        t7.set_completed_credit(-1)
        assert t7.get_completed_credit() == 120

    def test_set_completed_courses(self):
        t8 = Transcript(3.5, 120, [], [])
        course9 = Mandatory("CSE1005", "1005", 4, 2, 100, 1, [], [])
        t8.add_to_completed_courses(course9)
        course10 = Mandatory("CSE1005", "1005", 4, 2, 100, 1, [], [])
        temp_courses = t8.get_completed_courses()
        courses = temp_courses
        courses.append(course10)
        t8.set_completed_courses(courses)
        self.assertEqual(t8.get_completed_courses(), courses)

    def test_set_failed_courses(self):
        t9 = Transcript(3.5, 120, [], [])
        course = Mandatory("CSE1006", "1006", 4, 2, 100, 1, [], [])
        t9.add_to_failed_courses(course)
        course2 = Mandatory("CSE1006", "1006", 4, 2, 100, 1, [], [])
        temp_courses = t9.get_failed_courses()
        courses = temp_courses
        courses.append(course2)
        t9.set_failed_courses(courses)
        self.assertEqual(t9.get_failed_courses(), courses)

    def test_add_to_completed_courses(self):
        t10 = Transcript(3.5, 120, [], [])
        course = Mandatory("CSE1008", "1008", 4, 2, 100, 1, [], [])
        course.set_letter_grade("AA")
        t10.add_to_completed_courses(course)
        assert t10.get_completed_courses() == [course]

    def test_add_to_failed_courses(self):
        t11 = Transcript(3.5, 120, [], [])
        course = Mandatory("CSE1009", "1009", 5, 2, 100, 1, [], [])
        course.set_letter_grade("AA")
        t11.add_to_failed_courses(course)
        assert t11.get_failed_courses() == [course]
