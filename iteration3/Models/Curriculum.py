class Curriculum:
    def __init__(self):
        self.courses = {}
        self.file_path = "/iteration2/Logs/Curriculum.log"
        self.logger = logging.getLogger(__name__)
        self.file_handler = None

        try:
            self.file_handler = logging.FileHandler(os.getcwd() + self.file_path, mode='a')
            self.logger.addHandler(self.file_handler)
            self.logger.setLevel(logging.INFO)
        except IOError:
            print("Error setting up logger.")

    def custom_log(self, is_info, message):
        formatter = logging.Formatter("%(asctime)s - %(levelname)s - %(message)s")
        self.file_handler.setFormatter(formatter)

        if is_info:
            self.logger.info(message)
        else:
            self.logger.warning(message)

    def add_course_to_semester(self, course):
        if course.get_semester() not in self.courses:
            self.courses[course.get_semester()] = []
            self.courses[course.get_semester()].append(course)
            self.custom_log(True, "Created new semester array list for courses and added courses to semester.")
        else:
            self.courses[course.get_semester()].append(course)
            self.custom_log(True, "Added courses to semester.")

        return True