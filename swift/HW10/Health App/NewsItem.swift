//
//  Quotation.swift
//  Health App
//
//  Created by Mykhailo on 2/2/21.
//

import Foundation

class NewsItem {
    
    var title: String?
    var source: String?
    var URL: String? = "UrlNotLoaded"
    var imageURL: String? = ""

    internal init(title: String? = "TitleNotLoaded", source: String? = "SourceNotLoaded", URL: String? = "UrlNotLoaded", imageURL: String? = "") {
        self.title = title
        self.source = source
        self.URL = URL
        self.imageURL = imageURL
    }
}
