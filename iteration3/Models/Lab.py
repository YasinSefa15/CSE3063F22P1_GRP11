import json
import math
import random
from typing import List

from iteration3.Models.Course import Course


class Lab(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester,
                 pre_requisite_courses, weekly_hours):
        super().__init__(name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours)
        self.__section_number = self._generate_section_number()

    def _generate_section_number(self):
        return math.floor(random.random() * 4 + 1)

    def get_credit(self):
        return self.__credit

    def set_section_number(self, section_number: int):
        self.section_number = section_number

    def to_json(self) -> json:
        return None
