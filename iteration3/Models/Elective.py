from iteration3.Models.Course import Course


class Elective(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours, type):
        self.__name = name
        self.__code = code
        self.__credit = credit
        self.__required_credits = required_credits
        self.__quota = quota
        self.__semester = semester
        self.__pre_requisite_courses = pre_requisite_courses
        self.__weekly_hours = weekly_hours
        self.__type = type

    def set_type(self, type):
        self.type = type

    def get_type(self):
        return self.type

    def to_json(self):
        return None
