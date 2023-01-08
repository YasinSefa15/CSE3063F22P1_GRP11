
class RegistrationError:
    error_type = {}
    error_list = []
    all_error_messages = []
    no_of_last_error_type = 1006

    def __init__(self):
        self.error_type = {
            1001: "semester inconsistency",
            1002: "lack of credits",
            1003: "lack of quota",
            1004: "did not meet the prerequisite",
            1005: "course collision",
            1006: "elective error",
    }

    def report_error(self, error_code, data):
        message = ""
        if error_code == 1001:
            message = f"The advisor didn't approve {data[0]} because course's semester is {data[1]} while student's is {data[2]}"
            self.store_error_info(error_code, data[0])
        elif error_code == 1002:
            message = f"The advisor didn't approve {data[0]} because student completed credits {data[1]}"
            self.store_error_info(error_code, data[0])
        elif error_code == 1003:
            message = f"The advisor didn't approve {data[0]} because course's quota is full"
            self.store_error_info(error_code, data[0])
        elif error_code == 1004:
            message = f"The system didn't allow {data[0]} because student failed prereq: {data[1]}"
            self.store_error_info(error_code, data[0])
        elif error_code == 1005:
            message = "Advisor didn't approve {data[0]} because of {data[2]} hours collision with {data[1]} in schedule."
            self.store_error_info(error_code, data[0])
        elif error_code == 1006:
            message = f"The advisor didn't approve {data[0]} because student already took {data[1]} and in FALL semester only 2 TE can be taken"
        else:
            message="Unknown Error!!!"


    def store_error_info(self, error_code, data):
        temp = f"{error_code},{data}"   """ String temp= errorCode.toString()+data; """
        self.error_list.append(temp)

    def write_all_errors(self, error_list):
        i=0
        for i in range(self.no_of_last_error_type - 1000):
            course_list = []
            for error in error_list:
                error_code, course = error.split(",")
                if error_code == str(i + 1001):
                    course_list.append(course)
            s = set(course_list)
            temp = list(s)
            self.write_number_of_errors(temp, course_list, i + 1001)
            course_list.clear()


    def write_number_of_errors(self, unique_courses, course_list, error_code):
        count = 0
        current_course = ""
        for course in unique_courses:
            current_course = course
            for c in course_list:
                if course == c:
                    count=count+1

            if count != 0:
                message = f"{count} students could not register {course} because of {self.error_type[error_code]}!"
                self.all_error_messages.append(message)
            count = 0

    def get_all_error_messages(self):
        return self.all_error_messages

    def to_json(self):
        self.write_all_errors(self.error_list)
        return {
            "totalErrors": self.all_error_messages,
        }