import random
import iteration3.Controllers.Controller as Controller
import iteration3.Models.Student as Student
import iteration3.Models.Course as Course


class StudentController(Controller):
    def register_to_course(self, student, course):
        permission = student.advisor.course_availability(student, course)

        if not permission:
            return False

        # if advisor allows, register to course
        student.add_to_selected_courses(course, self.change_course_status(student, course))
        course.add_registered_student()
        return True

    def change_course_status(self, student, course):
        if random.randint(0, 1) == 1:
            student.transcript.add_to_completed_courses(course)
            student.transcript.set_completed_credit(
                student.transcript.get_completed_credit() + course.credit
            )
            return True
        student.transcript.add_to_failed_courses(course)
        return False
