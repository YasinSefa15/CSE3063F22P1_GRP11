import json
from typing import List

from iteration3.Models.Course import Course


class Mandatory(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours):
        super().__init__()
        self.__name = name
        self.__code = code
        self.__credit = credit
        self.__required_credits = required_credits
        self.__quota = quota
        self.__semester = semester
        self.__pre_requisite_courses = pre_requisite_courses
        self.__weekly_hours = weekly_hours

    def to_json(self) -> json:
        return super().to_json()
