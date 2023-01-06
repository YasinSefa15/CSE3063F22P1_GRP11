from abc import ABC

from iteration3.Models.Person import Person


class Advisor(Person, ABC):
    def __init__(self, name, surname, ssn, gender, students, error):
        super().__init__(name, surname, ssn, gender)
        self._students = students
        self._error = error

    def to_json(self):
        return ""

