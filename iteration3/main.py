from iteration3.Controllers.LabelingController import LabelingController

labelingController = LabelingController()
labelingController.execute()

#person class ın da setter olduğu için = deyip değer atıyabiliyorum
#person class ın da @property deyip getter yazıyorum get_name demedim name dedim adına
#aynı şekilde labeling controller içerisindeki advisord da private olmasına rağmen @property kullanıp fonksiyon aracılığla
#eğrebiliyorum
labelingController.advisors[0].ssn = "12345678910"
print(labelingController.advisors[0].ssn)
print(labelingController.advisors[0].surname)
print(labelingController.advisors[0].name)
