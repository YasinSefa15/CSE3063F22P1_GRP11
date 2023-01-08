import json
from typing import List

from iteration3.Models.Course import Course


class Mandatory(Course):
    def __init__(self, name: str, code: str, credit: int, required_credits: int, quota: int, semester: int,
                 pre_requisite_courses: List[Course], weekly_hours: List[str]) -> None:
        super().__init__()
        self.__name = name
        self.__code = code
        self.__credit = credit
        self.__required_credits = required_credits
        self.__quota = quota
        self.__semester = semester
        self.__pre_requisite_courses = pre_requisite_courses
        self.__weekly_hours = weekly_hours

    def get_credit(self):
        return self.__credit
    def to_json(self) -> json:
        return super().to_json()
