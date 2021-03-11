//
//  Quotation.swift
//  Health App
//
//  Created by Mykhailo on 2/2/21.
//

import Foundation

class Quotation {
    
    var quote: String
    var author: String?
    var id: String
    
    internal init(quote: String, author: String? = nil) {
        self.quote = quote
        self.author = author
        self.id = UUID().uuidString
    }
}
