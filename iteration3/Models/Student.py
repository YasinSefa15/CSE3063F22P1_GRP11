import json
import logging

from iteration3.Models.Person import Person
from abc import ABC, abstractmethod


class Student(Person):
    logger = logging.getLogger(__name__)
    file_handler = logging.FileHandler("RegistrationError.log")
    formatter = logging.Formatter("%(asctime)s - %(name)s - %(levelname)s - %(message)s")
    file_handler.setFormatter(formatter)
    logger.addHandler(file_handler)

    def __init__(self, name, surname, ssn, gender, id, isGraduate, registerDate, semesterNo, transcript, advisor):
        super().__init__(name, surname, ssn, gender)
        self.__id = id
        self.__registerDate = registerDate
        self.__isGraduate = isGraduate
        self.__semesterNo = semesterNo
        self.__transcript = transcript
        self.__advisor = advisor
        self.__errors = []
        self.__selectedCourses = []
        """self.custom_log(True, "Student constructor method is called and new student object is generated.")"""
    def custom_log(self, is_error, message):
        if is_error:
            self.logger.info(message)
        else:
            self.logger.warning(message)

    @property
    def id(self):
        return self.__id

    @property
    def register_date(self):
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
        self.__semesterNo = semesterNo

    @transcript.setter
    def set_transcript(self, transcript):
        self.__transcript = transcript

    @selected_courses.setter
    def set_selected_courses(self, selectedCourses):
        self.__selectedCourses = selectedCourses

    def add_error(self, error_message):
        self.errors.append(error_message)

    def to_json(self):
        json_object = {
            "name": self.name,
            "surname": self.surname,
            "ssn": self.ssn,
            "gender": self.gender,
            "id": self.id,
            "isGraduate": self.is_graduate,
            "registerDate": self.register_date,
            "semesterNo": self.semester_no,
            #"Transcript": self.transcript.to_json,
            "errors": self.errors,
            "Advisor": {
                "name": self.advisor.name,
                "surname": self.advisor.surname,
                "ssn": self.advisor.ssn
            }
        }
        return json_object

    def add_to_selected_courses(self, course, status):
        if course not in self.selected_courses:
            self.selected_courses.append([course, status])
            self.custom_log(True,
                            "Checked if the course is completed before and it is not completed. Added to selected courses")
        else:
            self.custom_log(False,
                            "Checked if the course is completed before and it is completed. Course cannot be added")
