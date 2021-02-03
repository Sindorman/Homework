//
//  ViewController.swift
//  Health App
//
//  Created by Mykhailo on 1/27/21.
//

import UIKit

class ViewController: UIViewController {
    
    var images = ["WSU.jpg", "WSU_ALT.jpg", "Spark.jpg"]
    var index = 0

    @IBAction func NextTapped(sender: UIButton)
    {
        index += 1
        if (index > images.count - 1)
        {
            index = 0
        }
        imageView.image = UIImage(named: images[index])
        
    }
    @IBOutlet weak var imageView: UIImageView!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view.
        imageView.image = UIImage(named: images[0])
        index = 0
    }


}

