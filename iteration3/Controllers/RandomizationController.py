import json
import os
import random

from iteration3.Controllers.Controller import Controller
from faker import Faker

from iteration3.Models.Student import Student
from iteration3.Models.Transcript import Transcript


class RandomizationController(Controller):
    def __init__(self, advisors, curriculums):
        super().__init__()
        self.fake = Faker()
        self.__advisors = advisors
        self.__curriculums = curriculums
        self.__students = []
        try:
            filelist = [f for f in os.listdir(os.getcwd() + '/Data/Input/Students/')]
            for f in filelist:
                os.remove(os.path.join(os.getcwd() + '/Data/Input/Students/', f))
            filelist = [f for f in os.listdir(os.getcwd() + '/Data/Output/Students/')]
            for f in filelist:
                os.remove(os.path.join(os.getcwd() + '/Data/Output/Students/', f))
        except Exception as e:
            print("An error occurred while deleting .json file.")
            print(e)

    def generate_students(self, num_students, semester_read):
        for i in range(num_students):
            advisor = self.choose_advisor()
            semester = 1 if semester_read == "FALL" else 2
            semester_no = semester + 2 * random.randint(0, 3)
            enter_year_last_two = int(22 - (semester_no / 2))
            self.__students.append(Student(
                self.fake.first_name(),
                self.fake.last_name(),
                self.fake.ssn(),
                'm' if random.randint(0, 1) == 0 else 'f',
                "1501" + str(enter_year_last_two) + str(random.randint(0, 9)) + str(random.randint(0, 9)) + str(random.randint(0, 9)),
                self.fake.boolean(),
                "2022",
                semester_no,
                self.generate_transcript(self.__curriculums[0], semester_no),
                advisor
            ))

            for student in self.__students:
                student.advisor.students.append(student)
                json_object = student.to_json()
                content = json_object
                full_file_name = os.getcwd() + '/Data/Input/Students/' + str(student.id) + '.json'
                try:
                    directory = os.path.dirname(full_file_name)

                    if not os.path.exists(directory):
                        os.makedirs(directory)

                    with open(full_file_name, 'w') as f:
                        json.dump(content, f)
                        # f.write(content)
                except OSError as e:
                    print("An error occurred while exporting .json file.")
                    print(e)
        print("--RC-->Generated " + str(num_students) + " students")
        return self.__students

    def generate_transcript(self, curriculum, semester_no):
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
