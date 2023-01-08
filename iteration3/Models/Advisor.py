from abc import ABC

from iteration3.Models.Person import Person


class Advisor(Person):
    def __init__(self, name, surname, ssn, gender, students, error):
        super().__init__(name, surname, ssn, gender)
        self._students = students
        self._error = error


        def to_json(self):
            return ""

    def course_availability(student, course):
        if not check_semester(student, course):
            student.add_error(error.report_error(1001, [course.name, str(course.semester), str(student.semester_no)]))
            return false
        if not check_credit(student, course):
            student.add_error(error.report_error(1002, [course.name, str(student.transcript.completed_credit)]))
            return false
        if not check_quota(course):
            student.add_error(error.report_error(1003, [course.name]))
            return false
        temp_return = check_pre_requisite(student, course)
        if not temp_return[0]:
            student.add_error(error.report_error(1004, [course.name, temp_return[1]]))
            return false
        temp_return_2 = check_collision(student)
        if not temp_return_2[0]:
            student.add_error(error.report_error(1005, [temp_return_2[1], temp_return_2[2], temp_return_2[3]]))
            return false
        if not check_elective(student, course):
            return false
        if not fte_takeable(student, course):
            # return False
            pass

        return true

    def check_quota(self, course):
        if course.get_registered_students_count() >= course.get_quota():
            return false
        return true

    def check_semester(self, student, course):
        if course.getSemester() <= student.getSemesterNo():
            return true
        return false


    def check_credit(self, student, course):
        if course.getRequiredCredits() > student.getTranscript().getCompletedCredit():
            return true
        return false

    def check_pre_requisite(student, course):
        temp_completed_course = student.transcript.completed_courses
        availability = true
        error_info = [None, None]
        for i in range(len(course.pre_requisite_courses)):
            for j in range(len(temp_completed_course)):
                if course.pre_requisite_courses[i].code != temp_completed_course[j].code:
                    availability = false
                    error_info[1] = course.pre_requisite_courses[i].code
                else:
                    availability = true
                    error_info[1] = ""
                    break
            if not availability:
                break
        error_info[0] = availability
        return error_info

    def check_collision(student):
        courses_of_hash = student.selected_courses.keys()
        courses = list(courses_of_hash)
        error_info = [None, None, None, None]
        error_info[0] = true
        error_info[1] = ""
        error_info[2] = ""
        error_info[3] = ""
        for i in range(len(courses)):
            temp = list(courses[i].weekly_hours)
            for j in range(i + 1, len(courses)):
                temp = [x for x in temp if x in courses[j].weekly_hours]
                if len(temp) != 0:
                    error_info[0] = false
                    error_info[1] = courses[i].name
                    error_info[2] = courses[j].name
                    error_info[3] = str(len(temp))
                    return error_info
        return error_info

    def check_elective(student, course):
        count = 0
        for k, v in student.selected_courses.items():
            if isinstance(k, Elective):
                count += 1
        if count >= 2:
            return false
        return true

    def fte_takeable(student, course):
        if student.semester_no >= 7 and isinstance(course, Elective) and course.type == ElectiveType.FTE:
            return true
        return false

    def get_students(self):
        return self.students

    def set_students(self, students):
        for i in range(len(students)):
            for j in range(len(self.students)):
                if students[i].id != self.students[j].id:
                    self.students.add(students[i])





    def to_json(self):
        return ""


