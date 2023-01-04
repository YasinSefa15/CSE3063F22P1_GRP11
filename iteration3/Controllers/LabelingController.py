from iteration3.Controllers.Controller import Controller
from iteration3.Controllers.RandomizationController import RandomizationController


class LabelingController(Controller):
    def __init__(self, age):
        super().__init__()
        self.__advisors = ["advisor1", "advisor2"]
        self.age = age

    def execute(self):
        print("*-->Executing LabelingController")
        self.init_objects()

    def init_objects(self):
        print("--LC-->Initializing objects")
        self.init_advisors()
        self.init_courses_and_curriculums()
        self.init_students()

    def init_advisors(self):
        print("--LC-->Initializing advisors")
        read_advisors = self.read_json_files("Advisors")

    def init_courses_and_curriculums(self):
        print("--LC-->Initializing courses and curriculums")
        curriculums = self.read_json_files("Curriculum")[0]

        for key in enumerate(curriculums):
            print(key[1])

    def init_students(self):
        print("--LC-->Initializing students")
        randomization_controller = RandomizationController()
        randomization_controller.generate_students(10)

    def get_advisors(self):
        return self.__advisors
