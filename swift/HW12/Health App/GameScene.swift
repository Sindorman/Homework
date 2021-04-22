//
//  GameScene.swift
//  Health App
//
//  Created by Mykhailo on 4/21/21.
//

import SpriteKit
import GameplayKit

class GameScene: SKScene, SKPhysicsContactDelegate {
    
    private var Score : SKLabelNode?
    private var score : Int = 0
    var spawnTimer : Timer?
     
    
    required init(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)!
        Score = self.childNode(withName: "Score") as? SKLabelNode
        spawnTimer = Timer.scheduledTimer(timeInterval: 3.0, target: self, selector: #selector(spawnVirus), userInfo: nil, repeats: true)
    }
    
    override func didMove(to view: SKView) {
        self.physicsWorld.contactDelegate = self
        self.scaleMode = .aspectFit
        self.physicsBody = SKPhysicsBody(edgeLoopFrom: self.frame)
    }
    
    override func touchesEnded(_ touches: Set<UITouch>, with event: UIEvent?) {
        if let touch = touches.first {
            let position = touch.location(in: self)
            self.spawnRocket(Int(position.x))
        }
    }
    
    
    override func update(_ currentTime: TimeInterval) {
    }
    
    @objc func spawnVirus()
    {
        let virusNode: SKSpriteNode = SKSpriteNode(imageNamed: "virus.png")

        // Add virus ball programmatically
        virusNode.name = "Virus"
        virusNode.physicsBody = SKPhysicsBody(circleOfRadius:
        virusNode.frame.size.width / 2.0)
        virusNode.physicsBody?.affectedByGravity = false
        virusNode.physicsBody?.restitution = 1.0
        virusNode.physicsBody?.linearDamping = 0.0
        
        let dx = Int.random(in: -200..<200)
        let dy = Int.random(in: -200..<200)
        
        let x = Int.random(in: -324..<324)
        let y = Int.random(in: -600..<600)
        
        virusNode.position = CGPoint(x: x, y: y)
        
        virusNode.physicsBody?.velocity = (CGVector(dx: dx, dy: dy))
        virusNode.physicsBody?.applyImpulse(CGVector(dx: dx, dy: dy))
        virusNode.physicsBody?.categoryBitMask = 0b0001 // Virus
        virusNode.physicsBody?.collisionBitMask = 0b0101 // Wall & Virus
        virusNode.physicsBody?.contactTestBitMask = 0b0010
        self.addChild(virusNode)
    }
    
    func spawnRocket(_ x: Int)
    {
        let rocketNode: SKSpriteNode = SKSpriteNode(imageNamed: "rocket.png")

        // Add rocket ball programmatically
        rocketNode.name = "Rocket"
        rocketNode.physicsBody = SKPhysicsBody(circleOfRadius:
        rocketNode.frame.size.width / 2.0)
        rocketNode.physicsBody?.affectedByGravity = false
        rocketNode.physicsBody?.linearDamping = 0.0
        
        rocketNode.position = CGPoint(x: x, y: -(Int(self.size.height) / 2))
        
        rocketNode.physicsBody?.velocity = (CGVector(dx: 0, dy: 200))
        rocketNode.physicsBody?.categoryBitMask = 0b0010 // Rocket
        rocketNode.physicsBody?.collisionBitMask = 0b0000 // nothing
        rocketNode.physicsBody?.contactTestBitMask = 0b0001
        self.addChild(rocketNode)
    }
    
    func didBegin(_ contact: SKPhysicsContact) {
        guard let nodeA = contact.bodyA.node else {return}
        guard let nodeB = contact.bodyB.node else {return}
        if (nodeA.name == "Virus" && nodeB.name == "Rocket" ||
                nodeA.name == "Rocket" && nodeB.name == "Virus")
        {
            // remove both and update score
            nodeA.removeFromParent()
            nodeB.removeFromParent()
            
            self.score += 1
            self.Score?.text = "Score: " + String(self.score)
        }
    }
}
