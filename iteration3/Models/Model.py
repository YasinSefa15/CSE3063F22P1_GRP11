import datetime

import json
from abc import ABC, abstractmethod

class Model(ABC):
    def __init__(self):
        self.__created_at = datetime.datetime.now()
        self.__updated_at = datetime.datetime.now()

    @abstractmethod
    def to_json(self):
        raise NotImplementedError






