import random

from iteration3.Controllers.Controller import Controller
from faker import Faker

from iteration3.Models.Student import Student
from iteration3.Models.Transcript import Transcript


class RandomizationController(Controller):
    def __init__(self, advisors, curriculums):
        super().__init__()
        self.fake = Faker("tr_TR")
        self.__advisors = advisors
        self.__curriculums = curriculums
        self.__students = []

    def generate_students(self, num_students, semester):
        students = []
        for i in range(num_students):
            advisor = self.choose_advisor()
            semester_no = self.fake.random_int(min=1, max=8)
            self.__students.append(Student(
                self.fake.first_name(),
                self.fake.last_name(),
                self.fake.ssn(),
                'm' if random.randint(0, 1) == 0 else 'f',
                self.fake.random_int(min=100000, max=999999),
                self.fake.boolean(),
                "2022",
                semester_no,
                self.generate_transcript(self.__curriculums[0], semester_no),
                advisor
            ))
        print("--RC-->Generated " + str(num_students) + " students")
        return students

    def generate_transcript(self, curriculum, semester_no):
        print("--RC-->Generated transcript for student id xxx")
        transcript = Transcript(
            0,
            0,
            [],
            []
        )

        return transcript

    def choose_advisor(self):
        student_count = self.__students.__len__()
        for advisor in self.__advisors:
            if advisor.students.__len__() <= student_count / self.__advisors.__len__():
                return advisor
        return self.__advisors[0]
