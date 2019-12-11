import unittest
import cluster

class KNN_tests(unittest.TestCase):
    def setUp(self):
        self.KNN = cluster.KNN()
    
    def test_basic(self):
        self.KNN.process_and_train("data.csv")
        t_test = self.KNN.scaler.transform([[70, 165, 22]])
        t_pred = self.KNN.classifier.predict(t_test)
        res = self.KNN.iris.loc[self.KNN.iris['Name'] == t_pred[0]]
        self.assertEqual(t_pred[0], "Alexi_Casilla")
    
    def test_second(self):
        self.KNN.process_and_train("data.csv")
        t_test = self.KNN.scaler.transform([[60, 140, 22]])
        t_pred = self.KNN.classifier.predict(t_test)
        res = self.KNN.iris.loc[self.KNN.iris['Name'] == t_pred[0]]
        self.assertEqual(t_pred[0], "Chone_Figgins")
    
    def test_third(self):
        self.KNN.process_and_train("data.csv")
        t_test = self.KNN.scaler.transform([[70, 220, 26]])
        t_pred = self.KNN.classifier.predict(t_test)
        res = self.KNN.iris.loc[self.KNN.iris['Name'] == t_pred[0]]
        self.assertEqual(t_pred[0], "Adrian_Beltre")
    
    def test_fourth(self):
        self.KNN.process_and_train("data.csv")
        t_test = self.KNN.scaler.transform([[75, 180, 23]])
        t_pred = self.KNN.classifier.predict(t_test)
        res = self.KNN.iris.loc[self.KNN.iris['Name'] == t_pred[0]]
        self.assertEqual(t_pred[0], "B.J._Upton")
    
    def test_fifth(self):
        self.KNN.process_and_train("data.csv")
        t_test = self.KNN.scaler.transform([[50, 200, 24]])
        t_pred = self.KNN.classifier.predict(t_test)
        res = self.KNN.iris.loc[self.KNN.iris['Name'] == t_pred[0]]
        self.assertEqual(t_pred[0], "Aaron_Miles")

if __name__ == '__main__':
    unittest.main()