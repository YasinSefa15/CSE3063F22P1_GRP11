import json

from iteration3.Models.Person import Person
from iteration3.Models.RegistrationError import customLog


class Student(Person):
    def __init__(self, name, surname, ssn, gender, id, isGraduate, registerDate, semesterNo, transcript, advisor):
        super().__init__(name, surname, ssn, gender)
        self._id = id
        self._registerDate = registerDate
        self._isGraduate = isGraduate
        self._semesterNo = semesterNo
        self._transcript = transcript
        self._advisor = advisor
        self._errors = []

        customLog(True, "Student constructor method is called and new student object is generated.")

    def getId(self):
        return self._id

    def getRegisterDate(self):
        return self._registerDate

    def get_is_graduate(self):
        return self._isGraduate

    def get_semester_no(self):
        return self._semesterNo

    def get_transcript(self):
        return self._transcript

    def get_errors(self):
        return self._errors

    def get_advisor(self):
        return self._advisor

    def get_selected_courses(self):
        return self._selectedCourses

    def set_semester_no(self, semesterNo):
        self._semesterNo = semesterNo

    def set_transcript(self, transcript):
        self._transcript = transcript

    def set_selected_courses(self, selectedCourses):
        self._selectedCourses = selectedCourses

    def add_error(self, error_message):
         self.errors.append(error_message)

    def get_name(self):
        return super().get_name()

    def get_surname(self):
        return super().get_surname()

    def get_ssn(self):
        return super().get_ssn()

    def get_gender(self):
        return super().get_gender()

    def to_json(self):
        json_object = {
            "name": self.get_name(),
            "surname": self.get_surname(),
            "ssn": self.get_ssn(),
            "gender": self.get_gender(),
            "id": self.get_id(),
            "isGraduate": self.get_is_graduate(),
            "registerDate": self.get_register_date(),
            "semesterNo": self.get_semester_no(),
            "Transcript": self.get_transcript().to_json(),
            "errors": self.get_errors(),
            "Advisor": {
                "name": self.get_advisor().get_name(),
                "surname": self.get_advisor().get_surname(),
                "ssn": self.get_advisor().get_ssn()
            }
        }
        return json.dumps(json_object)

    def add_to_selected_courses(self, course, status):
        if course not in self.selectedCourses:
            self.selectedCourses[course] = status
            self.custom_log(True,
                            "Checked if the course is completed before and it is not completed. Added to selected courses")
        else:
            self.custom_log(False,
                            "Checked if the course is completed before and it is completed. Course cannot be added")

