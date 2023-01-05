class Advisor(Person):
    def __init__(self, name, surname, ssn, gender, students, error):
        super().__init__(name, surname, ssn, gender)
        self._students = students
        self._error = error



