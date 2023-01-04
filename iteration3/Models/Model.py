from datetime import date
import json
from abc import ABC, abstractmethod

class Model(ABC):
    def __init__(self):
        self.__created_at = date.now()
        self.__updated_at = date.now()

    @abstractmethod
    def toJson(self):
        raise NotImplementedError






