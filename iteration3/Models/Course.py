import json
from abc import ABC, abstractmethod

from iteration3.Models.Model import Model


class Course(Model, metaclass=ABC):
    def __init__(self):
        self.name = ""
        self.code = ""
        self.letter_grade = ""
        self.credit = 0
        self.required_credits = 0
        self.quota = 0
        self.registered_students_count = 0
        self.semester = 0
        self.prerequisite_courses = []
        self.weekly_hours = []

    def get_name(self):
        return self.name

    def get_code(self):
        return self.code

    def get_letter_grade(self):
        return self.letter_grade

    def get_credit(self):
        return self.credit

    def get_required_credits(self):
        return self.required_credits

    def get_quota(self):
        return self.quota

    def get_semester(self):
        return self.semester

    def get_registered_students_count(self):
        return self.registered_students_count

    def get_prerequisite_courses(self):
        return self.prerequisite_courses

    def get_weekly_hours(self):
        return self.weeklyHours

    def set_name(self, name):
        self.name = name

    def set_code(self, code):
        self.code = code

    def set_letter_grade(self, letter_grade):
        self.letter_grade = letter_grade

    def set_credit(self, credit):
        self.credit = credit

    def set_required_credits(self, required_credits):
        self.required_credits = required_credits

    def set_quota(self, quota):
        self.quota = quota

    def set_semester(self, semester):
        self.semester = semester

    def set_registered_students_count(self, registered_students_count):
        self.registered_students_count = registered_students_count

    def set_pre_requisite_courses(self, pre_requisite_courses):
        self.prerequisite_courses = pre_requisite_courses

    def set_weekly_hours(self, weekly_hours):
        self.weekly_hours = weekly_hours

    def add_registered_student(self):
        self.registered_students_count += 1

    import json

    def to_json(self):
        json_object = {}
        json_object["name"] = self.name
        json_object["code"] = self.code
        json_object["credit"] = self.credit
        json_object["required_credits"] = self.required_credits
        json_object["quota"] = self.quota
        json_object["registeredStudentsCount"] = self.registered_students_count
        json_object["semester"] = self.semester
        json_object["preRequisiteCourses"] = self.to_json_array(self.prerequisite_courses)
        json_object["weeklyHours"] = self.weekly_hours
        return json.dumps(json_object)

    def to_json_array(self, prerequisite_courses):
        json_array = []

        for course in prerequisite_courses:
            json_array.append(course.to_json())

        return json.dumps(json_array)
