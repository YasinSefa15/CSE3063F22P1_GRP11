import json
from typing import List

class Mandatory(Course):
    def __init__(self, name: str, code: str, credit: int, required_credits: int, quota: int, semester: int, pre_requisite_courses: List[Course], weekly_hours: List[str]) -> None:
        self.name = name
        self.code = code
        self.credit = credit
        self.required_credits = required_credits
        self.quota = quota
        self.semester = semester
        self.pre_requisite_courses = pre_requisite_courses
        self.weekly_hours = weekly_hours

    def to_json(self) -> json:
        return super().to_json()
