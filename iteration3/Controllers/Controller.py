import json
import os

from iteration3.Models.RegistrationError import RegistrationError


class Controller:
    def __init__(self):
        self.__error = None

    def read_json_files(self, path):
        json_objects = []
        requested_path = os.getcwd() + '/Data/Input/' + path
        print("--C--->Reading json files on path: " + requested_path)
        file_names = os.listdir(requested_path)

        for file in file_names:
            try:
                with open(requested_path + '/' + file) as f:
                    obj = json.load(f)
                    jo = json.loads(json.dumps(obj))
                    json_objects.append(jo)
            except Exception as e:
                print(e)

        return json_objects

    def export_json_file(self, object):
        path = ""

        if object.__class__.__name__ == "Student":
            file_name = object.id
            json_object = object.to_json()
            path = "Output/Students"
            content = json_object
        elif object.__class__.__name__ == "Advisor":
            file_name = object.name + object.surname
            file_name = file_name.replace(" ", "")
            json_object = object.to_json()
            path = "Output/Advisors"
            content = json_object
        elif object.__class__.__name__ == "RegistrationError":
            file_name = "RegistrationErrors"
            json_object = object.to_json()
            content = json_object
        else:
            return False

        full_file_name = os.getcwd() + '/Data/' + path + '/' + str(file_name) + '.json'

        try:
            directory = os.path.dirname(full_file_name)

            if not os.path.exists(directory):
                os.makedirs(directory)

            with open(full_file_name, 'w') as f:
                json.dump(content, f)
                # f.write(content)
        except OSError as e:
            print("An error occurred while exporting .json file.")
            print(e)
        return False

    @property
    def error(self):
        return self.__error

    @error.setter
    def error(self, error):
        self.__error = error
