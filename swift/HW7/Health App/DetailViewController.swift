//
//  SecondViewController.swift
//  Health App
//
//  Created by Mykhailo on 2/16/21.
//

import UIKit

class DetailViewController: UIViewController, UITextFieldDelegate {
    var quote: Quotation?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        if (self.quote != nil)
        {
            let author = self.quote!.author == nil ? "anonymous" : self.quote?.author!
            quotationText?.text = "\"" + self.quote!.quote + "\" - " + author!
        }
    }
    
    @IBOutlet weak var quotationText: UILabel!

    @IBAction func scheduleNotificationTapped(_ sender: UIButton) {
        let center = UNUserNotificationCenter.current()
        center.getNotificationSettings(completionHandler: { (settings) in
        if settings.alertSetting == .enabled {
                self.scheduleNotification()
            } else {
                return
            }
        })
    }
    
    func scheduleNotification() {
        let content = UNMutableNotificationContent()
        content.title = "Quotation of the Day!"
        content.body = self.quote!.quote
        content.userInfo["id"] = self.quote!.id

        // Configure trigger for 5 seconds from now
        let trigger = UNTimeIntervalNotificationTrigger(timeInterval: 5.0,
        repeats: false)
        // Create request
        let request = UNNotificationRequest(identifier: self.quote!.id,
        content: content, trigger: trigger)
        // Schedule request
        let center = UNUserNotificationCenter.current()
            center.add(request, withCompletionHandler: { (error) in
            if let err = error {
                print(err.localizedDescription)
            }
        })
    }
}
