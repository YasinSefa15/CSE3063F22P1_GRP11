import json
from decimal import Decimal

from iteration3.Models.Model import Model


class Transcript(Model):

    def __init__(self, gpa:float, completed_credit:int, completed_courses:[], failed_courses:[]):
        self.gpa = gpa
        self.completed_credit = completed_credit
        self.completed_courses = completed_courses
        self.failed_courses = failed_courses

    def calculate_gpa(self):
        letter_grade_dict = {
            "AA": 4.0,
            "BA": 3.5,
            "BB": 3.0,
            "BC": 2.5,
            "CC": 2.0,
            "DC": 1.5,
            "DD": 1.0,
            "FF": 0.0
        }
        gpa = 0
        credit = 0
        for i in range(len(self.completed_courses)):
            gpa += self.completed_courses[i].get_credit() * letter_grade_dict[self.completed_courses[i].get_letter_grade()]
            credit += self.completed_courses[i].get_credit()
        for i in range(len(self.failed_courses)):
            gpa += self.failed_courses[i].get_credit() * letter_grade_dict[self.failed_courses[i].get_letter_grade()]
            credit += self.failed_courses[i].get_credit()
        self.gpa = gpa / credit


    def get_gpa(self):
        return self.gpa

    def set_gpa(self, gpa:float):
        if gpa >= 0:
            self.gpa = gpa

    def get_completed_credit(self):
        return self.completed_credit

    def set_completed_credit(self, completed_credit:int):
        if completed_credit >= 0:
            self.completed_credit = completed_credit

    def get_completed_courses(self):
        return self.completed_courses

    def set_completed_courses(self, completed_courses:[]):
        for i in range(len(completed_courses)):
            for j in range(len(self.completed_courses)):
                if completed_courses[i].get_code() != self.completed_courses[j].get_code():
                    self.completed_courses.add(completed_courses[i])

    def get_failed_courses(self):
        return self.failed_courses

    def set_failed_courses(self, failed_courses:[]):
        for i in range(len(failed_courses)):
            for j in range(len(self.failed_courses)):
                if failed_courses[i].get_code() != self.failed_courses[j].get_code():
                    self.failed_courses.add(failed_courses[i])

    def add_to_completed_courses(self, course):
        if course not in self.completed_courses:
            self.completed_courses.add(course)

    def add_to_failed_courses(self, course):
        if course not in self.failed_courses:
            self.failed_courses.add(course)

    def convert_arraylist_to_json_array(self, array_list:[]):
        json_array = []
        for i in range(len(array_list)):
            json_array.append(array_list[i].to_json())
        return json_array

    def to_json(self):
        json_object = {}
        json_object['gpa'] = format(self.gpa, '##.##')
        json_object['completedCredit'] = self.completed_credit
        json_object['completedCourses'] = self.convert_arraylist_to_json_array(self.completed_courses)
        json_object['failedCourses'] = self.convert_arraylist_to_json_array(self.failed_courses)
        return json_object
