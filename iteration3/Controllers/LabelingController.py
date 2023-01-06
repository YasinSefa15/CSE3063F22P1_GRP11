import json
import os

from iteration3.Controllers.Controller import Controller
from iteration3.Controllers.RandomizationController import RandomizationController
from iteration3.Controllers.SimulationController import SimulationController

from iteration3.Models.Advisor import Advisor


class LabelingController(Controller):
    def __init__(self):
        super().__init__()
        self.__advisors = []
        self.__students = []
        self.__curriculums = []

    def execute(self):
        print("*-->Executing LabelingController")
        self.init_objects()
        simulation_controller = SimulationController(self.__students, self.__curriculums, self.__advisors)
        simulation_controller.start_simulation()

    def init_objects(self):
        print("--LC-->Initializing objects")
        self.init_advisors()
        self.init_courses_and_curriculums()
        self.init_students()

    def init_advisors(self):
        print("--LC-->Initializing advisors")
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

    def init_courses_and_curriculums(self):
        print("--LC-->Initializing courses and curriculums")
        curriculums = self.read_json_files("Curriculum")[0]

        for key in enumerate(curriculums):
            print(key[1])

    def init_students(self):
        print("--LC-->Initializing students")
        randomization_controller = RandomizationController()
        requested_path = os.getcwd() + '/Data/Input/parameters.json'
        parameters = []
        try:
            with open(requested_path) as f:
                obj = json.load(f)
                jo = json.loads(json.dumps(obj))
                parameters.append(jo)
        except Exception as e:
            print(e)
        self.__students = randomization_controller.generate_students(parameters[0]["student_count"], parameters[0]["semester"])

    @property
    def advisors(self):
        return self.__advisors

    @property
    def students(self):
        return self.__students
