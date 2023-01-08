import json
from decimal import Decimal
from random import random

from iteration3.Models.Model import Model


class Transcript(Model):

    def __init__(self, gpa: float, completed_credit: int, completed_courses: [], failed_courses: []):
        super().__init__()
        self.__gpa = gpa
        self.__completed_credit = completed_credit
        self.__completed_courses = completed_courses
        self.__failed_courses = failed_courses

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
        for i in range(len(self.__completed_courses)):
            gpa += self.__completed_courses[i].get_credit()*letter_grade_dict[
                self.__completed_courses[i].get_letter_grade()]
            credit += self.__completed_courses[i].get_credit()
        for i in range(len(self.__failed_courses)):
            gpa += self.__failed_courses[i].get_credit()*letter_grade_dict[
                self.__failed_courses[i].get_letter_grade()]
            credit += self.__failed_courses[i].get_credit()
        self.__gpa = gpa / credit

    def get_gpa(self):
        return self.__gpa

    def set_gpa(self, gpa: float):
        if gpa >= 0:
            self.__gpa = gpa

    def get_completed_credit(self):
        return self.__completed_credit

    def set_completed_credit(self, completed_credit: int):
        if completed_credit >= 0:
            self.__completed_credit = completed_credit

    def get_completed_courses(self):
        return self.__completed_courses

    def set_completed_courses(self, input_completed_courses: []):
        for i in range(len(input_completed_courses)):
            for j in range(len(self.__completed_courses)):
                if input_completed_courses[i].get_code() != self.__completed_courses[j].get_code():
                    self.__completed_courses.append(input_completed_courses[i])

    def get_failed_courses(self):
        return self.__failed_courses

    def set_failed_courses(self, input_failed_courses: []):
        for i in range(len(input_failed_courses)):
            for j in range(len(self.__failed_courses)):
                if input_failed_courses[i].get_code() != self.__failed_courses[j].get_code():
                    self.__failed_courses.append(input_failed_courses[i])

    def add_to_completed_courses(self, course):
        if course not in self.__completed_courses:
            self.__completed_courses.append(course)

    def add_to_failed_courses(self, course):
        if course not in self.__failed_courses:
            self.__failed_courses.append(course)

    def convert_arraylist_to_json_array(self, array_list: []):
        json_array = []
        for i in range(len(array_list)):
            json_array.append(array_list[i].to_json())
        return json_array

    def to_json(self):
        json_object = {}
        json_object['gpa'] = format(self.__gpa, '##.##')
        json_object['completedCredit'] = self.__completed_credit
        json_object['completedCourses'] = self.convert_arraylist_to_json_array(self.__completed_courses)
        json_object['failedCourses'] = self.convert_arraylist_to_json_array(self.__failed_courses)
        return json_object
