from iteration3.Controllers.Controller import Controller
from faker import Faker


class RandomizationController(Controller):
    def __init__(self):
        super().__init__()
        self.fake = Faker("tr_TR")

    def generate_students(self, num_students):
        for i in range(num_students):
            """print(self.fake.first_name())
            print(self.fake.last_name())"""
        print("--RC-->Generated " + str(num_students) + " students")

    def generate_transcript(self):
        print("--RC-->Generated transcript for student id xxx")

    def choose_advisor(self):
        print("--RC-->Chosen advisor id xxx for student id xxx")