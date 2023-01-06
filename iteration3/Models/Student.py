import json

from iteration3.Models.Person import Person
from abc import ABC, abstractmethod


class Student(Person):
    def __init__(self, name, surname, ssn, gender, id, isGraduate, registerDate, semesterNo, transcript, advisor):
        super().__init__(name, surname, ssn, gender)
        self.__id = id
        self.__registerDate = registerDate
        self.__isGraduate = isGraduate
        self.__semesterNo = semesterNo
        self.__transcript = transcript
        self.__advisor = advisor
        self.__errors = []
        """self.custom_log(True, "Student constructor method is called and new student object is generated.")"""

    def custom_log(self, is_error, message):
        if is_error:
            self.logger.info(message)
        else:
            self.logger.warning(message)

    @property
    def Id(self):
        return self._id

    @property
    def registerDate(self):
        return self.__registerDate

    @property
    def is_graduate(self):
        return self.__isGraduate

    @property
    def semester_no(self):
        return self.__semesterNo

    @property
    def transcript(self):
        return self.__transcript

    @property
    def errors(self):
        return self.__errors

    @property
    def advisor(self):
        return self.__advisor

    @property
    def selected_courses(self):
        return self.__selectedCourses

    @semester_no.setter
    def set_semester_no(self, semesterNo):
        self._semesterNo = semesterNo

    @transcript.setter
    def set_transcript(self, transcript):
        self._transcript = transcript

    @selected_courses.setter
    def set_selected_courses(self, selectedCourses):
        self._selectedCourses = selectedCourses

    def add_error(self, error_message):
         self.errors.append(error_message)

    @property
    def name(self):
        return super.__name

    @property
    def surname(self):
        return super().get_surname()

    @property
    def ssn(self):
        return super().get_ssn()

    @property
    def gender(self):
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

