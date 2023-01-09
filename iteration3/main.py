from iteration3.Controllers.LabelingController import LabelingController
from iteration3.Models.RegistrationError import RegistrationError

labelingController = LabelingController()
labelingController.error = RegistrationError()
labelingController.execute()

