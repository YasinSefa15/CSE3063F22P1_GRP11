import logging
class Curriculum:
    def __init__(self, name, courses):
        self.__courses = courses
        self.__name = name

    logging.basicConfig(filename='Curriculum.log', filemode='w', format='%(name)s - %(levelname)s - %(message)s')
    @property
    def courses(self):
        return self.__courses

    @property
    def name(self):
        return self.__name

    def course_by_semester(self, semester_no):
        courses = []
        for course in self.__courses:
            if course.semester == semester_no:
                courses.append(course)
        return courses

    def custom_log(self, is_error, message):
        if is_error:
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
