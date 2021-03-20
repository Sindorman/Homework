//
//  ViewController.swift
//  HealthApp
//
//  Created by Mykhailo on 3/19/21.
//

import UIKit

class MainViewController: UIViewController {
    
    var circleGestureRecognizer: CircleGestureRecognizer?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        
        //Set label to nothing
        self.StatusLabel.text = ""
        
        //Draw two circles
        self.drawCircle(center: self.view.center, radius: 50)
        self.drawCircle(center: self.view.center, radius: 150)
        
        self.circleGestureRecognizer = CircleGestureRecognizer(target: self, action: #selector(handleDraw))
        
        circleGestureRecognizer?.center = self.view.center
        circleGestureRecognizer?.minRadius = 50
        circleGestureRecognizer?.maxRadius = 150
        self.view.addGestureRecognizer(circleGestureRecognizer!)
        
    }
    
    @objc func handleDraw(_ sender: CircleGestureRecognizer) {
        if sender.state == .ended {
            self.setStatusTo("Success!", UIColor.green)
        }
        else if sender.state == .cancelled {
            self.setStatusTo("Failed", UIColor.red)
        }
        else if sender.state == .began
        {
            self.setStatusTo("", UIColor.black)
        }
    }
    
    func setStatusTo(_ text:String, _ color: UIColor)
    {
        self.StatusLabel.text = text
        self.StatusLabel.textColor = color
    }
    
    @IBOutlet weak var StatusLabel: UILabel!

    func drawCircle(center: CGPoint, radius: Float) {
        let shapeLayer = CAShapeLayer()
        let circlePath = UIBezierPath(arcCenter: center, radius: CGFloat(radius), startAngle: 0, endAngle: (2 * CGFloat.pi), clockwise: true)
        shapeLayer.path = circlePath.cgPath
        shapeLayer.fillColor = UIColor.clear.cgColor
        shapeLayer.strokeColor = UIColor.blue.cgColor
        self.view.layer.addSublayer(shapeLayer)
    }
    @IBAction func ClearTapped(_ sender: UIButton) {
        self.circleGestureRecognizer?.clearBoxes()
        self.setStatusTo("", UIColor.black)
    }
    

}

