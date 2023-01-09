import logging
from abc import ABC

from iteration3.Models import Elective, ElectiveType
from iteration3.Models.Course import Course
from iteration3.Models.Person import Person
from iteration3.Models.Student import Student


class Advisor(Person):
    logger = logging.getLogger(__name__)
    file_handler = logging.FileHandler("RegistrationError.log")
    formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
    file_handler.setFormatter(formatter)
    logger.addHandler(file_handler)

    def __init__(self, name, surname, ssn, gender, students, error):
        super().__init__(name, surname, ssn, gender)
        self.__students = students
        self.__error = error

        def to_json(self):
            return ""

    def custom_log(self, is_error, message):
        if is_error:
            self.logger.info(message)
        else:
            self.logger.warning(message)

    def course_availability(self, student: Student, course: Course):
        if not self.check_semester(student, course):
            student.add_error(
                self.__error.report_error(1001, [course.get_name, str(course.semester), str(student.semester_no)]))
            self.custom_log(False,
                            "Checked semester of " + student.id +" and courses that requested by students and they are declined ")
            return False
        if not self.check_credit(student, course):
            student.add_error(self.__error.report_error(1002, [course.get_name, str(student.transcript.completed_credit)]))
            self.custom_log(False, "Compared credit of students" + student.id + " and required credit of course and it is not enough.")
            return False
        if not self.check_quota(course):
            student.add_error(self.__error.report_error(1003, [course.get_name]))
            self.custom_log(False, "Check quota of course "+ course.get_code  +" and there is not enough space for student.")
            return False
        temp_return = self.check_pre_requisite(student, course)
        if not temp_return[0]:
            student.add_error(self.__error.report_error(1004, [course.get_name, temp_return[1]]))
            return False
        temp_return_2 = self.check_collision(student)
        if not temp_return_2:
            student.add_error(self.__error.report_error(1005, [temp_return_2, temp_return_2, temp_return_2]))
            return False
        if not self.check_elective(student, course):
            return False
        if not self.fte_takeable(student, course):
            # return False
            pass
        self.custom_log(True, "courseAvailability method is called and advisor check the course availability.")

        return True

    def check_quota(self, course: Course):
        if course.get_registered_students_count() >= course.get_quota():
            return False
        return True

    def check_semester(self, student: Student, course: Course):
        if course.semester <= student.semester_no:
            return True
        return False

    def check_credit(self, student: Student, course: Course):
        if course.get_required_credits() <= student.transcript.completed_credit:
            return True
        return False

    def check_pre_requisite(self, student: Student, course: Course):
        temp_completed_course = student.transcript.get_completed_courses()
        availability = True
        error_info = [None, None]
        for i in range(len(course.get_prerequisite_courses())):
            for j in range(len(temp_completed_course)):
                if course.get_prerequisite_courses()[i].get_code != temp_completed_course[j].get_code:
                    availability = False
                    error_info[1] = course.get_prerequisite_courses()[i].get_code
                else:
                    availability = True
                    error_info[1] = ""
                    break
            if not availability:
                break
        error_info[0] = availability
        return error_info

    def check_collision(self, student: Student):
        return True
        courses_of_hash = student.selected_courses.keys()
        courses = list(courses_of_hash)
        error_info = [None, None, None, None]
        error_info[0] = True
        error_info[1] = ""
        error_info[2] = ""
        error_info[3] = ""
        for i in range(len(courses)):
            temp = list(courses[i].weekly_hours)
            for j in range(i + 1, len(courses)):
                temp = [x for x in temp if x in courses[j].weekly_hours]
                if len(temp) != 0:
                    error_info[0] = False
                    error_info[1] = courses[i].name
                    error_info[2] = courses[j].name
                    error_info[3] = str(len(temp))
                    return error_info
        return error_info

    # check elective calışmıyor
    def check_elective(self, student: Student, course: Course):
        count = 0
        try:
            for k, v in student.selected_courses:
                if isinstance(k, (Elective)):
                    print("-------------")
                    count += 1
            if count >= 2:
                return False
            return True
        except Exception as e:
            return True
        return True


    # fte takeable calışmıyor

    def fte_takeable(self, student: Student, course: Course):
        try:
            if student.semester_no >= 7 and course.type == ElectiveType.FTE:

                return False
        except Exception as e:
            return True
        return True

    @property
    def students(self):
        return self.__students

    @students.setter
    def students(self, students):
        self.__students = students

    @property
    def gender(self):
        return self.gender

    @students.setter
    def students(self, students):
        self.__students = students

    def student_json(self):
        result = {}
        for student in self.students:
            result[student.ssn] = student.to_json()
        return result

    def to_json(self):
        return {
            "name": self.name,
            "surname": self.surname,
            "ssn": self.ssn,
            "gender": super().gender,
            "students": self.student_json()
        }
