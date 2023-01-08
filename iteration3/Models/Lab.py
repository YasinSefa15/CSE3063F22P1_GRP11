import json
import math
import random
from typing import List

from iteration3.Models.Course import Course


class Lab(Course):
    def __init__(self, name: str, code: str, credit: int, required_credits: int, quota: int, semester: int, pre_requisite_courses: List[Course], weekly_hours: List[str]) -> None:
        self.__name = name
        self.__code = code
        self.__credit = credit
        self.__required_credits = required_credits
        self.__quota = quota
        self.__semester = semester
        self.__pre_requisite_courses = pre_requisite_courses
        self.__weekly_hours = weekly_hours
        self.__section_number = self._generate_section_number()

    def _generate_section_number(self) -> int:
        return int(math.floor(random.random() * 4 + 1))

    def set_section_number(self, section_number: int) -> None:
        self.section_number = section_number

    def to_json(self) -> json:
        return None

