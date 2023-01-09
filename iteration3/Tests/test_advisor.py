import unittest

from iteration3.Models.ElectiveType import ElectiveType
from iteration3.Models.Advisor import Advisor
from iteration3.Models.Elective import Elective
from iteration3.Models.Mandatory import Mandatory
from iteration3.Models.Student import Student
from iteration3.Models.Transcript import Transcript


class AdvisorTest(unittest.TestCase):

    def setUp(self):
        self.advisorTest = Advisor("", "", "", "", None, None)

    def test_checkSemester(self):
        course1 = Mandatory("CSE1111", "1111", 4, 0, 100, 2, [], [])
        student = Student("", "", "", "", "", False, 2019, 4, None, None)
        course1.set_semester(2)
        self.assertTrue(self.advisorTest.check_semester(student, course1))

        course2 = Mandatory("CSE1112", "1112", 3, 60, 100, 5, [], [])
        course2.set_semester(5)
        print(course2.get_semester())
        self.assertFalse(self.advisorTest.check_semester(student, course2))

    def test_checkQuota(self):
        course = Mandatory("CSE1111", "1111", 4, 0, 100, 2, [], [])
        course.set_quota(100)
        course.set_registered_students_count(99)
        self.assertTrue(self.advisorTest.check_quota(course))

        course.set_registered_students_count(100)
        self.assertFalse(self.advisorTest.check_quota(course))

    def test_checkPreRequisite(self):
        transcript = Transcript(3.24, 60, [], [])
        student = Student("", "", "", "", "", False, 2019, 4, transcript, None)
        course1 = Mandatory("CSE1111", "1111", 4, 0, 100, 2, [], [])
        course2 = Mandatory("CSE2222", "2222", 4, 0, 100, 3, [], [])
        course3 = Mandatory("CSE1112", "1112", 4, 0, 100, 2, [], [])

        pre_requisite_courses = [course3]

        completed_courses = [course1, course3]

        course2.set_registered_students_count(pre_requisite_courses)
        student.transcript.set_completed_courses(completed_courses)

        self.assertTrue(self.advisorTest.check_pre_requisite(student, course2)[0])

    def test_checkCredit(self):
        transcript = Transcript(3.40, 60, [], [])
        transcript.set_completed_credit(60)
        student = Student("", "", "", "", "", False, 2019, 4, transcript, None)
        student.set_transcript = transcript
        course1 = Mandatory("CSE1111", "1111", 6, 0, 100, 2, [], [])
        course1.set_required_credits(0)
        course2 = Elective("CSE4111", "4111", 5, 180, 45, 7, [], [], ElectiveType.FTE)
        course2.set_required_credits(180)
        print(student.transcript.get_completed_credit())
        print(course1.get_required_credits())
        print(course2.get_required_credits())
        self.assertTrue(self.advisorTest.check_credit(student, course1))
        self.assertFalse(self.advisorTest.check_credit(student, course2))

    def test_checkCollision(self):
        student = Student("", "", "", 'M', "", False, 2019, 4, None, None)
        course1 = Mandatory("CSE1111", "1111", 4, 0, 100, 2, [], [])
        course2 = Mandatory("CSE2222", "2222", 4, 0, 100, 3, [], [])

        weeklyHours1 = ["2.3", "2.4"]
        weeklyHours2 = ["2.4", "2.5"]
        course1.set_weekly_hours(weeklyHours1)
        course2.set_weekly_hours(weeklyHours2)

        course_of_hash_test = {course1: True, course2: True}

        student.set_selected_courses = course_of_hash_test

        self.assertEqual(False, self.advisorTest.check_collision(student)[0])


if __name__ == '__main__':
    unittest.main()
