//
//  Person.swift
//  simpleconsumer
//
//  Created by David Truxall on 11/10/15.
//  Copyright © 2015 Hewlett-Packard Company. All rights reserved.
//

import Foundation

class Person {
    
    var firstName:String
    var lastName:String
    var gender:String
    
    init(first:String,last:String,gender:String) {
        
        self.firstName = first
        self.lastName = last
        self.gender = gender
    }
    
    
}