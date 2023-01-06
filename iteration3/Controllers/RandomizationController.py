import random

from iteration3.Controllers.Controller import Controller
from faker import Faker

from iteration3.Models.Student import Student


class RandomizationController(Controller):
    def __init__(self):
        super().__init__()
        self.fake = Faker("tr_TR")

    def generate_students(self, num_students):
        students = []
        for i in range(num_students):
            semester_no = self.fake.random_int(min=1, max=8)
            students.append(Student(
                self.fake.first_name(),
                self.fake.last_name(),
                self.fake.ssn(),
                'm' if random.randint(0, 1) == 0 else 'f',
                self.fake.random_int(min=100000, max=999999),
                self.fake.boolean(),
                self.fake.date_between(start_date='-4y', end_date='today'),
                semester_no,
                None,
                None
            ))
        print(self.fake.ssn())
        print("--RC-->Generated " + str(num_students) + " students")
        return students




    def generate_transcript(self):
        print("--RC-->Generated transcript for student id xxx")

    def choose_advisor(self):
        print("--RC-->Chosen advisor id xxx for student id xxx")
