from typing import List
import iteration3.Controllers.Controller as Controller
import iteration3.Models.Student as Student
import iteration3.Models.Advisor as Advisor
import iteration3.Models.Curriculum as Curriculum
import iteration3.Controllers.StudentController as StudentController


class SimulationController(Controller.Controller):
    def __init__(self, students, advisors, curriculums):
        super().__init__()
        self.__students = students
        self.__curriculums = curriculums
        self.__advisors = advisors
        self.__denied_courses = []

    def start_simulation(self):
        #self.register_students_to_classes()
        self.export_objects()
        # self.print_error_messages()
        pass

    def export_objects(self):
        #for advisor in self.__advisors:
            #self.export_json_file(advisor)
        for student in self.__students:
            #student.transcript.calculate_gpa()
            self.export_json_file(student)
        self.export_json_file(self.error)

    def print_error_messages(self):
        for message in self.error.get_all_error_messages():
            print(message)

    def register_students_to_classes(self):
        student_controller = StudentController()
        student_controller.error = self.error
        self.denied_courses.clear()

        for i in range(len(self.students)):
            student = self.students[i]
            self.denied_courses.clear()
            # if student registered 2020 and later first indexed curriculum will be taken consideration
            student_registers_date_to_curriculum = 1 if student.register_date >= 2020 else 0
            simulation_semester = student.semester_no
            courses = self.curriculums[student_registers_date_to_curriculum].courses[simulation_semester]

            if courses is not None:
                for course in courses:
                    if not student_controller.register_to_course(student, course):
                        self.denied_courses.append(course)
            else:
                print(f"courses  is null {student.semester_no}")
