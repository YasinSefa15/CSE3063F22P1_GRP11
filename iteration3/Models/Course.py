import json
from abc import ABC, abstractmethod

from iteration3.Models.Model import Model


class Course(Model, ABC):
    def __init__(self):
        self.__name = ""
        self.__code = ""
        self.__letter_grade = ""
        self.__credit = 0
        self.__required_credits = 0
        self.__quota = 0
        self.__registered_students_count = 0
        self.__semester = 0
        self.__prerequisite_courses = []
        self.__weekly_hours = []

    def get_name(self):
        return self.__name

    def get_code(self):
        return self.__code

    def get_letter_grade(self):
        return self.__letter_grade

    def get_credit(self):
        return self.__credit

    def get_required_credits(self):
        return self.__required_credits

    def get_quota(self):
        return self.__quota

    def get_semester(self):
        return self.__semester

    def get_registered_students_count(self):
        return self.__registered_students_count

    def get_prerequisite_courses(self):
        return self.__prerequisite_courses

    def get_weekly_hours(self):
        return self.__weeklyHours

    def set_name(self, name):
        self.__name = name

    def set_code(self, code):
        self.__code = code

    def set_letter_grade(self, letter_grade):
        self.__letter_grade = letter_grade

    def set_credit(self, credit):
        self.__credit = credit

    def set_required_credits(self, required_credits):
        self.__required_credits = required_credits

    def set_quota(self, quota):
        self.__quota = quota

    def set_semester(self, semester):
        self.__semester = semester

    def set_registered_students_count(self, registered_students_count):
        self.__registered_students_count = registered_students_count

    def set_pre_requisite_courses(self, pre_requisite_courses):
        self.__prerequisite_courses = pre_requisite_courses

    def set_weekly_hours(self, weekly_hours):
        self.__weekly_hours = weekly_hours

    def add_registered_student(self):
        self.__registered_students_count += 1

    import json

    def to_json(self):
        json_object = {"name": self.name, "code": self.code, "credit": self.credit,
                       "required_credits": self.required_credits, "quota": self.quota,
                       "registeredStudentsCount": self.registered_students_count, "semester": self.semester,
                       "preRequisiteCourses": self.to_json_array(self.prerequisite_courses),
                       "weeklyHours": self.weekly_hours}
        return json.dumps(json_object)

    def to_json_array(self, prerequisite_courses):
        json_array = []

        for course in prerequisite_courses:
            json_array.append(course.to_json())

        return json.dumps(json_array)
