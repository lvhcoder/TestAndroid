package com.lvh.testandroid.model

import androidx.room.Entity

data class UserEntity(
        var name: NameEntity?,
        var location: LocationEntity?,
        var email: String?,
        var phone: String?,
        var id: Id?,
        var cell: String?,
        var login: Login?,
        var dob: Dob?,
        var picture: Picture?)

data class NameEntity(var title: String?, var first: String?, var last: String?)

data class LocationEntity(var city: String?, var street: Street?, var coordinates: Coordinates?, var state: String?, var postcode: Int?, var country: String?)
data class Street(var number: Int?, var name: String?)
data class Coordinates(var latitude: String?, var longitude: String?)

data class Picture(var large: String?, var medium: String?, var thumbnail: String?)

data class Login(var username: String?, var password: String?, var salt: String?, var md5: String?, var sha1: String?, var sha256: String?, var uuid: String?)
data class Dob(var date: String?, var age: Int?)
data class Id(var id: String?, var name: String?)


