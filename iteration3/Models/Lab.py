import json
import math
import random
from typing import List

from iteration3.Models.Course import Course


class Lab(Course):
    def __init__(self, name: str, code: str, credit: int, required_credits: int, quota: int, semester: int, pre_requisite_courses: List[Course], weekly_hours: List[str]) -> None:
        self.name = name
        self.code = code
        self.credit = credit
        self.required_credits = required_credits
        self.quota = quota
        self.semester = semester
        self.pre_requisite_courses = pre_requisite_courses
        self.weekly_hours = weekly_hours
        self.section_number = self._generate_section_number()

    def _generate_section_number(self) -> int:
        return int(math.floor(random.random() * 4 + 1))

    def set_section_number(self, section_number: int) -> None:
        self.section_number = section_number

    def to_json(self) -> json:
        return None

