class Advisor(Person):
    def __init__(self, name, surname, ssn, gender, students, error):
        super().__init__(name, surname, ssn, gender)
        self._students = students
        self._error = error

    def check_quota(self, course):
        if course.get_registered_students_count() >= course.get_quota():
            return False
        return True

    def checkSemester(self, student, course):
        if course.getSemester() <= student.getSemesterNo():
            return true
        return false


    def checkCredit(self, student, course):
        if course.getRequiredCredits() > student.getTranscript().getCompletedCredit():
            return true
        return false

    def setCompletedCourses(self, completedCourses):
        for i in range(len(completedCourses)):
            for j in range(len(self.completedCourses)):
                if completedCourses[i].getCode() != self.completedCourses[j].getCode():
                    self.completedCourses.append(completedCourses[i])




