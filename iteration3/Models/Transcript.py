import json
from decimal import Decimal

class Transcript(Model):
    def __init__(self, gpa:float, completedCredit:int, completedCourses:[], failedCourses:[]):
        self.gpa = gpa
        self.completedCredit = completedCredit
        self.completedCourses = completedCourses
        self.failedCourses = failedCourses

    def calculateGPA(self):
        temp_gpa = 0
        total_credit = 0
        course_credit = 0
        for course in self.completedCourses:
            course_credit = course.get_credit()
            total_credit += course_credit
            temp_gpa += course_credit * 4
        for course in self.failedCourses:
            course_credit = course.get_credit()
            total_credit += course_credit
            temp_gpa += course_credit * 0
        self.gpa = temp_gpa / total_credit

    def get_gpa(self):
        return self.gpa

    def set_gpa(self, gpa:float):
        if gpa >= 0:
            self.gpa = gpa

    def get_completed_credit(self):
        return self.completedCredit

    def set_completed_credit(self, completedCredit:int):
        if completedCredit >= 0:
            self.completedCredit = completedCredit

    def get_completed_courses(self):
        return self.completedCourses

    def set_completed_courses(self, completedCourses:[]):
        for i in range(len(completedCourses)):
            for j in range(len(self.completedCourses)):
                if completedCourses[i].get_code() != self.completedCourses[j].get_code():
                    self.completedCourses.add(completedCourses[i])

    def get_failed_courses(self):
        return self.failedCourses

    def set_failed_courses(self, failedCourses:[]):
        for i in range(len(failedCourses)):
            for j in range(len(self.failedCourses)):
                if failedCourses[i].get_code() != self.failedCourses[j].get_code():
                    self.failedCourses.add(failedCourses[i])

    def add_to_completed_courses(self, course):
        if course not in self.completedCourses:
            self.completedCourses.add(course)

    def add_to_failed_courses(self, course):
        if course not in self.failedCourses:
            self.failedCourses.add(course)

    def convert_arraylist_to_json_array(self, array_list:[]):
        json_array = []
        for i in range(len(array_list)):
            json_array.append(array_list[i].to_json())
        return json_array

    def to_json(self):
        json_object = {}
        json_object['gpa'] = format(self.gpa, '##.##')
        json_object['completedCredit'] = self.completedCredit
        json_object['completedCourses'] = self.convert_arraylist_to_json_array(self.completedCourses)
        json_object['failedCourses'] = self.convert_arraylist_to_json_array(self.failedCourses)
        return json_object
