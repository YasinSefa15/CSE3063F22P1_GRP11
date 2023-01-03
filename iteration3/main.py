from iteration3.Controllers.LabelingController import LabelingController

labelingController = LabelingController(10)
labelingController.execute()

print(labelingController.get_advisors())
