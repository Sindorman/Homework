//
//  CircleGestureRecognizer.swift
//  HealthApp
//
//  Created by Mykhailo on 3/19/21.
//

import UIKit

class CircleGestureRecognizer: UIGestureRecognizer {
    var minRadius: Float = 0
    var maxRadius: Float = 0
    var center: CGPoint!
    var boxViews: [UIView] = []
    
    override func touchesBegan(_ touches: Set<UITouch>, with event: UIEvent) {
        let touch = touches.first
        if let point = touch?.location(in: self.view) {
            let distance = self.distance(self.center, point)
            if ((distance > self.minRadius) && (distance < self.maxRadius))
            {
                self.drawBox(point, UIColor.red)
                state = .began
            }
            else
            {
                state = .failed
            }
        }
    }
    
    override func touchesMoved(_ touches: Set<UITouch>, with event: UIEvent)
    {
        let touch = touches.first
        if let point = touch?.location(in: self.view) {
            let distance = self.distance(self.center, point)
            if ((distance > self.minRadius) && (distance < self.maxRadius))
            {
                self.drawBox(point, UIColor.red)
                state = .changed
            }
            else
            {
                state = .failed
            }
        }
    }

    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent) {
        let touch = touches.first
        if let point = touch?.location(in: self.view) {
            let distance = self.distance(self.center, point)
            if ((distance > self.minRadius) && (distance < self.maxRadius))
            {
                var first = false
                var second = false
                var third = false
                var fourth = false
                
                for givenPoint in boxViews {
                    // Check first quadrant
                    if((givenPoint.center.x > self.center.x) && (givenPoint.center.y > self.center.y))
                    {
                        first = true
                    }
                    // Check second quadrant
                    else if((givenPoint.center.x < self.center.x) && (givenPoint.center.y > self.center.y)){
                        second = true
                    }
                    // Check third quadrant
                    else if((givenPoint.center.x < self.center.x) && (givenPoint.center.y < self.center.y)){
                        third = true
                    }
                    // Check fourth quadrant
                    else if((givenPoint.center.x > self.center.x) && (givenPoint.center.y < self.center.y)){
                        fourth = true
                    }
                }
                
                state = ((first && second && third && fourth) ? .ended : .failed)
            }
            else
            {
                state = .failed
            }
        }
    }
    
    func distance(_ p1: CGPoint, _ p2: CGPoint) -> Float {
        let xdist = abs(p1.x - p2.x)
        let ydist = abs(p1.y - p2.y)
        let dist = sqrt((xdist * xdist) + (ydist * ydist))
        return Float(dist)
    }
    
    func betweenPoints(_ point: CGPoint, _ minPoint: CGPoint, _ maxPoint: CGPoint ) -> Bool
    {
        let x = (minPoint.x < point.x) && (point.x < maxPoint.x)
        let y = (minPoint.y < point.y) && (point.y < maxPoint.y)
        return x && y
        
    }
    
    func drawBox(_ point: CGPoint, _ color: UIColor) {
        let boxRect = CGRect(x: point.x, y: point.y, width: 5.0, height: 5.0)
        let boxView = UIView(frame: boxRect)
        boxView.backgroundColor = color
        self.view?.addSubview(boxView)
        boxViews.append(boxView)
    }
    
    func clearBoxes() {
        for boxView in boxViews {
            boxView.removeFromSuperview()
        }
        boxViews.removeAll()
    }
    
    override func touchesCancelled(_ touches: Set<UITouch>, with event: UIEvent) {
        state = .cancelled
    }
}
