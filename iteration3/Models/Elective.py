from iteration3.Models.Course import Course
from iteration3.Models.ElectiveType import ElectiveType


class Elective(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours,
                 type: ElectiveType):
        super().__init__(name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours)
        self.__type = type

    def set_type(self, type):
        self.__type = type

    def get_type(self):
        return self.__type

    def get_credit(self):
        return self.__credit

    def to_json(self):
        return None
