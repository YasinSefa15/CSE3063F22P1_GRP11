import json
from typing import List

from iteration3.Models.Course import Course


class Mandatory(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours):
        super().__init__(name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours)


    def to_json(self) -> json:
        return super().to_json()
