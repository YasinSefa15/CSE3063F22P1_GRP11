import json
from datetime import date
from abc import ABC, abstractmethod

from iteration3.Models.Model import Model


class Person(Model, ABC):
    def __init__(self, name: str, surname: str, ssn: str, gender: str):
        super().__init__()
        self.__name = name
        self.__surname = surname
        self.__ssn = ssn
        self.__gender = gender

    @property
    def name(self):
        return self.__name

    @property
    def surname(self):
        return self.__surname

    @property
    def ssn(self) -> str:
        return self.__ssn

    @ssn.setter
    def ssn(self, ssn: str):
        self.__ssn = ssn

    @property
    def gender(self) -> str:
        return self.__gender

    @gender.setter
    def gender(self, gender: str):
        self.__gender = gender
