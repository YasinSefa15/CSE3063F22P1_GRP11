from iteration3.Controllers.Controller import Controller
from iteration3.Controllers.RandomizationController import RandomizationController
from iteration3.Models.Advisor import Advisor


class LabelingController(Controller):
    def __init__(self):
        super().__init__()
        self.__advisors = []

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
        advisors = []
        read_advisors = self.read_json_files("Advisors")
        for read_advisor in read_advisors:
            self.__advisors.append(Advisor(
                read_advisor["name"],
                read_advisor["surname"],
                read_advisor["ssn"],
                read_advisor["gender"],
                read_advisor["students"],
                None
            ))
        return self.__advisors
    def get_advisors(self):
        return self.__advisors

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
