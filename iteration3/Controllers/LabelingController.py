from iteration3.Controllers.Controller import Controller


class LabelingController(Controller):
    def __init__(self, age):
        super().__init__()
        self.__advisors = ["advisor1", "advisor2"]
        self.age = age

    def execute(self):
        print("--->Executing LabelingController")
        self.init_objects()

    def init_objects(self):
        print("->Initializing objects")
        self.init_advisors()
        self.init_courses_and_curriculums()
        """self.init_students()"""

    def init_advisors(self):
        print("->Initializing advisors")
        read_advisors = self.read_json_files("Advisors")

    def init_courses_and_curriculums(self):
        print("->Initializing courses and curriculums")
        curriculums = self.read_json_files("Curriculum")[0]

        for key in enumerate(curriculums):
            print(key[1])

    def init_students(self):
        print("->Initializing students")

    def get_advisors(self):
        return self.__advisors
