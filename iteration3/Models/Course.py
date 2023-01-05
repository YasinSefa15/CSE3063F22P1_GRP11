import json
from abc import ABC, abstractmethod

from iteration3.Models.Model import Model


class Course(Model, metaclass=ABC):
    def __init__(self):
        self.name = ""
        self.code = ""
        self.credit = 0
        self.requiredCredits = 0
        self.quota = 0
        self.registeredStudentsCount = 0
        self.semester = 0
        self.preRequisiteCourses = []
        self.weeklyHours = []

    def getName(self):
        return self.name

    def getCode(self):
        return self.code

    def getCredit(self):
        return self.credit

    def getRequiredCredits(self):
        return self.requiredCredits

    def getQuota(self):
        return self.quota

    def getSemester(self):
        return self.semester

    def getRegisteredStudentsCount(self):
        return self.registeredStudentsCount

    def getPreRequisiteCourses(self):
        return self.preRequisiteCourses

    def getWeeklyHours(self):
        return self.weeklyHours

    def setName(self, name):
        self.name = name

    def setCode(self, code):
        self.code = code

    def setCredit(self, credit):
        self.credit = credit

    def setRequiredCredits(self, requiredCredits):
        self.requiredCredits = requiredCredits

    def setQuota(self, quota):
        self.quota = quota

    def setSemester(self, semester):
        self.semester = semester

    def setRegisteredStudentsCount(self, registeredStudentsCount):
        self.registeredStudentsCount = registeredStudentsCount

    def setPreRequisiteCourses(self, preRequisiteCourses):
        self.preRequisiteCourses = preRequisiteCourses

    def setWeeklyHours(self, weeklyHours):
        self.weeklyHours = weeklyHours

    def add_registered_student(self):
        self.registered_students_count += 1

    import json

    def to_json(self):
        json_object = {}
        json_object["name"] = self.name
        json_object["code"] = self.code
        json_object["credit"] = self.credit
        json_object["required_credits"] = self.required_credits
        json_object["quota"] = self.quota
        json_object["registeredStudentsCount"] = self.registeredStudentsCount
        json_object["semester"] = self.semester
        json_object["preRequisiteCourses"] = self.to_json_array(self.preRequisiteCourses)
        json_object["weeklyHours"] = self.weeklyHours
        return json.dumps(json_object)

    def to_json_array(self, preRequisiteCourses):
        json_array = []

        for course in preRequisiteCourses:
            json_array.append(course.to_json())

        return json.dumps(json_array)
