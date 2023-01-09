import json
import unittest

from iteration3.Models.Course import Course
from iteration3.Models.Mandatory import Mandatory
from iteration3.Models.Person import Person
from iteration3.Models.Student import Student

class TestStudent(unittest.TestCase):
    def setUp(self):
        self.student = Student("John", "Doe", "123-45-6789", "Male", "1234", False, "01/01/2022", 1, None, None)
        self.course1 = Mandatory()
        self.course1.set_name("CSE1111")
        self.course1.set_code("1111")
        self.course1.set_credit(4)
        self.course1.set_required_credits(2)
        self.course1.set_quota(100)
        self.course1.set_semester(1)
        self.course1.set_pre_requisite_courses([])
        self.course1.set_weekly_hours([])
        self.course2 = Mandatory()
        self.course2.set_name("CSE2222")
        self.course2.set_code("2222")
        self.course2.set_credit(5)
        self.course2.set_required_credits(1)
        self.course2.set_quota(80)
        self.course2.set_semester(2)
        self.course2.set_pre_requisite_courses([])
        self.course2.set_weekly_hours([])

    def test_add_error(self):
        self.student.add_error("Error 1")
        self.student.add_error("Error 2")
        self.assertEqual(self.student.errors, ["Error 1", "Error 2"])

    def test_add_to_selected_courses(self):
        self.student.add_to_selected_courses(self.course1, True)
        self.assertEqual(self.student.selected_courses, [[self.course1, True]])

        self.student.add_to_selected_courses(self.course2, False)
        self.assertEqual(self.student.selected_courses, [[self.course1, True],[self.course2,False]])

