import json
import os

from iteration3.Controllers.Controller import Controller
from iteration3.Controllers.RandomizationController import RandomizationController
from iteration3.Controllers.SimulationController import SimulationController

from iteration3.Models.Advisor import Advisor
from iteration3.Models.Curriculum import Curriculum
from iteration3.Models.Mandatory import Mandatory
from iteration3.Models.RegistrationError import RegistrationError


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
        simulation_controller.error = RegistrationError()
        simulation_controller.start_simulation()

    def init_objects(self):
        print("--LC-->Initializing objects")
        self.init_advisors()
        self.init_curriculums()
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
                self.error
            ))
        return self.__advisors

    def init_curriculums(self):
        print("--LC-->Initializing courses and curriculums")
        curriculums = self.read_json_files("Curriculum")[0]

        for key in enumerate(curriculums):
            self.__curriculums.append(Curriculum(
                key[1],
                self.read_curriculum_json(curriculums[key[1]])
            ))

    def read_curriculum_json(self, curriculum):
        print("--LC-->Reading curriculum json")
        courses = []
        for key in enumerate(curriculum):
            read = key[1]
            if read["courseType"] == 0:
                courses.append(Mandatory(
                    read["name"],
                    read["code"],
                    read["credit"],
                    read["requiredCredits"],
                    read["quota"],
                    read["semester"],
                    [],  # todo : önkoşul dersler eklenecek
                    read["weeklyHours"],
                ))

        return courses

    def init_students(self):
        print("--LC-->Initializing students")
        randomization_controller = RandomizationController(self.__advisors, self.__curriculums)
        randomization_controller.error = self.error
        requested_path = os.getcwd() + '/Data/Input/parameters.json'
        parameters = []
        try:
            with open(requested_path) as f:
                obj = json.load(f)
                jo = json.loads(json.dumps(obj))
                parameters.append(jo)
        except Exception as e:
            print(e)
        self.__students = randomization_controller.generate_students(parameters[0]["student_count"],
                                                                     parameters[0]["semester"])
        return self.__students

    @property
    def advisors(self):
        return self.__advisors

    @property
    def students(self):
        return self.__students
