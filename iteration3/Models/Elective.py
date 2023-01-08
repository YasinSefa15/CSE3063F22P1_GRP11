class Elective(Course):
    def __init__(self, name, code, credit, required_credits, quota, semester, pre_requisite_courses, weekly_hours, type):
        self.name = name
        self.code = code
        self.credit = credit
        self.required_credits = required_credits
        self.quota = quota
        self.semester = semester
        self.pre_requisite_courses = pre_requisite_courses
        self.weekly_hours = weekly_hours
        self.type = type

    def set_type(self, type):
        self.type = type

    def get_type(self):
        return self.type

    def to_json(self):
        return None
